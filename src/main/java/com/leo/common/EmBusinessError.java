package com.leo.common;

/**
 * @author zql
 * @description
 * @Version: v1.0
 * @date 2019/11/20 15:17
 **/
public enum EmBusinessError implements CommonError {
    //1000开头定义为通用错误类型
    PARAMETER_VALIDATION_ERROR(100001,"参数不合法"),
    UNKNOWN_ERROR(100002, "未知错误"),
    //20000开头为用户信息相关错误定义
    USER_NOT_EXIST(20001,"用户不存在"),
    USER_LOGINERROR(20002,"用户名或密码错误"),
    USER_REMOTEERROR(20003,"权限不足"),
    USER_REPERROR(20005,"重复操作"),
    ;
    private int errCode;
    private String errMsg;

    private EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        // TODO Auto-generated method stub
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        // TODO Auto-generated method stub
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }

}