package pl.edu.pw.elka.pis05.prorec.assessment.model;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;
import pl.edu.pw.elka.pis05.prorec.security.User;

@Entity(name = "Assessment")
@Table(name = "ASSESSMENTS")
public class Assessment implements Serializable {
    @Id
    @Column(name = "ASSESSMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assessmentId;

    private String email;

    private String token;

    @Column(name = "CREATE_DATE")
    private ZonedDateTime createDate;

    @Column(name = "EXPIRY_DATE")
    private ZonedDateTime expiryDate;

    @Column(name = "SOLVING_TIME")
    private int solvingTime;

    @Enumerated(EnumType.ORDINAL)
    private AssessmentStatus status;

    @Column(name = "DEADLINE")
    private ZonedDateTime deadline;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User author;

    @ManyToMany
    @JoinTable(name = "ASSESSMENT_SELECTED_CHALLENGES", joinColumns = @JoinColumn(name = "ASSESSMENT_ID"), inverseJoinColumns = @JoinColumn(name = "CHALLENGE_ID"))
    private List<Challenge> challengesList;

    public Assessment() {
    }

    public Assessment(final String email, final String token, final ZonedDateTime expiryDate, final int solvingTime, final User author,
            final List<Challenge> challengesList) {
        this.email = email;
        this.token = token;
        this.solvingTime = solvingTime;
        this.createDate = ZonedDateTime.now();
        this.expiryDate = expiryDate;
        this.status = AssessmentStatus.AWAITING;
        this.author = author;
        this.challengesList = challengesList;
    }

    public Long getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(Long assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public ZonedDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(ZonedDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public AssessmentStatus getStatus() {
        return status;
    }

    public void setStatus(AssessmentStatus status) {
        this.status = status;
    }

    public ZonedDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(ZonedDateTime deadline) {
        this.deadline = deadline;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<Challenge> getChallengesList() {
        return challengesList;
    }

    public void setChallengesList(List<Challenge> challengesList) {
        this.challengesList = challengesList;
    }

    public int getSolvingTime() {
        return solvingTime;
    }

    public void setSolvingTime(final int solvingTime) {
        this.solvingTime = solvingTime;
    }

    public String getToken() {
        return token;
    }
}
