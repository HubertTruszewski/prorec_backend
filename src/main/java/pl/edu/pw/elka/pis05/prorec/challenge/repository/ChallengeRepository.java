package pl.edu.pw.elka.pis05.prorec.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.edu.pw.elka.pis05.prorec.challenge.model.Challenge;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    Challenge findByChallengeId(long challengeId);
}
