package cn.itcast.mp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//# 全局的表名的前缀
//mybatis-plus.global-config.db-config.table-prefix=tb_
//全局配置后，可以省略下行
//@TableName("tb_user")
public class User {

//    @TableId(type = IdType.AUTO) //如果开启了全局的id生成策略，此行可以删掉
//    # 全局的id生成策略
//    mybatis-plus.global-config.db-config.id-type=auto
    private Long id;
    private String userName;

//    在MP中通过@TableField注解可以指定字段的一些属性，常常解决的问题有2个：
//            1、对象中的属性名和字段名不一致的问题（非驼峰）
//            2、对象中的属性字段在表中不存在的问题
    @TableField(select = false) //查询时不返回该字段的值
    private String password;
    private String name;
    private Integer age;

    @TableField(value = "email") //指定数据表中字段名
    private String mail;

    @TableField(exist = false)
    private String address; //在数据库表中是不存在的
}