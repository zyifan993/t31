package com.zyf.t31.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.zyf.t31.core.po.BaseEntity;

import java.util.List;

/**
 * @author 雪松
 * @date 2021/10/28 11:59
 */
public interface ICrudService<T extends BaseEntity> extends IService<T> {

    /**
     * 分页查询方法
     * @param entity
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<T> listPage(T entity, int pageNum, int pageSize);

    /**
     * 查询所有方法
     * @param entity
     * @return
     */
    List<T> list(T entity);

}
