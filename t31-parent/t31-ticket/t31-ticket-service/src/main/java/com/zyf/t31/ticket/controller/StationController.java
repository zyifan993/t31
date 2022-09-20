package com.zyf.t31.ticket.controller;

import com.zyf.t31.core.controller.BaseController;
import com.zyf.t31.ticket.po.Station;
import com.zyf.t31.ticket.service.IStationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/station")
public class StationController extends BaseController<IStationService, Station> {

}
