package com.example.tecexpocw;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteTest {

    private delete delete;
    private TextField dltProjectIdText;



    @Test
    public void testValidateDeleteProjectInput_EmptyProjectId() {
        delete delete = new delete();
        String projectId = "";
        String errorMessage = delete.validateDeleteProjectInput(projectId);
        assertEquals("Project Id is empty", errorMessage);
    }

    @Test
    public void testValidateDeleteProjectInput_InvalidProjectId() {
        delete delete = new delete();
        String projectId = "abc";
        String errorMessage = delete.validateDeleteProjectInput(projectId);
        assertEquals("Invalid project ID. Please enter a valid integer.", errorMessage);
    }

    @Test
    public void testValidateDeleteProjectInput_NegativeProjectId() {
        delete delete = new delete();
        String projectId = "-1";
        String errorMessage = delete.validateDeleteProjectInput(projectId);
        assertEquals("Invalid project ID. Please enter a valid integer.", errorMessage);
    }

    @Test
    public void testValidateDeleteProjectInput_ProjectIdNotAvailable() {
        delete delete = new delete();
        String projectId = "1";
        String errorMessage = delete.validateDeleteProjectInput(projectId);
        assertEquals("Project Id is Not Available", errorMessage);

    }


}