package pl.edu.pw.elka.pis05.prorec.assessment.dto;

import java.time.ZonedDateTime;
import java.util.List;

import pl.edu.pw.elka.pis05.prorec.assessment.model.Assessment;
import pl.edu.pw.elka.pis05.prorec.assessment.model.AssessmentStatus;
import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;

public record AssessmentDTO(long assessmentId, String email, ZonedDateTime createDate, ZonedDateTime expiryDate,
                            AssessmentStatus status, ZonedDateTime deadline, long authorId, List<Long> challengesIds) {

    public static AssessmentDTO of(final Assessment assessment) {
        return new AssessmentDTO(assessment.getAssessmentId(),
                assessment.getEmail(),
                assessment.getCreateDate(),
                assessment.getExpiryDate(),
                assessment.getStatus(),
                assessment.getDeadline(),
                assessment.getAuthor().getId(),
                assessment.getChallengesList().stream().map(Challenge::getChallengeId).toList());
    }
}
