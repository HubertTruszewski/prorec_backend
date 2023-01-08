package pl.edu.pw.elka.pis05.prorec.challenge.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

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

import pl.edu.pw.elka.pis05.prorec.challenge.dto.ChallengeDTO;
import pl.edu.pw.elka.pis05.prorec.challenge.dto.NewChallengeDTO;
import pl.edu.pw.elka.pis05.prorec.challenge.dto.NewTestCaseDTO;
import pl.edu.pw.elka.pis05.prorec.challenge.dto.TestCaseDTO;
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

    @GetMapping("/all")
    public List<ChallengeDTO> getAllChallenges() {
        return challengeService.getAllChallenges();
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed("ROLE_USER")
    public ChallengeDTO addNewChallenge(@RequestBody final NewChallengeDTO newChallengeDTO) {
        return challengeService.addNewChallenge(newChallengeDTO);
    }

    @PostMapping("/addTestCase")
    @ResponseStatus(HttpStatus.CREATED)
    @RolesAllowed("ROLE_USER")
    public ResponseEntity<TestCaseDTO> addNewTestCase(@RequestBody final NewTestCaseDTO newTestCaseDTO) {
        return challengeService.addNewTestCase(newTestCaseDTO);
    }

    @GetMapping("/{challengeId}/testCases")
    @RolesAllowed("ROLE_USER")
    public List<TestCaseDTO> getTestCasesForChallenge(@PathVariable final long challengeId) {
        return challengeService.getTestCasesForChallenge(challengeId);
    }

    @PutMapping("/{challengeId}")
    @RolesAllowed("ROLE_USER")
    public ChallengeDTO modifyChallenge(@PathVariable final long challengeId,
            @RequestBody final NewChallengeDTO challenge) {
        return challengeService.modifyChallenge(challengeId, challenge);
    }
}
