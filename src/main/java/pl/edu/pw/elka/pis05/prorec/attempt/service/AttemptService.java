package pl.edu.pw.elka.pis05.prorec.attempt.service;

import java.util.List;

import pl.edu.pw.elka.pis05.prorec.attempt.dto.AttemptDTO;
import pl.edu.pw.elka.pis05.prorec.attempt.dto.AttemptSummaryDTO;
import pl.edu.pw.elka.pis05.prorec.attempt.dto.NewAttemptDTO;

public interface AttemptService {
    AttemptSummaryDTO performAttempt(NewAttemptDTO newAttemptDTO);

    AttemptDTO getAttempt(long attemptId);

    List<AttemptDTO> getAttemptsSummaryList(long assessmentId);
}
