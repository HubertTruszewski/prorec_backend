package pl.edu.pw.elka.pis05.prorec.attempt.dto;

public class NewAttemptDTO {
    private long challengeId;
    private String code;

    public NewAttemptDTO() {
    }

    public NewAttemptDTO(final long challengeId, final String code) {
        this.challengeId = challengeId;
        this.code = code;
    }

    public long getChallengeId() {
        return challengeId;
    }

    public String getCode() {
        return code;
    }
}
