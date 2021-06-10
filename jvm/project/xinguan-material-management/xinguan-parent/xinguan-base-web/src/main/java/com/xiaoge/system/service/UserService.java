package com.xiaoge.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoge.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xiaoge
 * @since 2020-09-04
 */
public interface UserService extends IService<User> {

    IPage<User> findUserPage(Page<User> page,QueryWrapper<User> wrapper);

    /**
     * 添加用户
     * @param user 用户对象
     */
    void addUser(User user);
}
