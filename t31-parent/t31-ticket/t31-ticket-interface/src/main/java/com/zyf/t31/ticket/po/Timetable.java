package com.zyf.t31.ticket.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyf.t31.core.po.BaseEntity;
import lombok.Data;

/**
 * 列车时间表
 */
@Data
@TableName("train_timetable")
public class Timetable extends BaseEntity {

    /**
     * 车次外键
     */
    @TableField("`number_id`")
    private Long numberId;

    /**
     *始发站
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
     * 历经时间
     */
    @TableField("`duration`")
    private String duration;
    /**
     *意见
     */
    @TableField("`comment`")
    private String comment;

    /**
     * 车次
     */
    @TableField("train_number")
    private String trainNumber;

}
