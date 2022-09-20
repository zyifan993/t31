package com.zyf.t31.ticket.controller;

import com.zyf.t31.core.controller.BaseController;
import com.zyf.t31.ticket.po.Carriage;
import com.zyf.t31.ticket.service.ICarriageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 车厢
 */
@RestController
@RequestMapping("/carriage")
public class CarriageController extends BaseController<ICarriageService, Carriage> {

}
