package pl.edu.pw.elka.pis05.prorec.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.edu.pw.elka.pis05.prorec.challenge.model.TestCase;

public interface TestCaseRepository extends JpaRepository<TestCase, Long> {
}
