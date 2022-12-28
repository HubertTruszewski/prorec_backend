package pl.edu.pw.elka.pis05.prorec.assessment.service;

import pl.edu.pw.elka.pis05.prorec.assessment.dto.AssessmentDTO;
import pl.edu.pw.elka.pis05.prorec.assessment.dto.NewAssessmentDTO;

public interface AssessmentService {
    AssessmentDTO addNewAssessment(NewAssessmentDTO newAssessmentDTO);
}
