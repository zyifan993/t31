package com.zyf.t31.admin.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zyf.t31.core.po.BaseTreeEntity;
import lombok.Data;

/**
 * 部门
 */
@Data
@TableName("`admin_dept`")
public class Dept extends BaseTreeEntity {

    //地址
    @TableField("`address`")
    private String address;
    //电话
    @TableField("`tel`")
    private String tel;
    //备注
    @TableField("`comment`")
    private String comment;

    /**
     * treesleect需要的属性
     * @return
     */
    public String getLabel(){
        return this.getTitle();
    }

}
