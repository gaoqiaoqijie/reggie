package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.common.R;
import com.itheima.pojo.Employee;
import com.itheima.service.EmployeeService;
import com.itheima.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @Classname EmployeeController
 * @Description TODO
 * @Date 2022/5/22 10:59
 * @Created by luochao
 */

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    MailService mailService;


    @PostMapping("/login")
    public R<Employee> login(HttpSession session, @RequestBody Employee employee) {
        //log.info("login: "+String.valueOf(Thread.currentThread().getId()));
        //加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        //根据用户名查数据
        LambdaQueryWrapper<Employee> queryChainWrapper = new LambdaQueryWrapper<>();
        queryChainWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryChainWrapper);

        if (emp == null) {
            return R.error("user not found");
        }

        if (!emp.getPassword().equals(password)) {
            return R.error("password error");
        }

        if (emp.getStatus() == 0) {
            return R.error("user status is no");
        }

        session.setAttribute("employee", emp.getId());


        return R.success(emp);
    }

    @PostMapping("logout")
    public R<String> logout(HttpSession session) {
        session.removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 添加员工
     *
     */
    @PostMapping()
    public R<String> add(HttpSession session, @RequestBody Employee employee) {
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employeeService.save(employee);
        return R.success("添加成功");
    }


    /**
     * 分页
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        Page<Employee> employeePage = new Page<>(page,pageSize);

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), Employee::getName, name);
        queryWrapper.orderByDesc(Employee::getName);

        employeeService.page(employeePage,queryWrapper);
        //log.info("page: "+String.valueOf(Thread.currentThread().getId()));
        return R.success(employeePage);
    }

    @PutMapping
    public R<String> update(HttpSession session,@RequestBody Employee employee){
        Long id = (Long) session.getAttribute("employee");
        employeeService.updateById(employee);
        return R.success("禁用成功");
    }

    @GetMapping("/{id}")
    public R<Employee> getEmployeeInfo(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        return R.success(employee);
    }

}
