package com.example.tecexpocw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class View implements Initializable {
    @FXML
    public Button home;
    @FXML
    public TableView<Project> projectTableView;
    @FXML
    public TableColumn<Project, Integer> projectIdColumn;
    @FXML
    public TableColumn<Project, String> projectNameColumn;
    @FXML
    public TableColumn<Project, String> projectCategoryColumn;
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
        ProjectManager.getInstance().shortProjectList();
        projectIdColumn.setCellValueFactory(new PropertyValueFactory<>("projectId"));
        projectNameColumn.setCellValueFactory(new PropertyValueFactory<>("projectName"));
        projectCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("projectCategory"));
        projectMembersColumn.setCellValueFactory(new PropertyValueFactory<>("projectMembers"));
        projectDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("projectDescription"));
        projectCountryColumn.setCellValueFactory(new PropertyValueFactory<>("projectCountry"));
        projectImageColumn.setCellValueFactory(cellData -> cellData.getValue().projectImageProperty());
        projectImageColumn.setCellFactory(this::thumbnailCellFactory);

        List<Project> projectList = ProjectManager.getInstance().getProjectList();
        if (projectList!= null) {
            ObservableList<Project> observableList = FXCollections.observableArrayList(projectList);
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
    public void onHomeButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) home.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
        stage.setTitle("View");

        Scene scene = new Scene(root, 1100, 600);
        stage.setScene(scene);
    }

    public void onExitButtonClick(MouseEvent mouseEvent) {
        ProjectManager.getInstance().saveProjectDetails();
        System.exit(0);
    }
}