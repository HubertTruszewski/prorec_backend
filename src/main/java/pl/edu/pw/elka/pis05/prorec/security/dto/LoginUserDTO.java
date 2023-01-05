package pl.edu.pw.elka.pis05.prorec.security.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public record LoginUserDTO(@NotBlank String username, @NotBlank String password) implements Serializable {
}
