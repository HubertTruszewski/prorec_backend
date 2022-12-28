package pl.edu.pw.elka.pis05.prorec.security;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User register(@RequestBody NewUser newUser) {
        final User userToRegister = new User(newUser.username(), newUser.password(), newUser.email());
        // TODO UserService
        return userRepository.save(userToRegister);
    }
}
