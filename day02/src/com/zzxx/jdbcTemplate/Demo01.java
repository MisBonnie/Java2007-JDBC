package com.zzxx.jdbcTemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.DruidUtils;

public class Demo01 {
    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate(DruidUtils.getDataSource());
        // 执行DML的方法
        String sql = "insert into emp(empno,ename,job,sal,comm,hiredate) values(?,?,?,?,?,?)";
        template.update(sql,8000,"jack","CLERK",800,0,"2020-08-14");
    }
}
