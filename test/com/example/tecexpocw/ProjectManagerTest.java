package com.example.tecexpocw;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProjectManagerTest {
    @Test
    public void testAddProject() {
        ProjectManager projectManager = ProjectManager.getInstance();
        Project project = new Project(1, "Test Project", "Category", "Description", "Members", "Country", null,null);
        projectManager.addProject(project);
        assertTrue(projectManager.getProjectList().contains(project));
    }

    @Test
    public void testDeleteProject() {
        ProjectManager projectManager = ProjectManager.getInstance();
        projectManager.clearProjectList();
        Project project = new Project(1, "Test Project", "Category", "Description", "Members", "Country", null,null);
        projectManager.addProject(project);
        projectManager.deleteProject(1);
        assertFalse(projectManager.getProjectList().contains(project));
    }

    @Test
    public void testGetProjectCategory() {
        ProjectManager projectManager = ProjectManager.getInstance();
        projectManager.clearProjectList();
        Project project = new Project(1, "Test Project", "Category", "Description", "Members", "Country", null,null);
        projectManager.addProject(project);
        assertEquals("Category", projectManager.getProject(1).getProjectCategory());
    }

    @Test
    public void testGetProjectList() {
        ProjectManager projectManager = ProjectManager.getInstance();
        projectManager.clearProjectList();
        Project project1 = new Project(1, "Test Project 1", "Category", "Description", "Members", "Country", null,null);
        Project project2 = new Project(2, "Test Project 2", "Category", "Description", "Members", "Country", null,null);
        projectManager.addProject(project1);
        projectManager.addProject(project2);
        assertEquals(2, projectManager.getProjectList().size());
    }

    @Test
    public void testIsEmpty() {
        ProjectManager projectManager = ProjectManager.getInstance();
        projectManager.clearProjectList();
        projectManager.clearProjectList();
        assertTrue(projectManager.isEmpty());
        projectManager.addProject(new Project(1, "Test Project", "Category", "Description", "Members", "Country", null,null));
        assertFalse(projectManager.isEmpty());
    }

    @Test
    public void testNotInProject() {
        ProjectManager projectManager = ProjectManager.getInstance();
        projectManager.clearProjectList();
        assertTrue(projectManager.notInProject(1));
        projectManager.addProject(new Project(1, "Test Project", "Category", "Description", "Members", "Country",
                null,null));
        assertFalse(projectManager.notInProject(1));
    }

    @Test
    public void testGetProject() {
        ProjectManager projectManager = ProjectManager.getInstance();
        projectManager.clearProjectList();
        Project project = new Project(1, "Test Project", "Category", "Description", "Members", "Country", null,null);
        projectManager.addProject(project);
        assertEquals(project, projectManager.getProject(1));
    }

    @Test
    public void testAddProjectCategory() {
        ProjectManager projectManager = ProjectManager.getInstance();
        projectManager.clearProjectList();
        Project project = new Project(1, "Test Project", "Category", "Description", "Members", "Country", null,null);
        projectManager.addProject(project);
        assertEquals("Category", projectManager.getProject(1).getProjectCategory());
    }
    @Test
    public void testSaveProjectDetails() throws IOException {
        ProjectManager projectManager = ProjectManager.getInstance();
        projectManager.clearProjectList();

        projectManager.clearCategoryList();



        Project project1 = new Project(1, "Test Project 1", "Category 1", "Description 1", "Members 1", "Country 1",
                null,null);
        Project project2 = new Project(2, "Test Project 2", "Category 2", "Description 2", "Members 2", "Country 2",
                null,null);
        projectManager.addProject(project1);
        projectManager.addProject(project2);

        projectManager.saveProjectDetails();



        Path filePath = Paths.get("projectDetails.txt");
        assertTrue(Files.exists(filePath));

        List<String> lines = Files.readAllLines(filePath);
        assertEquals(8, lines.size()); // 2 categories, 2 projects, and 3 separator lines

        assertEquals("Category : Category 1", lines.get(1));
        assertEquals("-------------------------------", lines.get(2));
        assertEquals("1|Test Project 1|Category 1|Description 1|Members 1|Country 1|null", lines.get(3));

        assertEquals("Category : Category 2", lines.get(5));
        assertEquals("-------------------------------", lines.get(6));
        assertEquals("2|Test Project 2|Category 2|Description 2|Members 2|Country 2|null", lines.get(7));
    }
    @Test
    public void testShortProjectList() {
        ProjectManager projectManager = ProjectManager.getInstance();
        projectManager.clearProjectList();
        Project project1 = new Project(3, "Test Project 3", "Category 1", "Description 1", "Members 1", "Country 1",
                null,null);
        Project project2 = new Project(1, "Test Project 1", "Category 2", "Description 2", "Members 2", "Country 2", null,null);
        Project project3 = new Project(2, "Test Project 2", "Category 3", "Description 3", "Members 3", "Country 3", null,null);

        projectManager.addProject(project1);
        projectManager.addProject(project2);
        projectManager.addProject(project3);

        projectManager.shortProjectList();


        assertEquals(1, projectManager.getProjectList().get(0).getProjectId());
        assertEquals(2, projectManager.getProjectList().get(1).getProjectId());
        assertEquals(3, projectManager.getProjectList().get(2).getProjectId());
    }

    @Test
    public void testSpotlightSelection() {
        ProjectManager projectManager = ProjectManager.getInstance();
        projectManager.clearProjectList();
        Project project1 = new Project(1, "Test Project 1", "Category A", "Description 1", "Members 1", "Country 1", null,null);
        Project project2 = new Project(2, "Test Project 2", "Category A", "Description 2", "Members 2", "Country 2", null,null);
        Project project3 = new Project(3, "Test Project 3", "Category B", "Description 3", "Members 3", "Country 3", null,null);
        Project project4 = new Project(4, "Test Project 4", "Category B", "Description 4", "Members 4", "Country 4", null,null);
        Project project5 = new Project(5, "Test Project 5", "Category C", "Description 5", "Members 5", "Country 5",
                null,null);
        projectManager.addProject(project1);
        projectManager.addProject(project2);
        projectManager.addProject(project3);
        projectManager.addProject(project4);
        projectManager.addProject(project5);

        projectManager.addProjectCategory();
        projectManager.spotlightSelection();

        assertEquals(3,projectManager.getProjectCategory().size());


        assertEquals(3, projectManager.getCategorizedProjects().size());


        assertEquals(3, projectManager.getSpotlightProjectDetails().size());


        Set<String> categories = new HashSet<>();
        for (Project project : projectManager.getSpotlightProjectDetails()) {
            categories.add(project.getProjectCategory());
        }
        assertEquals(3, categories.size());
    }
}