package com.escola.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection("jdbc:sqlite:escola.db");
    }
}
