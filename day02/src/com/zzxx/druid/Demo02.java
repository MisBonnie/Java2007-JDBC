package com.zzxx.druid;

import com.zzxx.entity.Emp;
import org.junit.Test;
import utils.DruidUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Demo02 {
    @Test
    /*
      向Emp表中添加一条数据, 应该插入的是一个emp对象
     */
    public void test01() throws SQLException {
        String sql = "insert into emp(empno,ename,job,sal,comm,hiredate) values(?,?,?,?,?,?)";
        Connection conn = DruidUtils.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        // 代码冗余度很高, 很繁琐
        stmt.setInt(1, 8000);
        stmt.setString(2,"JACK");
        stmt.setString(3, "ANALYST");
        stmt.setDouble(4, 6000);
        stmt.setDouble(5, 300);
        stmt.setString(6, "2020-08-14");

        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
    @Test
    public void test02Update() throws Exception {
        String sql = "update emp set ename=?,sal=?,job=?,comm=?,hiredate=? where empno=?";
        Connection conn = DruidUtils.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(6, 8000);
        stmt.setString(1,"JACK James");
        stmt.setString(3, "CLERK");
        stmt.setDouble(2, 5000);
        stmt.setDouble(4, 300);
        stmt.setString(5, "2020-08-14");

        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    @Test
    public void test03Delete() throws Exception {
        String sql = "delete from emp where empno = ?";
        Connection conn = DruidUtils.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, 8000);

        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
    @Test
    public void test04SelectById() throws Exception {
        String sql = "select * from emp where empno = ?";
        Connection conn = DruidUtils.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, 7369);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // 对象的封装很繁琐
            Emp emp = new Emp();
            emp.setEmpno(rs.getInt("empno"));
//            emp.setEname(rs.getString("ename"));
            emp.setSal(rs.getDouble("sal"));
            emp.setJob(rs.getString("job"));
            emp.setComm(rs.getDouble("comm"));
            emp.setHiredate(rs.getDate("hiredate"));
            System.out.println(emp);
        }
    }
    @Test
    public void test05SelectAll() throws Exception {
        String sql = "select * from emp";
        Connection conn = DruidUtils.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);

        ResultSet rs = stmt.executeQuery();
        List<Emp> list = new ArrayList<>();
        while (rs.next()) {
            Emp emp = new Emp();
            emp.setEmpno(rs.getInt("empno"));
//            emp.setEname(rs.getString("ename"));
            emp.setSal(rs.getDouble("sal"));
            emp.setJob(rs.getString("job"));
            emp.setComm(rs.getDouble("comm"));
            emp.setHiredate(rs.getDate("hiredate"));
            System.out.println(emp);
            list.add(emp);
        }
        System.out.println(list.size());
    }
    @Test
    public void test06SelectCount() throws Exception {
        String sql = "select count(*) from emp";
        Connection conn = DruidUtils.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            long count = rs.getLong(1);
            System.out.println(count);
        }
    }
}
