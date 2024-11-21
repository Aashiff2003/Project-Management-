package com.example.tecexpocw;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProjectManager {
    private static ProjectManager instance;
    private final List<Project> projectList;
    private final ArrayList<String> projectCategory = new ArrayList<>();
    private final ArrayList<ArrayList<Integer>> categorizedProjects = new ArrayList<>();
    private final ArrayList<Integer> spotlightProjects =new ArrayList<>() ;
    private final List<Project> spotlightProjectDetails = new ArrayList<>();
    private final ArrayList<String []> projectPointList = new ArrayList<>();

    private ProjectManager() {
        projectList = new ArrayList<>();
    }
    public static ProjectManager getInstance() {
        if (instance == null) {
            instance = new ProjectManager();
        }
        return instance;
    }
    public  ArrayList<String> getProjectCategory(){
        return projectCategory;

    }
    public List<Project> getProjectList() {
        return projectList;
    }
    public List<Project> getSpotlightProjectDetails() {
        return spotlightProjectDetails;
    }
    public ArrayList<String[]> getProjectPointList() {
        return projectPointList;
    }
    public ArrayList<ArrayList<Integer>> getCategorizedProjects() {
        return categorizedProjects;
    }
    public void clearProjectList() {

        projectList.clear();
    }
    public void clearCategoryList(){
        projectCategory.clear();
    }

    public Project getProject(int projectId) {
        for (Project project : projectList) {
            if (project.getProjectId() == projectId) {
                return project;
            }
        }
        return null;
    }
    public void addProject(Project project) {

        projectList.add(project);
    }


    public void addProjectCategory() {
        for (Project project : projectList) {
            if(!projectCategory.contains(project.getProjectCategory())) {
                projectCategory.add(project.getProjectCategory());
            }
        }
    }



    public void displayProjects() {
        for (Project p : projectList) {
            System.out.println("Project ID: " + p.getProjectId());
            System.out.println("Project Name: " + p.getProjectName());
            System.out.println("Project Category: " + p.getProjectCategory());
            System.out.println("Project Description: " + p.getProjectDescription());
            System.out.println("Project Members: " + p.getProjectMembers());
            System.out.println("Project Country: " + p.getProjectCountry());
            System.out.println("Project Image: " + p.getProjectImage());
            System.out.println();

        }
    }
    public Boolean isProjectIdAvailable(int projectId) {
        for (Project project : projectList) {
            if (project.getProjectId() == projectId) {
                return true;
            }
        }
        return false;
    }
    public boolean isEmpty() {
        if (projectList.isEmpty()) {
            return true;
        }
        return false;
    }
    public boolean notInProject(int projectId) {
        for (Project project : projectList) {
            if (project.getProjectId() == projectId) {
                return false;

            }

        }
        return true;
    }
    public void deleteProject(int projectId) {

        for (Project project : projectList) {
            if (project.getProjectId() == projectId) {
                projectList.remove(project);
                projectCategory.remove(project.getProjectCategory());
                addProjectCategory();
                break;
            }
        }
    }

    public void spotlightSelection() {
        // Iterate over each project category
        for (String category : projectCategory) {
            ArrayList<Integer> temp = new ArrayList<>();

            // Iterate over each project in the project list
            for (Project project : projectList) {
                if (category.equals(project.getProjectCategory())) {
                    temp.add(project.getProjectId());
                }
            }
            // Add the temp list to the categorizedProjects list
            categorizedProjects.add(temp);
        }
        // Iterate over each category in the categorizedProjects list
        for (List<Integer> category : categorizedProjects) {
            Random rand = new Random();
            int randomIndex = rand.nextInt(category.size());
            int projectId = category.get(randomIndex);
            // Add the randomly selected project ID to the spotlightProjects list
            spotlightProjects.add(projectId);
        }

        for (int projectId : spotlightProjects) {
            for (Project projectDetails :projectList){
                if(projectId == projectDetails.getProjectId()){
                    spotlightProjectDetails.add(projectDetails);
                }
            }
        }
    }
    public  void shortProjectList(){
        for (int i = 0; i < projectList.size(); i++) {
            for (int j = i + 1; j < projectList.size(); j++) {
                if (projectList.get(i).getProjectId() > projectList.get(j).getProjectId()) {
                    Project temp = projectList.get(i);
                    projectList.set(i, projectList.get(j));
                    projectList.set(j, temp);
                }

            }
        }
    }

    public void shortProjectPointList(){
        for (int i = 0; i < projectPointList.size(); i++) {
            for (int j = i + 1; j < projectPointList.size(); j++) {
                if(Integer.parseInt(projectPointList.get(i)[2] )< Integer.parseInt(projectPointList.get(j)[2])){
                    String[] temp = projectPointList.get(i);
                    projectPointList.set(i, projectPointList.get(j));
                    projectPointList.set(j, temp);
                }
            }
        }
    }
    public  void saveProjectDetails() {


        try {
            addProjectCategory();
            Path filePath = Paths.get("projectDetails.txt");
            try {
                Files.delete(filePath);
            }
            catch (Exception e){

            }
            FileWriter fileWriter = new FileWriter("projectDetails.txt");
            for (int i = 0; i < projectCategory.size(); i++) {
                fileWriter.write("\n");
                fileWriter.write("Category : " +projectCategory.get(i));
                fileWriter.write("\n");
                fileWriter.write("-------------------------------");

                for (int j = 0; j < projectList.size(); j++) {
                    if (projectCategory.get(i).equals(projectList.get(j).getProjectCategory())) {
                        fileWriter.write("\n");
                        fileWriter.write(projectList.get(j).getProjectId() + "|" + projectList.get(j).getProjectName() + "|" + projectList.get(j).getProjectCategory() + "|" + projectList.get(j).getProjectMembers()+ "|" + projectList.get(j).getProjectDescription()  + "|" + projectList.get(j).getProjectCountry() +"|"+projectList.get(j).getImagePath());
                        fileWriter.write("\n");
                    }
                }
            }
            fileWriter.close();
        }
        catch (Exception e){
            System.out.println("save error");
        }
    }

}
