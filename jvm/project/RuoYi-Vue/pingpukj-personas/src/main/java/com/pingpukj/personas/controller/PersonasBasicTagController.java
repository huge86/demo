package com.pingpukj.personas.controller;

import java.util.List;

import com.pingpukj.personas.service.impl.IPersonasBasicTagService;
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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.pingpukj.personas.domain.PersonasBasicTag;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 标签分类Controller
 * 
 * @author huzunjie
 * @date 2021-06-15
 */
@RestController
@RequestMapping("/personas/category")
public class PersonasBasicTagController extends BaseController
{
    @Autowired
    private IPersonasBasicTagService personasBasicTagService;

    /**
     * 查询标签分类列表
     */
    @PreAuthorize("@ss.hasPermi('personas:category:list')")
    @GetMapping("/list")
    public AjaxResult list(PersonasBasicTag personasBasicTag)
    {
        List<PersonasBasicTag> list = personasBasicTagService.selectPersonasBasicTagList(personasBasicTag);
        return AjaxResult.success(list);
    }

    /**
     * 导出标签分类列表
     */
    @PreAuthorize("@ss.hasPermi('personas:category:export')")
    @Log(title = "标签分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PersonasBasicTag personasBasicTag)
    {
        List<PersonasBasicTag> list = personasBasicTagService.selectPersonasBasicTagList(personasBasicTag);
        ExcelUtil<PersonasBasicTag> util = new ExcelUtil<PersonasBasicTag>(PersonasBasicTag.class);
        return util.exportExcel(list, "标签分类数据");
    }

    /**
     * 获取标签分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('personas:category:query')")
    @GetMapping(value = "/{tagId}")
    public AjaxResult getInfo(@PathVariable("tagId") Long tagId)
    {
        return AjaxResult.success(personasBasicTagService.selectPersonasBasicTagById(tagId));
    }

    /**
     * 新增标签分类
     */
    @PreAuthorize("@ss.hasPermi('personas:category:add')")
    @Log(title = "标签分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PersonasBasicTag personasBasicTag)
    {
        return toAjax(personasBasicTagService.insertPersonasBasicTag(personasBasicTag));
    }

    /**
     * 修改标签分类
     */
    @PreAuthorize("@ss.hasPermi('personas:category:edit')")
    @Log(title = "标签分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PersonasBasicTag personasBasicTag)
    {
        return toAjax(personasBasicTagService.updatePersonasBasicTag(personasBasicTag));
    }

    /**
     * 删除标签分类
     */
    @PreAuthorize("@ss.hasPermi('personas:category:remove')")
    @Log(title = "标签分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{tagIds}")
    public AjaxResult remove(@PathVariable Long[] tagIds)
    {
        return toAjax(personasBasicTagService.deletePersonasBasicTagByIds(tagIds));
    }
}
