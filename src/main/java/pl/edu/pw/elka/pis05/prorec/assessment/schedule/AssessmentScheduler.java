package pl.edu.pw.elka.pis05.prorec.assessment.schedule;

import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;
import pl.edu.pw.elka.pis05.prorec.assessment.service.AssessmentScheduleService;

@Slf4j
public class AssessmentScheduler {
    private final AssessmentScheduleService assessmentScheduleService;

    public AssessmentScheduler(final AssessmentScheduleService assessmentScheduleService) {
        this.assessmentScheduleService = assessmentScheduleService;
    }

    @Scheduled(cron = "0 0/15 * * * *")
    public void markAssessmentsAsExpired() {
        log.info("Starting marking assessments as expired");
        assessmentScheduleService.markAssessmentsAsExpired();
        log.info("Finished marking assessments as expired");
    }

    @Scheduled(cron = "0 0/15 * * * *")
    public void markAssessmentsAsDone() {
        log.info("Starting marking assessments as done");
        assessmentScheduleService.markAssessmentsAsDone();
        log.info("Finished marking assessments as done");
    }

}
