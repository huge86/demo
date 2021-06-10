package com.xiaoge.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoge.response.Result;
import com.xiaoge.system.entity.User;
import com.xiaoge.system.service.AliOssService;
import com.xiaoge.system.service.UserService;
import com.xiaoge.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xiaoge
 * @since 2020-09-03
 */
@Api(value = "用户管理")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 分页查询用户列表
     *
     * @return
     */
    @ApiOperation("分页查询用户列表")
    @GetMapping("/findUserList")
    public Result findUserList(@RequestParam(required = true, defaultValue = "1") Integer current,
                                @RequestParam(required = true, defaultValue = "6") Integer size) {
        //对用户进行分页,泛型中注入的就是用户实体类
        Page<User> page = new Page<>(current, size);
        //单表的时候其实这个方法是非常好用的
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        Page<User> userPage = userService.page(page);
        long total = userPage.getTotal();
        List<User> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    /**
     * 根据用户条件进行分页查询
     *
     * @param current
     * @param size
     * @param userVO
     * @return
     */
    @PostMapping("/findUserPage")
    public Result findUserPage(@RequestParam(required = true, defaultValue = "1") Integer current,
                               @RequestParam(required = true, defaultValue = "6") Integer size,
                               @RequestBody UserVO userVO) {
        //对用户进行分页,泛型中注入的就是用户实体类
        Page<User> page = new Page<>(current, size);
        //单表的时候其实这个方法是非常好用的
        QueryWrapper<User> wrapper = getWrapper(userVO);
        IPage<User> userPage = userService.findUserPage(page, wrapper);
        long total = userPage.getTotal();
        List<User> records = userPage.getRecords();
        return Result.ok().data("total", total).data("records", records);
    }

    /**
     * 封装查询条件
     *
     * @param userVO
     * @return
     */
    private QueryWrapper<User> getWrapper(UserVO userVO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (userVO != null) {
            if (!StringUtils.isEmpty(userVO.getDepartmentId())) {
                queryWrapper.eq("department_id", userVO.getDepartmentId());
            }
            if (!StringUtils.isEmpty(userVO.getUsername())) {
                queryWrapper.eq("username", userVO.getUsername());
            }
            if (!StringUtils.isEmpty(userVO.getEmail())) {
                queryWrapper.eq("email", userVO.getEmail());
            }
            if (!StringUtils.isEmpty(userVO.getSex())) {
                queryWrapper.eq("sex", userVO.getSex());
            }
            if (!StringUtils.isEmpty(userVO.getNickname())) {
                queryWrapper.eq("nickname", userVO.getNickname());
            }
        }
        return queryWrapper;
    }

    @ApiOperation(value = "添加用户",notes = "添加用户信息")
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user){
        try {
            userService.addUser(user);
            return Result.ok();
        }catch (Exception e){
            //打印日志
            return Result.error();
        }
    }
}

