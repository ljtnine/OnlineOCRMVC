package com.onlineocr.nick.model.actions;

import com.onlineocr.nick.model.entity.User;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

/**
 * Created by GrIfOn on 12.01.2017.
 */
@Component
public class ServiceClass {
    public void sendEmail(User user) {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.smtps.auth", "true");
        properties.put("mail.smtps.host", "smtp.gmail.com");
        properties.put("mail.smtps.user", System.getenv("email"));

        Session session = Session.getDefaultInstance(properties);

        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(System.getenv("email")));
            message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject("Testing Subject");
            message.setText("Hello! \n\n No spam to my email, please!");


            Transport transport = session.getTransport();
            transport.connect(null, System.getenv("pass"));
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void encryptPassword(User user) {
        try {
            MessageDigest alg = MessageDigest.getInstance("SHA-224");
            byte[] input = user.getPasswordHash().getBytes();
            byte[] hash = alg.digest(input);
            String result = "";
            for(int i = 0; i < hash.length; ++i) {
                int v = hash[i] & 0xFF;
                if(v < 16) result += "0";
                result += Integer.toString(v, 16).toUpperCase();
            }

            user.setPasswordHash(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
