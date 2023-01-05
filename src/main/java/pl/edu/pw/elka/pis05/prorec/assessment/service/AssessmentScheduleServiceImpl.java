package pl.edu.pw.elka.pis05.prorec.assessment.service;

import java.time.ZonedDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pl.edu.pw.elka.pis05.prorec.assessment.model.Assessment;
import pl.edu.pw.elka.pis05.prorec.assessment.model.AssessmentStatus;
import pl.edu.pw.elka.pis05.prorec.assessment.repository.AssessmentRepository;
import pl.edu.pw.elka.pis05.prorec.notification.service.NotificationService;

@Service
public class AssessmentScheduleServiceImpl implements AssessmentScheduleService {
    private final AssessmentRepository assessmentRepository;
    private final NotificationService notificationService;

    public AssessmentScheduleServiceImpl(final AssessmentRepository assessmentRepository,
            final NotificationService notificationService) {
        this.assessmentRepository = assessmentRepository;
        this.notificationService = notificationService;
    }

    @Override
    @Transactional
    public void markAssessmentsAsExpired() {
        final List<Assessment> expiredAssessments = assessmentRepository.getAssessmentsByStatusAndExpiryDateBefore(
                AssessmentStatus.AWAITING,
                ZonedDateTime.now());
        expiredAssessments.forEach(assessment -> assessment.setStatus(AssessmentStatus.EXPIRED));
    }

    @Override
    @Transactional
    public void markAssessmentsAsDone() {
        final List<Assessment> assessmentsToFinish = assessmentRepository.getAssessmentsByStatusAndDeadlineBefore(
                AssessmentStatus.IN_PROGRESS,
                ZonedDateTime.now());
        assessmentsToFinish.forEach(assessment -> {
            assessment.setStatus(AssessmentStatus.DONE);
            notificationService.sendAssessmentFinishedMail(assessment);
        });
    }
}
