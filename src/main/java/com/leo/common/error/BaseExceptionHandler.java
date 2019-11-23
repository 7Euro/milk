package com.leo.common.error;

import com.leo.common.result.EmBusinessResult;
import com.leo.common.result.Result;
import com.leo.common.utils.SpringContextUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author zql
 * @description 通用异常处理类
 * @date 2019/11/20 14:58
 * @Version: v1.0
 **/
public class BaseExceptionHandler {

    /** 拦截自定义异常 */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result resultException(BusinessException e){
        return Result.error(e.getCode(), e.getMessage());
    }

    /** 拦截表单验证异常 */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result bindException(BindException e){
        BindingResult bindingResult = e.getBindingResult();
        return Result.error(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /** 拦截未知的运行时异常 */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result runtimeException(HttpServletRequest request, Exception ex) {
        if(ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException)ex;
            return Result.error(businessException.getCode(), businessException.getMessage());
        }
        return Result.error(500, "未知错误：EX4399");
    }
}
