package com.pingpukj.tag.service.impl;

import java.util.List;
import com.pingpukj.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pingpukj.tag.mapper.PersonasBasicCategoryMapper;
import com.pingpukj.tag.domain.PersonasBasicCategory;
import com.pingpukj.tag.service.IPersonasBasicCategoryService;

/**
 * 分类管理Service业务层处理
 * 
 * @author huzunjie
 * @date 2021-07-07
 */
@Service
public class PersonasBasicCategoryServiceImpl implements IPersonasBasicCategoryService 
{
    @Autowired
    private PersonasBasicCategoryMapper personasBasicCategoryMapper;

    /**
     * 查询分类管理
     * 
     * @param tagId 分类管理ID
     * @return 分类管理
     */
    @Override
    public PersonasBasicCategory selectPersonasBasicCategoryById(Long tagId)
    {
        return personasBasicCategoryMapper.selectPersonasBasicCategoryById(tagId);
    }

    /**
     * 查询分类管理列表
     * 
     * @param personasBasicCategory 分类管理
     * @return 分类管理
     */
    @Override
    public List<PersonasBasicCategory> selectPersonasBasicCategoryList(PersonasBasicCategory personasBasicCategory)
    {
        return personasBasicCategoryMapper.selectPersonasBasicCategoryList(personasBasicCategory);
    }

    /**
     * 新增分类管理
     * 
     * @param personasBasicCategory 分类管理
     * @return 结果
     */
    @Override
    public int insertPersonasBasicCategory(PersonasBasicCategory personasBasicCategory)
    {
        personasBasicCategory.setCreateTime(DateUtils.getNowDate());
        return personasBasicCategoryMapper.insertPersonasBasicCategory(personasBasicCategory);
    }

    /**
     * 修改分类管理
     * 
     * @param personasBasicCategory 分类管理
     * @return 结果
     */
    @Override
    public int updatePersonasBasicCategory(PersonasBasicCategory personasBasicCategory)
    {
        personasBasicCategory.setUpdateTime(DateUtils.getNowDate());
        return personasBasicCategoryMapper.updatePersonasBasicCategory(personasBasicCategory);
    }

    /**
     * 批量删除分类管理
     * 
     * @param tagIds 需要删除的分类管理ID
     * @return 结果
     */
    @Override
    public int deletePersonasBasicCategoryByIds(Long[] tagIds)
    {
        return personasBasicCategoryMapper.deletePersonasBasicCategoryByIds(tagIds);
    }

    /**
     * 删除分类管理信息
     * 
     * @param tagId 分类管理ID
     * @return 结果
     */
    @Override
    public int deletePersonasBasicCategoryById(Long tagId)
    {
        return personasBasicCategoryMapper.deletePersonasBasicCategoryById(tagId);
    }
}
