package com.zyf.t31.admin.controller;

import com.zyf.t31.admin.po.Role;
import com.zyf.t31.admin.po.User;
import com.zyf.t31.admin.service.IRoleService;
import com.zyf.t31.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<IRoleService, Role> {

    @Override
    public void afterEdit(Role entity) {
        // 生成用户列表, 如：1,3,4
        List<User> users = service.selectUserByRole(entity.getId());
        Long[] ids = new Long[users.size()];
        for (int i=0; i< users.size(); i++) {
            ids[i] = users.get(i).getId();
        }
        entity.setUserIds(ids);
    }
}
