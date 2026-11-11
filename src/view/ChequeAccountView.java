package view;

import model.Customer;
import model.ChequeAccount;
import util.BankData;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChequeAccountView {

    public void show(Stage stage) {

        Label title = new Label("OPEN CHEQUE ACCOUNT");

        TextField customerID = new TextField();
        customerID.setPromptText("Enter Registered Customer ID");

        TextField accountNumber = new TextField();
        accountNumber.setPromptText("Enter Account Number");

        TextField branch = new TextField();
        branch.setPromptText("Enter Branch");

        TextField deposit = new TextField();
        deposit.setPromptText("Enter Opening Deposit (BWP)");

        TextField companyName = new TextField();
        companyName.setPromptText("Enter Company Name");

        TextField companyAddress = new TextField();
        companyAddress.setPromptText("Enter Company Address");

        Button submit = new Button("Create Account");
        Button back = new Button("Back");

        Label message = new Label();


        submit.setOnAction(event -> {

            if (customerID.getText().isBlank()
                    || accountNumber.getText().isBlank()
                    || branch.getText().isBlank()
                    || deposit.getText().isBlank()
                    || companyName.getText().isBlank()
                    || companyAddress.getText().isBlank()) {

                message.setText("Please fill in all fields");

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

                    } else if (amount <= 0 || !Double.isFinite(amount)) {
                        message.setText("Enter a valid opening deposit");

                    } else if (BankData.findAccount(accountNumber.getText().trim()) != null) {
                        message.setText("Account number already exists");

                    } else {
                        ChequeAccount account = new ChequeAccount(
                                accountNumber.getText().trim(),
                                amount,
                                branch.getText().trim(),
                                companyName.getText().trim(),
                                companyAddress.getText().trim()
                        );

                        BankData.accounts.add(account);
                        customer.addAccount(account);

                        message.setText("Cheque Account created successfully!");
                    }

                } catch (NumberFormatException e) {
                    message.setText("Enter valid Customer ID and deposit");

                } catch (IllegalArgumentException e) {
                    message.setText(e.getMessage());
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

        layout.getChildren().addAll(
                title,
                customerID,
                accountNumber,
                branch,
                deposit,
                companyName,
                companyAddress,
                submit,
                message,
                back
        );

        Scene scene = new Scene(layout, 450, 550);

        stage.setTitle("Cheque Account");
        stage.setScene(scene);
        stage.show();
    }
}
