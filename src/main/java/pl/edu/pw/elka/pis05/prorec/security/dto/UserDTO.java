package pl.edu.pw.elka.pis05.prorec.security.dto;

import java.io.Serializable;

import pl.edu.pw.elka.pis05.prorec.security.model.User;

public record UserDTO(long userId, String username, String email) implements Serializable {
    public static UserDTO of(final User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail());
    }
}
