package com.zyf.t31.ticket.controller;

import com.zyf.t31.core.controller.BaseController;
import com.zyf.t31.ticket.po.Timetable;
import com.zyf.t31.ticket.service.ITimetableService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 时刻表查询
 */
@RestController
@RequestMapping("/scheduler")
public class SchedulerController extends BaseController<ITimetableService, Timetable> {

    @PostMapping("/from-stations")
    public List<String> getFromStation(String fromStation){
        return service.getFromStation(fromStation);
    }

    @PostMapping("/to-stations")
    public List<String> getToStation(String toStation){
        return service.getToStation(toStation);
    }

}
