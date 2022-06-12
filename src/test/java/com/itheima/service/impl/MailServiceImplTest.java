package com.itheima.service.impl;

import com.itheima.ReggieApplication;
import com.itheima.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Classname MailServiceImplTest
 * @Description TODO
 * @Date 2022/6/8 9:19
 * @Created by luochao
 */

@SpringBootTest(classes = {ReggieApplication.class})
public class MailServiceImplTest {

    @Autowired
    MailService mailService;

    @Test
    public void mailTest() {
       // MailUtils.sendMail("171261668@qq.com");
        mailService.sendMail("1712616668@qq.com");
    }
}
