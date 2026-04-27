package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String url = "jdbc:sqlite:database.sqlite";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
