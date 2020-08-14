package com.zzxx.jdbc;

import com.zzxx.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.*;

public class JDBCDemo06 {
    @Test
    public void testPreparedStatement() {
        Connection conn = JDBCUtils.getConnection();
        PreparedStatement stmt = null;
        // 1.设置事务为手动提交
        try {
            conn.setAutoCommit(false);
            String sql = "insert into user values(null,?,?,?,?)";
            // 预编译SQL, 一次
            stmt = conn.prepareStatement(sql);
            // 执行第一次sql - 传递一次参数
            stmt.setString(1, "赵六11");
            stmt.setInt(2, 28);
            stmt.setString(3, "2020-08-01");
            stmt.setString(4, "123");
            // 执行一次sql
            stmt.executeUpdate();

            // 保存当前事务结点 => savepoint a - 了解
//          Savepoint a = conn.setSavepoint();
            stmt.setString(1, "赵老六1");
            stmt.setInt(2, 28);
            stmt.setString(3, "2020-08-01");
            stmt.setString(4, "123");
            // 第二次执行SQL
            stmt.executeUpdate();
            // 事务提交
            conn.commit();
        } catch (Exception throwables) {
            // 事务回滚 -> 通常是在异常中回滚
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            JDBCUtils.close(stmt, conn);
        }

        // 了解
        // 回滚到保存的a结点中 => rollback to a
//        conn.rollback(a);
    }

}
