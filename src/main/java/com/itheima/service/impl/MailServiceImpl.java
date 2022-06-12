package com.itheima.service.impl;

import com.itheima.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

/**
 * @Classname MailServiceImpl
 * @Description TODO
 * @Date 2022/6/8 8:59
 * @Created by luochao
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {
    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${mail-text.context}")
    private String context;
    @Value("${mail-text.lenght}")
    private int lenght;
    @Value("${mail-text.title}")
    private String title;
    @Value("${spring.mail.nickname}")
    private String nickName;


    public void sendMail(String to, String title, String context) {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage);
        try {
            helper.setFrom(nickName+"<"+from+">");
            helper.setTo(to);
            helper.setSubject(title);
            helper.setText(context);
            javaMailSender.send(mimeMailMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String sendMail(String to){
        String code = randomCode(lenght);
        sendMail(to,title,context+code);
        return code;
    }

    private String randomCode(int lenght) {
        Random random = new Random();
        StringBuilder code = new StringBuilder(lenght);
        code.append(random.nextInt(9)+1);
        for (int i = 0; i < lenght-1; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

}
