package com.pingpukj.tag.service.impl;

import java.util.List;
import com.pingpukj.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pingpukj.tag.mapper.PersonasBasicTagMapper;
import com.pingpukj.tag.domain.PersonasBasicTag;
import com.pingpukj.tag.service.IPersonasBasicTagService;

/**
 * 基础标签Service业务层处理
 * 
 * @author huzunjie
 * @date 2021-06-28
 */
@Service
public class PersonasBasicTagServiceImpl implements IPersonasBasicTagService 
{
    @Autowired
    private PersonasBasicTagMapper personasBasicTagMapper;

    /**
     * 查询基础标签
     * 
     * @param tagId 基础标签ID
     * @return 基础标签
     */
    @Override
    public PersonasBasicTag selectPersonasBasicTagById(Long tagId)
    {
        return personasBasicTagMapper.selectPersonasBasicTagById(tagId);
    }

    /**
     * 查询基础标签列表
     * 
     * @param personasBasicTag 基础标签
     * @return 基础标签
     */
    @Override
    public List<PersonasBasicTag> selectPersonasBasicTagList(PersonasBasicTag personasBasicTag)
    {
        return personasBasicTagMapper.selectPersonasBasicTagList(personasBasicTag);
    }

    /**
     * 新增基础标签
     * 
     * @param personasBasicTag 基础标签
     * @return 结果
     */
    @Override
    public int insertPersonasBasicTag(PersonasBasicTag personasBasicTag)
    {
        personasBasicTag.setCreateTime(DateUtils.getNowDate());
        return personasBasicTagMapper.insertPersonasBasicTag(personasBasicTag);
    }

    /**
     * 修改基础标签
     * 
     * @param personasBasicTag 基础标签
     * @return 结果
     */
    @Override
    public int updatePersonasBasicTag(PersonasBasicTag personasBasicTag)
    {
        personasBasicTag.setUpdateTime(DateUtils.getNowDate());
        return personasBasicTagMapper.updatePersonasBasicTag(personasBasicTag);
    }

    /**
     * 批量删除基础标签
     * 
     * @param tagIds 需要删除的基础标签ID
     * @return 结果
     */
    @Override
    public int deletePersonasBasicTagByIds(Long[] tagIds)
    {
        return personasBasicTagMapper.deletePersonasBasicTagByIds(tagIds);
    }

    /**
     * 删除基础标签信息
     * 
     * @param tagId 基础标签ID
     * @return 结果
     */
    @Override
    public int deletePersonasBasicTagById(Long tagId)
    {
        return personasBasicTagMapper.deletePersonasBasicTagById(tagId);
    }
}
