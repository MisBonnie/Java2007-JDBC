package com.zzxx.jdbc;

import com.zzxx.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.*;

public class JDBCDemo03 {
    @Test
    public void testDQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_day01", "root", "root");
            Statement stmt = conn.createStatement();
            // 5.执行sql语句  DDL DML DQL
            // 执行DQL
            String sql = "select * from u ";
            /*
               将一整行数据作为一个整体, 并且存入ResultSet中
             */
            ResultSet rs = stmt.executeQuery(sql); // 返回结果集
            rs.next(); // 询问有没有下一个,并且指针向后移动
            // ResultSet获得的数据 就是当前指针位置这一行的数据
            // 获得这一行第一列的数据
            int id1 = rs.getInt(1);
            String name1 = rs.getString(2);
            System.out.println(id1 + " -- " + name1);

            // 获得第二行数据
            boolean is = rs.next();
            int id2 = rs.getInt(1);
            String name2 = rs.getString(2);
            System.out.println(id2 + " -- " + name2);

            boolean is2 = rs.next();
            System.out.println(is2);
            // After end of result set
            int id3 = rs.getInt(1);
            String name3 = rs.getString(2);
            System.out.println(id3 + " -- " + name3);

            System.out.println();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void testDQL2() {
        try {
            Connection conn = JDBCUtils.getConnection();
            Statement stmt = conn.createStatement();
            // 5.执行sql语句  DDL DML DQL
            // 执行DQL
            String sql = "select * from u ";
            ResultSet rs = stmt.executeQuery(sql); // 返回结果集
            while (rs.next()) {
                // ResultSet获得的数据 就是当前指针位置这一行的数据
                // 获得这一行第一列的数据
                int id1 = rs.getInt(1);
                String name1 = rs.getString(2);
                System.out.println(id1 + " -- " + name1);
            }

            System.out.println();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void testDQL3() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_day01", "root", "root");
            Statement stmt = conn.createStatement();
            // 5.执行sql语句  DDL DML DQL
            // 执行DQL
            String sql = "select id i, name from u ";
            ResultSet rs = stmt.executeQuery(sql); // 返回结果集
            while (rs.next()) {
                // ResultSet获得的数据 就是当前指针位置这一行的数据
                // 获得列名为 i 的值
                int id1 = rs.getInt("i");
                String name1 = rs.getString("name");
                System.out.println(id1 + " -- " + name1);
            }
            // 6.释放资源 - finally
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void testDQL3Finally() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_day01", "root", "root");
            stmt = conn.createStatement();
            // 5.执行sql语句  DDL DML DQL
            // 执行DQL
            String sql = "select id i, name from u ";
            rs = stmt.executeQuery(sql); // 返回结果集
            while (rs.next()) {
                // ResultSet获得的数据 就是当前指针位置这一行的数据
                // 获得列名为 i 的值
                int id1 = rs.getInt("i");
                String name1 = rs.getString("name");
                System.out.println(id1 + " -- " + name1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 6.释放资源 - finally
            JDBCUtils.close(rs, stmt, conn);
            /*try {
                // 避免空指针
                if (rs != null) {
                    rs.close();
                }
                stmt.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }*/
        }
    }
}
