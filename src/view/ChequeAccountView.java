package view;

import model.Customer;
import model.ChequeAccount;
import dao.AccountDAO;
import dao.ChequeAccountDAO;
import dao.CustomerDAO;

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

                    int id = Integer.parseInt(
                            customerID.getText().trim());

                    double amount = Double.parseDouble(
                            deposit.getText().trim());

                    if (amount <= 0 || !Double.isFinite(amount)) {
                        message.setText("Enter a valid opening deposit");
                        return;
                    }

                    CustomerDAO customerDAO = new CustomerDAO();

                    Customer customer =
                            customerDAO.getCustomerByID(id);

                    if (customer == null) {
                        message.setText(
                                "Customer not found. Register first.");
                        return;
                    }

                    ChequeAccount account = new ChequeAccount(
                            accountNumber.getText().trim(),
                            amount,
                            branch.getText().trim(),
                            companyName.getText().trim(),
                            companyAddress.getText().trim()
                    );

                    AccountDAO accountDAO = new AccountDAO();

                    boolean accountSaved =
                            accountDAO.saveAccount(account, id);

                    if (accountSaved) {

                        ChequeAccountDAO chequeDAO =
                                new ChequeAccountDAO();

                        boolean chequeSaved =
                                chequeDAO.saveChequeAccount(account);

                        if (chequeSaved) {

                            customer.addAccount(account);

                            message.setText(
                                    "Cheque Account created successfully!");

                        } else {

                            message.setText(
                                    "Company details could not be saved.");
                        }

                    } else {

                        message.setText(
                                "Cheque Account could not be saved.");
                    }

                } catch (NumberFormatException e) {

                    message.setText(
                            "Enter valid Customer ID and deposit");

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
