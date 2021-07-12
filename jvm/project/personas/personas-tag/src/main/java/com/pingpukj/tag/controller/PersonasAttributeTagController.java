package com.pingpukj.tag.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pingpukj.common.annotation.Log;
import com.pingpukj.common.core.controller.BaseController;
import com.pingpukj.common.core.domain.AjaxResult;
import com.pingpukj.common.enums.BusinessType;
import com.pingpukj.tag.domain.PersonasAttributeTag;
import com.pingpukj.tag.service.IPersonasAttributeTagService;
import com.pingpukj.common.utils.poi.ExcelUtil;

/**
 * 属性标签Controller
 * 
 * @author huzunjie
 * @date 2021-07-09
 */
@RestController
@RequestMapping("/tag/attrtag")
public class PersonasAttributeTagController extends BaseController
{
    @Autowired
    private IPersonasAttributeTagService personasAttributeTagService;

    /**
     * 查询属性标签列表
     */
    @PreAuthorize("@ss.hasPermi('tag:attrtag:list')")
    @GetMapping("/list")
    public AjaxResult list(PersonasAttributeTag personasAttributeTag)
    {
        List<PersonasAttributeTag> list = personasAttributeTagService.selectPersonasAttributeTagList(personasAttributeTag);
        return AjaxResult.success(list);
    }

    /**
     * 导出属性标签列表
     */
    @PreAuthorize("@ss.hasPermi('tag:attrtag:export')")
    @Log(title = "属性标签", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PersonasAttributeTag personasAttributeTag)
    {
        List<PersonasAttributeTag> list = personasAttributeTagService.selectPersonasAttributeTagList(personasAttributeTag);
        ExcelUtil<PersonasAttributeTag> util = new ExcelUtil<PersonasAttributeTag>(PersonasAttributeTag.class);
        return util.exportExcel(list, "属性标签数据");
    }

    /**
     * 获取属性标签详细信息
     */
    @PreAuthorize("@ss.hasPermi('tag:attrtag:query')")
    @GetMapping(value = "/{tagId}")
    public AjaxResult getInfo(@PathVariable("tagId") Long tagId)
    {
        return AjaxResult.success(personasAttributeTagService.selectPersonasAttributeTagById(tagId));
    }

    /**
     * 新增属性标签
     */
    @PreAuthorize("@ss.hasPermi('tag:attrtag:add')")
    @Log(title = "属性标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PersonasAttributeTag personasAttributeTag)
    {
        return toAjax(personasAttributeTagService.insertPersonasAttributeTag(personasAttributeTag));
    }

    /**
     * 修改属性标签
     */
    @PreAuthorize("@ss.hasPermi('tag:attrtag:edit')")
    @Log(title = "属性标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PersonasAttributeTag personasAttributeTag)
    {
        return toAjax(personasAttributeTagService.updatePersonasAttributeTag(personasAttributeTag));
    }

    /**
     * 删除属性标签
     */
    @PreAuthorize("@ss.hasPermi('tag:attrtag:remove')")
    @Log(title = "属性标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tagIds}")
    public AjaxResult remove(@PathVariable Long[] tagIds)
    {
        return toAjax(personasAttributeTagService.deletePersonasAttributeTagByIds(tagIds));
    }
}
