package com.zyf.t31.ticket.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyf.t31.core.po.BaseEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 车次
 */
@Data
@TableName("train_number")
public class TrainNumber extends BaseEntity {

    /**
     * 编号
     */
    @TableField("`number`")
    private String number;

    /**
     * 类型
     */
    @TableField("`type`")
    private String type;

    /**
     * 车厢座位数量
     */
    @TableField("`carriage_count`")
    private Integer carriageCount;

    /**
     * 开始站
     */
    @TableField("`start_station`")
    private String startStation;

    /**
     * 结束站
     */
    @TableField("`end_station`")
    private String endStation;

    /**
     * 开始时间
     */
    @TableField("`start_time`")
    private String startTime;

    /**
     * 结束时间
     */
    @TableField("`end_time`")
    private String endTime;

    /**
     * 到达类型
     */
    @TableField("`arrive_type`")
    private String arriveType;

    /**
     * 运行时间
     */
    @TableField("`duration`")
    private String duration;

    /**
     * 运行类型
     */
    @TableField("`run_type`")
    private String runType;

    @TableField("`comment`")
    private String comment;

    /**
     * 列车车厢列表
     */
    @TableField(exist = false)
    private List<Carriage> carriages = new ArrayList<>();

    /**
     * 列车经停站
     */
    @TableField(exist = false)
    private List<Station> stations = new ArrayList<>();

    /**
     * 列车时刻表
     */
    @TableField(exist = false)
    private List<Timetable> timetables = new ArrayList<>();


}
