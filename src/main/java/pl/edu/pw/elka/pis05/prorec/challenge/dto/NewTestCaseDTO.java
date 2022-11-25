package pl.edu.pw.elka.pis05.prorec.challenge.dto;

public class NewTestCaseDTO {
    private String expression;
    private int expectedValue;
    private long challengeId;

    public NewTestCaseDTO(final String expression, final int expectedValue, final long challengeId) {
        this.expression = expression;
        this.expectedValue = expectedValue;
        this.challengeId = challengeId;
    }

    public NewTestCaseDTO() {
    }

    public String getExpression() {
        return expression;
    }

    public int getExpectedValue() {
        return expectedValue;
    }

    public long getChallengeId() {
        return challengeId;
    }
}
