package com.zyf.t31.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyf.t31.admin.dao.MenuDao;
import com.zyf.t31.admin.po.Menu;
import com.zyf.t31.admin.service.IMenuService;
import com.zyf.t31.core.po.BaseEntity;
import com.zyf.t31.core.po.BaseTreeEntity;
import com.zyf.t31.core.service.impl.CrudServiceImpl;
import com.zyf.t31.core.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MenuServiceImpl extends CrudServiceImpl<Menu> implements IMenuService {
    @Override
    public List<Menu> listByUser(Long userId) {
        return ((MenuDao)getBaseMapper()).selectByUserId(userId);
    }

    @Override
    public List<Menu> listChecked(Long roleId) {
        //查询全部菜单
        List<Menu> menus = this.list();
        //根据角色Id查询菜单
        List<Menu> roleMenus = ((MenuDao) getBaseMapper()).selectByRoleId(roleId);
        updateChecked(menus,roleMenus);
        return menus;
    }
    private <T extends BaseTreeEntity> void updateChecked(List<T> menus,List<Menu> roleMenus){
        for (BaseTreeEntity m1 : menus) {
            for (Menu m2 : roleMenus) {
                if (m1.getId().equals(m2.getId())){
                    ((Menu)m1).setSelected(true);
                }
            }
        }
    }

    @Transactional(readOnly = false)
    @Override
    public void doAssignMenu2Role(Long roleId, Long[] menuIds) {
        MenuDao dao =  ((MenuDao)getBaseMapper());
        dao.deleteMenuByRole(roleId);
        for (Long menuId : menuIds) {
            dao.insertMenuAndRole(menuId,roleId);
        }
    }

    @Override
    public List<Menu> list(Menu entity) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(entity.getName())){
            queryWrapper.like("`name`",entity.getName());
        }
        if (null != entity.getTitle() && "" != entity.getTitle().trim()){
            queryWrapper.like("`title`",entity.getTitle());
        }
        return baseMapper.selectList(queryWrapper);
    }
}
