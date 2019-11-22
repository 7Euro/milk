package com.leo.controller;

import com.leo.common.error.BusinessException;
import com.leo.common.result.EmBusinessResult;
import com.leo.common.result.Result;
import com.leo.common.utils.ShiroUtil;
import com.leo.common.utils.SpringContextUtil;
import com.leo.common.data.URL;
import com.leo.config.ProjectProperties;
import com.leo.domain.User;
import com.leo.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private RoleService roleService;
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
        // 判断验证码是否正确
        ProjectProperties properties = SpringContextUtil.getBean(ProjectProperties.class);
        //使用shiro进行登录
        // 1.获取Subject主体对象
        Subject subject = SecurityUtils.getSubject();
        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //3.执行登录
        try {
            // 判断是否自动登录
            if (rememberMe != null) {
                token.setRememberMe(true);
            } else {
                token.setRememberMe(false);
            }
            subject.login(token);
            //判断是否拥有后台角色
            User user = ShiroUtil.getSubject();
            if (roleService.existsUserOk(user.getId())) {
                return Result.success("登录成功",new URL("/"));
            }  else {
                //退出
                SecurityUtils.getSubject().logout();
                return  Result.error("您不是后台管理员");
            }
        } catch (LockedAccountException e) {
            return Result.error("该账号已被冻结");
        } catch (AuthenticationException e) {
            return Result.error("用户名或密码错误");
        }
    }

    /**
     * 权限不足页面
     */
    @GetMapping("/noAuth")
    public String noAuth() {
        return "/system/main/noAuth";
    }
}
