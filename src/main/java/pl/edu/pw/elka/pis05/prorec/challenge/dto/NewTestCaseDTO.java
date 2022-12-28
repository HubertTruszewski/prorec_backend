package pl.edu.pw.elka.pis05.prorec.challenge.dto;

import java.io.Serializable;

import pl.edu.pw.elka.pis05.prorec.challenge.model.ExpectedValueType;

public record NewTestCaseDTO(long challengeId, String expression, String expectedValue,
                             ExpectedValueType expectedValueType) implements Serializable {
}
