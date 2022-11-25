package pl.edu.pw.elka.pis05.prorec.challenge.service;

import pl.edu.pw.elka.pis05.prorec.challenge.dto.ChallengeDTO;

public interface ChallengeService {
    ChallengeDTO findByChallengeId(long challengeId);
}
