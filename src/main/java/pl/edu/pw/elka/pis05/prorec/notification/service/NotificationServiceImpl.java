package pl.edu.pw.elka.pis05.prorec.notification.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import pl.edu.pw.elka.pis05.prorec.assessment.model.Assessment;
import pl.edu.pw.elka.pis05.prorec.email.service.EmailService;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final EmailService emailService;
    private final String frontendUrl;
    private final String applicantNewAssessmentTemplate;
    private final String applicantNewAssessmentSubject;
    private final String assessmentFinishedTemplate;
    private final String assessmentFinishedSubject;

    public NotificationServiceImpl(final EmailService emailService,
            @Value("${prorec.app.frontendUrl}") final String frontendUrl,
            @Value("${prorec.mail.template.applicantNewAssessmentMail}") final String applicantNewAssessmentTemplate,
            @Value("${prorec.mail.subject.applicantNewAssessmentMail}") final String applicantNewAssessmentSubject,
            @Value("${prorec.mail.template.assessmentFinishedMail}") final String assessmentFinishedTemplate,
            @Value("${prorec.mail.subject.assessmentFinishedMail}") final String assessmentFinishedSubject) {
        this.emailService = emailService;
        this.frontendUrl = frontendUrl;
        this.applicantNewAssessmentTemplate = applicantNewAssessmentTemplate;
        this.applicantNewAssessmentSubject = applicantNewAssessmentSubject;
        this.assessmentFinishedTemplate = assessmentFinishedTemplate;
        this.assessmentFinishedSubject = assessmentFinishedSubject;
    }

    @Override
    public void sendApplicantNewAssessmentMail(final Assessment assessment) {
        final Map<String, Object> model = new HashMap<>();
        model.put("authorUsername", assessment.getAuthor().getUsername());
        model.put("token", assessment.getToken());
        model.put("tokenLink", createLink(String.format("/assessments/applicant?token=%s", assessment.getToken())));
        model.put("expirationDate", assessment.getExpiryDate().toString());
        emailService.sendEmailWithTemplate(assessment.getEmail(),
                applicantNewAssessmentSubject,
                applicantNewAssessmentTemplate,
                model);
    }

    @Override
    public void sendAssessmentFinishedMail(final Assessment assessment) {
        final Map<String, Object> model = new HashMap<>();
        model.put("username", assessment.getAuthor().getUsername());
        model.put("email", assessment.getEmail());
        model.put("assessmentLink", createLink(String.format("/assessments/%s/view", assessment.getAssessmentId())));
        emailService.sendEmailWithTemplate(assessment.getAuthor().getEmail(),
                assessmentFinishedSubject,
                assessmentFinishedTemplate,
                model);
    }

    private String createLink(final String url) {
        return frontendUrl.concat(url);
    }
}
