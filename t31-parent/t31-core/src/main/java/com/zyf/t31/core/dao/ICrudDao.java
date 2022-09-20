package com.zyf.t31.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyf.t31.core.po.BaseEntity;

import java.util.List;

/**
 * Dao基类，实现标准CRUD的dao层操作
 * @author 雪松
 * @date 2021/10/28 11:58
 */
public interface ICrudDao<T extends BaseEntity> extends BaseMapper<T> {

    /**
     * 一般要是用动态sql语句查询
     * @param entity
     * @return
     */
    public List<T> selectByPage(T entity);

}
