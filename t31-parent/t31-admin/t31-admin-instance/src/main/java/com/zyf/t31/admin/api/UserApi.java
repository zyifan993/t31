package com.zyf.t31.admin.api;

import com.zyf.t31.admin.po.Role;
import com.zyf.t31.admin.po.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/user")
public interface UserApi {

    @GetMapping("/get/{userName}")
    User getUserName(@PathVariable("userName") String userName);

    @GetMapping("/select-roles/{id}")
    List<Role> selectRolesByUserId(@PathVariable("id") Long id);

    @GetMapping("/add-point}")
    void addPoint(@RequestParam("point") Long point,@RequestParam("userName") String userName);
}
