package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.dto.SetmealDTO;
import com.itheima.pojo.Setmeal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lenovo
 * @Classname SetmealService
 * @Description TODO
 * @Date 2022/5/28 11:04
 * @Created by luochao
 */

@Service
public interface SetmealService extends IService<Setmeal> {

    @Transactional
    void saveSetmealDTO(SetmealDTO setmealDTO);


    void updateStatusById(Integer status ,List<Long> ids);

    @Transactional
    void deleteSetmeal(List<Long> ids);

}
