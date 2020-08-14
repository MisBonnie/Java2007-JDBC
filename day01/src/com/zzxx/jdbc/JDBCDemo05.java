package com.zzxx.jdbc;

import com.zzxx.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class JDBCDemo05 {
    @Test
    public void testStatement() throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        String sql1 = "insert into user values(null, 'rose', 18, '1998-09-01', '1234')";
        String sql2 = "insert into user values(null, 'jack', 20, '1997-09-01', '12345')";

        Statement stmt = conn.createStatement();
        // 执行了两次SQL, 数据库编译了两次
        stmt.executeUpdate(sql1);
        stmt.executeUpdate(sql2);
        JDBCUtils.close(stmt, conn);
    }
    @Test
    public void testPreparedStatement() throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into user values(null,?,?,?,?)";
        // 预编译SQL, 一次
        PreparedStatement stmt = conn.prepareStatement(sql);
        // 执行第一次sql - 传递一次参数
        stmt.setString(1, "赵六");
        stmt.setInt(2, 28);
        stmt.setString(3, "2020-08-01");
        stmt.setString(4, "123");
        // 执行一次sql
        stmt.executeUpdate();

        stmt.setString(1, "赵老六");
        stmt.setInt(2, 28);
        stmt.setString(3, "2020-08-01");
        stmt.setString(4, "123");
        // 第二次执行SQL
        stmt.executeUpdate();
        JDBCUtils.close(stmt, conn);
    }
    // 批量执行SQL
    @Test
    public void testBatch() throws SQLException {
        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into user values(null,?,?,?,?)";
        // 预编译SQL, 一次
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < 10; i++) {
            stmt.setString(1, "赵六"+i);
            stmt.setInt(2, 28);
            stmt.setString(3, "2020-08-01");
            stmt.setString(4, "123");

            // 不马上执行SQL
            // 添加到SQL执行的队列中
            stmt.addBatch();
        }

        // 批量执行SQL队列中所有的语句
        stmt.executeBatch();
        // 清空之前的执行队列
        stmt.clearBatch();

        JDBCUtils.close(stmt, conn);
    }
}
