package pl.edu.pw.elka.pis05.prorec.attempt.dto;

import java.io.Serializable;

public record AttemptSummaryDTO(boolean syntaxError, Long testPassed, Long testFailed, String details)
        implements Serializable {
    public AttemptSummaryDTO(final long testPassed, final long testFailed) {
        this(false, testPassed, testFailed, null);
    }

    public AttemptSummaryDTO(final String details) {
        this(true, null, null, details);
    }
}
