package com.zyf.t31.ticket.controller;

import com.github.pagehelper.PageInfo;
import com.zyf.t31.admin.po.User;
import com.zyf.t31.core.controller.BaseController;
import com.zyf.t31.ticket.client.UserClient;
import com.zyf.t31.ticket.po.Passenger;
import com.zyf.t31.ticket.service.IPassengerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController extends BaseController<IPassengerService, Passenger> {

    @Autowired
    private UserClient userClient;

    @Override
    @ApiOperation(value="分页查询", notes="分页查询")
    @PostMapping("/list-page")
    public PageInfo<Passenger> listPage(Passenger entity,
                                        @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                        @RequestParam(name = "rows", defaultValue = "10", required = false) int rows) {
        // 根据用户名得到User，让后通过user_id_查询乘客
        User user = userClient.getUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        entity.setUserId(user.getId());

        PageInfo<Passenger> result = service.listPage(entity, page, rows);
        return result;
    }

    /**
     * 根据实体条件查询
     * @return
     */
    @Override
    @ApiOperation(value="查询", notes="根据实体条件查询所有")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public List<Passenger> list(Passenger entity) {
        // 根据用户名得到User，让后通过user_id_查询乘客
        User user = userClient.getUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        entity.setUserId(user.getId());

        List<Passenger> list = service.list(entity);
        return list;
    }

    @Override
    public void beforeSave(Passenger entity) {
        User user = userClient.getUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        entity.setUserId(user.getId());
    }

    @GetMapping(value = "/{id}")
    public Passenger getById(@PathVariable Long id){
        return service.getById(id);
    }

}
