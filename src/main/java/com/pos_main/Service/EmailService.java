package com.pos_main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ByteArrayResource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


/**
 * Title: EmailService.java. Company: www.codearson.com Copyright: Copyright (c) 2025.
 *
 * @author Bavithragithan Kuganesan
 * @date Feb 19, 2025
 * @version 1.0
 **/

@Service
public class EmailService {
	
	@Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("codearson@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
    
    public void sendEmailWithAttachment(String to, String subject, String text, 
                                       byte[] attachment, String filename) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            helper.setFrom("codearson@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            // Add PDF attachment
            helper.addAttachment(filename, new ByteArrayResource(attachment));

            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email with attachment", e);
        }
    }
}
