package com.pingpukj.tag.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pingpukj.common.annotation.Excel;
import com.pingpukj.common.core.domain.TreeEntity;

/**
 * 基础标签对象 personas_basic_tag
 * 
 * @author huzunjie
 * @date 2021-06-28
 */
public class PersonasBasicTag extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** tagid */
    private Long tagId;

    /** 标签名称 */
    @Excel(name = "标签名称")
    private String tagName;

    /** 标签状态 */
    @Excel(name = "标签状态")
    private String status;

    /** 删除标志 */
    @Excel(name = "删除标志")
    private String delFlag;

    /** 标签规则 */
    @Excel(name = "标签规则")
    private String rule;

    /** 业务描述 */
    @Excel(name = "业务描述")
    private String bizDesc;

    public void setTagId(Long tagId) 
    {
        this.tagId = tagId;
    }

    public Long getTagId() 
    {
        return tagId;
    }
    public void setTagName(String tagName) 
    {
        this.tagName = tagName;
    }

    public String getTagName() 
    {
        return tagName;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setRule(String rule) 
    {
        this.rule = rule;
    }

    public String getRule() 
    {
        return rule;
    }
    public void setBizDesc(String bizDesc) 
    {
        this.bizDesc = bizDesc;
    }

    public String getBizDesc() 
    {
        return bizDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tagId", getTagId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("tagName", getTagName())
            .append("status", getStatus())
            .append("orderNum", getOrderNum())
            .append("delFlag", getDelFlag())
            .append("rule", getRule())
            .append("bizDesc", getBizDesc())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
