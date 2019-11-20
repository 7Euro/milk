package com.leo.common;

/**
 * @author zql
 * @description 通用异常信息处理类
 * @Version: v1.0
 * @date 2019/11/20 15:12
 **/
public interface  CommonError {
    /**
     * 获取错误码
     * @return
     */
    public int getErrCode();

    /**
     * 获取错误信息
     * @return
     */
    public String getErrMsg();

    /**
     * 设置错误信息
     * @param errMsg
     * @return
     */
    public CommonError setErrMsg(String errMsg);
}
