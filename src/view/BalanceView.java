package view;

import model.Account;
import dao.AccountDAO;
import util.BankData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BalanceView {

    public void show(Stage stage) {

        Label title = new Label("VIEW ACCOUNT BALANCE");

        TextField accountNumber = new TextField();
        accountNumber.setPromptText("Enter Account Number");

        Button viewBalance = new Button("View Balance");
        Button applyInterest = new Button("Apply Monthly Interest");
        Button back = new Button("Back");

        Label message = new Label();
        message.setWrapText(true);

        viewBalance.setOnAction(event -> {

            if (accountNumber.getText().isBlank()) {
                message.setText("Please enter account number");
                return;
            }

            AccountDAO accountDAO = new AccountDAO();

            Account account = accountDAO.getAccountByNumber(
                    accountNumber.getText().trim()
            );

            if (account == null) {
                message.setText("Account number not found");
            } else {
                message.setText(
                        "Account Number: " + account.getAccountNumber()
                                + "\nAccount Type: " + account.getClass().getSimpleName()
                                + "\nBranch: " + account.getBranch()
                                + "\nCurrent Balance: BWP "
                                + String.format("%.2f", account.getBalance())
                );
            }
        });

        applyInterest.setOnAction(event -> {

            if (accountNumber.getText().isBlank()) {
                message.setText("Please enter account number");
                return;
            }

            AccountDAO accountDAO = new AccountDAO();

            Account account = accountDAO.getAccountByNumber(
                    accountNumber.getText().trim()
            );

            if (account == null) {
                message.setText("Account number not found");
                return;
            }

            if (!(account instanceof model.InterestBearing)) {
                message.setText("This account does not earn interest");
                return;
            }

            double interest =
                    ((model.InterestBearing) account).calculateInterest();

            double newBalance = account.getBalance() + interest;

            boolean updated = accountDAO.updateBalance(
                    account.getAccountNumber(), newBalance
            );

            if (updated) {
                BankData.addTransaction(
                        account.getAccountNumber(),
                        "Monthly Interest",
                        interest
                );

                message.setText(
                        "Interest applied successfully!"
                                + "\nInterest Added: BWP "
                                + String.format("%.2f", interest)
                                + "\nNew Balance: BWP "
                                + String.format("%.2f", newBalance)
                );
            } else {
                message.setText("Could not update account balance");
            }
        });

        back.setOnAction(event -> {
            DashboardView dashboard = new DashboardView();
            dashboard.show(stage);
        });

        VBox layout = new VBox(15);

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(
                title,
                accountNumber,
                viewBalance,
                applyInterest,
                message,
                back
        );

        Scene scene = new Scene(layout, 450, 500);

        stage.setTitle("View Balance");
        stage.setScene(scene);
        stage.show();
    }
}
