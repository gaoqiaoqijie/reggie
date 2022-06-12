package com.itheima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mapper.addressBookMapper;
import com.itheima.pojo.AddressBook;
import com.itheima.service.addressBookService;
import org.springframework.stereotype.Service;

/**
 * @Classname addressBookServiceImpl
 * @Description TODO
 * @Date 2022/6/10 8:47
 * @Created by luochao
 */
@Service
public class addressBookServiceImpl extends ServiceImpl<addressBookMapper, AddressBook> implements addressBookService {
}
