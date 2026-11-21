package view;

import model.Account;
import dao.AccountDAO;
import dao.TransactionDAO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DepositView {

    public void show(Stage stage) {

        Label title = new Label("DEPOSIT FUNDS");

        TextField accountNumber = new TextField();
        accountNumber.setPromptText("Enter Account Number");

        TextField amount = new TextField();
        amount.setPromptText("Enter Deposit Amount (BWP)");

        Button deposit = new Button("Deposit");
        Button back = new Button("Back");

        Label message = new Label();

        deposit.setOnAction(event -> {

            if (accountNumber.getText().isBlank()
                    || amount.getText().isBlank()) {

                message.setText("Please fill in all fields");

            } else {

                try {

                    double depositAmount =
                            Double.parseDouble(amount.getText().trim());

                    if (depositAmount <= 0
                            || !Double.isFinite(depositAmount)) {

                        message.setText("Enter a valid deposit amount");
                        return;
                    }

                    AccountDAO accountDAO = new AccountDAO();

                    Account account = accountDAO.getAccountByNumber(
                            accountNumber.getText().trim()
                    );

                    if (account == null) {

                        message.setText("Account number not found");

                    } else {

                        double newBalance =
                                account.getBalance() + depositAmount;

                        boolean updated = accountDAO.updateBalance(
                                account.getAccountNumber(),
                                newBalance
                        );

                        if (updated) {

                            TransactionDAO transactionDAO =
                                    new TransactionDAO();

                            transactionDAO.saveTransaction(
                                    account.getAccountNumber(),
                                    "Deposit",
                                    depositAmount
                            );

                            message.setText("Deposit successful!");

                        } else {

                            message.setText("Deposit failed");

                        }
                    }

                } catch (NumberFormatException e) {

                    message.setText("Please enter a valid amount");

                }
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
                amount,
                deposit,
                message,
                back
        );

        Scene scene = new Scene(layout, 450, 450);

        stage.setTitle("Deposit Funds");
        stage.setScene(scene);
        stage.show();
    }

}
