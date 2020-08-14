package com.zzxx.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class Demo01 {
    public static void main(String[] args) throws Exception {
        // 创建一个属性集 Properties
        Properties pros = new Properties();
        pros.load(Demo01.class.getResourceAsStream("druid.properties"));
        // Druid连接池提供了一个获得DataSource的工厂类
        DataSource dataSource = DruidDataSourceFactory.createDataSource(pros);
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }
}
