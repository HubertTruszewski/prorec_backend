package pl.edu.pw.elka.pis05.prorec.assessment.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import pl.edu.pw.elka.pis05.prorec.assessment.dto.AssessmentDTO;
import pl.edu.pw.elka.pis05.prorec.assessment.dto.NewAssessmentDTO;
import pl.edu.pw.elka.pis05.prorec.assessment.model.Assessment;
import pl.edu.pw.elka.pis05.prorec.assessment.repository.AssessmentRepository;
import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;
import pl.edu.pw.elka.pis05.prorec.challenge.repository.ChallengeRepository;
import pl.edu.pw.elka.pis05.prorec.security.model.User;
import pl.edu.pw.elka.pis05.prorec.security.repository.UserRepository;

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
        final long authorId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        final User author = userRepository.getReferenceById(authorId);
        final List<Challenge> challengesList = newAssessmentDTO.challengesIds()
                .stream()
                .map(challengeRepository::getReferenceById)
                .toList();
        Assessment assessment = new Assessment(newAssessmentDTO.email(),
                newAssessmentDTO.expiryDate(),
                author,
                challengesList);
        assessmentRepository.save(assessment);
        return AssessmentDTO.of(assessment);
    }
}
