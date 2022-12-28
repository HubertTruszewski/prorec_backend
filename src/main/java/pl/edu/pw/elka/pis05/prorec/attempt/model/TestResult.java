package pl.edu.pw.elka.pis05.prorec.attempt.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "TestResult")
@Table(name = "TEST_RESULTS")
public class TestResult implements Serializable {

    @EmbeddedId
    private TestResultId testResultId;
    private boolean passed;
    private String remarks;

    public TestResult(TestResultId testResultId, String remarks) {
        this.testResultId = testResultId;
        this.passed = false;
        this.remarks = remarks;
    }

    public TestResult(TestResultId testResultId) {
        this.testResultId = testResultId;
        this.passed = true;
    }

    public TestResult() {
    }

    public TestResultId getTestResultId() {
        return testResultId;
    }
    public void setTestResultId(TestResultId testResultId) {
        this.testResultId = testResultId;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
