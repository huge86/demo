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
import com.pingpukj.tag.domain.PersonasProduct;
import com.pingpukj.tag.service.IPersonasProductService;
import com.pingpukj.common.utils.poi.ExcelUtil;

/**
 * 产品Controller
 * 
 * @author huzunjie
 * @date 2021-06-16
 */
@RestController
@RequestMapping("/tag/product")
public class PersonasProductController extends BaseController
{
    @Autowired
    private IPersonasProductService personasProductService;

    /**
     * 查询产品列表
     */
    @PreAuthorize("@ss.hasPermi('tag:product:list')")
    @GetMapping("/list")
    public AjaxResult list(PersonasProduct personasProduct)
    {
        List<PersonasProduct> list = personasProductService.selectPersonasProductList(personasProduct);
        return AjaxResult.success(list);
    }

    /**
     * 导出产品列表
     */
    @PreAuthorize("@ss.hasPermi('tag:product:export')")
    @Log(title = "产品", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PersonasProduct personasProduct)
    {
        List<PersonasProduct> list = personasProductService.selectPersonasProductList(personasProduct);
        ExcelUtil<PersonasProduct> util = new ExcelUtil<PersonasProduct>(PersonasProduct.class);
        return util.exportExcel(list, "产品数据");
    }

    /**
     * 获取产品详细信息
     */
    @PreAuthorize("@ss.hasPermi('tag:product:query')")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") Long productId)
    {
        return AjaxResult.success(personasProductService.selectPersonasProductById(productId));
    }

    /**
     * 新增产品
     */
    @PreAuthorize("@ss.hasPermi('tag:product:add')")
    @Log(title = "产品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PersonasProduct personasProduct)
    {
        return toAjax(personasProductService.insertPersonasProduct(personasProduct));
    }

    /**
     * 修改产品
     */
    @PreAuthorize("@ss.hasPermi('tag:product:edit')")
    @Log(title = "产品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PersonasProduct personasProduct)
    {
        return toAjax(personasProductService.updatePersonasProduct(personasProduct));
    }

    /**
     * 删除产品
     */
    @PreAuthorize("@ss.hasPermi('tag:product:remove')")
    @Log(title = "产品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable Long[] productIds)
    {
        return toAjax(personasProductService.deletePersonasProductByIds(productIds));
    }
}
