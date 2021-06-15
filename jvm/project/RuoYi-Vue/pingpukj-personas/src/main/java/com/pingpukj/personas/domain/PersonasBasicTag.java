package com.pingpukj.personas.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 标签分类对象 personas_basic_tag
 * 
 * @author huzunjie
 * @date 2021-06-15
 */
public class PersonasBasicTag extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long tagId;

    /** 标签名称 */
    @Excel(name = "标签名称")
    private String tagName;

    /** 行业 */
    @Excel(name = "行业")
    private String industry;

    /** 标签规则 */
    @Excel(name = "标签规则")
    private String rule;

    /** 业务描述 */
    @Excel(name = "业务描述")
    private String business;

    /** 标签等级 */
    @Excel(name = "标签等级")
    private Long level;

    /** 状态 */
    @Excel(name = "状态")
    private Long state;

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
    public void setIndustry(String industry) 
    {
        this.industry = industry;
    }

    public String getIndustry() 
    {
        return industry;
    }
    public void setRule(String rule) 
    {
        this.rule = rule;
    }

    public String getRule() 
    {
        return rule;
    }
    public void setBusiness(String business) 
    {
        this.business = business;
    }

    public String getBusiness() 
    {
        return business;
    }
    public void setLevel(Long level) 
    {
        this.level = level;
    }

    public Long getLevel() 
    {
        return level;
    }
    public void setState(Long state) 
    {
        this.state = state;
    }

    public Long getState() 
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("tagId", getTagId())
            .append("parentId", getParentId())
            .append("tagName", getTagName())
            .append("industry", getIndustry())
            .append("rule", getRule())
            .append("business", getBusiness())
            .append("level", getLevel())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("state", getState())
            .append("remark", getRemark())
            .toString();
    }
}
