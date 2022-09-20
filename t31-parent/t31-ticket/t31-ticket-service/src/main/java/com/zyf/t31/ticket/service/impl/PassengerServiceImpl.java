package com.zyf.t31.ticket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zyf.t31.core.service.impl.CrudServiceImpl;
import com.zyf.t31.ticket.po.Passenger;
import com.zyf.t31.ticket.service.IPassengerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerServiceImpl extends CrudServiceImpl<Passenger> implements IPassengerService {

    @Override
    public List<Passenger> list(Passenger entity) {

        LambdaQueryWrapper<Passenger> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (null != entity.getUserId()){
            lambdaQueryWrapper.eq(Passenger::getUserId,entity.getUserId());
        }
        return baseMapper.selectList(lambdaQueryWrapper);
    }
}
