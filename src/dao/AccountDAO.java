package dao;

import model.Account;
import model.ChequeAccount;
import model.InvestmentAccount;
import model.SavingsAccount;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    public boolean saveAccount(Account account, int customerID) {

        if (account == null) {
            return false;
        }

        String accountType;

        if (account instanceof SavingsAccount) {
            accountType = "Savings";
        } else if (account instanceof InvestmentAccount) {
            accountType = "Investment";
        } else if (account instanceof ChequeAccount) {
            accountType = "Cheque";
        } else {
            return false;
        }

        String sql = "INSERT INTO account "
                + "(accountNumber, customerID, accountType, balance, branch) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn == null) {
                return false;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, account.getAccountNumber());
                stmt.setInt(2, customerID);
                stmt.setString(3, accountType);
                stmt.setDouble(4, account.getBalance());
                stmt.setString(5, account.getBranch());

                return stmt.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Account saving failed: " + e.getMessage());
            return false;
        }
    }

    public Account getAccountByNumber(String accountNumber) {

        String sql = "SELECT * FROM account "
                + "WHERE TRIM(accountNumber) = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn == null) {
                return null;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, accountNumber.trim());

                try (ResultSet rs = stmt.executeQuery()) {

                    if (rs.next()) {

                        String savedNumber = rs.getString("accountNumber");
                        String type = rs.getString("accountType");
                        double balance = rs.getDouble("balance");
                        String branch = rs.getString("branch");

                        if (type.equalsIgnoreCase("Savings")) {
                            return new SavingsAccount(
                                    savedNumber, balance, branch);
                        }

                        if (type.equalsIgnoreCase("Investment")) {
                            return new InvestmentAccount(
                                    savedNumber, balance, branch);
                        }

                        if (type.equalsIgnoreCase("Cheque")) {

                            ChequeAccountDAO chequeDAO =
                                    new ChequeAccountDAO();

                            ChequeAccount cheque =
                                    chequeDAO.getChequeAccountByNumber(savedNumber);

                            if (cheque != null) {
                                return new ChequeAccount(
                                        savedNumber,
                                        balance,
                                        branch,
                                        cheque.getCompanyName(),
                                        cheque.getCompanyAddress()
                                );
                            }
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Account search failed: " + e.getMessage());
        }

        return null;
    }

    public boolean updateBalance(String accountNumber, double newBalance) {

        String sql = "UPDATE account SET balance = ? "
                + "WHERE accountNumber = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn == null) {
                return false;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setDouble(1, newBalance);
                stmt.setString(2, accountNumber.trim());

                return stmt.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Balance update failed: " + e.getMessage());
            return false;
        }
    }
}
