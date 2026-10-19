package com.mycompany.phodichatapp;

public class Login {
    private String userName;
    private String password;
    private String cellphoneNum;
    private String firstName;
    private String surName;

    // Check Username
    public boolean checkUserName(String userName) {
        return userName.contains("_") && userName.length() <= 5;
    }

    // Check Password Complexity 
    public boolean checkPasswordComplexity(String password) {
        boolean hasLength = password.length() >= 8;
        boolean hasUpper = !password.equals(password.toLowerCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        return hasLength && hasUpper && hasDigit && hasSpecial;
    }

    // Check Phone Number 
    public boolean checkCellPhoneNumber(String cellphoneNum) {
        // This regex checks for a + followed by 10 to 12 numbers
        String regex = "^\\+[0-9]{10,12}$";
        return cellphoneNum.matches(regex);
    }

    // Register User
    public String registerUser(String firstName, String surName, String userName, String password, String cellphoneNum) {
        if (!checkUserName(userName)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellphoneNum)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        // If all checks pass, save the data
        this.firstName = firstName;
        this.surName = surName;
        this.userName = userName;
        this.password = password;
        this.cellphoneNum = cellphoneNum;

        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    // Login User
    public boolean loginUser(String enteredUserName, String enteredPassword) {
        if (this.userName == null) {
            return false;
        }
        return enteredUserName.equals(this.userName) && enteredPassword.equals(this.password);
    }

    // Return Login Status
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + ", " + surName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}