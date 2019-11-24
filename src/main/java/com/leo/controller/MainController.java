package com.leo.controller;

import com.leo.common.constant.AdminConst;
import com.leo.common.result.MenuTypeEnum;
import com.leo.common.result.StatusEnum;
import com.leo.common.utils.ShiroUtil;
import com.leo.domain.Menu;
import com.leo.domain.Role;
import com.leo.domain.User;
import com.leo.service.MenuService;
import com.leo.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**后台主体内容管理类
 * @author leo
 * @date 2019/11/24 6:12
 */
@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;
    /**
     * 后台主体内容
     */
    @GetMapping("/")
    @RequiresPermissions("index")
    public String main(Model model){
        // 获取当前登录的用户
        User user = ShiroUtil.getSubject();

        // 菜单键值对(ID->菜单)
        Map<Long, Menu> keyMenu = new HashMap<>(16);

        // 管理员实时更新菜单
        if(user.getId().equals(AdminConst.ADMIN_ID)){
            List<Menu> menus = menuService.getListBySortOk();
            menus.forEach(menu -> keyMenu.put(menu.getId(), menu));
        }else{
            // 其他用户需从相应的角色中获取菜单资源
            Set<Role> roles = ShiroUtil.getSubjectRoles();
            roles.forEach(role -> {
                role.getMenus().forEach(menu -> {
                    if(menu.getStatus().equals(StatusEnum.OK.getCode())){
                        keyMenu.put(menu.getId(), menu);
                    }
                });
            });
        }

        // 封装菜单树形数据
        Map<Long, Menu> treeMenu = new HashMap<>(16);
        keyMenu.forEach((id, menu) -> {
            if(!menu.getType().equals(MenuTypeEnum.BUTTON.getCode())){
                if(keyMenu.get(menu.getPid()) != null){
                    keyMenu.get(menu.getPid()).getChildren().put(Long.valueOf(menu.getSort()), menu);
                }else{
                    if(menu.getType().equals(MenuTypeEnum.DIRECTORY.getCode())){
                        treeMenu.put(Long.valueOf(menu.getSort()), menu);
                    }
                }
            }
        });

        model.addAttribute("user", user);
        model.addAttribute("treeMenu", treeMenu);
        return "/main";
    }
}
