package pl.edu.pw.elka.pis05.prorec.challenge.model;

public enum ChallengeType {
    EASY("EASY"),
    MEDIUM("MEDIUM"),
    HARD("HARD");

    private final String type;

    ChallengeType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
