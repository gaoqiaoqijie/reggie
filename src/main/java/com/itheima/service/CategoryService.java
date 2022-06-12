package com.itheima.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.pojo.Category;
import org.springframework.stereotype.Service;

/**
 * @Classname CategoryService
 * @Description TODO
 * @Date 2022/5/28 9:53
 * @Created by luochao
 */
@Service
public interface CategoryService extends IService<Category> {

    public void deleteCategoryById(Long id);

}
