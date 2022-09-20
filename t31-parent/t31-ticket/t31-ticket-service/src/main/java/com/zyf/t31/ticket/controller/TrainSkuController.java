package com.zyf.t31.ticket.controller;

import com.zyf.t31.core.controller.BaseController;
import com.zyf.t31.ticket.po.TrainSku;
import com.zyf.t31.ticket.service.ITrainSkuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sku")
public class TrainSkuController extends BaseController<ITrainSkuService, TrainSku> {
}
