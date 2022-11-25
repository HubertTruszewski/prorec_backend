package pl.edu.pw.elka.pis05.prorec.challenge.service;

import pl.edu.pw.elka.pis05.prorec.challenge.dto.ChallengeDTO;
import pl.edu.pw.elka.pis05.prorec.challenge.dto.NewChallengeDTO;

public interface ChallengeService {
    ChallengeDTO findByChallengeId(long challengeId);
    ChallengeDTO addNewChallenge(NewChallengeDTO newChallengeDTO);
}
