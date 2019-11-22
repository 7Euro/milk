package com.leo.common.error;

import com.leo.common.result.CommonResult;
import com.leo.common.result.EmBusinessResult;
import lombok.Getter;

/**
 * @author zql
 * @description
 * @Version: v1.0
 * @date 2019/11/20 15:14
 **/
@Getter
public class BusinessException extends RuntimeException{



    private Integer code;

    /**
     * 统一异常处理
     * @param resultEnum 状态枚举
     */
    public BusinessException(EmBusinessResult resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    /**
     * 统一异常处理
     * @param resultEnum 枚举类型，需要实现结果枚举接口
     */
    public BusinessException(CommonResult resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    /**
     * 统一异常处理
     * @param code 状态码
     * @param message 提示信息
     */
    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
