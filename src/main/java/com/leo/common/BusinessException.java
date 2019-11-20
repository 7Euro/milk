package com.leo.common;

/**
 * @author zql
 * @description
 * @Version: v1.0
 * @date 2019/11/20 15:14
 **/
public class BusinessException extends Exception implements CommonError {
    private static final long serialVersionUID = 1L;
    /**
     * 联一个CommonError类
     */
    private CommonError commonError;

    /**
     * 直接接收EmBusinessError的传参用于构造业务异常、
     * @param commonError
     */
    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    /**
     * 接收自定义errMsg的方式构造业务异常
     * @param commonError
     * @param errMsg
     */
    public BusinessException(CommonError commonError, String errMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }
    @Override
    public int getErrCode() {
        return 0;
    }

    @Override
    public String getErrMsg() {
        return null;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        return null;
    }
}
