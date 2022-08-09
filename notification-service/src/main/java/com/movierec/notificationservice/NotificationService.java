package com.movierec.notificationservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {
    @Autowired
    private MailSender mailSender;
    public void createAccSuccess(String email){
        log.info(email);
        mailSender.sendMail(email, "Tạo tài khoản thành công", "https://github.com/pad1092");
    }
}
