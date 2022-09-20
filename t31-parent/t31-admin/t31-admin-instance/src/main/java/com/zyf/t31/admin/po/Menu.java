package com.zyf.t31.admin.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyf.t31.core.po.BaseTreeEntity;
import lombok.Data;

@Data
@TableName("`admin_menu`")
public class Menu extends BaseTreeEntity {

    //路径
    @TableField("`path`")
    private String path;
    //名称
    @TableField("`name`")
    private String name;
    //组件
    @TableField("`component`")
    private String component;

    //设为true后在左侧菜单不会显示该页面选项
    @TableField("`hide_in_menu`")
    private Boolean hideInMenu = false;

    @TableField("`not_cache`")
    //设为true页面不会缓存
    private Boolean NotCache = false;

    @TableField("`icon`")
    //还页面在左侧菜单，面包屑和标签导航处显示的图标，如果是自定义图标，需要在图标名称前加上划线'_'
    private String icon;

    @TableField(exist = false)
    //查询条件，拥有查询角色的菜单
    private Long roleId;

    @TableField(exist = false)
    //查询条件，拥有查询用户的菜单
    private Long userId;

    @TableField(exist = false)
    //角色选择菜单，选中角色已有的菜单
    private Boolean selected;

    @TableField(exist = false)
    //角色选择菜单，选中角色已有的菜单
    private Boolean checked;

}
