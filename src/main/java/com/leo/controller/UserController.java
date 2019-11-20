package com.leo.controller;

import com.leo.common.BusinessException;
import com.leo.common.EmBusinessError;
import com.leo.common.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * @author leo
 * @date 2019/11/20 21:31
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public Result login(@RequestParam(name="userName")String userName,
                        @RequestParam(name="encrptPassword")String encrptPassword) throws BusinessException {
        //参数校验
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(encrptPassword)) {
            throw new BusinessException(EmBusinessError.USER_LOGINERROR);
        }
        return Result.create(null);
    }

    /**
     * 跳转到登录页面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
            return "/login.html";
    }

}
