package pl.edu.pw.elka.pis05.prorec.challenge.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pl.edu.pw.elka.pis05.prorec.challenge.dto.ChallengeDTO;
import pl.edu.pw.elka.pis05.prorec.challenge.dto.NewChallengeDTO;
import pl.edu.pw.elka.pis05.prorec.challenge.dto.NewTestCaseDTO;
import pl.edu.pw.elka.pis05.prorec.challenge.dto.TestCaseDTO;
import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;
import pl.edu.pw.elka.pis05.prorec.challenge.model.TestCase;
import pl.edu.pw.elka.pis05.prorec.challenge.repository.ChallengeRepository;
import pl.edu.pw.elka.pis05.prorec.challenge.repository.TestCaseRepository;

@Service
public class ChallengeServiceImpl implements ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final TestCaseRepository testCaseRepository;

    public ChallengeServiceImpl(final ChallengeRepository challengeRepository,
            final TestCaseRepository testCaseRepository) {
        this.challengeRepository = challengeRepository;
        this.testCaseRepository = testCaseRepository;
    }

    @Override
    public ChallengeDTO findByChallengeId(final long challengeId) {
        final Challenge challenge = challengeRepository.findByChallengeId(challengeId);
        return ChallengeDTO.of(challenge);
    }

    @Override
    public ChallengeDTO addNewChallenge(final NewChallengeDTO newChallengeDTO) {
        final Challenge newChallenge = new Challenge(newChallengeDTO.getName(),
                newChallengeDTO.getDescription(),
                newChallengeDTO.getCodeSnippet(),
                newChallengeDTO.getType(),
                newChallengeDTO.getExampleTestCases(),
                newChallengeDTO.getLanguage());
        challengeRepository.save(newChallenge);
        return ChallengeDTO.of(newChallenge);
    }

    @Override
    public ResponseEntity<TestCaseDTO> addNewTestCase(final NewTestCaseDTO newTestCaseDTO) {
        final Challenge challenge = challengeRepository.getReferenceById(newTestCaseDTO.challengeId());
        final TestCase testCase = new TestCase(newTestCaseDTO.expression(),
                newTestCaseDTO.expectedValue(),
                newTestCaseDTO.expectedValueType(),
                challenge);
        testCaseRepository.save(testCase);
        return ResponseEntity.status(HttpStatus.CREATED).body(TestCaseDTO.of(testCase));
    }

    @Override
    public List<ChallengeDTO> getAllChallenges() {
        return challengeRepository.findAll().stream().map(ChallengeDTO::of).toList();
    }

    @Override
    public List<TestCaseDTO> getTestCasesForChallenge(final long challengeId) {
        final Challenge challenge = challengeRepository.getReferenceById(challengeId);
        final List<TestCase> testCases = testCaseRepository.findAllByChallenge(challenge);
        return testCases.stream().map(TestCaseDTO::of).toList();
    }
}
