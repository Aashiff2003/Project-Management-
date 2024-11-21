package com.example.tecexpocw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class delete {

    @FXML
    public Button home;
    @FXML
    public Button view;
    @FXML
    public Label addError;
    @FXML
    public Button dltButton;
    @FXML
    public TextField dltProjectIdText;
    @FXML
    public AnchorPane exit;

    private final ProjectManager projectManager = ProjectManager.getInstance();

    @FXML
    public void onHomeButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) home.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
        stage.setTitle("Home!");
        Scene scene = new Scene(root,1100,600);
        stage.setScene(scene);
    }

    @FXML
    public void onViewButoonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) view.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view.fxml")));
        stage.setTitle("View");

        Scene scene = new Scene(root,1300,600);
        stage.setScene(scene);
    }

    @FXML
    public void dltProjectButton(ActionEvent actionEvent)  {
        String errorMessage = validateDeleteProjectInput(dltProjectIdText.getText());
        if (errorMessage!= null) {
            showErrorAlert(errorMessage);
            dltProjectIdText.clear();
        } else {
            int deleteProjectId = Integer.parseInt(dltProjectIdText.getText());
            ProjectManager.getInstance().deleteProject(deleteProjectId);
            ProjectManager.getInstance().saveProjectDetails();
            ProjectManager.getInstance().shortProjectList();
            ProjectManager.getInstance().displayProjects();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("project successfully deleted");
            alert.showAndWait();

        }
    }

    public String validateDeleteProjectInput(String dltId) {

        if (dltId.isEmpty()) {
            return "Project Id is empty";
        }
        try {
            int projectId = Integer.parseInt(dltId);
            if (projectId <= 0) {
                return "Invalid project ID. Please enter a valid integer.";
            }
            if (!projectManager.isProjectIdAvailable(Integer.parseInt(dltId))) {
                return "Project Id is Not Available";
            }
        } catch (NumberFormatException e) {
            return "Invalid project ID. Please enter a valid integer.";
        }
        return null;
    }

    public void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setContentText(message);
        alert.showAndWait();
    }


    public void onExitButtonClickS(MouseEvent mouseEvent) {
        ProjectManager.getInstance().saveProjectDetails();
        System.exit(0);
    }
}
