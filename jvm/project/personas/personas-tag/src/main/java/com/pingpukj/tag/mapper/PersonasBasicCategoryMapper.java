package com.pingpukj.tag.mapper;

import java.util.List;
import com.pingpukj.tag.domain.PersonasBasicCategory;

/**
 * 标签分类Mapper接口
 * 
 * @author huzunjie
 * @date 2021-06-28
 */
public interface PersonasBasicCategoryMapper 
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
     * 删除标签分类
     * 
     * @param catId 标签分类ID
     * @return 结果
     */
    public int deletePersonasBasicCategoryById(Long catId);

    /**
     * 批量删除标签分类
     * 
     * @param catIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePersonasBasicCategoryByIds(Long[] catIds);
}
