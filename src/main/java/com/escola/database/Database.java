package com.escola.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection getConnection() throws Exception {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:C:/sqlite/escola.db");
    }
}