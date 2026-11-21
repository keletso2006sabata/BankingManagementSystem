
package dao;

import model.ChequeAccount;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChequeAccountDAO {

    public boolean saveChequeAccount(ChequeAccount account) {

        String sql = "INSERT INTO chequeaccount "
                + "(accountNumber, companyName, companyAddress) "
                + "VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn == null) {
                return false;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, account.getAccountNumber());
                stmt.setString(2, account.getCompanyName());
                stmt.setString(3, account.getCompanyAddress());

                return stmt.executeUpdate() > 0;
            }

        } catch (SQLException e) {
            System.out.println("Cheque account saving failed: "
                    + e.getMessage());
            return false;
        }
    }

    public ChequeAccount getChequeAccountByNumber(String accountNumber) {

        String sql = "SELECT * FROM chequeaccount WHERE accountNumber = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn == null) {
                return null;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, accountNumber);

                try (ResultSet rs = stmt.executeQuery()) {

                    if (rs.next()) {
                        return new ChequeAccount(
                                accountNumber,
                                0,
                                "",
                                rs.getString("companyName"),
                                rs.getString("companyAddress")
                        );
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Cheque account search failed: "
                    + e.getMessage());
        }

        return null;
    }
}
