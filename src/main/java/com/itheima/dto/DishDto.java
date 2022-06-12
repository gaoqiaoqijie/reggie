package com.itheima.dto;

import com.itheima.pojo.Dish;
import com.itheima.pojo.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname DishDto
 * @Description TODO 数据传输对象，接受新增菜品时的数据
 * @Date 2022/6/1 10:24
 * @Created by luochao
 */

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
