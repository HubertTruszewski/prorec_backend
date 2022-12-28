package pl.edu.pw.elka.pis05.prorec.attempt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.edu.pw.elka.pis05.prorec.attempt.model.TestResult;
import pl.edu.pw.elka.pis05.prorec.attempt.model.TestResultId;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, TestResultId> {
}