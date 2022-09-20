package com.zyf.t31.ticket.service;

import com.zyf.t31.core.service.ICrudService;
import com.zyf.t31.ticket.po.Timetable;

import java.util.List;

/**
 * 时刻表
 */
public interface ITimetableService extends ICrudService<Timetable> {

    /**
     * 获取出发站
     * @param fromStation
     * @return
     */
    List<String> getFromStation(String fromStation);

    /**
     * 获取到达车站
     * @param toStation
     * @return
     */
    List<String> getToStation(String toStation);
}
