package pl.edu.pw.elka.pis05.prorec.attempt.service;

import java.util.List;

import pl.edu.pw.elka.pis05.prorec.attempt.dto.TestResultDTO;

public interface TestResultService {
    List<TestResultDTO> getTestResultsForAttempt(long attemptId);
}
