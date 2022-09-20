package com.zyf.t31.admin.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyf.t31.core.po.BaseEntity;
import lombok.Data;

/**
 * 字典
 */
@Data
@TableName("`admin_dict`")
public class Dict extends BaseEntity {

    //数据值
    @TableField("`value`")
    private String value;
    //标签名
    @TableField("`label`")
    private String label;
    //类型
    @TableField("`type`")
    private String type;
    //描述
    @TableField("`description`")
    private String description;
}
