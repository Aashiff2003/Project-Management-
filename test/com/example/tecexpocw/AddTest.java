package com.example.tecexpocw;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTest {



    @Test
    public void testValidateProject() {
        add addController = new add();
        Project project = new Project(1, "name", "category", "members", "description", "country", null ,null);
        String errorMessage = addController.validateProject(project);
        assertEquals("", errorMessage);
    }

    @Test
    public void testValidateProjectInvalidId() {
        add addController = new add();
        Project project = new Project(0, "name", "category", "members", "description", "country",  null ,null);
        String errorMessage = addController.validateProject(project);
        assertEquals("Invalid Project ID", errorMessage);
    }

    @Test
    public void testValidateProjectExistingId() {
        add addController = new add();
        Project project = new Project(2, "name", "category", "members", "description", "country", null ,null);
        ProjectManager.getInstance().addProject(project);
        String errorMessage = addController.validateProject(project);
        assertEquals("Project ID already exists", errorMessage);
    }

    @Test
    public void testValidateProjectEmptyFields() {
        add addController = new add();
        Project project = new Project(5, "", "category", "members", "description", "country", null ,null);
        String errorMessage = addController.validateProject(project);
        assertEquals("fields can't be empty", errorMessage);
    }

    @Test
    public void testGetProject() throws Exception {
        add addController = new add();

        Platform.startup(() -> {
            // Create a JFXPanel to initialize the toolkit
            new JFXPanel();
        });



        // Initialize the text fields
        addController.projectIdText = new javafx.scene.control.TextField();
        addController.projectNameText = new javafx.scene.control.TextField();
        addController.projectCategoryText = new javafx.scene.control.TextField();
        addController.projectDescriptionText = new javafx.scene.control.TextField();
        addController.projectMembersText = new javafx.scene.control.TextField();
        addController.projectCountryText = new javafx.scene.control.TextField();
        addController.imageView = new ImageView();

        // Set the input values on the controller
        addController.projectIdText.setText("1");
        addController.projectNameText.setText("Test Project");
        addController.projectCategoryText.setText("TestCategory");
        addController.projectDescriptionText.setText("This is a test project");
        addController.projectMembersText.setText("John Doe, Jane Doe");
        addController.projectCountryText.setText("USA");

        // Call the getProject() method
        Project newProject = addController.getProject();

        // Assert the project values
        assertEquals(1, newProject.getProjectId());
        assertEquals("Test Project", newProject.getProjectName());
        assertEquals("TestCategory", newProject.getProjectCategory());
        assertEquals("This is a test project", newProject.getProjectDescription());
        assertEquals("John Doe, Jane Doe", newProject.getProjectMembers());
        assertEquals("USA", newProject.getProjectCountry());
    }


}
