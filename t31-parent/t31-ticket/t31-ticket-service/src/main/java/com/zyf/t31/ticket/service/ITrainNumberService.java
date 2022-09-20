package com.zyf.t31.ticket.service;

import com.zyf.t31.core.service.ICrudService;
import com.zyf.t31.ticket.po.TrainNumber;

import java.util.List;

public interface ITrainNumberService extends ICrudService<TrainNumber> {

    /**
     * 获取开始车站
     * @param startStation
     * @return
     */
    List<String> getStartStation(String startStation);

    /**
     * 获取终点站
     * @param endStation
     * @return
     */
    List<String> getEndStation(String endStation);
}
