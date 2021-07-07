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
import com.pingpukj.tag.domain.PersonasBasicCategory;
import com.pingpukj.tag.service.IPersonasBasicCategoryService;
import com.pingpukj.common.utils.poi.ExcelUtil;

/**
 * 标签分类Controller
 * 
 * @author huzunjie
 * @date 2021-06-28
 */
@RestController
@RequestMapping("/tag/category")
public class PersonasBasicCategoryController extends BaseController
{
    @Autowired
    private IPersonasBasicCategoryService personasBasicCategoryService;

    /**
     * 查询标签分类列表
     */
    @PreAuthorize("@ss.hasPermi('tag:category:list')")
    @GetMapping("/list")
    public AjaxResult list(PersonasBasicCategory personasBasicCategory)
    {
        List<PersonasBasicCategory> list = personasBasicCategoryService.selectPersonasBasicCategoryList(personasBasicCategory);
        return AjaxResult.success(list);
    }

    /**
     * 导出标签分类列表
     */
    @PreAuthorize("@ss.hasPermi('tag:category:export')")
    @Log(title = "标签分类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PersonasBasicCategory personasBasicCategory)
    {
        List<PersonasBasicCategory> list = personasBasicCategoryService.selectPersonasBasicCategoryList(personasBasicCategory);
        ExcelUtil<PersonasBasicCategory> util = new ExcelUtil<PersonasBasicCategory>(PersonasBasicCategory.class);
        return util.exportExcel(list, "标签分类数据");
    }

    /**
     * 获取标签分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('tag:category:query')")
    @GetMapping(value = "/{catId}")
    public AjaxResult getInfo(@PathVariable("catId") Long catId)
    {
        return AjaxResult.success(personasBasicCategoryService.selectPersonasBasicCategoryById(catId));
    }

    /**
     * 新增标签分类
     */
    @PreAuthorize("@ss.hasPermi('tag:category:add')")
    @Log(title = "标签分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PersonasBasicCategory personasBasicCategory)
    {
        return toAjax(personasBasicCategoryService.insertPersonasBasicCategory(personasBasicCategory));
    }

    /**
     * 修改标签分类
     */
    @PreAuthorize("@ss.hasPermi('tag:category:edit')")
    @Log(title = "标签分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PersonasBasicCategory personasBasicCategory)
    {
        return toAjax(personasBasicCategoryService.updatePersonasBasicCategory(personasBasicCategory));
    }

    /**
     * 删除标签分类
     */
    @PreAuthorize("@ss.hasPermi('tag:category:remove')")
    @Log(title = "标签分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{catIds}")
    public AjaxResult remove(@PathVariable Long[] catIds)
    {
        return toAjax(personasBasicCategoryService.deletePersonasBasicCategoryByIds(catIds));
    }
}
