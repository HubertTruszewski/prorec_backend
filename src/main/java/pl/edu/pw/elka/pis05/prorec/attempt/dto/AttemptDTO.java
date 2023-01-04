package pl.edu.pw.elka.pis05.prorec.attempt.dto;

import java.time.ZonedDateTime;

public record AttemptDTO(long attemptId, String submittedCode, boolean codeError, String codeErrorDetails,
                         ZonedDateTime submitDate, AttemptSummaryDTO attemptSummary, long challengeId,
                         long assessmentId) {
}
