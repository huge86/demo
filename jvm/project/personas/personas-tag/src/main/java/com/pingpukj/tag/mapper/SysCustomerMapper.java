package com.pingpukj.tag.mapper;

import java.util.List;
import com.pingpukj.tag.domain.SysCustomer;
import com.pingpukj.tag.domain.SysGoods;

/**
 * 客户Mapper接口
 * 
 * @author huzunjie
 * @date 2021-07-08
 */
public interface SysCustomerMapper 
{
    /**
     * 查询客户
     * 
     * @param customerId 客户ID
     * @return 客户
     */
    public SysCustomer selectSysCustomerById(Long customerId);

    /**
     * 查询客户列表
     * 
     * @param sysCustomer 客户
     * @return 客户集合
     */
    public List<SysCustomer> selectSysCustomerList(SysCustomer sysCustomer);

    /**
     * 新增客户
     * 
     * @param sysCustomer 客户
     * @return 结果
     */
    public int insertSysCustomer(SysCustomer sysCustomer);

    /**
     * 修改客户
     * 
     * @param sysCustomer 客户
     * @return 结果
     */
    public int updateSysCustomer(SysCustomer sysCustomer);

    /**
     * 删除客户
     * 
     * @param customerId 客户ID
     * @return 结果
     */
    public int deleteSysCustomerById(Long customerId);

    /**
     * 批量删除客户
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCustomerByIds(Long[] customerIds);

    /**
     * 批量删除商品
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysGoodsByCustomerIds(Long[] customerIds);
    
    /**
     * 批量新增商品
     * 
     * @param sysGoodsList 商品列表
     * @return 结果
     */
    public int batchSysGoods(List<SysGoods> sysGoodsList);
    

    /**
     * 通过客户ID删除商品信息
     * 
     * @param customerId 客户ID
     * @return 结果
     */
    public int deleteSysGoodsByCustomerId(Long customerId);
}
