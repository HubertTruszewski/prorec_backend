package pl.edu.pw.elka.pis05.prorec.assessment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.edu.pw.elka.pis05.prorec.assessment.model.Assessment;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
}
