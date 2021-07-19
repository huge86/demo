package com.itheima.dao;

import com.itheima.domain.User;

import java.io.IOException;
import java.util.List;

public interface UserMapper {
    // 动态代理规范，要与UserMaper.xml selelct 的id一致
    public List<User> findAll() throws IOException;
    // 要与UserMaper.xml selelct 的id一致
    public User findById(int id);

}
