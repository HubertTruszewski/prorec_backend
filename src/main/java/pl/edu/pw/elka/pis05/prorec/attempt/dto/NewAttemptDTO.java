package pl.edu.pw.elka.pis05.prorec.attempt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewAttemptDTO {
    private long challengeId;
    private long assessmentId;
    private String code;
}
