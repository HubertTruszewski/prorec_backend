package pl.edu.pw.elka.pis05.prorec.attempt.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.pw.elka.pis05.prorec.attempt.dto.AttemptDTO;
import pl.edu.pw.elka.pis05.prorec.attempt.dto.AttemptSummaryDTO;
import pl.edu.pw.elka.pis05.prorec.attempt.dto.NewAttemptDTO;
import pl.edu.pw.elka.pis05.prorec.attempt.dto.TestResultDTO;
import pl.edu.pw.elka.pis05.prorec.attempt.service.AttemptService;
import pl.edu.pw.elka.pis05.prorec.attempt.service.TestResultService;

@RestController
@RequestMapping("/attempt")
public class AttemptController {
    private final AttemptService attemptService;
    private final TestResultService testResultService;

    public AttemptController(final AttemptService attemptService, final TestResultService testResultService) {
        this.attemptService = attemptService;
        this.testResultService = testResultService;
    }

    @PostMapping("/perform")
    public AttemptSummaryDTO performAttempt(@RequestBody final NewAttemptDTO newAttemptDTO) {
        return attemptService.performAttempt(newAttemptDTO);
    }

    @GetMapping("/{attemptId}")
    @RolesAllowed("ROLE_USER")
    public AttemptDTO getAttempt(@PathVariable long attemptId) {
        return attemptService.getAttempt(attemptId);
    }

    @GetMapping("/assessment/{assessmentId}")
    @RolesAllowed("ROLE_USER")
    public List<AttemptDTO> getAttemptsSummaryList(@PathVariable long assessmentId) {
        return attemptService.getAttemptsSummaryList(assessmentId);
    }

    @GetMapping("/results/{attemptId}")
    @RolesAllowed("ROLE_USER")
    public List<TestResultDTO> getAttemptResults(@PathVariable long attemptId) {
        return testResultService.getTestResultsForAttempt(attemptId);
    }
}
