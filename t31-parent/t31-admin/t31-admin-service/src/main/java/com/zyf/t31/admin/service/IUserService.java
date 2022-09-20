package com.zyf.t31.admin.service;

import com.zyf.t31.admin.po.Role;
import com.zyf.t31.admin.po.User;
import com.zyf.t31.core.service.ICrudService;

import java.util.List;

public interface IUserService extends ICrudService<User> {

    /**
     * 根据用户id查询角色
     * @param id
     * @return
     */
    List<Role> selectRoleByUser(Long id);

    /**
     * 根据用户名查询角色个数
     * @param userName
     * @return
     */
    Integer findCountByUserName(String userName);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);
}
