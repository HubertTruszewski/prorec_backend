package pl.edu.pw.elka.pis05.prorec.attempt.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import pl.edu.pw.elka.pis05.prorec.assessment.model.Assessment;
import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;

@Entity(name = "ATTEMPT")
@Table(name = "ATTEMPTS")
public class Attempt implements Serializable {
    @Id
    @Column(name = "ATTEMPT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attemptId;

    @Column(name = "SUBMITTED_CODE")
    private String submittedCode;

    @Column(name = "CODE_ERROR")
    private boolean codeError;

    @Column(name = "SUBMIT_DATE")
    private ZonedDateTime submitDate;

    @ManyToOne
    @JoinColumn(name = "ASSESSMENT_ID")
    private Assessment assessment;

    @ManyToOne
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;

    @OneToMany
    @JoinColumn(name = "ATTEMPT_ID")
    private List<TestResult> testResultsList;

    public Attempt(long attemptId, String submittedCode, ZonedDateTime submitDate,
            Assessment assessment, Challenge challenge, List<TestResult> testResultsList) {
        this.attemptId = attemptId;
        this.submittedCode = submittedCode;
        this.submitDate = submitDate;
        this.assessment = assessment;
        this.challenge = challenge;
        this.testResultsList = testResultsList;
    }

    public Attempt(String submittedCode, ZonedDateTime submitDate, Assessment assessment,
            Challenge challenge, List<TestResult> testResultsList) {
        this.submittedCode = submittedCode;
        this.submitDate = submitDate;
        this.assessment = assessment;
        this.challenge = challenge;
        this.testResultsList = testResultsList;
    }

    public Attempt() {
    }

    public Long getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(Long attemptId) {
        this.attemptId = attemptId;
    }

    public String getSubmittedCode() {
        return submittedCode;
    }

    public void setSubmittedCode(String submittedCode) {
        this.submittedCode = submittedCode;
    }

    public boolean isCodeError() {
        return codeError;
    }

    public void setCodeError(boolean codeError) {
        this.codeError = codeError;
    }

    public ZonedDateTime getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(ZonedDateTime submitDate) {
        this.submitDate = submitDate;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
}
