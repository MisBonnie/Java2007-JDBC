package com.zzxx.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import utils.JDBCUtils;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.security.spec.RSAOtherPrimeInfo;
import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.SQLException;

public class Demo04 {
    public static void main(String[] args) throws PropertyVetoException, SQLException {
        DataSource dataSource = new ComboPooledDataSource();
        // 通过连接池获得的连接
        Connection conn = dataSource.getConnection();
        System.out.println(conn);

        // 通过JDBC方式获得的连接
        Connection conn1 = JDBCUtils.getConnection();
        System.out.println(conn1);

        // ---------
        System.out.println("关闭后");
        conn.close();
        conn1.close();
        System.out.println(conn); // null
        System.out.println(conn1); // 原来的对象

//        conn.createStatement(); // NullPointerException
        conn1.createStatement(); // ConnectionIsClosedException
    }
}
