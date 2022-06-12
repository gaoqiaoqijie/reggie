package com.itheima.dto;

import com.itheima.pojo.Setmeal;
import com.itheima.pojo.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * @Classname SetmealDTO
 * @Description TODO
 * @Date 2022/6/3 9:45
 * @Created by luochao
 */
@Data
public class SetmealDTO extends Setmeal {

    //套餐所包含的菜品
    List<SetmealDish> setmealDishes;

    String categoryName;
}
