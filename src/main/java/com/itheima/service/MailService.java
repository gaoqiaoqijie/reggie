package com.itheima.service;

import org.springframework.stereotype.Service;

/**
 * @Classname MailService
 * @Description TODO
 * @Date 2022/6/8 8:57
 * @Created by luochao
 */
@Service
public interface MailService {

    String sendMail(String to);



}
