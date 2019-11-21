package com.leo.common.utils.result;

import com.leo.common.utils.result.CommonResult;
import lombok.Getter;

/**
 * @author zql
 * @description
 * @Version: v1.0
 * @date 2019/11/20 15:17
 **/
@Getter
public enum EmBusinessResult implements CommonResult {
    /**
     * 通用状态
     */
    SUCCESS(200, "成功"),
    ERROR(400, "错误"),
    //1000开头定义为通用错误类型
    PARAMETER_VALIDATION_ERROR(100001,"参数不合法"),
    UNKNOWN_ERROR(100002, "未知错误"),
    //20000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001,"用户不存在"),
    USER_LOGINERROR(20002,"用户名或密码错误"),
    USER_REMOTEERROR(20003,"权限不足"),
    USER_REPERROR(20005,"重复操作"),
    USER_ACCOUNT_FREEZE(20006,"账号冻结"),
    ;
    private int code;
    private String msg;

    private EmBusinessResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public CommonResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

}