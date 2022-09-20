package com.zyf.t31.order.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zyf.t31.core.po.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("`train_order`")
public class TrainOrder extends BaseEntity {

    /**
     * 发车日期
     */
    @TableField("`train_date`")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date trainDate;

    /**
     * 乘车人外键
     */
    @TableField("`passenger_id`")
    private Long passengerId;

    /**
     * 车次
     */
    @TableField("`train_number`")
    private String trainNumber;

    /**
     * 出发站编号
     */
    @TableField("`start_station_no`")
    private Integer startStationNo;

    /**
     * 出发站名称
     */
    @TableField("`start_station_name`")
    private String startStationName;

    /**
     * 到达站编号
     */
    @TableField("`end_station_no`")
    private Integer endStationNo;

    /**
     * 到达站名称
     */
    @TableField("`end_station_name`")
    private String endStationName;

    /**
     * 车厢
     */
    @TableField("`carriage_no`")
    private Integer carriageNo;

    /**
     * 座位编号
     */
    @TableField("`seat_no`")
    private Integer seatNo;

    /**
     * 座位名称
     */
    @TableField("`seat_name`")
    private String seatName;

    /**
     * 座位类型：硬座，一等座，二等座，特等座，硬卧，软卧
     */
    @TableField("`seat_type`")
    private String seatType;

    /**
     * 订单金额
     */
    @TableField("`money`")
    private Double money;

    /**
     * 下单时间
     */
    @TableField("`create_time`")
    private Date createTime;

    /**
     * 出发时间
     */
    @TableField("`start_time`")
    private Date startTime;

    /**
     * 订单状态
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 备注
     */
    @TableField("`comment`")
    private String desc;

    /**
     * 登录用户名-搜索条件
     */
    @TableField(exist = false)
    private String userName;

    /**
     * 乘车姓名（用于页面显示）
     */
    @TableField(exist = false)
    private String passengerName;
}
