package pl.edu.pw.elka.pis05.prorec.attempt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.edu.pw.elka.pis05.prorec.attempt.dto.TestResultDTO;
import pl.edu.pw.elka.pis05.prorec.attempt.repository.TestResultRepository;

@Service
public class TestResultServiceImpl implements TestResultService {
    private final TestResultRepository testResultRepository;

    public TestResultServiceImpl(final TestResultRepository testResultRepository) {
        this.testResultRepository = testResultRepository;
    }

    @Override
    public List<TestResultDTO> getTestResultsForAttempt(final long attemptId) {
        return testResultRepository.findAllByTestResultId(attemptId)
                .stream()
                .map(testResult -> new TestResultDTO(testResult.getTestResultId().getTestCase().getExpression(),
                        testResult.getTestResultId().getTestCase().getExpectedValue(),
                        testResult.getTestResultId().getTestCase().getExpectedValueType(),
                        testResult.isPassed(),
                        testResult.getRemarks()))
                .toList();
    }
}
