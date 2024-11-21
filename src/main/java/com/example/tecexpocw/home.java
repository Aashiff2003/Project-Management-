package com.example.tecexpocw;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class home {
    @FXML
    public ImageView add;
    @FXML
    public ImageView update;
    @FXML
    public ImageView delete;
    @FXML
    public ImageView spotlight;
    @FXML
    public ImageView view;
    @FXML
    public ImageView exit;
    @FXML
    public ImageView back;


    @FXML
    public void onAddButoonClick(MouseEvent actionEvent) throws IOException {
        Stage stage = (Stage) add.getScene().getWindow();
        Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("apd.fxml")));
        stage.setTitle("Add");
        Scene scene = new Scene(root,1100,600);
        stage.setScene(scene);
    }
    @FXML

    public void onUpdateButoonClick(MouseEvent actionEvent) throws IOException {
        Stage stage = (Stage) update.getScene().getWindow();
        Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("upd.fxml")));
        stage.setTitle("update");

        Scene scene = new Scene(root,1100,600);
        stage.setScene(scene);
    }
    @FXML

    public void onDeletButoonClick(MouseEvent actionEvent) throws IOException {
        Stage stage = (Stage) delete.getScene().getWindow();
        Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("dpd.fxml")));
        stage.setTitle("Delete");

        Scene scene = new Scene(root,1100,600);
        stage.setScene(scene);
    }
    @FXML

    public void onSpotlightButoonClick(MouseEvent actionEvent) throws IOException {
        ArrayList<String> projectCategory = ProjectManager.getInstance().getProjectCategory();

        if (projectCategory.size() < 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("At least 3 categories are needed to use this option");
            alert.showAndWait();
            return;
        }

        ProjectManager.getInstance().spotlightSelection();

        Stage stage = (Stage) spotlight.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("spotLight.fxml")));
        stage.setTitle("Spot Light");

        Scene scene = new Scene(root, 1300, 600);
        stage.setScene(scene);
    }
    @FXML

    public void onViewButoonClick(MouseEvent actionEvent) throws IOException {
        Stage stage = (Stage) view.getScene().getWindow();
        Parent root =FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view.fxml")));
        stage.setTitle("View");

        Scene scene = new Scene(root,1300,600);
        stage.setScene(scene);
    }

    public void onExitButtonClick(MouseEvent mouseEvent) {
        ProjectManager.getInstance().saveProjectDetails();
        System.exit(0);
    }


    public void onBackButtonClick(MouseEvent contextMenuEvent) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage.setTitle("login!");
        Scene scene = new Scene(root,800,600);
        stage.setScene(scene);
    }
}
