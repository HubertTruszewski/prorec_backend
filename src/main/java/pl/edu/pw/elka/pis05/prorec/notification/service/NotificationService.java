package pl.edu.pw.elka.pis05.prorec.notification.service;

import pl.edu.pw.elka.pis05.prorec.assessment.model.Assessment;

public interface NotificationService {
    void sendApplicantNewAssessmentMail(Assessment assessment);

    void sendAssessmentFinishedMail(Assessment assessment);
}
