package com.leo.controller;

import com.leo.common.utils.error.BusinessException;
import com.leo.common.utils.result.EmBusinessResult;
import com.leo.common.utils.result.Result;
import com.leo.common.utils.SpringContextUtil;
import com.leo.common.utils.data.URL;
import com.leo.config.ProjectProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;


/**
 * @author zql
 * @description
 * @Version: v1.0
 * @date 2019/11/21 11:02
 **/
@Controller
public class LoginController {

    /**
     * 跳转到登录页面
     * @param model
     * @return
     */
    @GetMapping("/login")
    public String toLogin(Model model){
        ProjectProperties properties = SpringContextUtil.getBean(ProjectProperties.class);
        model.addAttribute("isCaptcha", properties.isCaptchaOpen());
        return "/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(String username, String password, String captcha, String rememberMe) throws BusinessException {
        //判断账户密码是否为空
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessResult.USER_LOGINERROR);
        }
        /*// 判断验证码是否正确
        ProjectProperties properties = SpringContextUtil.getBean(ProjectProperties.class);
        //使用shiro进行登录
        // 1.获取Subject主体对象
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //执行登录
        try {
            // 判断是否自动登录
            if (rememberMe != null) {
                token.setRememberMe(true);
            } else {
                token.setRememberMe(false);
            }
            subject.login(token);
        }catch (LockedAccountException e) {
            throw new BusinessException(EmBusinessError.USER_ACCOUNT_FREEZE);
        }*/
        return Result.success("登录成功","/");
    }


}
