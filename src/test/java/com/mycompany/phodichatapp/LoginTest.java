//Filename:LoginTest.java
//Written by: Phodisho Mongwai
//Student number: 10523203
//Date: 18-09-2026
//Purpose: JUnit tests to verify the login methods.

package com.mycompany.phodichatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    // Creating a login object to test its methods
    Login login = new Login();

    // Test valid and invalid entries using assertEquals
    
    @Test
    public void 
        testUsernameCorrectlyFormatted() {
        login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        boolean isLoggedIn = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        String result = login.returnLoginStatus(isLoggedIn);
        assertEquals("Welcome Kyle, Smith it is great to see you again.", result);
    }

    @Test
    public void 
        testUsernameIncorrectlyFormatted() {
        String result = login.registerUser("Kyle", "Smith", "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    public void 
        testPasswordMeetsComplexity() {
        String result = login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.contains("Password successfully captured."));
    }

    @Test
    public void 
        testPasswordDoesNotMeetComplexity() {
        String result = login.registerUser("Kyle", "Smith", "kyl_1", "password", "+27838968976");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }

    @Test
    public void 
        testCellPhoneCorrectlyFormatted() {
        String result = login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.contains("Cell phone number successfully added."));
    }

    @Test
    public void 
        testCellPhoneIncorrectlyFormatted() {
        String result = login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", result);
    }

    // Testing boolean methods with assertTrue and assertFalse

    @Test
    public void 
        testLoginSuccessful() {
        login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void 
        testLoginFailed() {
        login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }

    @Test
    public void
         testUsernameCorrectlyFormattedAssertTrue() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void 
        testUsernameIncorrectlyFormattedAssertFalse() {
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void 
        testPasswordMeetsComplexityAssertTrue() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void 
        testPasswordDoesNotMeetComplexityAssertFalse() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void 
        testCellPhoneCorrectlyFormattedAssertTrue() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void 
        testCellPhoneIncorrectlyFormattedAssertFalse() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}