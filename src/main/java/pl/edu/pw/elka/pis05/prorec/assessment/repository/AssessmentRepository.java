package pl.edu.pw.elka.pis05.prorec.assessment.repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.edu.pw.elka.pis05.prorec.assessment.model.Assessment;
import pl.edu.pw.elka.pis05.prorec.assessment.model.AssessmentStatus;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
    Optional<Assessment> getAssessmentByToken(String token);

    Assessment getAssessmentsByAssessmentId(long assessmentId);

    List<Assessment> getAssessmentsByStatusAndExpiryDateBefore(AssessmentStatus status, ZonedDateTime date);

    List<Assessment> getAssessmentsByStatusAndDeadlineBefore(AssessmentStatus status, ZonedDateTime date);
}
