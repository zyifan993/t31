package com.zyf.t31.ticket.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyf.t31.core.po.BaseEntity;
import lombok.Data;

/**
 * 乘客
 */

@Data
@TableName("train_passenger")
public class Passenger extends BaseEntity {

    @TableField("`user_id`")
    private Long userId;
    @TableField("`phone`")
    private String phone;
    @TableField("`email`")
    private String email;
    @TableField("`id_number`")
    private String idNumber;
    @TableField("`type`")
    private String type;
    @TableField("`address`")
    private String address;
    @TableField("`name`")
    private String name;
}
