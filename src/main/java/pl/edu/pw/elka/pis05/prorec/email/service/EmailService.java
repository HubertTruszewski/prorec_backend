package pl.edu.pw.elka.pis05.prorec.email.service;

import java.util.Map;

public interface EmailService {
    void sendEmailPlainText(String mailReceiver, String subject, String content);
    void sendEmailWithTemplate(String mailReceiver, String subject, String templateName, Map<String, Object> model);
}
