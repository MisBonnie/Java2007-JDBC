package com.zzxx.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class Demo01 {
    public static void main(String[] args) throws PropertyVetoException, SQLException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass( "com.mysql.cj.jdbc.Driver" ); //loads the jdbc driver
        dataSource.setJdbcUrl( "jdbc:mysql:///db_day01" );
        dataSource.setUser("root");
        dataSource.setPassword("root");

        Connection conn = dataSource.getConnection();
        System.out.println(conn);
    }
}
