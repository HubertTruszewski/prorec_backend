package pl.edu.pw.elka.pis05.prorec.security.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import pl.edu.pw.elka.pis05.prorec.common.MessageResponse;
import pl.edu.pw.elka.pis05.prorec.config.JwtUtils;
import pl.edu.pw.elka.pis05.prorec.security.dto.JwtResponseDTO;
import pl.edu.pw.elka.pis05.prorec.security.dto.LoginUserDTO;
import pl.edu.pw.elka.pis05.prorec.security.dto.NewUserDTO;
import pl.edu.pw.elka.pis05.prorec.security.dto.UserDTO;
import pl.edu.pw.elka.pis05.prorec.security.model.User;
import pl.edu.pw.elka.pis05.prorec.security.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;

    public UserDetailsServiceImpl(final UserRepository userRepository,
            @Lazy final AuthenticationManager authenticationManager, final JwtUtils jwtUtils,
            @Lazy final PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found account with username: %s".formatted(
                        username)));
    }

    @Transactional
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody NewUserDTO newUserDTO) {
        if (userRepository.existsByUsername(newUserDTO.username())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByEmail(newUserDTO.email())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        final User user = new User(newUserDTO.username(), encoder.encode(newUserDTO.password()), newUserDTO.email());
        userRepository.save(user);
        return new ResponseEntity<>(new MessageResponse("User created successfully!"), HttpStatus.CREATED);
    }

    public ResponseEntity<JwtResponseDTO> loginUser(@Valid @RequestBody LoginUserDTO loginUser) {
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUser.username(),
                loginUser.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String jwt = jwtUtils.generateJwtToken(authentication);
        final UserDTO user = UserDTO.of((User) authentication.getPrincipal());
        return ResponseEntity.ok(new JwtResponseDTO(jwt, user));
    }
}
