package pl.edu.pw.elka.pis05.prorec.security.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.pw.elka.pis05.prorec.common.MessageResponse;
import pl.edu.pw.elka.pis05.prorec.security.dto.JwtResponseDTO;
import pl.edu.pw.elka.pis05.prorec.security.dto.LoginUserDTO;
import pl.edu.pw.elka.pis05.prorec.security.dto.NewUserDTO;
import pl.edu.pw.elka.pis05.prorec.security.service.UserDetailsServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserDetailsServiceImpl userDetailsService;

    public UserController(final UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> loginUser(@Valid @RequestBody final LoginUserDTO loginUser) {
        return userDetailsService.loginUser(loginUser);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody NewUserDTO newUser) {
        return userDetailsService.registerUser(newUser);
    }
}
