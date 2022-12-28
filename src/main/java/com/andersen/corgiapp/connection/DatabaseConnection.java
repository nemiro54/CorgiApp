package com.andersen.corgiapp.connection;

import com.andersen.corgiapp.exception.NoConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseProperties DATABASE_PROPERTIES = new DatabaseProperties();

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    DATABASE_PROPERTIES.getUrl(),
                    DATABASE_PROPERTIES.getUsername(),
                    DATABASE_PROPERTIES.getPassword()
            );
        } catch (SQLException e) {
            throw new NoConnectionException("Can't get database connection", e);
        }
    }

    public static void setDatabaseProperties(DatabaseProperties databaseProperties) {
        DATABASE_PROPERTIES = databaseProperties;
    }
}