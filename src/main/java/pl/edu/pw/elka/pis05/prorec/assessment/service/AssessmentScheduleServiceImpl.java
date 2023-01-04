package pl.edu.pw.elka.pis05.prorec.assessment.service;

import java.time.ZonedDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pl.edu.pw.elka.pis05.prorec.assessment.model.Assessment;
import pl.edu.pw.elka.pis05.prorec.assessment.model.AssessmentStatus;
import pl.edu.pw.elka.pis05.prorec.assessment.repository.AssessmentRepository;

@Service
public class AssessmentScheduleServiceImpl implements AssessmentScheduleService {
    private final AssessmentRepository assessmentRepository;

    public AssessmentScheduleServiceImpl(final AssessmentRepository assessmentRepository) {
        this.assessmentRepository = assessmentRepository;
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
        final List<Assessment> assessmentsToFinish = assessmentRepository.getAssessmentsByStatusAndExpiryDateBefore(
                AssessmentStatus.IN_PROGRESS,
                ZonedDateTime.now());
        assessmentsToFinish.forEach(assessment -> assessment.setStatus(AssessmentStatus.DONE));
    }
}
