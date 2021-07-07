package com.pingpukj.tag.service;

import java.util.List;
import com.pingpukj.tag.domain.PersonasBasicTag;

/**
 * 基础标签Service接口
 * 
 * @author huzunjie
 * @date 2021-06-28
 */
public interface IPersonasBasicTagService 
{
    /**
     * 查询基础标签
     * 
     * @param tagId 基础标签ID
     * @return 基础标签
     */
    public PersonasBasicTag selectPersonasBasicTagById(Long tagId);

    /**
     * 查询基础标签列表
     * 
     * @param personasBasicTag 基础标签
     * @return 基础标签集合
     */
    public List<PersonasBasicTag> selectPersonasBasicTagList(PersonasBasicTag personasBasicTag);

    /**
     * 新增基础标签
     * 
     * @param personasBasicTag 基础标签
     * @return 结果
     */
    public int insertPersonasBasicTag(PersonasBasicTag personasBasicTag);

    /**
     * 修改基础标签
     * 
     * @param personasBasicTag 基础标签
     * @return 结果
     */
    public int updatePersonasBasicTag(PersonasBasicTag personasBasicTag);

    /**
     * 批量删除基础标签
     * 
     * @param tagIds 需要删除的基础标签ID
     * @return 结果
     */
    public int deletePersonasBasicTagByIds(Long[] tagIds);

    /**
     * 删除基础标签信息
     * 
     * @param tagId 基础标签ID
     * @return 结果
     */
    public int deletePersonasBasicTagById(Long tagId);
}
