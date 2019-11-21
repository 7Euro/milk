package com.leo.common.utils.error;

import com.leo.common.utils.result.CommonResult;

/**
 * @author zql
 * @description
 * @Version: v1.0
 * @date 2019/11/20 15:14
 **/
public class BusinessException extends Exception implements CommonResult {
    private static final long serialVersionUID = 1L;
    /**
     * 联一个CommonError类
     */
    private CommonResult commonResult;

    /**
     * 直接接收EmBusinessError的传参用于构造业务异常、
     * @param commonResult
     */
    public BusinessException(CommonResult commonResult) {
        super();
        this.commonResult = commonResult;
    }

    /**
     * 接收自定义errMsg的方式构造业务异常
     * @param commonResult
     * @param msg
     */
    public BusinessException(CommonResult commonResult, String msg) {
        super();
        this.commonResult = commonResult;
        this.commonResult.setMsg(msg);
    }
    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMsg() {
        return null;
    }

    @Override
    public CommonResult setMsg(String errMsg) {
        return null;
    }
}
