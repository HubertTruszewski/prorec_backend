package pl.edu.pw.elka.pis05.prorec.attempt.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import pl.edu.pw.elka.pis05.prorec.challenge.model.TestCase;

@Embeddable
public class TestResultId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "ATTEMPT_ID")
    private Attempt attempt;

    @ManyToOne
    @JoinColumn(name = "TEST_CASE_ID")
    private TestCase testCase;

    public TestResultId(Attempt attempt, TestCase testCase) {
        this.attempt = attempt;
        this.testCase = testCase;
    }

    public TestResultId() {
    }

    public Attempt getAttempt() {
        return attempt;
    }

    public void setAttempt(Attempt attempt) {
        this.attempt = attempt;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TestResultId that = (TestResultId) o;
        return attempt.equals(that.attempt) && testCase.equals(that.testCase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attempt, testCase);
    }
}
