package pl.edu.pw.elka.pis05.prorec.attempt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.graalvm.polyglot.Context;
import org.springframework.stereotype.Service;

import pl.edu.pw.elka.pis05.prorec.attempt.dto.NewAttemptDTO;
import pl.edu.pw.elka.pis05.prorec.attempt.model.Attempt;
import pl.edu.pw.elka.pis05.prorec.attempt.repository.AttemptRepository;
import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;
import pl.edu.pw.elka.pis05.prorec.challenge.model.TestCase;
import pl.edu.pw.elka.pis05.prorec.challenge.repository.ChallengeRepository;

@Service
public class AttemptServiceImpl implements AttemptService {
    private final AttemptRepository attemptRepository;
    private final ChallengeRepository challengeRepository;

    public AttemptServiceImpl(final AttemptRepository attemptRepository,
            final ChallengeRepository challengeRepository) {
        this.attemptRepository = attemptRepository;
        this.challengeRepository = challengeRepository;
    }

    @Override
    @Transactional
    public String performAttempt(final NewAttemptDTO newAttemptDTO) {
        final Challenge challenge = challengeRepository.findByChallengeId(newAttemptDTO.getChallengeId());
        String testSummary = "Success: %d, failure: %d\n";
        try (final Context context = Context.create()) {
            final String code = newAttemptDTO.getCode();
            final String language = challenge.getLanguage().getName();
            context.eval(language, code);
            int correct = 0;
            int incorrect = 0;
            final List<TestCase> testCases = challenge.getTestCases();

            for(TestCase testCase : testCases) {
                int expectedValue = testCase.getExpectedValue();
                int actualResult = context.eval(language, testCase.getExpression()).asInt();
                if (expectedValue == actualResult) {
                    ++correct;
                } else {
                    ++incorrect;
                }
            }
            testSummary = String.format(testSummary, correct, incorrect);
            final Attempt attempt = new Attempt(code, testSummary, challenge);
            attemptRepository.save(attempt);
        } catch (Exception e) {
            final Attempt attempt = new Attempt(newAttemptDTO.getCode(), "Code parsing error!", challenge);
            attemptRepository.save(attempt);
            return "Error during code parsing!";
        }
        return testSummary;
    }
}
