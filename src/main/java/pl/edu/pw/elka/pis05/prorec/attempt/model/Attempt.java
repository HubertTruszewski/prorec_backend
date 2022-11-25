package pl.edu.pw.elka.pis05.prorec.attempt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;

@Entity(name = "ATTEMPT")
@Table(name = "ATTEMPTS")
public class Attempt {
    @Id
    @Column(name = "ATTEMPT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long attemptId;

    @Column(columnDefinition = "text")
    private String code;

    private String results;

    @ManyToOne
    @JoinColumn(name = "CHALLENGE_ID")
    private Challenge challenge;

    public Attempt(long attemptId, String code, String results, Challenge challenge) {
        this.attemptId = attemptId;
        this.code = code;
        this.results = results;
        this.challenge = challenge;
    }

    public Attempt(String code, String results, Challenge challenge) {
        this.code = code;
        this.results = results;
        this.challenge = challenge;
    }

    public Attempt() {
    }

    public long getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(long attemptId) {
        this.attemptId = attemptId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }
}
