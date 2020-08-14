package com.zzxx.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class Demo03 {
    public static void main(String[] args) throws PropertyVetoException, SQLException {
        DataSource dataSource = new ComboPooledDataSource();

        for (int i = 0; i < 11; i++) {
            Connection conn = dataSource.getConnection();
            System.out.println(conn);
            if (i == 5) {
                conn.close();
            }
        }
    }
}
