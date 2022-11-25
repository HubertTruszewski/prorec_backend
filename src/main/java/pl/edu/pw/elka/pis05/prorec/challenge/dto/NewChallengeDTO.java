package pl.edu.pw.elka.pis05.prorec.challenge.dto;

public class NewChallengeDTO {
    private String name;
    private String description;
    private String codeSnippet;
    private String type;
    private String exampleTestCases;

    public NewChallengeDTO() {
    }

    public NewChallengeDTO(final String name, final String description, final String codeSnippet, final String type,
            final String exampleTestCases) {
        this.name = name;
        this.description = description;
        this.codeSnippet = codeSnippet;
        this.type = type;
        this.exampleTestCases = exampleTestCases;
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

    public String getType() {
        return type;
    }

    public String getExampleTestCases() {
        return exampleTestCases;
    }
}
