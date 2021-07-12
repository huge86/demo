package com.pingpukj.tag.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pingpukj.common.annotation.Excel;
import com.pingpukj.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * Author: HuZunJie
 * Date: 2021-07-09 11:57
 * Email: 826992656@qq.com
 * Version: 0.0.1
 * Desc:
 */


public class PersonasBasicTag extends BaseEntity {
    private static final long serialVersionUID = 1L;

//    tag.tag_name, #标签名称,文本
//    tag.ancestors, #标签分类,下拉
//    tag.biz_desc, #业务含义,文本域
//    tag.rule, #标签规则,文本域

    /** 标签id */
    private Long tagId;

    /** 标签名称 */
    @Excel(name = "标签名称")
    private String tagName;

    /** 祖级列表 */
    @Excel(name = "祖级列表")
    private String ancestors;

    /** 业务含义 */
    @Excel(name = "业务含义")
    private String bizDesc;

    /** 标签规则 */
    @Excel(name = "标签规则")
    private String rule;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getBizDesc() {
        return bizDesc;
    }

    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    @Override
    public String toString() {
        return "PersonasBasicTag{" +
                "tagId=" + tagId +
                ", tagName='" + tagName + '\'' +
                ", ancestors='" + ancestors + '\'' +
                ", bizDesc='" + bizDesc + '\'' +
                ", rule='" + rule + '\'' +
                '}';
    }
}
