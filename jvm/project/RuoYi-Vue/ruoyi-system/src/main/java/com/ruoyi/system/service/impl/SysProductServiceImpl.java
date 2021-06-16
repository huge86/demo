package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysProductMapper;
import com.ruoyi.system.domain.SysProduct;
import com.ruoyi.system.service.ISysProductService;

/**
 * 产品Service业务层处理
 * 
 * @author huzunjie
 * @date 2021-06-15
 */
@Service
public class SysProductServiceImpl implements ISysProductService 
{
    @Autowired
    private SysProductMapper sysProductMapper;

    /**
     * 查询产品
     * 
     * @param productId 产品ID
     * @return 产品
     */
    @Override
    public SysProduct selectSysProductById(Long productId)
    {
        return sysProductMapper.selectSysProductById(productId);
    }

    /**
     * 查询产品列表
     * 
     * @param sysProduct 产品
     * @return 产品
     */
    @Override
    public List<SysProduct> selectSysProductList(SysProduct sysProduct)
    {
        return sysProductMapper.selectSysProductList(sysProduct);
    }

    /**
     * 新增产品
     * 
     * @param sysProduct 产品
     * @return 结果
     */
    @Override
    public int insertSysProduct(SysProduct sysProduct)
    {
        return sysProductMapper.insertSysProduct(sysProduct);
    }

    /**
     * 修改产品
     * 
     * @param sysProduct 产品
     * @return 结果
     */
    @Override
    public int updateSysProduct(SysProduct sysProduct)
    {
        return sysProductMapper.updateSysProduct(sysProduct);
    }

    /**
     * 批量删除产品
     * 
     * @param productIds 需要删除的产品ID
     * @return 结果
     */
    @Override
    public int deleteSysProductByIds(Long[] productIds)
    {
        return sysProductMapper.deleteSysProductByIds(productIds);
    }

    /**
     * 删除产品信息
     * 
     * @param productId 产品ID
     * @return 结果
     */
    @Override
    public int deleteSysProductById(Long productId)
    {
        return sysProductMapper.deleteSysProductById(productId);
    }
}
