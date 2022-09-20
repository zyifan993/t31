package com.zyf.t31.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zyf.t31.admin.po.Dept;
import com.zyf.t31.admin.service.IDeptService;
import com.zyf.t31.core.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl extends CrudServiceImpl<Dept> implements IDeptService{

    @Override
    public List<Dept> list(Dept entity) {
        LambdaQueryWrapper<Dept> lambdaQueryWrapper = new LambdaQueryWrapper();
        if (null != entity.getAddress() && "" != entity.getAddress().trim()){
            lambdaQueryWrapper.like(Dept::getAddress,entity.getAddress());
        }
        if (null != entity.getTitle() && "" != entity.getTitle().trim()){
            lambdaQueryWrapper.like(Dept::getTitle,entity.getTitle());
        }
        return baseMapper.selectList(lambdaQueryWrapper);
    }
}
