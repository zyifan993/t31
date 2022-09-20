package com.zyf.t31.admin.service.impl;

import com.zyf.t31.admin.po.Post;
import com.zyf.t31.admin.service.IPostService;
import com.zyf.t31.core.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl extends CrudServiceImpl<Post> implements IPostService {
}
