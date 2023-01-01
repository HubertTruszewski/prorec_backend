package pl.edu.pw.elka.pis05.prorec.assessment.model;

public enum AssessmentStatus {
    AWAITING("AWAITING"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE"),
    EXPIRED("EXPIRED"),
    CANCELLED("CANCELLED");


    private final String status;

    AssessmentStatus(final String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
