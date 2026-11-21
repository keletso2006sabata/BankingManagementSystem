package view;

import model.Account;
import model.SavingsAccount;
import model.InvestmentAccount;
import model.ChequeAccount;

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

public class WithdrawView {

    public void show(Stage stage) {

        Label title = new Label("WITHDRAW FUNDS");

        TextField accountNumber = new TextField();
        accountNumber.setPromptText("Enter Account Number");

        TextField amount = new TextField();
        amount.setPromptText("Enter Withdrawal Amount (BWP)");

        Button withdraw = new Button("Withdraw");
        Button back = new Button("Back");

        Label message = new Label();

        withdraw.setOnAction(event -> {

            if (accountNumber.getText().isBlank()
                    || amount.getText().isBlank()) {

                message.setText("Please fill in all fields");

            } else {

                try {

                    double withdrawalAmount =
                            Double.parseDouble(amount.getText().trim());

                    if (withdrawalAmount <= 0
                            || !Double.isFinite(withdrawalAmount)) {

                        message.setText("Enter a valid withdrawal amount");
                        return;
                    }

                    AccountDAO accountDAO = new AccountDAO();

                    Account account = accountDAO.getAccountByNumber(
                            accountNumber.getText().trim()
                    );

                    if (account == null) {

                        message.setText("Account number not found");

                    } else if (account instanceof SavingsAccount) {

                        message.setText(
                                "Savings Account does not allow withdrawals"
                        );

                    } else if (!(account instanceof InvestmentAccount)
                            && !(account instanceof ChequeAccount)) {

                        message.setText("Invalid account type");

                    } else if (withdrawalAmount > account.getBalance()) {

                        message.setText("Insufficient funds");

                    } else {

                        double newBalance =
                                account.getBalance() - withdrawalAmount;

                        boolean updated = accountDAO.updateBalance(
                                account.getAccountNumber(),
                                newBalance
                        );

                        if (updated) {

                            TransactionDAO transactionDAO =
                                    new TransactionDAO();

                            boolean recorded =
                                    transactionDAO.saveTransaction(
                                            account.getAccountNumber(),
                                            "Withdrawal",
                                            withdrawalAmount
                                    );

                            if (recorded) {

                                message.setText(
                                        "Withdrawal successful!\n"
                                                + "New Balance: BWP "
                                                + String.format("%.2f", newBalance)
                                );

                            } else {

                                message.setText(
                                        "Withdrawal successful, "
                                                + "but transaction recording failed."
                                );
                            }

                        } else {

                            message.setText("Withdrawal failed");

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
                withdraw,
                message,
                back
        );

        Scene scene = new Scene(layout, 450, 450);

        stage.setTitle("Withdraw Funds");
        stage.setScene(scene);
        stage.show();
    }
}