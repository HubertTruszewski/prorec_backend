package pl.edu.pw.elka.pis05.prorec.security.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record NewUserDTO(@NotBlank @Size(min = 3, max = 50) String username, @NotBlank String password,
                         @NotBlank @Email @Size(max = 70) String email) implements Serializable {
}
