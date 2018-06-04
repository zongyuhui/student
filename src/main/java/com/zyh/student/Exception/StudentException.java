package com.zyh.student.Exception;


import com.zyh.student.enums.ResultEnum;

public class StudentException extends RuntimeException{
    private Integer code;

    public StudentException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
