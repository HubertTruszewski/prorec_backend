package pl.edu.pw.elka.pis05.prorec.challenge.dto;

import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;
import pl.edu.pw.elka.pis05.prorec.challenge.model.ChallengeType;
import pl.edu.pw.elka.pis05.prorec.common.LanguageName;

public class ChallengeDTO {
    private final long challengeId;
    private final String name;
    private final String description;
    private final String codeSnippet;
    private final String exampleTestCases;
    private final ChallengeType type;
    private final LanguageName language;

    public ChallengeDTO(final long challengeId, final String name, final String description, final String codeSnippet,
            final String exampleTestCases, final ChallengeType type, final LanguageName language) {
        this.challengeId = challengeId;
        this.name = name;
        this.description = description;
        this.codeSnippet = codeSnippet;
        this.exampleTestCases = exampleTestCases;
        this.type = type;
        this.language = language;
    }

    public static ChallengeDTO of(final Challenge challenge) {
        return new ChallengeDTO(challenge.getChallengeId(),
                challenge.getName(),
                challenge.getDescription(),
                challenge.getCodeSnippet(),
                challenge.getExampleTestCases(),
                challenge.getType(),
                challenge.getLanguage());
    }

    public long getChallengeId() {
        return challengeId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCodeSnippet() {
        return codeSnippet;
    }

    public String getExampleTestCases() {
        return exampleTestCases;
    }

    public ChallengeType getType() {
        return type;
    }

    public LanguageName getLanguage() {
        return language;
    }
}
