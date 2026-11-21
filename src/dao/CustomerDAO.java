
package dao;

import model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DatabaseConnection;

public class CustomerDAO {

    public boolean registerCustomer(
            int customerID,
            String firstname,
            String surname,
            String address,
            String phoneNumber,
            String email,
            String username,
            String password) {

        String sql = "INSERT INTO customer "
                + "(customerID, firstname, surname, address, "
                + "phoneNumber, email, username, password) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {

            if (connection == null) {
                return false;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, customerID);
                statement.setString(2, firstname);
                statement.setString(3, surname);
                statement.setString(4, address);
                statement.setString(5, phoneNumber);
                statement.setString(6, email);
                statement.setString(7, username);
                statement.setString(8, password);

                return statement.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Customer registration failed: " + e.getMessage());
            return false;
        }
    }

    public Customer getCustomerByID(int customerID) {

        String sql = "SELECT * FROM customer WHERE customerID = ?";

        try (Connection connection = DatabaseConnection.getConnection()) {

            if (connection == null) {
                return null;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, customerID);

                try (ResultSet result = statement.executeQuery()) {

                    if (result.next()) {

                        return new Customer(
                                result.getInt("customerID"),
                                result.getString("firstname"),
                                result.getString("surname"),
                                result.getString("address"),
                                result.getString("phoneNumber"),
                                result.getString("email")
                        );
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Customer search failed: " + e.getMessage());
        }

        return null;
    }

    public boolean loginCustomer(String username, String password) {

        String sql = "SELECT * FROM customer WHERE username = ? AND password = ?";

        try (Connection connection = DatabaseConnection.getConnection()) {

            if (connection == null) {
                return false;
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, username);
                statement.setString(2, password);

                try (ResultSet result = statement.executeQuery()) {
                    return result.next();
                }
            }

        } catch (SQLException e) {
            System.out.println("Login failed: " + e.getMessage());
        }

        return false;
    }
}
