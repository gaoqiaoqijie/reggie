package com.itheima.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Classname GlobalExceptionHandler
 * @Description TODO
 * @Date 2022/5/26 15:47
 * @Created by luochao
 */

@Slf4j
@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        return R.error("添加失败\n"+ex.getMessage());
    }

    @ExceptionHandler
    public R<String> exceptionHandler(CustomException ex){
        return R.error(ex.getMessage());
    }
}
