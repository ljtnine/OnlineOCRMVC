package com.onlineocr.nick.model.actions;

import com.onlineocr.nick.model.entity.User;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

/**
 * Created by GrIfOn on 12.01.2017.
 */
@Service
public class ServiceClass {
    private JavaMailSenderImpl sender;

    public void sendEmail(User user) {
        sender = new JavaMailSenderImpl();
        Properties properties = new Properties();
        properties.put("mail.protocol", "smtps");
        properties.put("mail.host", "smtp.gmail.com");
        properties.put("mail.smtps.auth", "true");
        properties.put("mail.debug", "true");

        properties.put("mail.user", System.getenv("email"));



        sender.setJavaMailProperties(properties);
        sender.setPort(465);
        sender.setUsername(System.getenv("email"));
        sender.setPassword(System.getenv("pass"));


        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(System.getenv("email"));
        message.setTo(user.getEmail());
        message.setSubject("Hello!");
        message.setText("Hola mi amigo! ^_^");
        sender.send(message);
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
