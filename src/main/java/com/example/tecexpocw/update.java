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

public class update {

    @FXML
    public Button home;
    @FXML
    public Button view;
    @FXML
    public TextField projectIdText;
    @FXML
    public TextField projectNameText;
    @FXML
    public TextField projectCategoryText;
    @FXML
    public TextField projectMembersText;
    @FXML
    public TextField projectDescriptionText;
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
    public Button updateButton;
    @FXML
    public ImageView exit;

    public void onHomeButtonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) home.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
        stage.setTitle("Home!");
        Scene scene = new Scene(root,1100,600);
        stage.setScene(scene);
    }

    public void onViewButoonClick(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) view.getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("view.fxml")));
        stage.setTitle("View");

        Scene scene = new Scene(root,1300,600);
        stage.setScene(scene);
    }

    public void onAddResetButoonClick(ActionEvent actionEvent) {
        projectIdText.clear();
        projectNameText.clear();
        projectCategoryText.clear();
        projectDescriptionText.clear();
        projectMembersText.clear();
        projectCountryText.clear();
        imageView.setImage(null);
    }

    public void onUpdateSubmitButoonClick(ActionEvent actionEvent) {
        updateCurrentDetails();

    }

    public void retrieveData() throws Exception {
        try{
            int updateProjectId = Integer.parseInt(projectIdText.getText());
            if(projectIdText.getText().isEmpty() || Objects.equals(projectIdText.getText(), "0")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Popup");
                alert.setContentText("Project Id can't be empty or can't be equal to 0");
                alert.showAndWait();
            } else if (ProjectManager.getInstance().notInProject(updateProjectId)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Popup");
                alert.setContentText("Project Id is Invalid");
                alert.showAndWait();
            }
            else {
                System.out.println("first");
                Project project = ProjectManager.getInstance().getProject(updateProjectId);
                projectNameText.setText(project.getProjectName());
                projectCategoryText.setText(project.getProjectCategory());
                projectDescriptionText.setText(project.getProjectDescription());
                projectMembersText.setText(project.getProjectMembers());
                projectCountryText.setText(project.getProjectCountry());
                imageView.setImage(project.getProjectImage());


                ProjectManager.getInstance().deleteProject(updateProjectId);
                ProjectManager.getInstance().displayProjects();
                System.out.println("one");



                System.out.println("second");

            }
        }

        catch (NumberFormatException e) {
            showErrorAlert("Invalid project ID");
        }catch (Exception e) {
            showErrorAlert("Something went wrong" + e.getMessage());
        }
    }

    public void updateCurrentDetails(){
        try {

            int updateProjectId = Integer.parseInt(projectIdText.getText());
            String updateProjectName = projectNameText.getText();
            String updateProjectCategory = projectCategoryText.getText();
            String updateProjectDescription = projectDescriptionText.getText();
            String updateProjectMembers = projectMembersText.getText();
            String updateProjectCountry = projectCountryText.getText();
            Image updateProjectImage = imageView.getImage();
            String updateImagePath = updateProjectImage.getUrl();
            String errorMessage = validateUpdateInput(projectIdText.getText(),updateProjectName,updateProjectCategory
                    ,updateProjectDescription,updateProjectMembers,updateProjectCountry);
            if (errorMessage != null) {
                showErrorAlert(errorMessage);
                return;
            }

            if(!ProjectManager.getInstance().isProjectIdAvailable(updateProjectId)) {
                Project updatedProject = new Project(updateProjectId, updateProjectName,
                        updateProjectCategory,
                        updateProjectMembers
                        , updateProjectDescription,
                        updateProjectCountry,updateProjectImage, updateImagePath);

                ProjectManager.getInstance().addProject(updatedProject);
                ProjectManager.getInstance().saveProjectDetails();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("project successfully updated");
                alert.showAndWait();

                projectIdText.clear();
                projectNameText.clear();
                projectCategoryText.clear();
                projectDescriptionText.clear();
                projectMembersText.clear();
                projectCountryText.clear();
                imageView.setImage(null);
            }
            else {
                showErrorAlert("projectId already available\n if you need to update click load button");
            }

        }
        catch (NumberFormatException e) {
            showErrorAlert("Invalid project ID");
        } catch (NullPointerException e) {
            showErrorAlert("Invalid input data");
        } catch (Exception e) {
            showErrorAlert("An error occurred: " + e.getMessage());
        }

    }


    public String validateUpdateInput(String updateProjectId,
                                      String updateProjectName,
                                      String updateProjectCategory,
                                      String updateProjectMembers,
                                      String updateProjectDescription,
                                      String updateProjectCountry) {


        if (updateProjectId.isEmpty() || !isInteger(updateProjectId)) {
            return "Project ID is required and must be an integer";
        }

        if (updateProjectName.isEmpty()) {
            return "Project name is required";
        }

        if (updateProjectCategory.isEmpty()) {
            return "Project category is required";
        }

        if (updateProjectDescription.isEmpty()) {
            return "Project description is required";
        }

        if (updateProjectMembers.isEmpty()) {
            return "Project members are required";
        }

        if (updateProjectCountry.isEmpty()) {
            return "Project country is required";
        }

        int projectId = Integer.parseInt(updateProjectId);
        if (ProjectManager.getInstance().isProjectIdAvailable(projectId)) {
            return "Project ID already exists";
        }

        return null;
    }

    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void chooseimage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File"); // Set a title for the file chooser dialog
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        ); // Filter for image files

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                Image image = new Image(selectedFile.toURI().toString());
                imageView.setImage(image); // Set the selected image to the ImageView
            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
            }
        }
    }

    public void onUpdateButtonClick(ActionEvent actionEvent) throws Exception {
        retrieveData();

    }

    public void onExitButtonClick(MouseEvent mouseEvent) {
        ProjectManager.getInstance().saveProjectDetails();
        System.exit(0);
    }

}
