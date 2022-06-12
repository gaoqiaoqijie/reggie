package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.common.MyBaseContext;
import com.itheima.common.R;
import com.itheima.pojo.ShoppingCart;
import com.itheima.service.ShoppingCartService;
import com.sun.prism.impl.BaseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname ShoppingCartController
 * @Description TODO
 * @Date 2022/6/9 15:04
 * @Created by luochao
 */
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/list")
    public R<List<ShoppingCart>> list() {
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper();
        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);
        return R.success(list);
    }

    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        Long userId = MyBaseContext.getId();
        shoppingCart.setUserId(userId);

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        //添加的是单个菜品
        queryWrapper.eq(shoppingCart.getDishId() != null, ShoppingCart::getDishId, shoppingCart.getDishId());
        //添加的是套餐
        queryWrapper.eq(shoppingCart.getSetmealId() != null, ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        //数据是否已经存在
        ShoppingCart selectedDish = shoppingCartService.getOne(queryWrapper);
        if (selectedDish != null) {
            Integer number = selectedDish.getNumber();
            selectedDish.setNumber(number+1);
            shoppingCartService.updateById(selectedDish);
        }else {
            shoppingCartService.save(shoppingCart);
            selectedDish=shoppingCart;
        }

        return R.success(selectedDish);
    }
}
