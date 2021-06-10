package com.xiaoge.system.controller;


import com.xiaoge.handler.BusinessException;
import com.xiaoge.response.Result;
import com.xiaoge.response.ResultCode;
import com.xiaoge.system.entity.Department;
import com.xiaoge.system.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaoge
 * @since 2020-09-08
 */
@Api(value = "部门管理")
@RestController
@RequestMapping("/department")
@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "查询部门及人数",notes = "从部门表中分组查询用户人数")
    @GetMapping("/findDeptAndCount")
    public Result findDeptAndCount(){
        List<Department> departments = departmentService.findDeptAndCount();
        if(departments.size()==0){
            throw new BusinessException(ResultCode.DEPARTMENT_NOT_EXIST.getCode(),
                    ResultCode.DEPARTMENT_NOT_EXIST.getMessage());
        }
        return Result.ok().data("departments",departments);
    }

}

