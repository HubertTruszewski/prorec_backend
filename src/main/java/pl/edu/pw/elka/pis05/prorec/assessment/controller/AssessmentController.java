package pl.edu.pw.elka.pis05.prorec.assessment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.pw.elka.pis05.prorec.assessment.dto.AssessmentDTO;
import pl.edu.pw.elka.pis05.prorec.assessment.dto.NewAssessmentDTO;
import pl.edu.pw.elka.pis05.prorec.assessment.service.AssessmentService;

@RestController
@RequestMapping("/assessment")
public class AssessmentController {
    private final AssessmentService assessmentService;

    public AssessmentController(final AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AssessmentDTO addNewAssessment(@RequestBody final NewAssessmentDTO newAssessmentDTO) {
        return assessmentService.addNewAssessment(newAssessmentDTO);
    }
}
