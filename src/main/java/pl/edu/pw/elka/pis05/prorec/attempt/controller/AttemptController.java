package pl.edu.pw.elka.pis05.prorec.attempt.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.pw.elka.pis05.prorec.attempt.dto.NewAttemptDTO;
import pl.edu.pw.elka.pis05.prorec.attempt.service.AttemptService;

@RestController
@RequestMapping("/attempt")
public class AttemptController {
    private final AttemptService attemptService;

    public AttemptController(final AttemptService attemptService) {
        this.attemptService = attemptService;
    }

    @PostMapping("/perform")
    public String performAttempt(@RequestBody final NewAttemptDTO newAttemptDTO) {
        return attemptService.performAttempt(newAttemptDTO);
    }
}
