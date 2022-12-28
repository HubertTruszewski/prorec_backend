package pl.edu.pw.elka.pis05.prorec.challenge.dto;

import java.io.Serializable;

import pl.edu.pw.elka.pis05.prorec.challenge.model.ChallengeType;
import pl.edu.pw.elka.pis05.prorec.common.LanguageName;

public class NewChallengeDTO implements Serializable {
    private String name;
    private String description;
    private String codeSnippet;
    private ChallengeType type;
    private String exampleTestCases;
    private LanguageName language;

    public NewChallengeDTO() {
    }

    public NewChallengeDTO(final String name, final String description, final String codeSnippet,
            final ChallengeType type, final String exampleTestCases, final LanguageName language) {
        this.name = name;
        this.description = description;
        this.codeSnippet = codeSnippet;
        this.type = type;
        this.exampleTestCases = exampleTestCases;
        this.language = language;
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

    public ChallengeType getType() {
        return type;
    }

    public String getExampleTestCases() {
        return exampleTestCases;
    }

    public LanguageName getLanguage() {
        return language;
    }
}
