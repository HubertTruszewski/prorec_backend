package pl.edu.pw.elka.pis05.prorec.assessment.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pl.edu.pw.elka.pis05.prorec.assessment.dto.AssessmentDTO;
import pl.edu.pw.elka.pis05.prorec.assessment.dto.NewAssessmentDTO;
import pl.edu.pw.elka.pis05.prorec.assessment.model.Assessment;
import pl.edu.pw.elka.pis05.prorec.assessment.model.AssessmentStatus;
import pl.edu.pw.elka.pis05.prorec.assessment.repository.AssessmentRepository;
import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;
import pl.edu.pw.elka.pis05.prorec.challenge.repository.ChallengeRepository;
import pl.edu.pw.elka.pis05.prorec.common.MessageResponse;
import pl.edu.pw.elka.pis05.prorec.security.model.User;
import pl.edu.pw.elka.pis05.prorec.security.repository.UserRepository;

@Service
@Slf4j
public class AssessmentServiceImpl implements AssessmentService {

    private final AssessmentRepository assessmentRepository;
    private final ChallengeRepository challengeRepository;
    private final UserRepository userRepository;
    private final ThreadPoolTaskScheduler threadPoolTaskScheduler;

    public AssessmentServiceImpl(final AssessmentRepository assessmentRepository,
            final ChallengeRepository challengeRepository, final UserRepository userRepository,
            final ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.assessmentRepository = assessmentRepository;
        this.challengeRepository = challengeRepository;
        this.userRepository = userRepository;
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    @Override
    public AssessmentDTO addNewAssessment(NewAssessmentDTO newAssessmentDTO) {
        final long authorId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        final User author = userRepository.getReferenceById(authorId);
        final String token = UUID.randomUUID().toString();
        final List<Challenge> challengesList = newAssessmentDTO.challengesIds()
                .stream()
                .map(challengeRepository::getReferenceById)
                .toList();
        final Assessment assessment = new Assessment(newAssessmentDTO.email(),
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
    public ResponseEntity<AssessmentDTO> startAssessment(final long assessmentId) {
        final Optional<Assessment> assessment = assessmentRepository.findById(assessmentId);
        if (assessment.isEmpty() || assessment.get().getStatus() != AssessmentStatus.AWAITING) {
            return ResponseEntity.badRequest().build();
        }
        final ZonedDateTime deadline = ZonedDateTime.now().plusMinutes(assessment.get().getSolvingTime());
        assessment.get().setDeadline(deadline);
        assessment.get().setStatus(AssessmentStatus.IN_PROGRESS);
        threadPoolTaskScheduler.schedule(() -> finishAssessment(assessmentId), deadline.toInstant());
        return ResponseEntity.ok(AssessmentDTO.of(assessment.get()));
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

    @Override
    @Transactional
    public ResponseEntity<Void> finishAssessment(final long assessmentId) {
        final Optional<Assessment> assessment = assessmentRepository.findById(assessmentId);
        if (assessment.isPresent() && assessment.get().getStatus() == AssessmentStatus.IN_PROGRESS) {
            assessment.get().setStatus(AssessmentStatus.DONE);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<MessageResponse> getAssessmentIdByToken(final String token) {
        final Optional<Assessment> assessment = assessmentRepository.getAssessmentByToken(token);
        if (assessment.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Not found any assessment with provided code"));
        }
        if (ZonedDateTime.now().isAfter(assessment.get().getExpiryDate())) {
            return ResponseEntity.badRequest().body(new MessageResponse("The assessment expired"));
        }
        if (assessment.get().getStatus() == AssessmentStatus.CANCELLED) {
            return ResponseEntity.badRequest().body(new MessageResponse("The assessment has been cancelled"));
        }
        if (assessment.get().getStatus() == AssessmentStatus.DONE) {
            return ResponseEntity.badRequest().body(new MessageResponse("The assessment has been finished"));
        }
        return ResponseEntity.ok(new MessageResponse(assessment.get().getAssessmentId().toString()));
    }
}
