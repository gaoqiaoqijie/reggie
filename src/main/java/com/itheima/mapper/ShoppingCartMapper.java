package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.pojo.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Classname ShoppingCartMapper
 * @Description TODO
 * @Date 2022/6/9 14:55
 * @Created by luochao
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart>  {
}
