package com.xiaoge.system.service;

import com.xiaoge.system.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaoge
 * @since 2020-09-08
 */
public interface DepartmentService extends IService<Department> {

    List<Department> findDeptAndCount();
}
