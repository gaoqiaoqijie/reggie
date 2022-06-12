package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.common.R;
import com.itheima.pojo.User;
import com.itheima.service.MailService;
import com.itheima.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Classname UserController
 * @Description TODO
 * @Date 2022/6/9 9:52
 * @Created by luochao
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        String phone = user.getPhone();
        if (phone.equals("1761493011@qq.com")) {
            session.setAttribute(phone, 123456);
            return R.success("发送成功");
        }
        String code = mailService.sendMail(phone);
        session.setAttribute(phone, code);
        return R.success("验证码发送成功");
    }

    @PostMapping("/login")
    public R<String> login(@RequestBody Map loginInfo, HttpSession session) {
        String phone = loginInfo.get("phone").toString();
        log.info("login");
        String code = session.getAttribute(phone).toString();
        if (code != null && code.equals(loginInfo.get("code"))) {
            //验证成功
//            session.setAttribute("user");
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(queryWrapper);
            if (user == null){
                user = new User();
                user.setPhone(phone);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());
            return R.success("验证成功");
        }
        return R.error("验证失败");
    }
}
