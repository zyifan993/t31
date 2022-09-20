package com.zyf.t31.ticket.dao;

import com.zyf.t31.core.dao.ICrudDao;
import com.zyf.t31.ticket.po.TrainNumber;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TrainNumberDao extends ICrudDao<TrainNumber> {

    /**
     * 获取始发站
     * @param startStation
     * @return
     */
    @Select("SELECT DISTINCT `start_station` from `train_number` where `start_station` like '%${startStation}%'")
    List<String> getStartStations(String startStation);

    /**
     * 获取终点站
     * @param endStation
     * @return
     */
    @Select("select distinct `end_station` from `train_number` where `end_station` like '%${endStation}%'")
    List<String> getEndStations(String endStation);

}
