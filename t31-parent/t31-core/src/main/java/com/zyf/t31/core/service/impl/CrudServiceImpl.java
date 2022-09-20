package com.zyf.t31.core.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyf.t31.core.dao.ICrudDao;
import com.zyf.t31.core.po.BaseEntity;
import com.zyf.t31.core.service.ICrudService;

import java.util.List;

/**
 * @author 雪松
 * @date 2021/10/28 12:00
 */
public class CrudServiceImpl<T extends BaseEntity> extends ServiceImpl<ICrudDao<T>, T> implements ICrudService<T> {

    @Override
    public PageInfo<T> listPage(T entity, int pageNum, int pageSize) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> {
            baseMapper.selectByPage(entity);
        });
    }

    @Override
    public List<T> list(T entity) {
        return getBaseMapper().selectList(Wrappers.emptyWrapper());
    }

}
