package view;

import model.Customer;
import model.SavingsAccount;
import dao.AccountDAO;
import dao.CustomerDAO;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SavingsAccountView {

    public void show(Stage stage) {

        Label title = new Label("OPEN SAVINGS ACCOUNT");

        TextField customerID = new TextField();
        customerID.setPromptText("Enter Registered Customer ID");

        TextField accountNumber = new TextField();
        accountNumber.setPromptText("Enter Account Number");

        TextField branch = new TextField();
        branch.setPromptText("Enter Branch");

        TextField deposit = new TextField();
        deposit.setPromptText("Enter Deposit");

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

                    if (amount <= 0 || !Double.isFinite(amount)) {
                        message.setText("Enter a valid opening deposit");
                        return;
                    }

                    CustomerDAO customerDAO = new CustomerDAO();
                    Customer customer = customerDAO.getCustomerByID(id);

                    if (customer == null) {
                        message.setText("Customer not found. Register first.");
                        return;
                    }

                    SavingsAccount account = new SavingsAccount(
                            accountNumber.getText().trim(),
                            amount,
                            branch.getText().trim()
                    );

                    AccountDAO accountDAO = new AccountDAO();

                    boolean saved = accountDAO.saveAccount(account, id);

                    if (saved) {
                        customer.addAccount(account);
                        message.setText("Savings Account created successfully!");
                    } else {
                        message.setText("Account could not be saved.");
                    }

                } catch (NumberFormatException e) {
                    message.setText("Enter valid Customer ID and deposit");
                } catch (IllegalArgumentException e) {
                    message.setText(e.getMessage());
                }
            }
        });

        back.setOnAction(event -> {
            OpenAccountView accountView = new OpenAccountView();
            accountView.show(stage);
        });

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(
                title, customerID, accountNumber,
                branch, deposit, submit, message, back
        );

        Scene scene = new Scene(layout, 450, 450);

        stage.setTitle("Savings Account");
        stage.setScene(scene);
        stage.show();
    }
}
