package com.movierec.notificationservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
@Service
public class MailSender {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String receiver, String subject, String message){
        MimeMessage mimeMailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, "utf-8");
        try {
            helper.setFrom("PADSHOP");
            helper.setSubject(subject);
            helper.setText(message, true);
            helper.setTo(receiver);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        mailSender.send(mimeMailMessage);
    }

}
