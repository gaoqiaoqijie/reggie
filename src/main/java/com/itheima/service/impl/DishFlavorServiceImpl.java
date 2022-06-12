package com.itheima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mapper.DishFlavorMapper;
import com.itheima.pojo.DishFlavor;
import com.itheima.service.DishFlavorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Classname DishFlavorServiceImpl
 * @Description TODO
 * @Date 2022/6/1 10:47
 * @Created by luochao
 */
@Service
@Slf4j
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
