package com.pingpukj.tag.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pingpukj.common.annotation.Excel;
import com.pingpukj.common.core.domain.TreeEntity;

/**
 * 属性标签对象 personas_basic_tag
 * 
 * @author huzunjie
 * @date 2021-07-09
 */
public class PersonasAttributeTag extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** tagid */
    private Long tagId;

    /** 标签名称 */
    @Excel(name = "标签名称")
    private String tagName;

    /** 标签规则 */
    @Excel(name = "标签规则")
    private String rule;

    /** 业务描述 */
    @Excel(name = "业务描述")
    private String bizDesc;

    /** 标签等级 */
    @Excel(name = "标签等级")
    private Integer level;

    /** 业务类型 */
    @Excel(name = "业务类型")
    private String industry;

    /** 删除标志 */
    private String delFlag;

    /** 标签状态 */
    @Excel(name = "标签状态")
    private Integer state;

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
    public void setLevel(Integer level) 
    {
        this.level = level;
    }

    public Integer getLevel() 
    {
        return level;
    }
    public void setIndustry(String industry) 
    {
        this.industry = industry;
    }

    public String getIndustry() 
    {
        return industry;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setState(Integer state) 
    {
        this.state = state;
    }

    public Integer getState() 
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tagId", getTagId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("tagName", getTagName())
            .append("rule", getRule())
            .append("bizDesc", getBizDesc())
            .append("level", getLevel())
            .append("industry", getIndustry())
            .append("orderNum", getOrderNum())
            .append("delFlag", getDelFlag())
            .append("state", getState())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
