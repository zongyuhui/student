package com.zyh.student.handle;


import com.zyh.student.Exception.StudentException;
import com.zyh.student.Utils.ResultUtil;
import com.zyh.student.domain.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandle {
    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof StudentException) {
            StudentException girlException = (StudentException) e;
            return ResultUtil.error(girlException.getCode(), girlException.getMessage());
        } else {
            return ResultUtil.error(400, "未知错误");
        }
    }
}
