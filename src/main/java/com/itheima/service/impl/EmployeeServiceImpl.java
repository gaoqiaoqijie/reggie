package com.itheima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mapper.EmployeeMapper;
import com.itheima.pojo.Employee;
import com.itheima.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @Classname EmployeeServiceImpl
 * @Description TODO
 * @Date 2022/5/22 10:58
 * @Created by luochao
 */

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
