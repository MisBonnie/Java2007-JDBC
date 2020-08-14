package com.zzxx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo01 {
    public static void main(String[] args) {
        // 1.导入jar包
        /* 2.加载驱动
            可能产生异常: ClassNotFoundException
            原因: 1.没有导入jar包
                 2.类名写错
         */
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 3.获得连接对象 DriverManager
            /* DriverManager 提供了一个静态方法 getConnection
               参数1: 连接数据库的url地址
                     jdbc:mysql://ip:port/dataBase
               参数2: 连接数据库的账号
               参数3: 连接数据库的密码

               可能产生异常: SQLException
                 1.dataBase 指定错误
                 2.账号/密码错误
                 3.端口错误 -> Connection refused
                 4.ip错误 -> Network is unreachable
                 5.jdbc:mysql 错误 -> No suitable driver
                   结论: 连接数据库url需要匹配合适的驱动类
             */
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_day01", "root", "root");
            // 4.获得操作数据库的Statement对象 - 执行sql语句
            Statement stmt = conn.createStatement();
            // 5.执行sql语句  DDL DML DQL
            String sql = "create table u (id int primary key, name varchar(20))";
            stmt.execute(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
