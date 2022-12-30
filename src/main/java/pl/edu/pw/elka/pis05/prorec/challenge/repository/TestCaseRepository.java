package pl.edu.pw.elka.pis05.prorec.challenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;
import pl.edu.pw.elka.pis05.prorec.challenge.model.TestCase;

public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
    List<TestCase> findAllByChallenge(Challenge challenge);
}
