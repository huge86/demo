package com.pingpukj.tag.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.pingpukj.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.pingpukj.tag.domain.SysGoods;
import com.pingpukj.tag.mapper.SysCustomerMapper;
import com.pingpukj.tag.domain.SysCustomer;
import com.pingpukj.tag.service.ISysCustomerService;

/**
 * 客户Service业务层处理
 * 
 * @author huzunjie
 * @date 2021-07-08
 */
@Service
public class SysCustomerServiceImpl implements ISysCustomerService 
{
    @Autowired
    private SysCustomerMapper sysCustomerMapper;

    /**
     * 查询客户
     * 
     * @param customerId 客户ID
     * @return 客户
     */
    @Override
    public SysCustomer selectSysCustomerById(Long customerId)
    {
        return sysCustomerMapper.selectSysCustomerById(customerId);
    }

    /**
     * 查询客户列表
     * 
     * @param sysCustomer 客户
     * @return 客户
     */
    @Override
    public List<SysCustomer> selectSysCustomerList(SysCustomer sysCustomer)
    {
        return sysCustomerMapper.selectSysCustomerList(sysCustomer);
    }

    /**
     * 新增客户
     * 
     * @param sysCustomer 客户
     * @return 结果
     */
    @Transactional
    @Override
    public int insertSysCustomer(SysCustomer sysCustomer)
    {
        int rows = sysCustomerMapper.insertSysCustomer(sysCustomer);
        insertSysGoods(sysCustomer);
        return rows;
    }

    /**
     * 修改客户
     * 
     * @param sysCustomer 客户
     * @return 结果
     */
    @Transactional
    @Override
    public int updateSysCustomer(SysCustomer sysCustomer)
    {
        sysCustomerMapper.deleteSysGoodsByCustomerId(sysCustomer.getCustomerId());
        insertSysGoods(sysCustomer);
        return sysCustomerMapper.updateSysCustomer(sysCustomer);
    }

    /**
     * 批量删除客户
     * 
     * @param customerIds 需要删除的客户ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteSysCustomerByIds(Long[] customerIds)
    {
        sysCustomerMapper.deleteSysGoodsByCustomerIds(customerIds);
        return sysCustomerMapper.deleteSysCustomerByIds(customerIds);
    }

    /**
     * 删除客户信息
     * 
     * @param customerId 客户ID
     * @return 结果
     */
    @Override
    public int deleteSysCustomerById(Long customerId)
    {
        sysCustomerMapper.deleteSysGoodsByCustomerId(customerId);
        return sysCustomerMapper.deleteSysCustomerById(customerId);
    }

    /**
     * 新增商品信息
     * 
     * @param sysCustomer 客户对象
     */
    public void insertSysGoods(SysCustomer sysCustomer)
    {
        List<SysGoods> sysGoodsList = sysCustomer.getSysGoodsList();
        Long customerId = sysCustomer.getCustomerId();
        if (StringUtils.isNotNull(sysGoodsList))
        {
            List<SysGoods> list = new ArrayList<SysGoods>();
            for (SysGoods sysGoods : sysGoodsList)
            {
                sysGoods.setCustomerId(customerId);
                list.add(sysGoods);
            }
            if (list.size() > 0)
            {
                sysCustomerMapper.batchSysGoods(list);
            }
        }
    }
}
