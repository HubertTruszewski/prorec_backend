package pl.edu.pw.elka.pis05.prorec.challenge.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import pl.edu.pw.elka.pis05.prorec.common.LanguageName;

@Entity(name = "CHALLENGE")
@Table(name = "CHALLENGES")
@AllArgsConstructor
public class Challenge implements Serializable {
    @Id
    @Column(name = "CHALLENGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long challengeId;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "CODE_SNIPPET", columnDefinition = "text")
    private String codeSnippet;

    @Enumerated(EnumType.ORDINAL)
    private ChallengeType type;

    @Column(name = "EXAMPLE_TEST_CASES", columnDefinition = "text")
    private String exampleTestCases;

    @Enumerated(EnumType.ORDINAL)
    private LanguageName language;

    @OneToMany(mappedBy = "challenge")
    private List<TestCase> testCases;

    public Challenge() {
    }

    public Challenge(final String name, final String description, final String codeSnippet, final ChallengeType type,
            final String exampleTestCases, final LanguageName language) {
        this.name = name;
        this.description = description;
        this.codeSnippet = codeSnippet;
        this.type = type;
        this.exampleTestCases = exampleTestCases;
        this.language = language;
    }

    public long getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(long challengeId) {
        this.challengeId = challengeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCodeSnippet() {
        return codeSnippet;
    }

    public void setCodeSnippet(String codeSnippet) {
        this.codeSnippet = codeSnippet;
    }

    public ChallengeType getType() {
        return type;
    }

    public void setType(ChallengeType type) {
        this.type = type;
    }

    public String getExampleTestCases() {
        return exampleTestCases;
    }

    public void setExampleTestCases(String exampleTestCases) {
        this.exampleTestCases = exampleTestCases;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public LanguageName getLanguage() {
        return language;
    }

    public void setLanguage(LanguageName language) {
        this.language = language;
    }
}
