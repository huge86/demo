package com.pingpukj.tag.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.pingpukj.common.annotation.Excel;
import com.pingpukj.common.core.domain.TreeEntity;

/**
 * 产品对象 personas_product
 * 
 * @author huzunjie
 * @date 2021-06-16
 */
public class PersonasProduct extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 产品id */
    private Long productId;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 产品价格 */
    @Excel(name = "产品价格")
    private BigDecimal productPrice;

    /** 产品状态 */
    @Excel(name = "产品状态")
    private String status;

    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setProductPrice(BigDecimal productPrice) 
    {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductPrice() 
    {
        return productPrice;
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
            .append("productId", getProductId())
            .append("parentId", getParentId())
            .append("productName", getProductName())
            .append("productPrice", getProductPrice())
            .append("orderNum", getOrderNum())
            .append("status", getStatus())
            .toString();
    }
}
