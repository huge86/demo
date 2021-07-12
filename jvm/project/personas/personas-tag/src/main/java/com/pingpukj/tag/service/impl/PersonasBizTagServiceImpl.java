package com.pingpukj.tag.service.impl;

import com.pingpukj.common.utils.DateUtils;
import com.pingpukj.tag.domain.PersonasAttributeTag;
import com.pingpukj.tag.domain.PersonasBasicTag;
import com.pingpukj.tag.mapper.PersonasAttributeTagMapper;
import com.pingpukj.tag.mapper.PersonasBizTagMapper;
import com.pingpukj.tag.service.IPersonasAttributeTagService;
import com.pingpukj.tag.service.IPersonasBizTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 属性标签Service业务层处理
 * 
 * @author huzunjie
 * @date 2021-07-09
 */
@Service
public class PersonasBizTagServiceImpl implements IPersonasBizTagService
{
    @Autowired
    private PersonasBizTagMapper personasBizTagMapper;
    /**
     * 查询基本标签
     *
     * @param tagId 基本标签ID
     * @return 基本标签
     */
    @Override
    public PersonasBasicTag selectPersonasBasicTagById(Long tagId) {
        return personasBizTagMapper.selectPersonasBasicTagById(tagId);

    }
    /**
     * 查询基本标签列表
     *
     * @param personasBasicTag 基本标签
     * @return 基本标签
     */

    @Override
    public List<PersonasBasicTag> selectPersonasBasicTagList(PersonasBasicTag personasBasicTag) {
        return personasBizTagMapper.selectPersonasBasicTagList(personasBasicTag);

    }
    /**
     * 新增基本标签
     *
     * @param personasBasicTag 基本标签
     * @return 结果
     */

    @Override
    public int insertPersonasBasicTag(PersonasBasicTag personasBasicTag) {
        personasBasicTag.setCreateTime(DateUtils.getNowDate());
        return personasBizTagMapper.insertPersonasBasicTag(personasBasicTag);

    }
    /**
     * 修改基本标签
     *
     * @param personasBasicTag 基本标签
     * @return 结果
     */

    @Override
    public int updatePersonasBasicTag(PersonasBasicTag personasBasicTag) {
        personasBasicTag.setUpdateTime(DateUtils.getNowDate());
        return personasBizTagMapper.updatePersonasBasicTag(personasBasicTag);

    }
    /**
     * 批量删除基本标签
     *
     * @param tagIds 需要删除的属性标签ID
     * @return 结果
     */

    @Override
    public int deletePersonasBasicTagByIds(Long[] tagIds) {
        return personasBizTagMapper.deletePersonasBasicTagByIds(tagIds);

    }
    /**
     * 删除属性标签信息
     *
     * @param tagId 属性标签ID
     * @return 结果
     */

    @Override
    public int deletePersonasBasicTagById(Long tagId) {
        return personasBizTagMapper.deletePersonasBasicTagById(tagId);

    }
}
