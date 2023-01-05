package pl.edu.pw.elka.pis05.prorec.assessment.dto;

import java.time.ZonedDateTime;
import java.util.List;

public record NewAssessmentDTO(String email, ZonedDateTime expiryDate, int solvingTime, List<Long> challengesIds) {
}
