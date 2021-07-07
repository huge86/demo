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
import com.pingpukj.tag.domain.PersonasBasicTag;
import com.pingpukj.tag.service.IPersonasBasicTagService;
import com.pingpukj.common.utils.poi.ExcelUtil;

/**
 * 基础标签Controller
 * 
 * @author huzunjie
 * @date 2021-06-28
 */
@RestController
@RequestMapping("/tag/basic")
public class PersonasBasicTagController extends BaseController
{
    @Autowired
    private IPersonasBasicTagService personasBasicTagService;

    /**
     * 查询基础标签列表
     */
    @PreAuthorize("@ss.hasPermi('tag:basic:list')")
    @GetMapping("/list")
    public AjaxResult list(PersonasBasicTag personasBasicTag)
    {
        List<PersonasBasicTag> list = personasBasicTagService.selectPersonasBasicTagList(personasBasicTag);
        return AjaxResult.success(list);
    }

    /**
     * 导出基础标签列表
     */
    @PreAuthorize("@ss.hasPermi('tag:basic:export')")
    @Log(title = "基础标签", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PersonasBasicTag personasBasicTag)
    {
        List<PersonasBasicTag> list = personasBasicTagService.selectPersonasBasicTagList(personasBasicTag);
        ExcelUtil<PersonasBasicTag> util = new ExcelUtil<PersonasBasicTag>(PersonasBasicTag.class);
        return util.exportExcel(list, "基础标签数据");
    }

    /**
     * 获取基础标签详细信息
     */
    @PreAuthorize("@ss.hasPermi('tag:basic:query')")
    @GetMapping(value = "/{tagId}")
    public AjaxResult getInfo(@PathVariable("tagId") Long tagId)
    {
        return AjaxResult.success(personasBasicTagService.selectPersonasBasicTagById(tagId));
    }

    /**
     * 新增基础标签
     */
    @PreAuthorize("@ss.hasPermi('tag:basic:add')")
    @Log(title = "基础标签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PersonasBasicTag personasBasicTag)
    {
        return toAjax(personasBasicTagService.insertPersonasBasicTag(personasBasicTag));
    }

    /**
     * 修改基础标签
     */
    @PreAuthorize("@ss.hasPermi('tag:basic:edit')")
    @Log(title = "基础标签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PersonasBasicTag personasBasicTag)
    {
        return toAjax(personasBasicTagService.updatePersonasBasicTag(personasBasicTag));
    }

    /**
     * 删除基础标签
     */
    @PreAuthorize("@ss.hasPermi('tag:basic:remove')")
    @Log(title = "基础标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tagIds}")
    public AjaxResult remove(@PathVariable Long[] tagIds)
    {
        return toAjax(personasBasicTagService.deletePersonasBasicTagByIds(tagIds));
    }
}
