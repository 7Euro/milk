package com.leo.controller;

import com.leo.common.constant.AdminConst;
import com.leo.common.error.BusinessException;
import com.leo.common.result.EmBusinessResult;
import com.leo.common.result.Result;
import com.leo.common.utils.EntityBeanUtil;
import com.leo.common.utils.ShiroUtil;
import com.leo.domain.User;
import com.leo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author leo
 * @date 2019/11/20 21:31
 */
@Controller
@RequestMapping("/system/user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public String toList(Model model, User user){
        Page<User> userList = userService.getPageList(user);
        model.addAttribute("list",userList.getContent());
        return "/system/user/index";
    }

    /**
     * 保存添加/修改的数据
     * @param valid 验证对象
     * @param user 实体对象
     */
    @PostMapping("/save")
    @ResponseBody
    public Result save(User user) {

        // 验证数据是否合格
        if (user.getId() == null) {

            // 判断密码是否为空
            if (user.getPassword().isEmpty() || "".equals(user.getPassword().trim())) {
                throw new BusinessException(EmBusinessResult.PSSWORD_NOT_NULL);
            }

            // 判断两次密码是否一致
//            if (!user.getPassword().equals(valid.getConfirm())) {
//                throw new BusinessException(EmBusinessResult.PSSWORD_NOT_NULL);
//            }

            // 对密码进行加密
            String salt = ShiroUtil.getRandomSalt();
            String encrypt = ShiroUtil.encrypt(user.getPassword(), salt);
            user.setPassword(encrypt);
            user.setSalt(salt);
        }

        // 判断用户名是否重复
        if (userService.repeatByUsername(user)) {
            throw new BusinessException(EmBusinessResult.USER_EXIST);
        }

        // 复制保留无需修改的数据
        if (user.getId() != null) {
            // 不允许操作超级管理员数据
            if (user.getId().equals(AdminConst.ADMIN_ID) &&
                    !ShiroUtil.getSubject().getId().equals(AdminConst.ADMIN_ID)) {
                throw new BusinessException(EmBusinessResult.NO_ADMIN_AUTH);
            }

            User beUser = userService.getById(user.getId());
            String[] fields = {"password", "salt", "picture", "roles"};
            EntityBeanUtil.copyProperties(beUser, user, fields);
        }

        // 保存数据
        userService.save(user);
        return Result.SAVE_SUCCESS;
    }
}
