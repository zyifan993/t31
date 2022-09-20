package com.zyf.t31.admin.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyf.t31.core.po.BaseEntity;
import lombok.Data;

/**
 * 岗位
 */
@Data
@TableName("`admin_post`")
public class Post extends BaseEntity {

    @TableField("`name`")
    private String name;
    @TableField("`title`")
    private String title;
    @TableField("`comment`")
    private String comment;
}
