package com.itheima.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Classname MyBaseContext
 * @Description TODO
 * @Date 2022/5/28 9:30
 * @Created by luochao
 */

@Slf4j
@Component
public class MyBaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal();

    public static Long getId(){
        return threadLocal.get();
    }

    public static void setId(Long id) {
        threadLocal.set(id);
    }
}
