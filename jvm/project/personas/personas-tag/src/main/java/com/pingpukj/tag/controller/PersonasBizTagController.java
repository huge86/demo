package com.pingpukj.tag.controller;

import com.pingpukj.common.annotation.Log;
import com.pingpukj.common.core.controller.BaseController;
import com.pingpukj.common.core.domain.AjaxResult;
import com.pingpukj.common.enums.BusinessType;
import com.pingpukj.common.utils.poi.ExcelUtil;
import com.pingpukj.tag.domain.PersonasAttributeTag;
import com.pingpukj.tag.domain.PersonasBasicTag;
import com.pingpukj.tag.service.IPersonasAttributeTagService;
import com.pingpukj.tag.service.IPersonasBizTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 业务标签Controller
 * 
 * @author huzunjie
 * @date 2021-07-09
 */
@RestController
@RequestMapping("/tag/biztag")
public class PersonasBizTagController extends BaseController
{
    @Autowired
    private IPersonasBizTagService personasBizTagService;

    /**
     * 查询业务标签列表
     */
    @PreAuthorize("@ss.hasPermi('tag:biztag:list')")
    @GetMapping("/list")
    public AjaxResult list(PersonasBasicTag personasBasicTag)
    {
        List<PersonasBasicTag> list = personasBizTagService.selectPersonasBasicTagList(personasBasicTag);
        return AjaxResult.success(list);
    }

    /**
     * 导出业务标签列表
     */
    @PreAuthorize("@ss.hasPermi('tag:biztag:export')")
    @Log(title = "业务标签", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PersonasBasicTag personasBasicTag)
    {
        List<PersonasBasicTag> list = personasBizTagService.selectPersonasBasicTagList(personasBasicTag);
        ExcelUtil<PersonasBasicTag> util = new ExcelUtil<PersonasBasicTag>(PersonasBasicTag.class);
        return util.exportExcel(list, "业务标签数据");
    }

    /**
     * 获取业务标签详细信息
     */
    @PreAuthorize("@ss.hasPermi('tag:biztag:query')")
    @GetMapping(value = "/{tagId}")
    public AjaxResult getInfo(@PathVariable("tagId") Long tagId)
    {
        return AjaxResult.success(personasBizTagService.selectPersonasBasicTagById(tagId));
    }

    /**
     * 新增业务标签
     */
    @PreAuthorize("@ss.hasPermi('tag:biztag:add')")
    @Log(title = "业务标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PersonasBasicTag personasBasicTag)
    {
        return toAjax(personasBizTagService.insertPersonasBasicTag(personasBasicTag));
    }

    /**
     * 修改业务标签
     */
    @PreAuthorize("@ss.hasPermi('tag:biztag:edit')")
    @Log(title = "业务标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PersonasBasicTag personasBasicTag)
    {
        return toAjax(personasBizTagService.updatePersonasBasicTag(personasBasicTag));
    }

    /**
     * 删除业务标签
     */
    @PreAuthorize("@ss.hasPermi('tag:biztag:remove')")
    @Log(title = "业务标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tagIds}")
    public AjaxResult remove(@PathVariable Long[] tagIds)
    {
        return toAjax(personasBizTagService.deletePersonasBasicTagByIds(tagIds));
    }
}
