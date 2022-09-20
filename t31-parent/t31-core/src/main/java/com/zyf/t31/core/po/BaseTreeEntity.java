package com.zyf.t31.core.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @Title: 树状结构实体类父类，包括父节点id等
 * @Author 雪松
 * @Date 2021/10/28 11:56
 */
@Data
@JsonIgnoreProperties(value = {"handler"})
public class BaseTreeEntity extends BaseEntity {

    /**
     * 排序字段
     */
    @TableField("`sequence`")
    private Integer sequence;

    /**
     * 父节点id
     */
    @TableField("`parent_id`")
    private Long parentId;

    /**
     * 节点名称
     */
    @TableField("`title`")
    private String title;

    /**
     * 是否展开节点
     */
    @TableField("`is_expand`")
    private Boolean expand = false;

}
