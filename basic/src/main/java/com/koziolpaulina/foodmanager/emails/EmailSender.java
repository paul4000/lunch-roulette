package com.koziolpaulina.foodmanager.emails;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailSender {

    private static final String USER_NAME = "lunch.roulette.manager@gmail.com";
    private static final String PASSWORD = "lunch_roulette";
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587";

    private static final Logger logger = Logger.getLogger(EmailSender.class.toString());

    public void sendSelfEmail(EmailParser emailParser) throws MessagingException{
        sendEmail(USER_NAME, emailParser);
    }

    public static void sendEmail(String recipient, EmailParser emailParser) throws MessagingException{

        Properties properties = new Properties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.user", USER_NAME);
        properties.put("mail.smtp.password", PASSWORD);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.connectiontimeout", "1000");
        properties.put("mail.smtp.timeout", "1000");


        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USER_NAME, PASSWORD);
                    }
                });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER_NAME));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(recipient));
            message.setSubject(emailParser.getEmailTitle());
            message.setContent(emailParser.getEmailBody(), "text/html; charset=utf-8");

            Transport.send(message);

            logger.log(Level.INFO, "Email send to " + recipient);

        } catch (Exception e) {
            throw new MessagingException("Error while sending email");
        }

    }

}
