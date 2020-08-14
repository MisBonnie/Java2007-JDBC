package com.zzxx.jdbcTemplate;

import com.zzxx.entity.Emp;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import utils.DruidUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Demo02 {
    JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());
    @Test
    public void insertTest()  {
        // 执行DML的方法
        String sql = "insert into emp(empno,ename,job,sal,comm,hiredate) values(?,?,?,?,?,?)";
        template.update(sql,8000,"jack","CLERK",800,0,"2020-08-14");
    }
    @Test
    public void updateTest()  {
        // 执行DML的方法
        String sql = "update emp set ename=?,sal=?,job=?,comm=?,hiredate=? where empno=?";
        template.update(sql,"jack james",800,"CLERK",100,"2020-08-14",8000);
    }
    @Test
    public void deleteTest()  {
        // 执行DML的方法
        String sql = "delete from emp where empno = ?";
        template.update(sql,8000);
    }

    @Test
    public void findTest()  {
        // 执行DQL的方法
        String sql = "select * from emp where empno=?";
        Map<String, Object> map = template.queryForMap(sql, 7369);
        // {empno=7369, ename=SMITH, job=CLERK, mgr=7902, hiredate=1980-12-17, sal=800, comm=null, deptno=20}
        System.out.println(map);
    }
    @Test
    public void findTest1()  {
        // 执行DQL的方法
        String sql = "select * from emp";
        List<Map<String, Object>> list = template.queryForList(sql);
        list.stream().forEach(System.out::println);
    }

    @Test
    public void findTest2()  {
        // 执行DQL的方法
        String sql = "select count(*) from emp";
        Long count = template.queryForObject(sql, Long.class);
        System.out.println(count);
    }
    @Test
    public void findTest3()  {
        // 执行DQL的方法
        String sql = "select * from emp";
        /**
         * RowMapper: 将结果集进行封装处理, 通过mapRow进行处理
         * query 得到的结果集ResultSet, 并且遍历结果集, 
         *  然后调用RowMapper中的mapRow方法对每一行结果进行封装处理
         */
        List<Emp> list = template.query(sql, new RowMapper<Emp>() {
            @Override
            /**
             * rs: 当前指向的那一行结果数据 rs.getString/Int..
             * i: 当前遍历的次数 - 了解
             * @return 将一行数据封装得到的对象
             */
            public Emp mapRow(ResultSet rs, int i) throws SQLException {
                Emp emp = new Emp();
                emp.setEmpno(rs.getInt("empno"));
//                emp.setEname(rs.getString("ename"));
                emp.setSal(rs.getDouble("sal"));
                emp.setJob(rs.getString("job"));
                emp.setComm(rs.getDouble("comm"));
                emp.setHiredate(rs.getDate("hiredate"));
                return emp;
            }
        });
        list.stream().forEach(System.out::println);
    }
    @Test
    public void findTest4()  {
        // 执行DQL的方法
        String sql = "select * from emp";
        /**
         * RowMapper: 将结果集进行封装处理, 通过mapRow进行处理
         * query 得到的结果集ResultSet, 并且遍历结果集,
         *  然后调用RowMapper中的mapRow方法对每一行结果进行封装处理
         */
        List<Emp> list = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        list.stream().forEach(System.out::println);
    }
}
