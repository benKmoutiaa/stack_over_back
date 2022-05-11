package com.stack.stack_over.Email;


import com.stack.stack_over.Validator.EmailValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService implements EmailSender{

    @Value("${app.baseURL}")
    private String baseUrl;

    @Value("${sender.email.name}")
    private String senderEmail;

    @Value("${sender.email.password}")
    private String senderEmailPassword;

    private static Session emailSessionInstance;



    public boolean sendSignupVerificationEmail(String email, String htmlContent) {


        try {
            Session emailSession = getEmailSessionInstance();
            Message msg = new MimeMessage(emailSession);
            msg.setFrom(new InternetAddress("test@email.com"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            msg.setSubject("Confirm your email");
            msg.setContent(htmlContent, "text/html");
            Transport.send(msg);


        } catch (MessagingException e) {
            return false;
        }
        return true;

    }

    private Session getEmailSessionInstance() {
        if (emailSessionInstance != null)
            return emailSessionInstance;

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        //properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put(".mail.smtp.connectiontimeout", "5000");
        properties.put("mail.smtp.timeout", "3000");
        properties.put("mail.smtp.writetimeout", "5000");


        emailSessionInstance = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail,senderEmailPassword);
            }
        });

        return emailSessionInstance;
    }

    public boolean sendForgotPasswordEmail(String email, String htmlContent) {

        try {
            Session emailSession = getEmailSessionInstance();
            Message msg = new MimeMessage(emailSession);
            msg.setFrom(new InternetAddress("khaled.moutiaaDev@gmail.com"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            msg.setSubject("Reset Your Password - SpringAuthentication"); // to set the subject (not mandatory)
            msg.setContent(htmlContent, "text/html");
            Transport.send(msg);
        } catch (MessagingException e) {
            return false;
        }
        return true;
    }

    @Override
    public void sendEmail(String to, String email) {

    }
}
