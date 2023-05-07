package com.szw.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Szw 2001
 * @Date 2023/4/29 22:04
 * @Slogn 致未来的你！
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private Integer code;
    private String message;
    private T data;

    //成功
    public static <T> Result<T> success(){
        return new Result<>(20000,"成功",null);
    }

    public static <T> Result<T> success(String message){
        return new Result<>(20000,message,null);
    }

    public static <T> Result<T> success(T data){
        return new Result<>(20000,"成功",data);
    }

    public static <T> Result<T> success(String message,T data){
        return new Result<>(20000,message,data);
    }



    //失败
    public static <T> Result<T> fail(){
        return new Result<>(20001,"失败",null);
    }

    public static <T> Result<T> fail(Integer code){
        return new Result<>(code,"失败",null);
    }

    public static <T> Result<T> fail(String message){
        return new Result<>(20001,message,null);
    }

    public static <T> Result<T> fail(Integer code,String message){
        return new Result<>(code,message,null);
    }
}
