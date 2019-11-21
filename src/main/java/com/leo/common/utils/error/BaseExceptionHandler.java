package com.leo.common.utils.error;

import com.leo.common.utils.result.EmBusinessResult;
import com.leo.common.utils.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zql
 * @description 通用异常处理类
 * @date 2019/11/20 14:58
 * @Version: v1.0
 **/
public class BaseExceptionHandler {

    /**
     * 定义exceptionhandler解决未被controller层吸收的exception
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Result exception(HttpServletRequest request, Exception  ex) {
        if(ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException)ex;
            return Result.error(businessException.getCode(),businessException.getMsg());
        }else {
            return Result.error(EmBusinessResult.UNKNOWN_ERROR.getCode(),EmBusinessResult.UNKNOWN_ERROR.getMsg());
        }
    }
}
