package com.pingpukj.tag.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pingpukj.common.annotation.Excel;
import com.pingpukj.common.core.domain.BaseEntity;

/**
 * 商品对象 sys_goods
 * 
 * @author huzunjie
 * @date 2021-07-08
 */
public class SysGoods extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品id */
    private Long goodsId;

    /** 客户id */
    @Excel(name = "客户id")
    private Long customerId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 商品重量 */
    @Excel(name = "商品重量")
    private Integer weight;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private BigDecimal price;

    /** 商品时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "商品时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 商品种类 */
    @Excel(name = "商品种类")
    private String type;

    public void setGoodsId(Long goodsId) 
    {
        this.goodsId = goodsId;
    }

    public Long getGoodsId() 
    {
        return goodsId;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setWeight(Integer weight) 
    {
        this.weight = weight;
    }

    public Integer getWeight() 
    {
        return weight;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setDate(Date date) 
    {
        this.date = date;
    }

    public Date getDate() 
    {
        return date;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsId", getGoodsId())
            .append("customerId", getCustomerId())
            .append("name", getName())
            .append("weight", getWeight())
            .append("price", getPrice())
            .append("date", getDate())
            .append("type", getType())
            .toString();
    }
}
