package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.common.CustomException;
import com.itheima.dto.SetmealDTO;
import com.itheima.mapper.SetmealMapper;
import com.itheima.pojo.Dish;
import com.itheima.pojo.Setmeal;
import com.itheima.pojo.SetmealDish;
import com.itheima.service.SetmealDishService;
import com.itheima.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname SetmealServiceImpl
 * @Description TODO
 * @Date 2022/5/28 11:06
 * @Created by luochao
 */
@Slf4j
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    SetmealDishService setmealDishService;


    /**
     * 新增套餐
     *
     * @param setmealDTO
     */
    @Override
    public void saveSetmealDTO(SetmealDTO setmealDTO) {
        this.save(setmealDTO);
        //手动添加套餐id
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        setmealDishes = setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDTO.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * 起售和停售
     * @param ids
     */
    @Override
    public void updateStatusById(Integer status ,List<Long> ids) {
        List<Setmeal> setmeals = new ArrayList<>(ids.size());
        setmeals = ids.stream().map(item -> {
            Setmeal setmeal = this.getById(item);
            setmeal.setStatus(status);
            return setmeal;
        }).collect(Collectors.toList());
        this.updateBatchById(setmeals);
    }

    /**
     * 删除
     * @param ids
     */
    @Override
    public void deleteSetmeal(List<Long> ids) {
        //查询套餐状态
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus, 1);
        if(this.count(queryWrapper)>0){
            throw new CustomException("套餐售卖中，不能删除");
        }

        //修改setmeal
        List<Setmeal> setmeals = this.listByIds(ids);
        setmeals = setmeals.stream().map(item->{
            item.setIsDeleted(1);
            return item;
        }).collect(Collectors.toList());

        //修改semealdish
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        List<SetmealDish> setmealDishes = setmealDishService.list(setmealDishLambdaQueryWrapper);
        setmealDishes = setmealDishes.stream().map(item -> {
            item.setIsDeleted(1);
            return item;
        }).collect(Collectors.toList());
        this.updateBatchById(setmeals);
        setmealDishService.updateBatchById(setmealDishes);
    }


}
