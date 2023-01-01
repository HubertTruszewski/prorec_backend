package pl.edu.pw.elka.pis05.prorec.assessment.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import pl.edu.pw.elka.pis05.prorec.assessment.dto.AssessmentDTO;
import pl.edu.pw.elka.pis05.prorec.assessment.dto.NewAssessmentDTO;
import pl.edu.pw.elka.pis05.prorec.assessment.model.Assessment;
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
        Assessment assessment = new Assessment(newAssessmentDTO.email(), token, newAssessmentDTO.expiryDate(),
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
}
