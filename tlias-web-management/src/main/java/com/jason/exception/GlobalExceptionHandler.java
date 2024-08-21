package com.jason.exception;

import com.jason.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器
 */
@SuppressWarnings("CallToPrintStackTrace")
@RestControllerAdvice //表示当前类为"全局异常处理器" @RestControllerAdvice = @ControllerAdvice + @ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class) //指定可以捕获哪种类型的异常进行处理
    public Result ex(SQLIntegrityConstraintViolationException ex) {
        ex.printStackTrace();
        return Result.error("操作失败，部门名称不能重复");
    }

    //Spring 会匹配到专门处理该异常的第一个 @ExceptionHandler 方法
    @ExceptionHandler(Exception.class)
    public Result ex(Exception ex) {
        ex.printStackTrace();
        return Result.error("操作失败，请练习管理员");
    }
}

