package com.esprit.microservice.etudiant.service;

import com.esprit.microservice.etudiant.models.Mail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceIMP implements IEmailSenderService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(Mail mail) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        helper.setTo(mail.getMailTo());
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());
        helper.setText(mail.getBody());

        mailSender.send(message);
    }
}
