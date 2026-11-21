
package view;

import dao.CustomerDAO;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterView {

    public void show(Stage stage) {

        Label title = new Label("CUSTOMER REGISTRATION");

        TextField customerID = new TextField();
        customerID.setPromptText("Enter Customer ID");

        TextField firstName = new TextField();
        firstName.setPromptText("Enter First Name");

        TextField surname = new TextField();
        surname.setPromptText("Enter Surname");

        TextField address = new TextField();
        address.setPromptText("Enter Address");

        TextField phone = new TextField();
        phone.setPromptText("Enter Phone Number");

        TextField email = new TextField();
        email.setPromptText("Enter Email");

        TextField username = new TextField();
        username.setPromptText("Enter Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Enter Password");

        Button register = new Button("Register");
        Button back = new Button("Back");

        Label message = new Label();

        CustomerDAO customerDAO = new CustomerDAO();

        register.setOnAction(event -> {

            if (customerID.getText().isBlank()
                    || firstName.getText().isBlank()
                    || surname.getText().isBlank()
                    || address.getText().isBlank()
                    || phone.getText().isBlank()
                    || email.getText().isBlank()
                    || username.getText().isBlank()
                    || password.getText().isBlank()) {

                message.setText("Please fill in all fields");

            } else {

                try {

                    int id = Integer.parseInt(
                            customerID.getText().trim());

                    boolean saved = customerDAO.registerCustomer(
                            id,
                            firstName.getText().trim(),
                            surname.getText().trim(),
                            address.getText().trim(),
                            phone.getText().trim(),
                            email.getText().trim(),
                            username.getText().trim(),
                            password.getText()
                    );

                    if (saved) {

                        message.setText(
                                "Customer registered successfully!");

                    } else {

                        message.setText(
                                "Registration failed. Check existing details.");
                    }

                } catch (NumberFormatException e) {

                    message.setText("Enter a valid Customer ID");
                }
            }
        });

        back.setOnAction(event -> {

            DashboardView dashboard = new DashboardView();
            dashboard.show(stage);
        });

        VBox layout = new VBox(12);

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));

        layout.getChildren().addAll(
                title,
                customerID,
                firstName,
                surname,
                address,
                phone,
                email,
                username,
                password,
                register,
                message,
                back
        );

        Scene scene = new Scene(layout, 450, 650);

        stage.setTitle("Customer Registration");
        stage.setScene(scene);
        stage.show();
    }
}
