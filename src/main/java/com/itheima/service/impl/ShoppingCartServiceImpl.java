package com.itheima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mapper.ShoppingCartMapper;
import com.itheima.pojo.ShoppingCart;
import com.itheima.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @Classname ShoppingCartServiceImpl
 * @Description TODO
 * @Date 2022/6/9 14:56
 * @Created by luochao
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
