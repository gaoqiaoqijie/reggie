package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname EmployeeMapper
 * @Description TODO
 * @Date 2022/5/22 10:40
 * @Created by luochao
 */

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
