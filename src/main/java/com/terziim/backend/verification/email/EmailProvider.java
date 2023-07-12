package com.terziim.backend.verification.email;

import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

import static javax.mail.Message.RecipientType.CC;
import static javax.mail.Message.RecipientType.TO;

@Component
public class EmailProvider {


    public void sendActivationEmail(String username, String activationPassword, String email) throws MessagingException, jakarta.mail.MessagingException {
//        Message message = createMail(username, activationPassword, email);
//        SMTPTransport smtpTransport = (SMTPTransport) getEmailSession().getTransport(SIMPLE_MAIL_TRANSFER_PROTOCOL);
//        smtpTransport.connect(EmailConstants.GMAIL_SMTP_SERVER, EmailConstants.USERNAME, EmailConstants.PASSWORD);
//        smtpTransport.sendMessage(message, message.getAllRecipients());
//        smtpTransport.close();
    }


    // Mail
    private Message createMail(String username, String activationPassword, String email) throws MessagingException {              // Buna el gezdir
        Message message = new MimeMessage(getEmailSession());
        message.setFrom(new InternetAddress(EmailConstants.FROM_EMAIL));
        message.setRecipients(TO, InternetAddress.parse(email, false));
        message.setRecipients(CC, InternetAddress.parse(EmailConstants.CC_EMAIL, false));
        message.setSubject(EmailConstants.EMAIL_SUBJECT);
        message.setText("Hello " + username + ", \nTerziim\n \n " +
                "-----------------------------------------------------------------\n" +
                "Please click the link bellow, to activate your account: \n" +
                EmailConstants.ACTIVATION_PATH + "activate/" + username + "?code=" + activationPassword +
                "\n \nIf there is an error, please contact with Us. \n \nRegardly, \nExnotis Support Team");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }



    private Session getEmailSession() {
        Properties properties = System.getProperties();
        properties.put(EmailConstants.SMTP_HOST, EmailConstants.GMAIL_SMTP_SERVER);
        properties.put(EmailConstants.SMTP_AUTH, true);
        properties.put(EmailConstants.SMTP_PORT, EmailConstants.DEFAULT_PORT);
        properties.put(EmailConstants.SMTP_STARTTLS_ENABLE, true);
        properties.put(EmailConstants.SMTP_STARTTLS_REQUIRED, true);
        return Session.getInstance(properties, null);
    }



}

