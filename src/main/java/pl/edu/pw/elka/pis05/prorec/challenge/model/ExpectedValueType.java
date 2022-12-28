package pl.edu.pw.elka.pis05.prorec.challenge.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ExpectedValueType {
    BOOLEAN("BOOLEAN"),
    INTEGER("INTEGER"),
    STRING("STRING");

    @Getter
    private final String type;
}
