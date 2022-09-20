package com.zyf.t31.ticket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zyf.t31.core.service.impl.CrudServiceImpl;
import com.zyf.t31.ticket.dao.TrainNumberDao;
import com.zyf.t31.ticket.po.TrainNumber;
import com.zyf.t31.ticket.service.ITrainNumberService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainNumberServiceImpl extends CrudServiceImpl<TrainNumber> implements ITrainNumberService {

    @Override
    public List<TrainNumber> list(TrainNumber entity) {
        LambdaQueryWrapper<TrainNumber> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (entity.getNumber() != null && !"".equals(entity.getNumber().trim())){
            lambdaQueryWrapper.like(TrainNumber::getNumber,entity.getNumber());
        }
        return getBaseMapper().selectList(lambdaQueryWrapper);
    }

    @Override
    public List<String> getStartStation(String startStation) {
        return ((TrainNumberDao)getBaseMapper()).getEndStations(startStation);
    }

    @Override
    public List<String> getEndStation(String endStation) {
        return ((TrainNumberDao)getBaseMapper()).getEndStations(endStation);
    }
}
