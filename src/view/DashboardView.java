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
        Button deposit = new Button("Deposit Funds");
        Button withdraw = new Button("Withdraw Funds");
        Button viewBalance = new Button("View Balance");
        Button transactionHistory = new Button("Transaction History");
        Button logout = new Button("Logout");

        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                title,
                welcome,
                openAccount,
                deposit,
                withdraw,
                viewBalance,
                transactionHistory,
                logout
        );
        Scene scene = new Scene(layout, 450, 450);
        stage.setScene(scene);
        stage.show();
    }
}
