package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OpenAccountView {

    public void show(Stage stage) {

        Label title = new Label("OPEN BANK ACCOUNT");
        Label message = new Label("Select Account Type");

        Button savings = new Button("Savings Account");
        Button investment = new Button ("Investment Account");
        Button cheque = new Button ("Cheque Account");
        Button back = new Button ("Back");

        savings.setOnAction(event ->{
                SavingsAccountView view = new SavingsAccountView();
                view.show(stage);
    });
        investment.setOnAction(event -> {
                    InvestmentAccountView view = new InvestmentAccountView();
                    view.show(stage);
        });
        cheque.setOnAction(event ->{
            ChequeAccountView view = new ChequeAccountView();
            view.show(stage);
                });
        back.setOnAction(event -> {
            DashboardView dashboard = new DashboardView();
            dashboard.show(stage);
        });

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(title, message, savings, investment, cheque, back);
        Scene scene = new Scene(layout, 450, 450);

        stage.setTitle("Open Account");
        stage.setScene(scene);
        stage.show();
    }
}
