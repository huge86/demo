package com.pingpukj.tag.service;

import java.util.List;
import com.pingpukj.tag.domain.PersonasBasicCategory;

/**
 * 标签分类Service接口
 * 
 * @author huzunjie
 * @date 2021-06-28
 */
public interface IPersonasBasicCategoryService 
{
    /**
     * 查询标签分类
     * 
     * @param catId 标签分类ID
     * @return 标签分类
     */
    public PersonasBasicCategory selectPersonasBasicCategoryById(Long catId);

    /**
     * 查询标签分类列表
     * 
     * @param personasBasicCategory 标签分类
     * @return 标签分类集合
     */
    public List<PersonasBasicCategory> selectPersonasBasicCategoryList(PersonasBasicCategory personasBasicCategory);

    /**
     * 新增标签分类
     * 
     * @param personasBasicCategory 标签分类
     * @return 结果
     */
    public int insertPersonasBasicCategory(PersonasBasicCategory personasBasicCategory);

    /**
     * 修改标签分类
     * 
     * @param personasBasicCategory 标签分类
     * @return 结果
     */
    public int updatePersonasBasicCategory(PersonasBasicCategory personasBasicCategory);

    /**
     * 批量删除标签分类
     * 
     * @param catIds 需要删除的标签分类ID
     * @return 结果
     */
    public int deletePersonasBasicCategoryByIds(Long[] catIds);

    /**
     * 删除标签分类信息
     * 
     * @param catId 标签分类ID
     * @return 结果
     */
    public int deletePersonasBasicCategoryById(Long catId);
}
