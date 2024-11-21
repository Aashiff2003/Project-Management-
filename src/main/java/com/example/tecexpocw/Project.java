package com.example.tecexpocw;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class Project {
    private final SimpleIntegerProperty projectId;
    private final SimpleStringProperty projectName;
    private final SimpleStringProperty projectCategory;
    private final SimpleStringProperty projectMembers;
    private final SimpleStringProperty projectDescription;
    private final SimpleStringProperty projectCountry;
    private final SimpleObjectProperty<Image> projectImage;
    private final SimpleStringProperty imagePath;



    public Project(int projectId, String projectName, String projectCategory, String projectMembers,
                   String projectDescription, String projectCountry, Image projectImage, String imagePath){
        this.projectId = new SimpleIntegerProperty(projectId);
        this.projectName = new SimpleStringProperty(projectName);
        this.projectCategory = new SimpleStringProperty(projectCategory);
        this.projectMembers = new SimpleStringProperty(projectMembers);
        this.projectDescription = new SimpleStringProperty(projectDescription);
        this.projectCountry = new SimpleStringProperty(projectCountry);
        this.projectImage = new SimpleObjectProperty<>(projectImage);
        this.imagePath = new SimpleStringProperty(imagePath);

    }

    public int getProjectId() {
        return projectId.get();
    }
    public void setProjectId(int projectId) {
        this.projectId.set(projectId);
    }
    public SimpleIntegerProperty projectIdProperty() {
        return projectId;
    }
    public String getProjectName() {
        return projectName.get();
    }
    public void setProjectName(String projectName) {
        this.projectName.set(projectName);
    }
    public SimpleStringProperty projectNameProperty() {
        return projectName;
    }
    public String getProjectCategory() {
        return projectCategory.get();
    }
    public void setProjectCategory(String projectCategory) {
        this.projectCategory.set(projectCategory);
    }
    public SimpleStringProperty projectCategoryProperty() {
        return projectCategory;
    }
    public String getProjectMembers() {
        return projectMembers.get();
    }
    public String getImagePath(){
        return imagePath.get();
    }
    public void setProjectMembers(String projectMembers) {
        this.projectMembers.set(projectMembers);
    }

    public SimpleStringProperty projectMembersProperty() {
        return projectMembers;
    }

    public String getProjectDescription() {
        return projectDescription.get();
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription.set(projectDescription);
    }

    public SimpleStringProperty projectDescriptionProperty() {
        return projectDescription;
    }

    public String getProjectCountry() {
        return projectCountry.get();
    }

    public void setProjectCountry(String projectCountry) {
        this.projectCountry.set(projectCountry);
    }

    public SimpleStringProperty projectCountryProperty() {
        return projectCountry;
    }

    public Image getProjectImage() {
        return projectImage.get();
    }

    public void setProjectImage(Image projectImage) {
        this.projectImage.set(projectImage);
    }

    public ObjectProperty<Image> projectImageProperty() {
        return projectImage;
    }
    @Override
    public String toString() {
        return "Project [projectId=" + projectId.get() +
                ", projectName=" + projectName.get() + ", projectCategory="+projectCategory.get()+", projectMembers="+projectMembers.get()+", projectDescription="+projectDescription.get()+", projectCountry="+projectCountry.get()+"]";
    }


}