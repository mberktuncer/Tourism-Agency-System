package main.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Config {

    public static final String DB_URL = "jdbc:postgresql://localhost:5432/tourismagency";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "postgres";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

}
