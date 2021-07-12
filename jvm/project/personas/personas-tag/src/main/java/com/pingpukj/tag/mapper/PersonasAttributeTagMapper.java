package com.pingpukj.tag.mapper;

import java.util.List;
import com.pingpukj.tag.domain.PersonasAttributeTag;

/**
 * 属性标签Mapper接口
 * 
 * @author huzunjie
 * @date 2021-07-09
 */
public interface PersonasAttributeTagMapper 
{
    /**
     * 查询属性标签
     * 
     * @param tagId 属性标签ID
     * @return 属性标签
     */
    public PersonasAttributeTag selectPersonasAttributeTagById(Long tagId);

    /**
     * 查询属性标签列表
     * 
     * @param personasAttributeTag 属性标签
     * @return 属性标签集合
     */
    public List<PersonasAttributeTag> selectPersonasAttributeTagList(PersonasAttributeTag personasAttributeTag);

    /**
     * 新增属性标签
     * 
     * @param personasAttributeTag 属性标签
     * @return 结果
     */
    public int insertPersonasAttributeTag(PersonasAttributeTag personasAttributeTag);

    /**
     * 修改属性标签
     * 
     * @param personasAttributeTag 属性标签
     * @return 结果
     */
    public int updatePersonasAttributeTag(PersonasAttributeTag personasAttributeTag);

    /**
     * 删除属性标签
     * 
     * @param tagId 属性标签ID
     * @return 结果
     */
    public int deletePersonasAttributeTagById(Long tagId);

    /**
     * 批量删除属性标签
     * 
     * @param tagIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePersonasAttributeTagByIds(Long[] tagIds);
}
