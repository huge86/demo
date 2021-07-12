package com.pingpukj.tag.mapper;

import com.pingpukj.tag.domain.*;

import java.util.List;

/**
 * 业务标签Mapper接口
 * 
 * @author huzunjie
 * @date 2021-07-09
 */
public interface PersonasBizTagMapper
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
     * @param personasBasicTag 基本标签
     * @return 基本标签集合
     */
    public List<PersonasBasicTag> selectPersonasBasicTagList(PersonasBasicTag personasBasicTag);


    /**
     * 新增基本标签
     * 
     * @param personasBasicTag 基本标签
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
     * 删除基本标签
     * 
     * @param tagId 基本标签ID
     * @return 结果
     */
    public int deletePersonasBasicTagById(Long tagId);

    /**
     * 批量删除基本标签
     * 
     * @param tagIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePersonasBasicTagByIds(Long[] tagIds);

    /**
     * 批量删除标签ID对应的模型
     *
     * @param tagIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePersonasModelByTagIds(Long[] tagIds);

    /**
     * 批量新增基本标签ID对应的模型
     *
     * @param personasModelList 商品列表
     * @return 结果
     */
    public int batchPersonasModel(List<PersonasModel> personasModelList);


    /**
     * 通过客户tagID删除商品信息
     *
     * @param tagId 客户ID
     * @return 结果
     */
    public int deletePersonasModelByTagId(Long tagId);

}
