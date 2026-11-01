package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;

public class LoginView {

    public void show(Stage stage) {
        Label title = new Label("BANKING MANGEMENT SYSTEM");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");

        Button loginButton = new Button("Login");

        Label message = new Label();

        loginButton.setOnAction(event -> {

            String Username = usernameField.getText();
            String Password = passwordField.getText();

            if (Username.isBlank() || Password.isBlank()){
                message.setText("Please enter username and password");
            } else{
                message.setText("Login details entered");
            }
        });
        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        layout.getChildren().addAll(title,usernameField,passwordField,loginButton,message);

        Scene scene = new Scene(layout, 400, 350);

        stage.setTitle("BANKING MANAGEMENT SYSTEM - Login");
        stage.setScene(scene);
        stage.show();
    }
}
