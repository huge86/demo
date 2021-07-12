package com.pingpukj.tag.service;

import com.pingpukj.tag.domain.PersonasAttributeTag;
import com.pingpukj.tag.domain.PersonasBasicTag;

import java.util.List;

/**
 * 属性标签Service接口
 * 
 * @author huzunjie
 * @date 2021-07-09
 */
public interface IPersonasBizTagService
{
    /**
     * 查询基本标签
     * 
     * @param tagId 基本标签ID
     * @return 基本标签
     */
    public PersonasBasicTag selectPersonasBasicTagById(Long tagId);

    /**
     * 查询基本标签列表
     * 
     * @param personasBasicTag 属性标签
     * @return 属性标签集合
     */
    public List<PersonasBasicTag> selectPersonasBasicTagList(PersonasBasicTag personasBasicTag);

    /**
     * 新增基本标签
     * 
     * @param personasBasicTag 属性标签
     * @return 结果
     */
    public int insertPersonasBasicTag(PersonasBasicTag personasBasicTag);

    /**
     * 修改基本标签
     * 
     * @param personasBasicTag 基本标签
     * @return 结果
     */
    public int updatePersonasBasicTag(PersonasBasicTag personasBasicTag);

    /**
     * 批量删除基本标签
     * 
     * @param tagIds 需要删除的基本标签ID
     * @return 结果
     */
    public int deletePersonasBasicTagByIds(Long[] tagIds);

    /**
     * 删除基本标签信息
     * 
     * @param tagId 基本标签ID
     * @return 结果
     */
    public int deletePersonasBasicTagById(Long tagId);
}
