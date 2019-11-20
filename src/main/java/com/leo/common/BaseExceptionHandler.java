package com.leo.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
        Map<String,Object> responseData = new HashMap<>();
        if(ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException)ex;
            responseData.put("errCode", businessException.getErrCode());
            responseData.put("errMsg", businessException.getErrMsg());
        }else {
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }
        return Result.create(responseData,false);
    }
}
