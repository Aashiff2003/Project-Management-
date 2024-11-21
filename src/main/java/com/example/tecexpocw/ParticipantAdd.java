package com.example.tecexpocw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ParticipantAdd {

    @FXML
    public TextField projectIdText;
    @FXML
    public TextField projectNameText;
    @FXML
    public TextField projectCategoryText;
    @FXML
    public TextField projectDescriptionText;
    @FXML
    public TextField projectMembersText;
    @FXML
    public TextField projectCountryText;
    @FXML
    public Button addReset;
    @FXML
    public Button addSubmit;
    @FXML
    public ImageView imageView;
    @FXML
    public Button imageChooseButton;
    @FXML
    public ImageView back;


    @FXML
    public void onAddResetButoonClick(ActionEvent actionEvent) {
        projectIdText.clear();
        projectNameText.clear();
        projectCategoryText.clear();
        projectDescriptionText.clear();
        projectMembersText.clear();
        projectCountryText.clear();
        imageView.setImage(null);
    }
    public void onAddSubmitButoonClick(ActionEvent actionEvent) {
        try {
            Project newProject = getProject();
            String errorMessage = validateProject(newProject);
            if (errorMessage.isEmpty()) {
                ProjectManager.getInstance().addProject(newProject);
                ProjectManager.getInstance().saveProjectDetails();
                ProjectManager.getInstance().shortProjectList();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("project successfully added");
                alert.showAndWait();

                projectIdText.clear();
                projectNameText.clear();
                projectCategoryText.clear();
                projectDescriptionText.clear();
                projectMembersText.clear();
                projectCountryText.clear();
                imageView.setImage(null);

                ProjectManager.getInstance().displayProjects();
            } else {
                // Display the error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText(errorMessage);
                alert.showAndWait();
            }
        }
        catch (NumberFormatException e) {
            // Display the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Invalid Project ID. Please enter a valid integer.");
            alert.showAndWait();
        }
        catch (Exception e) {
            // Display the error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    public void chooseimage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Project Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile;
        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            Image projectImage = new Image(selectedFile.toURI().toString());
            imageView.setImage(projectImage);
        }
    }
    public Project getProject() throws Exception {
        int projectId = Integer.parseInt(projectIdText.getText());
        String projectName = projectNameText.getText();
        String projectCategory = projectCategoryText.getText();
        String projectDescription = projectDescriptionText.getText();
        String projectMembers = projectMembersText.getText();
        String projectCountry = projectCountryText.getText();
        Image projectImage;
        String imagePath;

        if (imageView.getImage() == null) {
            imagePath = "file:/C:/Users/aashi/IdeaProjects/tecExpoCw/src/main/resources/image/no-image.png";
            projectImage = new Image(imagePath);
        } else {
            projectImage = imageView.getImage();
            imagePath = projectImage.getUrl();
        }

        Project newProject = new Project(projectId, projectName, projectCategory, projectMembers,
                projectDescription, projectCountry, projectImage, imagePath);
        return newProject;
    }


    public String validateProject(Project project) {
        if (project.getProjectId() <= 0) {
            return "Invalid Project ID";
        }
        if (ProjectManager.getInstance().isProjectIdAvailable(project.getProjectId())) {
            return "Project ID already exists";
        }
        if (project.getProjectName().isEmpty() || project.getProjectCategory().isEmpty() || project.getProjectDescription().isEmpty() || project.getProjectMembers().isEmpty() || project.getProjectCountry().isEmpty()) {
            return "fields can't be empty";
        }
        return "";
    }

    public void onClickBackButton(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login.fxml")));
        stage.setTitle("login!");
        Scene scene = new Scene(root,800,600);
        stage.setScene(scene);
    }
}
