package com.itheima.dao.impl;


import com.itheima.dao.UserMapper;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Author: HuZunJie
 * Date: 2021-07-12 18:15
 * Email: 826992656@qq.com
 * Version: 0.0.1
 * Desc:
 */


public class UserMapperImpl implements UserMapper {
    @Override
    public List<User> findAll() throws IOException {
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session回话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作  参数：namespace+id
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        //打印数据
       // System.out.println(userList);
        //释放资源
        sqlSession.close();
        return userList;
    }

    @Override
    public User findById(int id) {
        return null;
    }
}
