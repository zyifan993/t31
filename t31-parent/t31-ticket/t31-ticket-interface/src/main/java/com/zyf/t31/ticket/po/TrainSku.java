package com.zyf.t31.ticket.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zyf.t31.core.po.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Title: 火车票SKU
 */
@Data
@TableName("`train_sku`")
public class TrainSku extends BaseEntity {

    /**
     * 车次外键
     */
    @TableField("`number_id`")
    private Long numberId;

    /**
     * 车次
     */
    @TableField("`train_number`")
    private String trainNumber;

    /**
     * 发车日期
     */
    @TableField("`train_date`")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date trainDate;

    /**
     * 始发站
     */
    @TableField("`start_station`")
    private String startStation;

    /**
     * 终点站
     */
    @TableField("`end_station`")
    private String endStation;

    /**
     * 出发站
     */
    @TableField("`from_station`")
    private String fromStation;

    /**
     * 到达站
     */
    @TableField("`to_station`")
    private String toStation;

    /**
     * 发车时间
     */
    @TableField("`start_time`")
    private String startTime;

    /**
     * 到达时间
     */
    @TableField("`arrival_time`")
    private String arrivalTime;

    /**
     * 历时时长
     */
    @TableField("`duration`")
    private String duration;

    /**
     * 硬座
     */
    @TableField("`hard_seat_stock`")
    private Integer hardSeatStock;

    /**
     * 一等座
     */
    @TableField("`first_seat_stock`")
    private Integer firstSeatStock;

    /**
     * 二等座
     */
    @TableField("`second_seat_stock`")
    private Integer secondSeatStock;

    /**
     * 特等座
     */
    @TableField("`special_seat_stock`")
    private Integer specialSeatStock;

    /**
     * 硬卧
     */
    @TableField("`hard_sleeper_stock`")
    private Integer hardSleeperStock;

    /**
     * 软卧
     */
    @TableField("`soft_sleeper_stock`")
    private Integer softSleeperStock;

}
