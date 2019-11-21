package com.leo.common.utils.result;

/**
 * @author zql
 * @description 通用异常信息处理类
 * @Version: v1.0
 * @date 2019/11/20 15:12
 **/
public interface CommonResult {
    /**
     * 获取错误码
     * @return
     */
    public int getCode();

    /**
     * 获取错误信息
     * @return
     */
    public String getMsg();

    /**
     * 设置错误信息
     * @param msg
     * @return
     */
    public CommonResult setMsg(String msg);
}
