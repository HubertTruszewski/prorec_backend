package pl.edu.pw.elka.pis05.prorec.challenge.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import pl.edu.pw.elka.pis05.prorec.challenge.dto.ChallengeDTO;
import pl.edu.pw.elka.pis05.prorec.challenge.dto.NewChallengeDTO;
import pl.edu.pw.elka.pis05.prorec.challenge.dto.NewTestCaseDTO;
import pl.edu.pw.elka.pis05.prorec.challenge.dto.TestCaseDTO;

public interface ChallengeService {
    ChallengeDTO findByChallengeId(long challengeId);

    ChallengeDTO addNewChallenge(NewChallengeDTO newChallengeDTO);

    ResponseEntity<TestCaseDTO> addNewTestCase(NewTestCaseDTO newTestCaseDTO);

    List<ChallengeDTO> getAllChallenges();

    List<TestCaseDTO> getTestCasesForChallenge(long challengeId);

    ChallengeDTO modifyChallenge(long challengeId, NewChallengeDTO challenge);
}
