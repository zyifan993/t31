package com.zyf.t31.admin.controller;

import com.zyf.t31.admin.po.Dept;
import com.zyf.t31.admin.service.IDeptService;
import com.zyf.t31.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController<IDeptService, Dept> {

}
