package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname CategoryMapper
 * @Description TODO
 * @Date 2022/5/28 9:53
 * @Created by luochao
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
