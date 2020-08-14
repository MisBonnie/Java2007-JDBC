package com.zzxx.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class Demo02 {
    public static void main(String[] args) throws PropertyVetoException, SQLException {
        // 使用配置文件 c3p0-config.xml 来指定连接池的参数/属性配置
        // 在使用ComboPooledDataSource无参构造器创建这个连接池对象时, 会自动去读取c3p0-config.xml文件内容
        // 读取完c3p0-config.xml 后, 使用的是default-config里的配置信息
//        DataSource dataSource = new ComboPooledDataSource();
        // 有参构造器, 传入一个name, 使用的就是named-config="otherc3p0"
        DataSource dataSource = new ComboPooledDataSource("otherc3p0");

        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }
}
