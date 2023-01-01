package pl.edu.pw.elka.pis05.prorec.assessment.dto;

import java.time.ZonedDateTime;
import java.util.List;

import pl.edu.pw.elka.pis05.prorec.assessment.model.Assessment;
import pl.edu.pw.elka.pis05.prorec.assessment.model.AssessmentStatus;
import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;

public record AssessmentDTO(long assessmentId, String email, String token, ZonedDateTime createDate, ZonedDateTime expiryDate,
                            int solvingTime, AssessmentStatus status, ZonedDateTime deadline, long authorId,
                            List<Long> challengesIds) {

    public static AssessmentDTO of(final Assessment assessment) {
        return new AssessmentDTO(assessment.getAssessmentId(),
                assessment.getEmail(),
                assessment.getToken(),
                assessment.getCreateDate(),
                assessment.getExpiryDate(),
                assessment.getSolvingTime(),
                assessment.getStatus(),
                assessment.getDeadline(),
                assessment.getAuthor().getId(),
                assessment.getChallengesList().stream().map(Challenge::getChallengeId).toList());
    }
}
