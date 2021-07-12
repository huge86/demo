package com.pingpukj.tag.service;

import java.util.List;
import com.pingpukj.tag.domain.SysCustomer;

/**
 * 客户Service接口
 * 
 * @author huzunjie
 * @date 2021-07-08
 */
public interface ISysCustomerService 
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
     * 批量删除客户
     * 
     * @param customerIds 需要删除的客户ID
     * @return 结果
     */
    public int deleteSysCustomerByIds(Long[] customerIds);

    /**
     * 删除客户信息
     * 
     * @param customerId 客户ID
     * @return 结果
     */
    public int deleteSysCustomerById(Long customerId);
}
