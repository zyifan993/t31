package com.zyf.t31.ticket.controller;

import com.zyf.t31.core.controller.BaseController;
import com.zyf.t31.ticket.po.TrainNumber;
import com.zyf.t31.ticket.service.ITrainNumberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/number")
public class TrainNumberController extends BaseController<ITrainNumberService, TrainNumber> {

    @PostMapping("/start-stations")
    public List<String> getStartStation(String startStation){
        return service.getStartStation(startStation);
    }

    @PostMapping("/end-stations")
    public List<String> getEndStation(String endStation){
        return service.getEndStation(endStation);
    }
}
