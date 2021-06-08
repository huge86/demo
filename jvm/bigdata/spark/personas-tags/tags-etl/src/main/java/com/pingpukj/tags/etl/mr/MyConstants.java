package com.pingpukj.tags.etl.mr;

import org.apache.hadoop.hbase.util.Bytes;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: HuZunJie
 * Date: 2021-06-06 17:02
 * Email: 826992656@qq.com
 * Version: 0.0.1
 * Desc: 定义常量
 */


interface MyConstants {
    // hive表数据目录,对应MySQL数据库中tags_dat.tbl_students
    String INPUT_PATH = "hdfs://bigdata-cdh01.itcast.cn:8020/user/hive/warehouse/tags_dat.db/tbl_students";
    // 生成的hfile目录
    String HFILE_PATH = "hdfs://bigdata-cdh01.itcast.cn:8020/datas/output_hfile/tbl_students";
    // 表名
    String TABLE_NAME = "tbl_students";
    // 列簇名称
    byte[] COLUMN_FAMILY = Bytes.toBytes("detail");

    // 表字段
    List<byte[]> list = new ArrayList<byte[]>() {
        private static final long serialVersionUID = -6125158551837044300L;

        {
            add(Bytes.toBytes("id"));
            add(Bytes.toBytes("name"));
            add(Bytes.toBytes("age"));
            add(Bytes.toBytes("createtime"));
        }
    };
}
