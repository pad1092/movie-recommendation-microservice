package com.movierec.notificationservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.function.Consumer;

@Configuration
public class Config {

    @Bean("javamail")
    public JavaMailSender getJavaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("padshop885@gmail.com");
        mailSender.setPassword("gcexgryxhyqmgdhr");

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.debug", "true");
        return mailSender;
    }

    @Bean
    @DependsOn("javamail")
    @Lazy
    public Consumer<Message<String>> notificationEventSupplier() {
        return message ->{
            String receiver = message.getPayload();
            String subject = "Tạo tài khoản thành công";
            String content = "https://github.com/pad1092";

            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);

            mailSender.setUsername("padshop885@gmail.com");
            mailSender.setPassword("gcexgryxhyqmgdhr");

            Properties properties = mailSender.getJavaMailProperties();
            properties.put("mail.transport.protocol", "smtp");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.debug", "true");

            MimeMessage mimeMailMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, "utf-8");
            try {
                helper.setFrom("PADSHOP");
                helper.setSubject(subject);
                helper.setText(content, true);
                helper.setTo(receiver);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            mailSender.send(mimeMailMessage);
        };
    }
}
