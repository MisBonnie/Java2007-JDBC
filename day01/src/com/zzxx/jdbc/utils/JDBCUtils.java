package com.zzxx.jdbc.utils;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/*
   工具类的封装
 */
public class JDBCUtils {
    private static String driver ;
    private static String url ;
    private static String user ;
    private static String password ;
    static {
        // 读取属性集文件
        Properties pro = new Properties();
        try {
            pro.load(JDBCUtils.class.getResourceAsStream("db.properties"));
            driver = pro.getProperty("driver");
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        try {
            // 避免空指针
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void close(Statement stmt, Connection conn) {
        try {
            // 避免空指针
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
