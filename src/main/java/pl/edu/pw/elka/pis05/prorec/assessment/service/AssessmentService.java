package pl.edu.pw.elka.pis05.prorec.assessment.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import pl.edu.pw.elka.pis05.prorec.assessment.dto.AssessmentDTO;
import pl.edu.pw.elka.pis05.prorec.assessment.dto.NewAssessmentDTO;
import pl.edu.pw.elka.pis05.prorec.common.MessageResponse;

public interface AssessmentService {
    AssessmentDTO addNewAssessment(NewAssessmentDTO newAssessmentDTO);

    List<AssessmentDTO> getAllAssessments();

    AssessmentDTO getAssessment(long assessmentId);

    ResponseEntity<AssessmentDTO> startAssessment(long assessmentId);

    ResponseEntity<Void> cancelAssessment(long assessmentId);

    ResponseEntity<Void> finishAssessment(long assessmentId);

    ResponseEntity<MessageResponse> getAssessmentIdByToken(String token);
}
