package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.common.R;
import com.itheima.dto.DishDto;
import com.itheima.dto.SetmealDTO;
import com.itheima.pojo.Category;
import com.itheima.pojo.Dish;
import com.itheima.pojo.Setmeal;
import com.itheima.service.CategoryService;
import com.itheima.service.SetmealDishService;
import com.itheima.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname SetmealController
 * @Description TODO
 * @Date 2022/6/3 9:31
 * @Created by luochao
 */

@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    SetmealService setmealService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    SetmealDishService setmealDishService;

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        Page<Setmeal> setmealPage = new Page<>(page, pageSize);
        Page<SetmealDTO> setmealDTOPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), Setmeal::getName, name);
        queryWrapper.eq(Setmeal::getIsDeleted, 0);
        setmealService.page(setmealPage, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(setmealPage, setmealDTOPage, "records");
        List<Setmeal> records = setmealPage.getRecords();
        List<SetmealDTO> list = records.stream().map((item) -> {
            SetmealDTO setmealDTO = new SetmealDTO();
            BeanUtils.copyProperties(item, setmealDTO);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            String categoryName = category.getName();
            setmealDTO.setCategoryName(categoryName);

            return setmealDTO;
        }).collect(Collectors.toList());

        setmealDTOPage.setRecords(list);

        return R.success(setmealDTOPage);
    }


    @PostMapping
    public R<String> addSetmeal(@RequestBody SetmealDTO setmealDTO) {
        setmealService.saveSetmealDTO(setmealDTO);
        return R.success("添加成功");
    }

    /**
     * 起售和停售
     *
     * @param ids
     */
    @PostMapping("/status/1")
    public R<String> start(@RequestParam List<Long> ids) {
        setmealService.updateStatusById(1, ids);
        return R.success("修改成功");
    }

    @PostMapping("/status/0")
    public R<String> stop(@RequestParam List<Long> ids) {
        setmealService.updateStatusById(0, ids);
        return R.success("修改成功");
    }

    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> ids) {
        setmealService.deleteSetmeal(ids);
        return R.success("删除成功");
    }

    @GetMapping("/list")
    public R<List<Setmeal>> list(Setmeal setmeal){
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(setmeal.getCategoryId()!=null,Setmeal::getCategoryId,setmeal.getCategoryId());
        queryWrapper.eq(setmeal.getStatus() != null, Setmeal::getStatus, setmeal.getStatus());
        List<Setmeal> list = setmealService.list(queryWrapper);
        return R.success(list);
    }
}
