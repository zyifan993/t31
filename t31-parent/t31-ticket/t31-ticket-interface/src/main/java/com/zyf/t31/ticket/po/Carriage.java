package com.zyf.t31.ticket.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.zyf.t31.core.po.BaseEntity;
import lombok.Data;

/**
 * @Title: 车厢
 *
 */
@Data
@TableName("`train_carriage`")
public class Carriage extends BaseEntity {

    /**
     * 车次外键
     */
    @TableField("`number_id`")
    private Long numberId;

    /**
     * 车厢号
     */
    @TableField("`carriage_no`")
    private Integer carriageNo;

    /**
     * 车厢类型
     */
    @TableField("`type`")
    private String type;

    /**
     * 座位数量
     */
    @TableField("`count`")
    private Integer count;

    /**
     * 价格
     */
    @TableField("`price`")
    private Double price;

    /**
     * 备注
     */
    @TableField("`comment`")
    private String comment;

}
