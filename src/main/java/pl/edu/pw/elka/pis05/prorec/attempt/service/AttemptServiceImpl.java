package pl.edu.pw.elka.pis05.prorec.attempt.service;

import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.PolyglotException;
import org.graalvm.polyglot.SourceSection;
import org.graalvm.polyglot.Value;
import org.springframework.stereotype.Service;

import pl.edu.pw.elka.pis05.prorec.assessment.model.Assessment;
import pl.edu.pw.elka.pis05.prorec.assessment.model.AssessmentStatus;
import pl.edu.pw.elka.pis05.prorec.assessment.repository.AssessmentRepository;
import pl.edu.pw.elka.pis05.prorec.attempt.dto.AttemptDTO;
import pl.edu.pw.elka.pis05.prorec.attempt.dto.AttemptSummaryDTO;
import pl.edu.pw.elka.pis05.prorec.attempt.dto.NewAttemptDTO;
import pl.edu.pw.elka.pis05.prorec.attempt.model.Attempt;
import pl.edu.pw.elka.pis05.prorec.attempt.model.TestResult;
import pl.edu.pw.elka.pis05.prorec.attempt.model.TestResultId;
import pl.edu.pw.elka.pis05.prorec.attempt.repository.AttemptRepository;
import pl.edu.pw.elka.pis05.prorec.attempt.repository.TestResultRepository;
import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;
import pl.edu.pw.elka.pis05.prorec.challenge.model.TestCase;
import pl.edu.pw.elka.pis05.prorec.challenge.repository.ChallengeRepository;

@Service
public class AttemptServiceImpl implements AttemptService {
    private final AttemptRepository attemptRepository;
    private final ChallengeRepository challengeRepository;
    private final AssessmentRepository assessmentRepository;
    private final TestResultRepository testResultRepository;

    public AttemptServiceImpl(final AttemptRepository attemptRepository, final ChallengeRepository challengeRepository,
            final AssessmentRepository assessmentRepository, TestResultRepository testResultRepository) {
        this.attemptRepository = attemptRepository;
        this.challengeRepository = challengeRepository;
        this.assessmentRepository = assessmentRepository;
        this.testResultRepository = testResultRepository;
    }

    @Override
    @Transactional
    public AttemptSummaryDTO performAttempt(final NewAttemptDTO newAttemptDTO) {
        final Challenge challenge = challengeRepository.findByChallengeId(newAttemptDTO.getChallengeId());
        final String language = challenge.getLanguage().getName();
        final String code = newAttemptDTO.getCode();
        final List<TestResult> testResults = new LinkedList<>();
        final Assessment assessment = assessmentRepository.getAssessmentsByAssessmentId(newAttemptDTO.getAssessmentId());
        final Attempt attempt = new Attempt(code, null, ZonedDateTime.now(), assessment, challenge, null);
        attemptRepository.save(attempt);

        if (assessment.getStatus() != AssessmentStatus.IN_PROGRESS) {
            attempt.setCodeError(true);
            final String errorMessage = String.format("You cannot send solutions to assessment with status %s",
                    assessment.getStatus());
            attempt.setCodeErrorDetails(errorMessage);
            return new AttemptSummaryDTO(errorMessage);
        }

        try (final Context context = Context.create()) {
            context.eval(language, code);
            for (final TestCase testCase : challenge.getTestCases()) {
                final Value actualValue = context.eval(language, testCase.getExpression());
                final boolean testPassed = testPassed(actualValue, testCase);
                if (testPassed) {
                    testResults.add(new TestResult(new TestResultId(attempt, testCase)));
                } else {
                    final String remarks = String.format("Test failed. Expected: %s, got: %s",
                            testCase.getExpectedValue(),
                            actualValue);
                    testResults.add(new TestResult(new TestResultId(attempt, testCase), remarks));
                }
            }
        } catch (PolyglotException e) {
            attempt.setCodeError(true);
            final String errorMessage = generateErrorMessage(e);
            attempt.setCodeErrorDetails(errorMessage);
            return new AttemptSummaryDTO(errorMessage);
        }

        testResultRepository.saveAll(testResults);
        final long correct = testResults.stream().filter(TestResult::isPassed).count();
        final long incorrect = testResults.size() - correct;
        return new AttemptSummaryDTO(correct, incorrect);
    }

    @Override
    public AttemptDTO getAttempt(final long attemptId) {
        final Attempt attempt = attemptRepository.findByAttemptId(attemptId);
        return new AttemptDTO(attempt.getAttemptId(),
                attempt.getSubmittedCode(),
                attempt.isCodeError(),
                attempt.getCodeErrorDetails(),
                attempt.getSubmitDate(),
                createAttemptSummaryDTO(attempt),
                attempt.getChallenge().getChallengeId(),
                attempt.getAssessment().getAssessmentId());
    }

    @Override
    public List<AttemptDTO> getAttemptsSummaryList(final long assessmentId) {
        final Assessment assessment = assessmentRepository.getReferenceById(assessmentId);
        return attemptRepository.findAllByAssessment(assessment)
                .stream()
                .map(attempt -> new AttemptDTO(attempt.getAttemptId(),
                        attempt.getSubmittedCode(),
                        attempt.isCodeError(),
                        attempt.getCodeErrorDetails(),
                        attempt.getSubmitDate(),
                        createAttemptSummaryDTO(attempt),
                        attempt.getChallenge().getChallengeId(),
                        assessmentId))
                .toList();
    }

    private boolean testPassed(final Value value, final TestCase testCase) {
        return switch (testCase.getExpectedValueType()) {
            case BOOLEAN -> value.asBoolean() == Boolean.parseBoolean(testCase.getExpectedValue());
            case INTEGER -> value.asInt() == Integer.parseInt(testCase.getExpectedValue());
            case STRING -> value.asString().equals(testCase.getExpectedValue());
        };
    }

    private String generateErrorMessage(final PolyglotException exception) {
        final String errorType = exception.getMessage();
        final SourceSection codeSection = exception.getSourceLocation();
        final int line = codeSection.getStartLine();
        final int column = codeSection.getStartColumn();

        return String.format("Type: %s, location: line %d, column %d", errorType, line, column);
    }

    private AttemptSummaryDTO createAttemptSummaryDTO(final Attempt attempt) {
        if (attempt.isCodeError()) {
            return new AttemptSummaryDTO(attempt.getCodeErrorDetails());
        }
        final List<TestResult> testResults = attempt.getTestResultsList();
        final long testPassed = testResults.stream().filter(TestResult::isPassed).count();
        final long testFailed = testResults.size() - testPassed;
        return new AttemptSummaryDTO(testPassed, testFailed);
    }
}
