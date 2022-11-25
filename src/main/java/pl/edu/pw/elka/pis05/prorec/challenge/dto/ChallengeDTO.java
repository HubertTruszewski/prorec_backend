package pl.edu.pw.elka.pis05.prorec.challenge.dto;

import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;

public class ChallengeDTO {
    private final long challengeId;
    private final String name;
    private final String description;
    private final String codeSnippet;
    private final String exampleTestCases;
    private final String type;

    public ChallengeDTO(final long challengeId, final String name, final String description, final String codeSnippet,
            final String exampleTestCases, final String type) {
        this.challengeId = challengeId;
        this.name = name;
        this.description = description;
        this.codeSnippet = codeSnippet;
        this.exampleTestCases = exampleTestCases;
        this.type = type;
    }

    public static ChallengeDTO of(final Challenge challenge) {
        return new ChallengeDTO(challenge.getChallengeId(),
                challenge.getName(),
                challenge.getDescription(),
                challenge.getCodeSnippet(),
                challenge.getExampleTestCases(),
                challenge.getType().name());
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

    public String getType() {
        return type;
    }
}
