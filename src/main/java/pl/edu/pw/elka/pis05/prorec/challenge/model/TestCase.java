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

    private String code;

    @ManyToOne
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;
    public TestCase(long testCaseId, String code, Challenge challenge) {
        this.testCaseId = testCaseId;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
}
