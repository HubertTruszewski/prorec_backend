package pl.edu.pw.elka.pis05.prorec.challenge.dto;

import pl.edu.pw.elka.pis05.prorec.challenge.model.ExpectedValueType;
import pl.edu.pw.elka.pis05.prorec.challenge.model.TestCase;

public record TestCaseDTO(long testCaseId, long challengeId, String expression, String expectedValue,
                          ExpectedValueType expectedValueType) {
    public static TestCaseDTO of(final TestCase testCase) {
        return new TestCaseDTO(testCase.getTestCaseId(),
                testCase.getChallenge().getChallengeId(),
                testCase.getExpression(),
                testCase.getExpectedValue(),
                testCase.getExpectedValueType());
    }
}
