package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BankingApp extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Banking Management System");
        Label message = new Label("Welcome to our Bank");

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, message);

        Scene scene = new Scene(layout, 400, 300);

        stage.setTitle("Banking Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

