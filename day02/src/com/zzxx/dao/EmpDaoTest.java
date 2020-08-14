package com.zzxx.dao;

import com.zzxx.entity.Emp;
import org.junit.Test;

public class EmpDaoTest {
    // 测试EmpDao中的方法
    EmpDao ed = new EmpDao();
    @Test
    public void testFindById() {
        Emp emp = ed.findEmpById(736);
        System.out.println(emp);
    }
}
