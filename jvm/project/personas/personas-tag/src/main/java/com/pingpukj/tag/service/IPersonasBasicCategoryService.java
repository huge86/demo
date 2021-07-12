package com.pingpukj.tag.service;

import java.util.List;
import com.pingpukj.tag.domain.PersonasBasicCategory;

/**
 * 分类管理Service接口
 * 
 * @author huzunjie
 * @date 2021-07-07
 */
public interface IPersonasBasicCategoryService 
{
    /**
     * 查询分类管理
     * 
     * @param tagId 分类管理ID
     * @return 分类管理
     */
    public PersonasBasicCategory selectPersonasBasicCategoryById(Long tagId);

    /**
     * 查询分类管理列表
     * 
     * @param personasBasicCategory 分类管理
     * @return 分类管理集合
     */
    public List<PersonasBasicCategory> selectPersonasBasicCategoryList(PersonasBasicCategory personasBasicCategory);

    /**
     * 新增分类管理
     * 
     * @param personasBasicCategory 分类管理
     * @return 结果
     */
    public int insertPersonasBasicCategory(PersonasBasicCategory personasBasicCategory);

    /**
     * 修改分类管理
     * 
     * @param personasBasicCategory 分类管理
     * @return 结果
     */
    public int updatePersonasBasicCategory(PersonasBasicCategory personasBasicCategory);

    /**
     * 批量删除分类管理
     * 
     * @param tagIds 需要删除的分类管理ID
     * @return 结果
     */
    public int deletePersonasBasicCategoryByIds(Long[] tagIds);

    /**
     * 删除分类管理信息
     * 
     * @param tagId 分类管理ID
     * @return 结果
     */
    public int deletePersonasBasicCategoryById(Long tagId);
}
