package com.zyf.t31.ticket.dao;

import com.zyf.t31.core.dao.ICrudDao;
import com.zyf.t31.ticket.po.Timetable;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TimetableDao extends ICrudDao<Timetable> {

    /**
     * 获取开始车站
     * @param startStation
     * @return
     */
    @Select("select distinct `from_station` from `train_timetable` where `from_station` like `%${fromStation}%`")
    List<String> getFromStations(String startStation);

    /**
     * 获取到达车站
     * @param toStation
     * @return
     */
    @Select("SELECT DISTINCT `to_station` from train_timetable where `to_station` like `%${toStation}%`")
    List<String> getToStations(String toStation);
}
