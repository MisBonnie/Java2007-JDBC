package com.zzxx.jdbc;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo02 {
    @Test
    public void testDDL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_day01", "root", "root");
            Statement stmt = conn.createStatement();
            // 5.执行sql语句  DDL DML DQL
            /* Statement 中执行sql语句的方法有三个
                boolean execute(String sql)
                    DDL DML: 返回false    DQL: 返回true
                    因为DML 和 DQL有专门的执行方法, 所以该方法通常用来执行DDL
                    返回值: 有没有结果
                int executeUpdate(String sql)
                    只能执行 DML
                    返回值: 影响了几行
                ResultSet executeQuery(String sql)
                    只能执行 DQL
                    返回值: 查询出来的结果集
             */
            // 执行DDL
            String sql = "create table u1 (id int primary key, name varchar(20))";
            boolean b = stmt.execute(sql); // false
            System.out.println(b);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void testDML() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_day01", "root", "root");
            Statement stmt = conn.createStatement();
            // 5.执行sql语句  DDL DML DQL
            // 执行DML
            String sql = "insert into u values(1, 'lucy')";
            boolean b = stmt.execute(sql); // false
            System.out.println(b);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void testDQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_day01", "root", "root");
            Statement stmt = conn.createStatement();
            // 5.执行sql语句  DDL DML DQL
            // 执行DQL
            String sql = "select * from u ";
            boolean b = stmt.execute(sql); // true
            System.out.println(b);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void testDML2() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_day01", "root", "root");
            Statement stmt = conn.createStatement();
            // 5.执行sql语句  DDL DML DQL
            // 执行DML
            String sql = "insert into u values(2, 'tom')";
            int i = stmt.executeUpdate(sql); // 1
            System.out.println(i);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
