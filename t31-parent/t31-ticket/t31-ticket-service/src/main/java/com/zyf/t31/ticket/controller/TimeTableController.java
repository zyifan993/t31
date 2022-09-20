package com.zyf.t31.ticket.controller;

import com.zyf.t31.core.controller.BaseController;
import com.zyf.t31.ticket.po.Timetable;
import com.zyf.t31.ticket.service.ITimetableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 时刻表
 */
@RestController
@RequestMapping("/timetable")
public class TimeTableController extends BaseController<ITimetableService, Timetable> {
}
