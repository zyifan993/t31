package com.zyf.t31.order.controller;

import com.github.pagehelper.PageInfo;
import com.zyf.t31.core.controller.BaseController;
import com.zyf.t31.order.po.TrainOrder;
import com.zyf.t31.order.service.ITrainOrderService;
import com.zyf.t31.order.vo.SeatType;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 乘客管理 - Controller
 * @author 雪松
 * @date 2021/9/22 13:26
 */
@RestController
@RequestMapping("/order")
public class TrainOrderController extends BaseController<ITrainOrderService, TrainOrder> {

    @Override
    @ApiOperation(value="分页查询", notes="分页查询")
    @PostMapping("/list-page")
    public PageInfo<TrainOrder> listPage(TrainOrder entity,
                                         @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                         @RequestParam(name = "rows", defaultValue = "10", required = false) int rows) {
        entity.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());

        PageInfo<TrainOrder> result = service.listPage(entity, page, rows);
        return result;
    }

    @ApiOperation(value="根据车次查询座位类型", notes="根据车次查询座位类型")
    @GetMapping("/list-seat-type/{numberId}")
    public List<SeatType> getSeatTypeByNumberId(@PathVariable("numberId") Long numberId) {
        return service.getSeatTypeByNumberId(numberId);
    }

}
