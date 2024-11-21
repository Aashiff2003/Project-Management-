package com.example.tecexpocw;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class Controller extends Application {
    public List<Project> projectList = ProjectManager.getInstance().getProjectList();


    @Override
    public void start(Stage stage) throws IOException {

        loadFromTextFile("projectDetails.txt");
        ProjectManager.getInstance().displayProjects();

        FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setTitle("Home!");
        stage.setScene(scene);
        stage.show();

        ProjectManager.getInstance().saveProjectDetails();
    }

    public static void main(String[] args) {
        launch();

    }

    public void loadFromTextFile(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            String category = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim(); // remove leading and trailing whitespace
                if (line.isEmpty()) {
                    // skip empty lines
                    continue;
                }
                if (line.startsWith("Category")) {
                    category = line.substring(9); // remove "Category " prefix
                } else if (line.startsWith("-------------------")) {
                    // ignore separator line
                    continue;
                } else {
                    String[] parts = line.split("\\|");
                    if (parts.length == 7) {
                        int projectId = Integer.parseInt(parts[0].trim());
                        String projectName = parts[1].trim();
                        String projectCategory = parts[2].trim();
                        String projectMembers = parts[3].trim();
                        String projectDescription = parts[4].trim();
                        String projectCountry = parts[5].trim();
                        String imagePath = parts[6].trim();


                        // Load the image from the file path
                        Image projectImage = new Image(imagePath);


                        Project project = new Project(projectId, projectName, projectCategory, projectMembers,
                                projectDescription, projectCountry, projectImage, imagePath);
                        projectList.add(project);
                        ProjectManager.getInstance().shortProjectList();
                        ProjectManager.getInstance().saveProjectDetails();

                    }
                }

            }

            reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}















































