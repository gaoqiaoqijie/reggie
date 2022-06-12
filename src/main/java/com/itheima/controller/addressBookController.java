package com.itheima.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.common.MyBaseContext;
import com.itheima.common.R;
import com.itheima.pojo.AddressBook;
import com.itheima.service.addressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/***
 * @Classname addressBookController
 * @Description TODO
 * @Date 2022/6/10 8:44
 * @Created by luochao
 */
@RestController
@RequestMapping("/addressBook")
@Slf4j
public class addressBookController {

    @Autowired
    addressBookService addressBookService;

    @PostMapping
    public R<String> add(@RequestBody AddressBook address) {
        address.setUserId(MyBaseContext.getId());
        addressBookService.save(address);
        return R.success("地址添加成功");

    }

    @GetMapping("/list")
    public R<List<AddressBook>> list() {
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId, MyBaseContext.getId());
        queryWrapper.orderByDesc(AddressBook::getDetail);
        List<AddressBook> list = addressBookService.list(queryWrapper);
        return R.success(list);
    }

    @PutMapping("/default")
    public R<String> setDefault(@RequestBody AddressBook addressBook) {
        Long id = addressBook.getId();
        //清楚默认地址
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId, MyBaseContext.getId());
        queryWrapper.eq(AddressBook::getIsDefault, 1);
        AddressBook address = addressBookService.getOne(queryWrapper);
        if (address != null) {
            address.setIsDefault(0);
            addressBookService.updateById(address);
        }
        //设置默认地址
        queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getId, id);
        address = addressBookService.getOne(queryWrapper);
        if (address != null) {
            address.setIsDefault(1);
            addressBookService.updateById(address);
        }

        return R.success("设置默认成功");
    }

    @GetMapping("default")
    public R<AddressBook> getDefault(){
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId, MyBaseContext.getId());
        queryWrapper.eq(AddressBook::getIsDefault, 1);
        AddressBook address = addressBookService.getOne(queryWrapper);
        return R.success(address);
    }
}
