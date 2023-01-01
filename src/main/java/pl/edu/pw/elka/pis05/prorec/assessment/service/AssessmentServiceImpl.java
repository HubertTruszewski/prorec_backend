package pl.edu.pw.elka.pis05.prorec.assessment.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.edu.pw.elka.pis05.prorec.assessment.dto.AssessmentDTO;
import pl.edu.pw.elka.pis05.prorec.assessment.dto.NewAssessmentDTO;
import pl.edu.pw.elka.pis05.prorec.assessment.model.Assessment;
import pl.edu.pw.elka.pis05.prorec.assessment.model.AssessmentStatus;
import pl.edu.pw.elka.pis05.prorec.assessment.repository.AssessmentRepository;
import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;
import pl.edu.pw.elka.pis05.prorec.challenge.repository.ChallengeRepository;
import pl.edu.pw.elka.pis05.prorec.security.User;
import pl.edu.pw.elka.pis05.prorec.security.UserRepository;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;

    public AssessmentServiceImpl(final AssessmentRepository assessmentRepository,
            final ChallengeRepository challengeRepository, final UserRepository userRepository) {
        this.assessmentRepository = assessmentRepository;
        this.challengeRepository = challengeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AssessmentDTO addNewAssessment(NewAssessmentDTO newAssessmentDTO) {
        // TODO user
        final User author = userRepository.getReferenceById(newAssessmentDTO.authorId());
        final String token = UUID.randomUUID().toString();
        final List<Challenge> challengesList = newAssessmentDTO.challengesIds()
                .stream()
                .map(challengeRepository::getReferenceById)
                .toList();
        Assessment assessment = new Assessment(newAssessmentDTO.email(),
                token,
                newAssessmentDTO.expiryDate(),
                newAssessmentDTO.solvingTime(),
                author,
                challengesList);
        assessmentRepository.save(assessment);
        return AssessmentDTO.of(assessment);
    }

    @Override
    public List<AssessmentDTO> getAllAssessments() {
        return assessmentRepository.findAll().stream().map(AssessmentDTO::of).toList();
    }

    @Override
    public AssessmentDTO getAssessment(final long assessmentId) {
        return AssessmentDTO.of(Objects.requireNonNull(assessmentRepository.findById(assessmentId).orElse(null)));
    }

    @Override
    @Transactional
    public ResponseEntity<Void> cancelAssessment(final long assessmentId) {
        final Optional<Assessment> assessment = assessmentRepository.findById(assessmentId);
        if (assessment.isPresent() && assessment.get().getStatus() == AssessmentStatus.AWAITING) {
            assessment.get().setStatus(AssessmentStatus.CANCELLED);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
