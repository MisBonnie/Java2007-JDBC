package com.zzxx.jdbc;

import com.zzxx.jdbc.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class JDBCDemo04 {
    public static void main(String[] args) {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into user values(null,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "王五六");
            stmt.setInt(2, 28);
            // 时间 java.sql.Date -> java.util.Date
            // mysql数据库插入时间,可以是一个字符串
//            stmt.setString(3, "2020-08-01");
            Date date = new Date();
            stmt.setDate(3, new java.sql.Date(date.getTime()));
            stmt.setString(4, "123");

            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
