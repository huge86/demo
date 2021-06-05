package com.pingpukj.tags.test.hbase;

import com.chb.tags.Constant;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.regionserver.StoreFile;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * @Author: chb
 * @Date: 2021/4/22 14:21
 * @E-Mail:
 * @DESC:
 */
public class HbaseTest {
    /**
     * 声明静态配置
     */
    static Configuration conf = null;
    static Connection conn = null;

    static {
        conf = HBaseConfiguration.create();
//        conf.set("hbase.zookeeper.quorum", "hadoop01,hadoop02,hadoop03");
//        conf.set("hbase.zookeeper.property.client", "2181");
        try {
            conn = ConnectionFactory.createConnection(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void listTable() throws IOException {
        Admin admin = conn.getAdmin();
        for (TableName tableName : admin.listTableNames()) {
            System.out.println(tableName);
        }
    }

    public static void createTable(String tableName) throws IOException {
        Admin admin = conn.getAdmin();

        if (!admin.tableExists(TableName.valueOf(tableName))) {
            HTableDescriptor htd = new HTableDescriptor(TableName.valueOf(tableName));
            HColumnDescriptor cf = new HColumnDescriptor("user");

            htd.addFamily(cf);
            admin.createTable(htd);
        } else {
            System.out.println(tableName + " is exists!");
        }
    }

    public static void scanData(String tableName) throws IOException {
        TableName tn = TableName.valueOf(tableName);
        Table table = conn.getTable(tn);
        Scan scan = new Scan();
        // 只获取某个列簇
        scan.addFamily(Bytes.toBytes("user"));
        // 只获取某列数据
        // scan.addColumn(Bytes.toBytes("user"), Bytes.toBytes("gender"));

        ResultScanner scanner = table.getScanner(scan);
        for (Result rs : scanner) {
            String rowKey = Bytes.toString(rs.getRow());
            System.out.println("rowkey: " + rowKey);
            for (Cell cell : rs.rawCells()) {
                System.out.println(Bytes.toString(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength()) + "::"
                        + Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()) + "::"
                        + Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
            }
            System.out.println("-------------------------------------------");
        }

    }


    public static void main(String[] args) throws IOException {
        String tableName = Constant.TAG_TABLE_HBASE();
        // createTable(tableName);
        // listTable();
        System.out.println(tableName);
        long start = System.currentTimeMillis();
        scanData(tableName);
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");

    }
}
