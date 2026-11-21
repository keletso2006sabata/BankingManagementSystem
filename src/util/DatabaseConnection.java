package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/bankingmanagementdb";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }

    public static class TestConnection {
        public static void main(String[] args) {

            Connection connection =
                    getConnection();

            if (connection != null) {
                System.out.println("Database connected successfully!");
            } else {
                System.out.println("Database connection failed!");
            }
        }
    }
}
