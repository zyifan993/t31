package com.zyf.t31.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zyf.t31.admin.dao.UserDao;
import com.zyf.t31.admin.po.Role;
import com.zyf.t31.admin.po.User;
import com.zyf.t31.admin.service.IUserService;
import com.zyf.t31.core.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl extends CrudServiceImpl<User> implements IUserService {
    @Override
    public List<Role> selectRoleByUser(Long id) {
        return ((UserDao) getBaseMapper()).selectRoleByUser(id);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean saveOrUpdate(User entity) {
        //添加时，Lock为false
        if (null == entity.getId()) {
            entity.setLock(false);
        }
        boolean result = super.saveOrUpdate(entity);
        ((UserDao) getBaseMapper()).deleteRoleByUser(entity.getId());
        Long[] roleIds = entity.getRoleIds();
        if (roleIds != null) {
            for (Long roleId : roleIds) {
                ((UserDao) (getBaseMapper())).insertRoleAndUser(roleId, entity.getId());
            }
        }
        return result;
    }

    @Override
    public Integer findCountByUserName(String userName) {
        return getBaseMapper().selectCount(new LambdaQueryWrapper<User>().eq(User::getUserName,userName));
    }

    @Override
    public User getUserByUserName(String userName) {
        return getBaseMapper().selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName,userName));
    }
}
