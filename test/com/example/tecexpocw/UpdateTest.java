package com.example.tecexpocw;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class UpdateTest{

    @Test
    public void testValidateUpdateInput_EmptyProjectId() {
        update update = new update();
        String errorMessage = update.validateUpdateInput("", "projectName", "category", "members", "description", "country");
        assertNotNull(errorMessage);
        assertEquals("Project ID is required and must be an integer", errorMessage);
    }

    @Test
    public void testValidateUpdateInput_NonIntegerProjectId() {
        update update = new update();

        String errorMessage = update.validateUpdateInput("abc", "projectName", "category", "members", "description", "country");
        assertNotNull(errorMessage);
        assertEquals("Project ID is required and must be an integer", errorMessage);
    }

    @Test
    public void testValidateUpdateInput_EmptyProjectName() {
        update update = new update();

        String errorMessage = update.validateUpdateInput("1", "", "category", "members", "description", "country");
        assertNotNull(errorMessage);
        assertEquals("Project name is required", errorMessage);
    }

    @Test
    public void testValidateUpdateInput_EmptyProjectCategory() {
        update update = new update();

        String errorMessage = update.validateUpdateInput("1", "projectName", "", "members", "description", "country");
        assertNotNull(errorMessage);
        assertEquals("Project category is required", errorMessage);
    }

    @Test
    public void testValidateUpdateInput_EmptyProjectMembers() {
        update update = new update();

        String errorMessage = update.validateUpdateInput("1", "projectName", "category", "", "description", "country");
        assertNotNull(errorMessage);
        assertEquals("Project members are required", errorMessage);
    }

    @Test
    public void testValidateUpdateInput_EmptyProjectDescription() {
        update update = new update();

        String errorMessage = update.validateUpdateInput("1", "projectName", "category", "members", "", "country");
        assertNotNull(errorMessage);
        assertEquals("Project description is required", errorMessage);
    }

    @Test
    public void testValidateUpdateInput_EmptyProjectCountry() {
        update update = new update();

        String errorMessage = update.validateUpdateInput("1", "projectName", "category", "members", "description", "");
        assertNotNull(errorMessage);
        assertEquals("Project country is required", errorMessage);
    }

    @Test
    public void testValidateUpdateInput_ValidInput() {
        update update = new update();

        String errorMessage = update.validateUpdateInput("1", "projectName", "category", "members", "description", "country");
        assertNull(errorMessage);
    }
}