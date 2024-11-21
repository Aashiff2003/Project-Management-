package com.example.tecexpocw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Spotlight implements Initializable {

    @FXML
    public Button points;
    @FXML
    public Button visual;
    @FXML
    public TableView<Project> projectTableView;
    @FXML
    public TableColumn<Project, Integer> projectIdColumn;
    @FXML
    public TableColumn<Project, String> projectCategoryColumn;
    @FXML
    public TableColumn<Project, String> projectNameColumn;
    @FXML
    public TableColumn<Project, String> projectMembersColumn;
    @FXML
    public TableColumn<Project, String> projectDescriptionColumn;
    @FXML
    public TableColumn<Project, String> projectCountryColumn;
    @FXML
    public TableColumn<Project, Image> projectImageColumn;
    @FXML
    public ImageView exit;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        projectIdColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        projectCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("projectCategory"));
        projectMembersColumn.setCellValueFactory(new PropertyValueFactory<>("projectMembers"));
        projectDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("projectDescription"));
        projectCountryColumn.setCellValueFactory(new PropertyValueFactory<>("projectCountry"));
        projectImageColumn.setCellValueFactory(cellData -> cellData.getValue().projectImageProperty());
        projectImageColumn.setCellFactory(this::thumbnailCellFactory);

        List<Project> spotlightProjectDetails = ProjectManager.getInstance().getSpotlightProjectDetails();
        if (spotlightProjectDetails != null) {
            ObservableList<Project> observableList = FXCollections.observableArrayList(spotlightProjectDetails);
            projectTableView.setItems(observableList);
        }
    }

    private TableCell<Project, Image> thumbnailCellFactory(TableColumn<Project, Image> param) {
        return new TableCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(Image image, boolean empty) {
                super.updateItem(image, empty);
                if (empty || image == null) {
                    imageView.setImage(null);
                    setGraphic(null);
                } else {
                    imageView.setImage(image);
                    imageView.setFitWidth(100);
                    imageView.setFitHeight(80);
                    setGraphic(imageView);
                }
            }
        };
    }
    @FXML
    public void onPointButtonClick(ActionEvent actionEvent) {
        pointDialogWindow();
    }



    public void pointDialogWindow() {
        ArrayList<String[]> projectPointList = ProjectManager.getInstance().getProjectPointList();
        for (Project project : ProjectManager.getInstance().getSpotlightProjectDetails()) {
            int totalPoints = 0;
            for (int i = 1; i <= 4; i++) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Add Point");
                dialog.setHeaderText("Enter the point of judge " + i + " for project " + project.getProjectName());
                dialog.getDialogPane().setContentText("Points (1-5 stars, e.g. *****): ");

                String input;
                int points;
                while (true) {
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()) {
                        input = result.get();
                        if (input.isEmpty()) {
                            showErrorAlert("Please enter a valid input.");
                            continue;
                        }
                        points = 0;
                        boolean isValidInput = true;
                        for (char c : input.toCharArray()) {
                            if (c == '*') {
                                points++;
                            } else {
                                isValidInput = false;
                                break;
                            }
                        }
                        if (!isValidInput) {
                            showErrorAlert("Invalid input. Please enter 1-5 stars.");
                            continue;
                        }
                        if (points < 1 || points > 5) {
                            showErrorAlert("Invalid input. Please enter 1-5 stars.");
                            continue;
                        }
                        break; // valid input, exit the loop
                    }
                }
                totalPoints += points;
                System.out.println("Point added: " + points);
            }
            String[] temp = new String[3];
            temp[0] = String.valueOf(project.getProjectId());
            temp[1] = project.getProjectCountry();
            temp[2] = String.valueOf(totalPoints);
            projectPointList.add(temp);

        }
        ProjectManager.getInstance().shortProjectPointList();
        for (String[] projectPoints  : ProjectManager.getInstance().getProjectPointList()) {
            System.out.println(Arrays.toString(projectPoints));
        }

    }
    @FXML
    public void onVisualButtonClick(ActionEvent actionEvent) throws IOException {
        if(ProjectManager.getInstance().getProjectPointList().isEmpty()){
            showErrorAlert("first input the points");
        }
        else {
            Stage stage = (Stage) visual.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Visualizing .fxml")));
            stage.setTitle("Visualizing Spotlight");
            Scene scene = new Scene(root, 1100, 600);
            stage.setScene(scene);
        }

    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void onExitButtonClick(MouseEvent mouseEvent) {
        ProjectManager.getInstance().saveProjectDetails();
        System.exit(0);
    }
}

