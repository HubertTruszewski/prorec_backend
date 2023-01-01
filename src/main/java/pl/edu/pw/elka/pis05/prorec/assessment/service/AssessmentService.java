package pl.edu.pw.elka.pis05.prorec.assessment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import pl.edu.pw.elka.pis05.prorec.assessment.dto.AssessmentDTO;
import pl.edu.pw.elka.pis05.prorec.assessment.dto.NewAssessmentDTO;

public interface AssessmentService {
    AssessmentDTO addNewAssessment(NewAssessmentDTO newAssessmentDTO);

    List<AssessmentDTO> getAllAssessments();

    AssessmentDTO getAssessment(long assessmentId);

    ResponseEntity<Void> cancelAssessment(long assessmentId);
}
