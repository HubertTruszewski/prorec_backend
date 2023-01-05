package pl.edu.pw.elka.pis05.prorec.attempt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.edu.pw.elka.pis05.prorec.attempt.model.TestResult;
import pl.edu.pw.elka.pis05.prorec.attempt.model.TestResultId;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, TestResultId> {

    @Query("SELECT tr FROM TestResult tr WHERE tr.testResultId.attempt.attemptId = ?1")
    List<TestResult> findAllByTestResultId(long attemptId);
}
