package com.pingpukj.personas.service.impl;

import java.util.List;
import com.pingpukj.personas.domain.PersonasBasicTag;

/**
 * 标签分类Service接口
 * 
 * @author huzunjie
 * @date 2021-06-15
 */
public interface IPersonasBasicTagService 
{
    /**
     * 查询标签分类
     * 
     * @param tagId 标签分类ID
     * @return 标签分类
     */
    public PersonasBasicTag selectPersonasBasicTagById(Long tagId);

    /**
     * 查询标签分类列表
     * 
     * @param personasBasicTag 标签分类
     * @return 标签分类集合
     */
    public List<PersonasBasicTag> selectPersonasBasicTagList(PersonasBasicTag personasBasicTag);

    /**
     * 新增标签分类
     * 
     * @param personasBasicTag 标签分类
     * @return 结果
     */
    public int insertPersonasBasicTag(PersonasBasicTag personasBasicTag);

    /**
     * 修改标签分类
     * 
     * @param personasBasicTag 标签分类
     * @return 结果
     */
    public int updatePersonasBasicTag(PersonasBasicTag personasBasicTag);

    /**
     * 批量删除标签分类
     * 
     * @param tagIds 需要删除的标签分类ID
     * @return 结果
     */
    public int deletePersonasBasicTagByIds(Long[] tagIds);

    /**
     * 删除标签分类信息
     * 
     * @param tagId 标签分类ID
     * @return 结果
     */
    public int deletePersonasBasicTagById(Long tagId);
}
