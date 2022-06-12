package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname UserMapper
 * @Description TODO
 * @Date 2022/6/9 9:49
 * @Created by luochao
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
