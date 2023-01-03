package com.andersen.corgiapp.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static final HikariConfig config = new HikariConfig("/db.properties");
    private static final HikariDataSource ds = new HikariDataSource(config);

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private DataSource() {
    }
}