package com.zyf.t31.order.service.impl;

import com.zyf.t31.core.service.impl.CrudServiceImpl;
import com.zyf.t31.order.dao.TrainOrderDao;
import com.zyf.t31.order.po.TrainOrder;
import com.zyf.t31.order.service.ITrainOrderService;
import com.zyf.t31.order.vo.SeatType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainOrderServiceImpl extends CrudServiceImpl<TrainOrder> implements ITrainOrderService {

    @Override
    public List<SeatType> getSeatTypeByNumberId(Long numberId) {
        return ((TrainOrderDao) getBaseMapper()).getSeatTypeByNumberId(numberId);
    }

}
