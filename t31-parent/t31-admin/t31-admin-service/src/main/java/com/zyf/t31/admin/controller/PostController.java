package com.zyf.t31.admin.controller;

import com.zyf.t31.admin.po.Post;
import com.zyf.t31.admin.service.IPostService;
import com.zyf.t31.core.controller.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController extends BaseController<IPostService, Post> {
}
