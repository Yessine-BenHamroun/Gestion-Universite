package com.esprit.microservice.etudiant.service;
import jakarta.mail.MessagingException;
import com.esprit.microservice.etudiant.models.Mail;

public interface IEmailSenderService {

    void sendEmail(Mail mail) throws MessagingException;

}
