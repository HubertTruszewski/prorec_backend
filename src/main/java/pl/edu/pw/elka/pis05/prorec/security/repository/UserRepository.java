package pl.edu.pw.elka.pis05.prorec.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.edu.pw.elka.pis05.prorec.security.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
