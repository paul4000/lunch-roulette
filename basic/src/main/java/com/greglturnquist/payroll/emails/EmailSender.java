package com.greglturnquist.payroll.emails;

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

/**
 * Created by Paulina on 16.01.2018.
 */
public class EmailSender {

    private static final String USER_NAME = "lunch.roulette.manager@gmail.com";
    private static final String PASSWORD = "lunch_roulette";
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587";

    private static final Logger logger = Logger.getLogger(EmailSender.class.toString());

    public void sendSelfEmail(EmailParser emailParser) throws MessagingException{
        sendEmail(USER_NAME, emailParser);
    }

    public void sendEmail(String recipient, EmailParser emailParser) throws MessagingException{

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
//        Session session = Session.getDefaultInstance(properties);
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER_NAME));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(recipient));
            message.setSubject(emailParser.getEmailTitle());
            message.setText(emailParser.getEmailBody());

            Transport.send(message);

            logger.log(Level.INFO, "Email send to " + recipient);

        } catch (Exception e) {
            e.printStackTrace();
//            throw new MessagingException("Error while sending email");
        }

    }

}
