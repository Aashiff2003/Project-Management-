package com.example.tecexpocw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Login {
    @FXML
    public TextField userName;
    @FXML
    public PasswordField inputPassword;
    @FXML
    public Button loginButton;

    public Button participantLogin;

    public void onParticipantsLoginButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) participantLogin.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ParticipantAdd.fxml")));
        stage.setTitle("Add");
        Scene scene = new Scene(root,1100,600);
        stage.setScene(scene);
    }

    public void onLoginButtonClick(ActionEvent actionEvent) throws IOException {
        String name = userName.getText();
        String Password = inputPassword.getText();
        if(userName.equals("") || inputPassword.equals("")) {
            showErrorAlert("fields can't be empty");
        }
        else if(name.equalsIgnoreCase("sarah") && Password.equals("123456")) {
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
            stage.setTitle("Add");
            Scene scene = new Scene(root,1100,600);
            stage.setScene(scene);
        }
        else {
            showErrorAlert("invalid username or password");
        }
    }



    public void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
