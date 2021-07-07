package com.pingpukj.tag.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pingpukj.common.annotation.Excel;
import com.pingpukj.common.core.domain.TreeEntity;

/**
 * 标签分类对象 personas_basic_category
 * 
 * @author huzunjie
 * @date 2021-06-28
 */
public class PersonasBasicCategory extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** cat_id */
    private Long catId;

    /** 分类名称 */
    @Excel(name = "分类名称")
    private String catName;

    /** 分类状态 */
    @Excel(name = "分类状态")
    private String status;

    public void setCatId(Long catId) 
    {
        this.catId = catId;
    }

    public Long getCatId() 
    {
        return catId;
    }
    public void setCatName(String catName) 
    {
        this.catName = catName;
    }

    public String getCatName() 
    {
        return catName;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("catId", getCatId())
            .append("parentId", getParentId())
            .append("ancestors", getAncestors())
            .append("catName", getCatName())
            .append("status", getStatus())
            .append("orderNum", getOrderNum())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
