package pl.edu.pw.elka.pis05.prorec.assessment.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/all")
    public List<AssessmentDTO> getAllAssessments() {
        return assessmentService.getAllAssessments();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AssessmentDTO addNewAssessment(@RequestBody final NewAssessmentDTO newAssessmentDTO) {
        return assessmentService.addNewAssessment(newAssessmentDTO);
    }

    @GetMapping("/{assessmentId}")
    public AssessmentDTO getAssessment(@PathVariable final long assessmentId) {
        return assessmentService.getAssessment(assessmentId);
    }

    @PutMapping("/cancel/{assessmentId}")
    public ResponseEntity<Void> cancelAssessment(@PathVariable final long assessmentId) {
        return assessmentService.cancelAssessment(assessmentId);
    }
}
