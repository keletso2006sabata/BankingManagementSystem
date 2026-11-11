package view;

import model.Customer;
import model.InvestmentAccount;
import util.BankData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InvestmentAccountView {

    public void show(Stage stage) {

        Label title = new Label("Open Investment Account");
        TextField customerID = new TextField();
        customerID.setPromptText("Enter Registered Customer ID");

        TextField accountNumber = new TextField();
        accountNumber.setPromptText("Enter Account Number");

        TextField branch = new TextField();
        branch.setPromptText("Enter Branch");

        TextField deposit = new TextField();
        deposit.setPromptText("Enter Opening Deposit (BWP)");

        Button submit = new Button("Create Account");
        Button back = new Button("Back");

        Label message = new Label();


        submit.setOnAction(event -> {
            if (customerID.getText().isBlank()
                    || accountNumber.getText().isBlank()
                    || branch.getText().isBlank()
                    || deposit.getText().isBlank()) {

                message.setText("Please fill all the fields");

            } else {
                try {
                    int id = Integer.parseInt(customerID.getText().trim());
                    double amount = Double.parseDouble(deposit.getText());

                    Customer customer = null;

                    for (Customer c : BankData.customers) {
                        if (c.getCustomerID() == id) {
                            customer = c;
                            break;
                        }
                    }

                    if (customer == null) {
                        message.setText("Customer not found. Register first.");

                    } else if (amount < 500 || !Double.isFinite(amount)) {
                        message.setText("Minimum opening deposit is BWP 500.00");

                    } else if (BankData.findAccount(accountNumber.getText().trim()) != null) {
                        message.setText("Account number already exists");

                    } else {
                        InvestmentAccount account = new InvestmentAccount(
                                accountNumber.getText().trim(),
                                amount,
                                branch.getText().trim()
                        );

                        BankData.accounts.add(account);
                        customer.addAccount(account);

                        message.setText("Investment Account created successfully!");
                    }

                } catch (NumberFormatException e) {
                    message.setText("Enter valid Customer ID and deposit");
                }
            }
        });

        back.setOnAction(event -> {
            OpenAccountView view = new OpenAccountView();
            view.show(stage);
        });

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(title, customerID, accountNumber, branch, deposit, submit,message, back);

        Scene scene = new Scene(layout, 450, 450);

        stage.setTitle("Investment Account");
        stage.setScene(scene);
        stage.show();

    }
}

