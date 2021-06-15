package com.pingpukj.personas.service;

import java.util.List;

import com.pingpukj.personas.service.impl.IPersonasBasicTagService;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pingpukj.personas.mapper.PersonasBasicTagMapper;
import com.pingpukj.personas.domain.PersonasBasicTag;


/**
 * 标签分类Service业务层处理
 * 
 * @author huzunjie
 * @date 2021-06-15
 */
@Service
public class PersonasBasicTagServiceImpl implements IPersonasBasicTagService
{
    @Autowired
    private PersonasBasicTagMapper personasBasicTagMapper;

    /**
     * 查询标签分类
     * 
     * @param tagId 标签分类ID
     * @return 标签分类
     */
    @Override
    public PersonasBasicTag selectPersonasBasicTagById(Long tagId)
    {
        return personasBasicTagMapper.selectPersonasBasicTagById(tagId);
    }

    /**
     * 查询标签分类列表
     * 
     * @param personasBasicTag 标签分类
     * @return 标签分类
     */
    @Override
    public List<PersonasBasicTag> selectPersonasBasicTagList(PersonasBasicTag personasBasicTag)
    {
        return personasBasicTagMapper.selectPersonasBasicTagList(personasBasicTag);
    }

    /**
     * 新增标签分类
     * 
     * @param personasBasicTag 标签分类
     * @return 结果
     */
    @Override
    public int insertPersonasBasicTag(PersonasBasicTag personasBasicTag)
    {
        personasBasicTag.setCreateTime(DateUtils.getNowDate());
        return personasBasicTagMapper.insertPersonasBasicTag(personasBasicTag);
    }

    /**
     * 修改标签分类
     * 
     * @param personasBasicTag 标签分类
     * @return 结果
     */
    @Override
    public int updatePersonasBasicTag(PersonasBasicTag personasBasicTag)
    {
        personasBasicTag.setUpdateTime(DateUtils.getNowDate());
        return personasBasicTagMapper.updatePersonasBasicTag(personasBasicTag);
    }

    /**
     * 批量删除标签分类
     * 
     * @param tagIds 需要删除的标签分类ID
     * @return 结果
     */
    @Override
    public int deletePersonasBasicTagByIds(Long[] tagIds)
    {
        return personasBasicTagMapper.deletePersonasBasicTagByIds(tagIds);
    }

    /**
     * 删除标签分类信息
     * 
     * @param tagId 标签分类ID
     * @return 结果
     */
    @Override
    public int deletePersonasBasicTagById(Long tagId)
    {
        return personasBasicTagMapper.deletePersonasBasicTagById(tagId);
    }
}
