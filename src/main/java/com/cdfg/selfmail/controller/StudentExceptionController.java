package com.cdfg.selfmail.controller;


import cn.cdfg.exceptionHandle.SelfMailNotFoundException;
import com.cdfg.selfmail.pojo.until.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StudentExceptionController {
    @ExceptionHandler(value = SelfMailNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> exception (SelfMailNotFoundException exception){

        return new Result<String>(exception.getRetCode(),exception.getMsg(),"");
    }
}
