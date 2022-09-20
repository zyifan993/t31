package com.zyf.t31.admin.dao;

import com.zyf.t31.admin.po.Role;
import com.zyf.t31.admin.po.User;
import com.zyf.t31.core.dao.ICrudDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao extends ICrudDao<User> {

    /**
     * 删除用户角色关联
     * @param id
     * @return
     */
    int deleteRoleByUser(Long id);

    /**
     * 关联用户角色关联
     * @param roleId
     * @param userId
     * @return
     */
    int insertRoleAndUser(@Param("roleId")Long roleId,@Param("userId")Long userId);

    /**
     * 查询用户的角色
     * @param id
     * @return
     */
    List<Role> selectRoleByUser(Long id);

}
