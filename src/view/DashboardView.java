package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DashboardView {
    public void show(Stage stage) {

        Label title = new Label("BANKING MANAGEMENT SYSTEM");
        Label welcome = new Label("Welcome to your Dashboard");

        Button openAccount = new Button("Open Account");
        openAccount.setOnAction(event -> {
            OpenAccountView accountView = new OpenAccountView();
            accountView.show(stage);
        });
        Button deposit = new Button("Deposit Funds");
        deposit.setOnAction(event -> {
        DepositView view = new DepositView();
        view.show(stage);
        });
        Button withdraw = new Button("Withdraw Funds");
        withdraw.setOnAction(event -> {
            WithdrawView view = new WithdrawView();
            view.show(stage);
        });
        Button viewBalance = new Button("View Balance");
        viewBalance.setOnAction(event -> {
            BalanceView view = new BalanceView();
            view.show(stage);
        });
        Button transactionHistory = new Button("Transaction History");
        transactionHistory.setOnAction(event -> {
            TransactionHistoryView view = new TransactionHistoryView();
            view.show(stage);
        });
        Button register = new Button("Customer Registration");
        register.setOnAction(event -> {
            RegisterView view = new RegisterView();
            view.show(stage);
        });
        Button logout = new Button("Logout");

        logout.setOnAction(event -> {
            LoginView login = new LoginView();
            login.show(stage);
        });
        VBox layout = new VBox(15);
        layout.setAlignment(javafx.geometry.Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                title,
                welcome,
                openAccount,
                deposit,
                withdraw,
                viewBalance,
                transactionHistory,
                register,
                logout
        );
        Scene scene = new Scene(layout, 450, 450);
        stage.setTitle("BANKING MANAGEMENT SYSTEM");
        stage.setScene(scene);
        stage.show();
    }
}
