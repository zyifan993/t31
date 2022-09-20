package com.zyf.t31.admin.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyf.t31.core.po.BaseEntity;
import lombok.Data;

@Data
@TableName("`admin_role`")
public class Role extends BaseEntity {

    @TableField("`name`")
    private String name;
    @TableField("`title`")
    private String title;
    @TableField("`comment`")
    private String comment;

    /**
     * 瞬时属性，角色的用户列表，如：[1,3,4,5]
     */
    @TableField(exist = false)
    private Long[] userIds;

}

