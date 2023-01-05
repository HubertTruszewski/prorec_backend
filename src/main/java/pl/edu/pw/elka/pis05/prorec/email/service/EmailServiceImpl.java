package pl.edu.pw.elka.pis05.prorec.email.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import pl.edu.pw.elka.pis05.prorec.email.service.template.service.TemplateService;

@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender emailSender;
    private final TemplateService templateService;
    private final String senderAddress;
    private final String senderName;

    public EmailServiceImpl(final JavaMailSender javaMailSender, final TemplateService templateService,
            @Value("${spring.mail.username}") final String senderAddress,
            @Value("${prorec.mail.senderName}") final String senderName) {
        this.emailSender = javaMailSender;
        this.templateService = templateService;
        this.senderAddress = senderAddress;
        this.senderName = senderName;
    }

    @Override
    @Async
    public void sendEmailPlainText(final String mailReceiver, final String subject, final String content) {
        try {
            final MimeMessage message = createMessage(mailReceiver, subject);
            message.setText(content);
            emailSender.send(message);
        } catch (final UnsupportedEncodingException | MessagingException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void sendEmailWithTemplate(final String mailReceiver, final String subject, final String templateName,
            final Map<String, Object> model) {
        try {
            final MimeMessage message = createMessage(mailReceiver, subject);
            final String messageContent = templateService.createContent(templateName, model);
            message.setContent(messageContent, "text/html");
            emailSender.send(message);
        } catch (final UnsupportedEncodingException | MessagingException e) {
            log.error(e.getMessage());
        }
    }

    private MimeMessage createMessage(final String mailReceiver, final String subject)
            throws UnsupportedEncodingException, MessagingException {
        final MimeMessage message = emailSender.createMimeMessage();
        message.setFrom(new InternetAddress(senderAddress, senderName));
        message.setRecipients(Message.RecipientType.TO, mailReceiver);
        message.setSubject(subject);
        return message;
    }
}
