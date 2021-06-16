package com.pingpukj.tag.service;

import java.util.List;
import com.pingpukj.tag.domain.PersonasProduct;

/**
 * 产品Service接口
 * 
 * @author huzunjie
 * @date 2021-06-16
 */
public interface IPersonasProductService 
{
    /**
     * 查询产品
     * 
     * @param productId 产品ID
     * @return 产品
     */
    public PersonasProduct selectPersonasProductById(Long productId);

    /**
     * 查询产品列表
     * 
     * @param personasProduct 产品
     * @return 产品集合
     */
    public List<PersonasProduct> selectPersonasProductList(PersonasProduct personasProduct);

    /**
     * 新增产品
     * 
     * @param personasProduct 产品
     * @return 结果
     */
    public int insertPersonasProduct(PersonasProduct personasProduct);

    /**
     * 修改产品
     * 
     * @param personasProduct 产品
     * @return 结果
     */
    public int updatePersonasProduct(PersonasProduct personasProduct);

    /**
     * 批量删除产品
     * 
     * @param productIds 需要删除的产品ID
     * @return 结果
     */
    public int deletePersonasProductByIds(Long[] productIds);

    /**
     * 删除产品信息
     * 
     * @param productId 产品ID
     * @return 结果
     */
    public int deletePersonasProductById(Long productId);
}
