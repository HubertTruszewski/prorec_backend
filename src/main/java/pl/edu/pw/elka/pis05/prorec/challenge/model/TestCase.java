package pl.edu.pw.elka.pis05.prorec.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "TEST_CASE")
@Table(name = "TEST_CASES")
public class TestCase {

    @Id
    @Column(name = "TEST_CASE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long testCaseId;

    @Column(nullable = false)
    private String expression;

    @Column(name = "EXPECTED_VALUE")
    private int expectedValue;

    @ManyToOne
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;

    public TestCase(long testCaseId, final String expression, final int expectedValue, final Challenge challenge) {
        this.testCaseId = testCaseId;
        this.expression = expression;
        this.expectedValue = expectedValue;
        this.challenge = challenge;
    }

    public TestCase(final String expression, final int expectedValue, final Challenge challenge) {
        this.expression = expression;
        this.expectedValue = expectedValue;
        this.challenge = challenge;
    }

    public TestCase() {
    }

    public long getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(long testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public int getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(int expectedValue) {
        this.expectedValue = expectedValue;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
}
