package com.zyf.t31.admin.service;

import com.zyf.t31.admin.po.Menu;
import com.zyf.t31.core.service.ICrudService;

import java.util.List;

public interface IMenuService extends ICrudService<Menu> {

    /**
     * 查询用户的菜单
     * @param userId
     * @return
     */
    List<Menu> listByUser(Long userId);

    /**
     * 查询所有菜单，选中角色已有的菜单
     * @param roleId
     * @return
     */
    List<Menu> listChecked(Long roleId) ;

    /**
     * 关联角色和菜单
     * @param roleId
     * @param menuIds
     */
    void doAssignMenu2Role(Long roleId, Long[] menuIds);
}
