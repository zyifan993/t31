package com.zyf.t31.admin.dao;

import com.zyf.t31.admin.po.Menu;
import com.zyf.t31.core.dao.ICrudDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao extends ICrudDao<Menu> {

    /**
     * 根据角色查询菜单
     * @param roleId
     * @return
     */
    List<Menu> selectByRoleId(Long roleId);

    /**
     * 根据用户查询菜单
     * @param userId
     * @return
     */
    List<Menu> selectByUserId(Long userId);

    /**
     * 删除角色的菜单
     * @param id
     * @return
     */
    int deleteMenuByRole(Long id);

    /**
     * 关联菜单和角色
     * @param menuId
     * @param roleId
     * @return
     */
    int insertMenuAndRole(@Param("menuId") Long menuId, @Param("roleId") Long roleId);

}
