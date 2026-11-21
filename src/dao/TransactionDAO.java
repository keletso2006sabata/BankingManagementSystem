package dao;

import model.Transaction;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDAO {

    public boolean recordTransaction(
            String accountNumber,
            String transactionType,
            double amount) {

        String sql = "INSERT INTO `transaction` "
                + "(accountNumber, transactionType, amount, transactionDate) "
                + "VALUES (?, ?, ?, NOW())";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);
            statement.setString(2, transactionType);
            statement.setDouble(3, amount);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {
            System.out.println("Transaction recording failed: "
                    + e.getMessage());
            return false;
        }
    }

    public boolean saveTransaction(
            String accountNumber,
            String transactionType,
            double amount) {

        String sql = "INSERT INTO transaction "
                + "(accountNumber, transactionType, amount) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn == null) {
                return false;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, accountNumber);
                stmt.setString(2, transactionType);
                stmt.setDouble(3, amount);

                return stmt.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Transaction saving failed: " + e.getMessage());
            return false;
        }
    }
    public List<Transaction> getTransactionsByAccountNumber(String accountNumber) {

        List<Transaction> transactions = new ArrayList<>();

        String sql = "SELECT * FROM `transaction` WHERE accountNumber = ? ORDER BY transactionDate DESC";

        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn == null) {
                return transactions;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, accountNumber.trim());

                try (ResultSet rs = stmt.executeQuery()) {

                    while (rs.next()) {

                        Transaction transaction = new Transaction(
                                rs.getInt("transactionID"),
                                rs.getString("accountNumber"),
                                rs.getString("transactionType"),
                                rs.getDouble("amount"),
                                rs.getTimestamp("transactionDate").toLocalDateTime()
                        );

                        transactions.add(transaction);
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println("Transaction history failed: " + e.getMessage());
        }

        return transactions;
    }
}
