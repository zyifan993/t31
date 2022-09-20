package com.zyf.t31.admin.service;

import com.zyf.t31.admin.po.Role;
import com.zyf.t31.admin.po.User;
import com.zyf.t31.core.service.ICrudService;

import java.util.List;

public interface IRoleService extends ICrudService<Role> {

    /**
     * 查询角色的用户
     * @param id
     * @return
     */
    List<User> selectUserByRole(Long id);

    /**
     * 生成属于这个角色的用户列表
     * @param entity
     */
    void selectUserByRoleId(Role entity);

}
