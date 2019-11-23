package com.leo.common.result;

/**
 * @author zql
 * @description 通用异常信息处理类
 * @Version: v1.0
 * @date 2019/11/20 15:12
 **/
public interface CommonResult {
    /**
     * 获取状态编码
     * @return 编码
     */
    Integer getCode();

    /**
     * 获取提示信息
     * @return 提示信息
     */
    String getMessage();
}
