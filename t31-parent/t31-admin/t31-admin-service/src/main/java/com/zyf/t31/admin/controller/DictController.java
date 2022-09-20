package com.zyf.t31.admin.controller;


import com.zyf.t31.admin.po.Dict;
import com.zyf.t31.admin.service.IDictService;
import com.zyf.t31.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dict")
public class DictController extends BaseController<IDictService, Dict> {


}

