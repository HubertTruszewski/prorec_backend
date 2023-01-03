package pl.edu.pw.elka.pis05.prorec.attempt.dto;

import pl.edu.pw.elka.pis05.prorec.challenge.model.ExpectedValueType;

public record TestResultDTO(String expression, String expectedValue, ExpectedValueType expectedValueType,
                            boolean passed, String details) {
}
