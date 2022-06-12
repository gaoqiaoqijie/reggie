package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.pojo.Dish;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname DishMapper
 * @Description TODO
 * @Date 2022/5/28 11:01
 * @Created by luochao
 */

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
