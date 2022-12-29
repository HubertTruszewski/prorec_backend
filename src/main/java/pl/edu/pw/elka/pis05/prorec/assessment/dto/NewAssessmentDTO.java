package pl.edu.pw.elka.pis05.prorec.assessment.dto;

import java.time.ZonedDateTime;
import java.util.List;

public class NewAssessmentDTO {
    private String email;
    private ZonedDateTime expiryDate;
    private List<Long> challengesIds;
    private long authorId;

    private NewAssessmentDTO() {
    }

    public NewAssessmentDTO(String email, ZonedDateTime expiryDate, List<Long> challengesIds, long authorId) {
        this.email = email;
        this.expiryDate = expiryDate;
        this.challengesIds = challengesIds;
        this.authorId = authorId;
    }

    public String getEmail() {
        return email;
    }

    public ZonedDateTime getExpiryDate() {
        return expiryDate;
    }

    public List<Long> getChallengesIds() {
        return challengesIds;
    }

    public long getAuthorId() {
        return authorId;
    }
}
