package com.pingpukj.tag.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pingpukj.tag.mapper.PersonasProductMapper;
import com.pingpukj.tag.domain.PersonasProduct;
import com.pingpukj.tag.service.IPersonasProductService;

/**
 * 产品Service业务层处理
 * 
 * @author huzunjie
 * @date 2021-06-16
 */
@Service
public class PersonasProductServiceImpl implements IPersonasProductService 
{
    @Autowired
    private PersonasProductMapper personasProductMapper;

    /**
     * 查询产品
     * 
     * @param productId 产品ID
     * @return 产品
     */
    @Override
    public PersonasProduct selectPersonasProductById(Long productId)
    {
        return personasProductMapper.selectPersonasProductById(productId);
    }

    /**
     * 查询产品列表
     * 
     * @param personasProduct 产品
     * @return 产品
     */
    @Override
    public List<PersonasProduct> selectPersonasProductList(PersonasProduct personasProduct)
    {
        return personasProductMapper.selectPersonasProductList(personasProduct);
    }

    /**
     * 新增产品
     * 
     * @param personasProduct 产品
     * @return 结果
     */
    @Override
    public int insertPersonasProduct(PersonasProduct personasProduct)
    {
        return personasProductMapper.insertPersonasProduct(personasProduct);
    }

    /**
     * 修改产品
     * 
     * @param personasProduct 产品
     * @return 结果
     */
    @Override
    public int updatePersonasProduct(PersonasProduct personasProduct)
    {
        return personasProductMapper.updatePersonasProduct(personasProduct);
    }

    /**
     * 批量删除产品
     * 
     * @param productIds 需要删除的产品ID
     * @return 结果
     */
    @Override
    public int deletePersonasProductByIds(Long[] productIds)
    {
        return personasProductMapper.deletePersonasProductByIds(productIds);
    }

    /**
     * 删除产品信息
     * 
     * @param productId 产品ID
     * @return 结果
     */
    @Override
    public int deletePersonasProductById(Long productId)
    {
        return personasProductMapper.deletePersonasProductById(productId);
    }
}
