package com.leo.common.result;

import com.leo.common.data.URL;
import lombok.Data;

/**
 *
 * @author zll
 * @date 2019/11/20 13:08
 **/
@Data
public class Result<T> {
    /** 状态码 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 响应数据 */
    private T data;

    public static Result SAVE_SUCCESS = success("保存成功");

    /**
     * 操作成功
     * @param msg 提示信息
     * @param object 对象
     */
    public static Result success(String msg, Object object){
        Result<Object> res = new Result<>();
        res.setMsg(msg);
        res.setCode(EmBusinessResult.SUCCESS.getCode());
        res.setData(object);
        return res;
    }
    /**
     * 操作成功，返回url地址
     * @param msg 提示信息
     * @param url URL包装对象
     */
    public static Result success(String msg, URL url){
        return success(msg, url.getUrl());
    }

    /**
     * 操作成功，使用默认的提示信息
     * @param object 对象
     */
    public static Result success(Object object){
        String message = EmBusinessResult.SUCCESS.getMessage();
        return success(message, object);
    }

    /**
     * 操作成功，返回提示信息，不返回数据
     */
    public static Result success(String msg){
        Object object = null;
        return success(msg, object);
    }

    /**
     * 操作成功，不返回数据
     */
    public static Result success(){
        return success(null);
    }

    /**
     * 操作有误
     * @param code 错误码
     * @param msg 提示信息
     */
    public static Result error(Integer code, String msg){
        Result rs = new Result();
        rs.setMsg(msg);
        rs.setCode(code);
        return rs;
    }

    /**
     * 操作有误，使用默认400错误码
     * @param msg 提示信息
     */
    public static Result error(String msg){
        Integer code = EmBusinessResult.ERROR.getCode();
        return error(code, msg);
    }

    /**
     * 操作有误，只返回默认错误状态码
     */
    public static Result error(){
        return error(null);
    }
}
