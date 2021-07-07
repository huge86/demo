package com.pingpukj.tag.service.impl;

import java.util.List;
import com.pingpukj.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pingpukj.tag.mapper.PersonasBasicCategoryMapper;
import com.pingpukj.tag.domain.PersonasBasicCategory;
import com.pingpukj.tag.service.IPersonasBasicCategoryService;

/**
 * 标签分类Service业务层处理
 * 
 * @author huzunjie
 * @date 2021-06-28
 */
@Service
public class PersonasBasicCategoryServiceImpl implements IPersonasBasicCategoryService 
{
    @Autowired
    private PersonasBasicCategoryMapper personasBasicCategoryMapper;

    /**
     * 查询标签分类
     * 
     * @param catId 标签分类ID
     * @return 标签分类
     */
    @Override
    public PersonasBasicCategory selectPersonasBasicCategoryById(Long catId)
    {
        return personasBasicCategoryMapper.selectPersonasBasicCategoryById(catId);
    }

    /**
     * 查询标签分类列表
     * 
     * @param personasBasicCategory 标签分类
     * @return 标签分类
     */
    @Override
    public List<PersonasBasicCategory> selectPersonasBasicCategoryList(PersonasBasicCategory personasBasicCategory)
    {
        return personasBasicCategoryMapper.selectPersonasBasicCategoryList(personasBasicCategory);
    }

    /**
     * 新增标签分类
     * 
     * @param personasBasicCategory 标签分类
     * @return 结果
     */
    @Override
    public int insertPersonasBasicCategory(PersonasBasicCategory personasBasicCategory)
    {
        personasBasicCategory.setCreateTime(DateUtils.getNowDate());
        return personasBasicCategoryMapper.insertPersonasBasicCategory(personasBasicCategory);
    }

    /**
     * 修改标签分类
     * 
     * @param personasBasicCategory 标签分类
     * @return 结果
     */
    @Override
    public int updatePersonasBasicCategory(PersonasBasicCategory personasBasicCategory)
    {
        personasBasicCategory.setUpdateTime(DateUtils.getNowDate());
        return personasBasicCategoryMapper.updatePersonasBasicCategory(personasBasicCategory);
    }

    /**
     * 批量删除标签分类
     * 
     * @param catIds 需要删除的标签分类ID
     * @return 结果
     */
    @Override
    public int deletePersonasBasicCategoryByIds(Long[] catIds)
    {
        return personasBasicCategoryMapper.deletePersonasBasicCategoryByIds(catIds);
    }

    /**
     * 删除标签分类信息
     * 
     * @param catId 标签分类ID
     * @return 结果
     */
    @Override
    public int deletePersonasBasicCategoryById(Long catId)
    {
        return personasBasicCategoryMapper.deletePersonasBasicCategoryById(catId);
    }
}
