package com.zyf.t31.ticket.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyf.t31.core.po.BaseEntity;
import lombok.Data;

/**
 * 经停站
 */
@Data
@TableName("train_station")
public class Station extends BaseEntity {

    /**
     * 车次外键
     */
    @TableField("`number_id`")
    private Long numberId;

    /**
     * 到达类型
     */
    @TableField("`arrive_type`")
    private String arriveType;

    /**
     * 到达时间
     */
    @TableField("`arrive_time`")
    private String arriveTime;

    /**
     * 开始时间
     */
    @TableField("`start_time`")
    private String startTime;

    /**
     * 持续时间
     */
    @TableField("duration")
    private String duration;

    /**
     * 车站编号
     */
    @TableField("`num`")
    private Integer num;
    @TableField("`name`")
    private String name;
    /**
     * 评论
     */
    @TableField("`comment`")
    private String comment;

}
