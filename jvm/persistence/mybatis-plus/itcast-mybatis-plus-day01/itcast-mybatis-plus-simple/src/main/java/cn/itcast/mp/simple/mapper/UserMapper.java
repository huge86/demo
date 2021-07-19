package cn.itcast.mp.simple.mapper;

import cn.itcast.mp.simple.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
//继承BaseMapper，可以不用写sql语句
public interface UserMapper extends BaseMapper<User> {

    List<User> findAll();
}
