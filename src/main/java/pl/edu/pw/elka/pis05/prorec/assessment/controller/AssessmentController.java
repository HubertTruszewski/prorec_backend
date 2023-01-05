package pl.edu.pw.elka.pis05.prorec.assessment.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import pl.edu.pw.elka.pis05.prorec.common.MessageResponse;


@RestController
@RequestMapping("/assessment")
public class AssessmentController {
    private final AssessmentService assessmentService;

    public AssessmentController(final AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping("/all")
    @RolesAllowed("ROLE_ADMIN")
    public List<AssessmentDTO> getAllAssessments() {
        return assessmentService.getAllAssessments();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed("ROLE_USER")
    public AssessmentDTO addNewAssessment(@RequestBody final NewAssessmentDTO newAssessmentDTO) {
        return assessmentService.addNewAssessment(newAssessmentDTO);
    }

    @GetMapping("/{assessmentId}")
    public AssessmentDTO getAssessment(@PathVariable final long assessmentId) {
        return assessmentService.getAssessment(assessmentId);
    }

    @PutMapping("/start/{assessmentId}")
    public ResponseEntity<AssessmentDTO> startAssessment(@PathVariable final long assessmentId) {
        return assessmentService.startAssessment(assessmentId);
    }

    @PutMapping("/cancel/{assessmentId}")
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<Void> cancelAssessment(@PathVariable final long assessmentId) {
        return assessmentService.cancelAssessment(assessmentId);
    }

    @PutMapping("/finish/{assessmentId}")
    public ResponseEntity<Void> finishAssessment(@PathVariable final long assessmentId) {
        return assessmentService.finishAssessment(assessmentId);
    }

    @GetMapping("/token/{token}")
    public ResponseEntity<MessageResponse> getAssessmentIdByToken(@PathVariable final String token) {
        return assessmentService.getAssessmentIdByToken(token);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("#userId == authentication.principal.id")
    public List<AssessmentDTO> getAssessmentForUser(@PathVariable final long userId) {
        return assessmentService.getAssessmentForUser(userId);
    }
}
