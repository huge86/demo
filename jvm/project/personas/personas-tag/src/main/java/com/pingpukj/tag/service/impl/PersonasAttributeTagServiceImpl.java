package com.pingpukj.tag.service.impl;

import java.util.List;
import com.pingpukj.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pingpukj.tag.mapper.PersonasAttributeTagMapper;
import com.pingpukj.tag.domain.PersonasAttributeTag;
import com.pingpukj.tag.service.IPersonasAttributeTagService;

/**
 * 属性标签Service业务层处理
 * 
 * @author huzunjie
 * @date 2021-07-09
 */
@Service
public class PersonasAttributeTagServiceImpl implements IPersonasAttributeTagService 
{
    @Autowired
    private PersonasAttributeTagMapper personasAttributeTagMapper;

    /**
     * 查询属性标签
     * 
     * @param tagId 属性标签ID
     * @return 属性标签
     */
    @Override
    public PersonasAttributeTag selectPersonasAttributeTagById(Long tagId)
    {
        return personasAttributeTagMapper.selectPersonasAttributeTagById(tagId);
    }

    /**
     * 查询属性标签列表
     * 
     * @param personasAttributeTag 属性标签
     * @return 属性标签
     */
    @Override
    public List<PersonasAttributeTag> selectPersonasAttributeTagList(PersonasAttributeTag personasAttributeTag)
    {
        return personasAttributeTagMapper.selectPersonasAttributeTagList(personasAttributeTag);
    }

    /**
     * 新增属性标签
     * 
     * @param personasAttributeTag 属性标签
     * @return 结果
     */
    @Override
    public int insertPersonasAttributeTag(PersonasAttributeTag personasAttributeTag)
    {
        personasAttributeTag.setCreateTime(DateUtils.getNowDate());
        return personasAttributeTagMapper.insertPersonasAttributeTag(personasAttributeTag);
    }

    /**
     * 修改属性标签
     * 
     * @param personasAttributeTag 属性标签
     * @return 结果
     */
    @Override
    public int updatePersonasAttributeTag(PersonasAttributeTag personasAttributeTag)
    {
        personasAttributeTag.setUpdateTime(DateUtils.getNowDate());
        return personasAttributeTagMapper.updatePersonasAttributeTag(personasAttributeTag);
    }

    /**
     * 批量删除属性标签
     * 
     * @param tagIds 需要删除的属性标签ID
     * @return 结果
     */
    @Override
    public int deletePersonasAttributeTagByIds(Long[] tagIds)
    {
        return personasAttributeTagMapper.deletePersonasAttributeTagByIds(tagIds);
    }

    /**
     * 删除属性标签信息
     * 
     * @param tagId 属性标签ID
     * @return 结果
     */
    @Override
    public int deletePersonasAttributeTagById(Long tagId)
    {
        return personasAttributeTagMapper.deletePersonasAttributeTagById(tagId);
    }
}
