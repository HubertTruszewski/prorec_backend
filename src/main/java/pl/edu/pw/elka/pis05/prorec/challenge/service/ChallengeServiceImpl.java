package pl.edu.pw.elka.pis05.prorec.challenge.service;

import org.springframework.stereotype.Service;

import pl.edu.pw.elka.pis05.prorec.challenge.dto.ChallengeDTO;
import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;
import pl.edu.pw.elka.pis05.prorec.challenge.repository.ChallengeRepository;

@Service
public class ChallengeServiceImpl implements ChallengeService {
    private final ChallengeRepository challengeRepository;

    public ChallengeServiceImpl(final ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    @Override
    public ChallengeDTO findByChallengeId(final long challengeId) {
        final Challenge challenge = challengeRepository.findByChallengeId(challengeId);
        return ChallengeDTO.of(challenge);
    }
}
