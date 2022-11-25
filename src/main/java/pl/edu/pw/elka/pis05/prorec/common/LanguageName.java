package pl.edu.pw.elka.pis05.prorec.common;

public enum LanguageName {
    PYTHON("python"),
    JAVASCRIPT("js");

    private final String name;

    LanguageName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
