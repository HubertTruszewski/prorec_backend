package pl.edu.pw.elka.pis05.prorec.challenge.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.edu.pw.elka.pis05.prorec.challenge.dto.ChallengeDTO;
import pl.edu.pw.elka.pis05.prorec.challenge.dto.NewChallengeDTO;
import pl.edu.pw.elka.pis05.prorec.challenge.service.ChallengeService;

@RestController
@RequestMapping(("/challenge"))
public class ChallengeController {
    private final ChallengeService challengeService;

    public ChallengeController(final ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping("/{challengeId}")
    public ChallengeDTO getChallengeDTObyId(@PathVariable final long challengeId) {
        return challengeService.findByChallengeId(challengeId);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ChallengeDTO addNewChallenge(@RequestBody final NewChallengeDTO newChallengeDTO) {
        return challengeService.addNewChallenge(newChallengeDTO);
    }
}
