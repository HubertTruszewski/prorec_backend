package pl.edu.pw.elka.pis05.prorec.security.dto;

import java.io.Serializable;

public record JwtResponseDTO(String token, UserDTO user) implements Serializable {
}
