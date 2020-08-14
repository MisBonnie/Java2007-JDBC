package com.zzxx.jdbc.login;

import com.zzxx.jdbc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginDemo {
    public static void main(String[] args) {
        // 1.控制台输入账号密码
        Scanner console = new Scanner(System.in);
        System.out.println("请输入账号:");
        String username = console.nextLine();
        System.out.println("请输入密码:");
        String password = console.nextLine();

        // 2.调用登录方法
        User user = login(username, password);

        if (user == null) {
            System.out.println("用户名/密码错误!");
        } else {
            System.out.println(user.getName() + "欢迎你!");
        }
    }
    public static User login(String username, String password) {
        // 2.封装一个sql语句
        String sql = "select * from user where name = '" +
                username +
                "' and password = '" +
                password +
                "'";
        // 3.连接数据库, 查询 executeQuery
        Connection conn = JDBCUtils.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            // 4.ResultSet
            // 有没有登录成功 next() 是true - 成功
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setAge(rs.getInt("age"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPassword(rs.getString("password"));
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // 抛出异常
        return null;
    }

}
