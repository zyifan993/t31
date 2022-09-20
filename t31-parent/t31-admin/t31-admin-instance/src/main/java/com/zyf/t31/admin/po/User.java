package com.zyf.t31.admin.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zyf.t31.core.po.BaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("`admin_user`")
public class User extends BaseEntity {

    /**
     * 登录名
     */
    @TableField("`user_name`")
    private String userName;

    /**
     * 真实姓名
     */
    @TableField("`real_name`")
    private String realName;

    @TableField("`password`")
    private String password;

    /**
     * 加密密码的盐
     */
    @TableField("`salt`")
    private String salt;
    @TableField("`sex`")
    private String sex;
    @TableField("`tel`")
    private String tel;
    @TableField("`email`")
    private String email;
    @TableField("`comment`")
    private String comment;
    /**
     * 是否锁定
     */
    @TableField("`lock`")
    private Boolean lock;

    @TableField("`birthday`")
    @JsonFormat(timezone = "utf-8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /**
     * 是否为部门负责人
     */
    @TableField("`principal`")
    private Boolean principal;

    //部门
    @TableField("`dept_id`")
    private Long deptId;
    //岗位
    @TableField("post_id")
    private Long postId;
    //部门名称，用于列表显示
    @TableField(exist = false)
    private String deptName;

    //用于显示用户列表
    @TableField(exist = false)
    private Long[] roleIds;
    //岗位名称，用于列表显示
    @TableField(exist = false)
    private String postName;

    public String credentialsSalt(){
        return userName + salt;
    }
}
