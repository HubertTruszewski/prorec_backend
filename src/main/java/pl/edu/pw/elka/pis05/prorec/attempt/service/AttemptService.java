package pl.edu.pw.elka.pis05.prorec.attempt.service;

import pl.edu.pw.elka.pis05.prorec.attempt.dto.NewAttemptDTO;

public interface AttemptService {
    String performAttempt(NewAttemptDTO newAttemptDTO);
}
