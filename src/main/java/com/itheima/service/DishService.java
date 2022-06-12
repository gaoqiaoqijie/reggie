package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.dto.DishDto;
import com.itheima.pojo.Dish;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname DishService
 * @Description TODO
 * @Date 2022/5/28 11:03
 * @Created by luochao
 */

@Service
public interface DishService extends IService<Dish> {

    @Transactional
    public void saveWithFlavor(DishDto dishDto);

    public DishDto getByIdWithFlavor(Long id);

    @Transactional
    void updateWithFlavor(DishDto dishDto);


    @Transactional
    void updateStatusById(Long id);

    void getDishByCategoryId(Long Id);
}
