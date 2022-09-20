package com.zyf.t31.admin.controller;

import com.zyf.t31.admin.po.Menu;
import com.zyf.t31.admin.service.IMenuService;
import com.zyf.t31.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController<IMenuService, Menu> {
}
