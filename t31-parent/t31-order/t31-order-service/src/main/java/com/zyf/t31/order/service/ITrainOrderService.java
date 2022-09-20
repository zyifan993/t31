package com.zyf.t31.order.service;

import com.zyf.t31.core.service.ICrudService;
import com.zyf.t31.order.po.TrainOrder;
import com.zyf.t31.order.vo.SeatType;

import java.util.List;

/**
 * 订单 - service
 */
public interface ITrainOrderService extends ICrudService<TrainOrder> {

    /**
     * @param numberId 车次
     * @return
     */
    List<SeatType> getSeatTypeByNumberId(Long numberId);

}
