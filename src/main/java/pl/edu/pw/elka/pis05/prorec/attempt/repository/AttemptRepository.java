package pl.edu.pw.elka.pis05.prorec.attempt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.edu.pw.elka.pis05.prorec.assessment.model.Assessment;
import pl.edu.pw.elka.pis05.prorec.attempt.model.Attempt;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    Attempt findByAttemptId(long attemptId);

    List<Attempt> findAllByAssessment(Assessment assessment);
}
