package com.zzxx.dao;

import com.zzxx.entity.Emp;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import utils.DruidUtils;

import java.util.List;

public class EmpDao {
    // 后期 template 的创建 交给 Spring框架来做
    private JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());

    public void insertEmp(Emp emp) {
        // 执行DML的方法
        String sql = "insert into emp(empno,ename,job,sal,comm,hiredate) values(?,?,?,?,?,?)";
//        stmt.setDate(date);
        template.update(sql, emp.getEmpno(), emp.getEmpno(), emp.getJob(), emp.getSal(), emp.getComm(), emp.getHiredate());
    }

    public void updateEmp(Emp e) {
        // 执行DML的方法
        String sql = "update emp set ename=?,sal=?,job=?,comm=?,hiredate=? where empno=?";
        template.update(sql, e.getEname(), e.getSal(), e.getJob(), e.getComm(), e.getHiredate(), e.getEmpno());
    }

    public void deleteEmpById(int id) {
        // 执行DML的方法
        String sql = "delete from emp where empno = ?";
        template.update(sql, id);
    }

    public Emp findEmpById(int id) {
        // 执行DQL的方法
        String sql = "select * from emp where empno=?";
        List<Emp> list = template.query(sql, new BeanPropertyRowMapper<>(Emp.class), id);
        // 如果id查不出来, 结果会不会有影响
        if (list == null || list.size() == 0) {
            return null;
        }
        return list.get(0);
    }

    public List<Emp> findAllEmp() {
        // 执行DQL的方法
        String sql = "select * from emp";
        List<Emp> list = template.query(sql, new BeanPropertyRowMapper<>(Emp.class));
        return list;
    }

    public long findCount() {
        // 执行DQL的方法
        String sql = "select count(*) from emp";
        Long count = template.queryForObject(sql, Long.class);
        return count;
    }


}
