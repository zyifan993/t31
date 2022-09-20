package com.zyf.t31.admin.service.impl;

import com.zyf.t31.admin.dao.RoleDao;
import com.zyf.t31.admin.po.Role;
import com.zyf.t31.admin.po.User;
import com.zyf.t31.admin.service.IRoleService;
import com.zyf.t31.core.po.BaseEntity;
import com.zyf.t31.core.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends CrudServiceImpl<Role> implements IRoleService {

    @Override
    public List<User> selectUserByRole(Long id) {
        return ((RoleDao) getBaseMapper()).selectUserByRole(id);
    }

    @Override
    public void selectUserByRoleId(Role entity) {
        if (entity != null && entity.getId() != null) {
            List<User> users = ((RoleDao) getBaseMapper()).selectUserByRole(entity.getId());
            Objects.requireNonNull(users);
            List<Long> ids = users.stream().map(BaseEntity::getId).collect(Collectors.toList());
            entity.setUserIds(new Long[ids.size()]);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public boolean saveOrUpdate(Role entity) {
        RoleDao dao = ((RoleDao) getBaseMapper());
        boolean result = super.saveOrUpdate(entity);
        // 删除角色的用户
        dao.deleteUserByRole(entity.getId());

        // 添加角色和权限关系
        Role role = (Role) entity;

        // 添加用户和角色关系
        if (null != role.getUserIds()) {
            for (Long userId : role.getUserIds()) {
                dao.insertUserAndRole(userId, entity.getId());
            }
        }
        return result;
    }

}
