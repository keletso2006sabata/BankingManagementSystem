
package view;

import model.Account;
import model.Transaction;
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

import java.util.List;

public class TransactionHistoryView {

    public void show(Stage stage) {

        Label title = new Label("TRANSACTION HISTORY");

        TextField accountNumber = new TextField();
        accountNumber.setPromptText("Enter Account Number");

        Button viewHistory = new Button("View History");
        Button back = new Button("Back");

        Label message = new Label();
        message.setWrapText(true);

        viewHistory.setOnAction(event -> {

            if (accountNumber.getText().isBlank()) {

                message.setText("Please enter account number");

            } else {

                String number = accountNumber.getText().trim();

                AccountDAO accountDAO = new AccountDAO();

                Account account = accountDAO.getAccountByNumber(number);

                if (account == null) {

                    message.setText("Account number not found");

                } else {

                    TransactionDAO transactionDAO = new TransactionDAO();

                    List<Transaction> transactions =
                            transactionDAO.getTransactionsByAccountNumber(number);

                    if (transactions.isEmpty()) {

                        message.setText("No transactions found");

                    } else {

                        StringBuilder history = new StringBuilder();

                        for (Transaction transaction : transactions) {

                            history.append(transaction.toString())
                                    .append("\n\n");
                        }

                        message.setText(history.toString());
                    }
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
                viewHistory,
                message,
                back
        );

        Scene scene = new Scene(layout, 550, 550);

        stage.setTitle("Transaction History");
        stage.setScene(scene);
        stage.show();
    }
}
