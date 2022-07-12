package com.wyk.ebook.service;

import com.wyk.ebook.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @Override
    public String sendMail(String phoneNumber) {

        User user = userService.getUser(phoneNumber);

        if (user == null)
            return "用户不存在";
        String password = user.getPassword();
        String mail = user.getMail();

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("w577159462@163.com");
            messageHelper.setTo(mail);
            messageHelper.setSubject("主题：EBook密码找回");
            messageHelper.setText("<h3>您的密码为 " + password + "</h3>", true);

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return "发送失败";
        }

        return mail;
    }
}
