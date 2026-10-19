//Filename: Main.java
//Written by: Phodisho Mongwai
//Student number: 10523203
//Date: 18-09-2026
//Purpose: Console application to test the login class.

package com.mycompany.phodichatapp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputDevice = new Scanner(System.in);
        Login loginSystem = new Login(); // Assumes your Part 1 Login works

        // --- PART 1: REGISTRATION & LOGIN ---
        System.out.println("=== REGISTRATION ===");
        System.out.print("Enter First Name: ");
        String firstName = inputDevice.nextLine();
        System.out.print("Enter Last Name: ");
        String surName = inputDevice.nextLine();
        System.out.print("Enter Username (must contain _ and be <= 5 chars): ");
        String userName = inputDevice.nextLine();
        System.out.print("Enter Password (min 8 chars, 1 capital, 1 number, 1 special): ");
        String password = inputDevice.nextLine();
        System.out.print("Enter Cell Phone Number (+27838968976): ");
        String cellphoneNum = inputDevice.nextLine();

        String registrationMessage = loginSystem.registerUser(firstName, surName, userName, password, cellphoneNum);
        System.out.println("\n" + registrationMessage);

        if (registrationMessage.contains("successfully")) {
            System.out.println("\n=== LOGIN ===");
            System.out.print("Enter Username: ");
            String loginUserName = inputDevice.nextLine();
            System.out.print("Enter Password: ");
            String loginPassWord = inputDevice.nextLine();

            boolean isLoggedIn = loginSystem.loginUser(loginUserName, loginPassWord);
            String loginStatus = loginSystem.returnLoginStatus(isLoggedIn);
            System.out.println("\n" + loginStatus);

            if (isLoggedIn) {
                System.out.println("\nWelcome to QuickChat.");
                startQuickChat(inputDevice);
            }
        } else {
            System.out.println("\nRegistration failed. Please fix the errors and try again.");
        }
        inputDevice.close();
    } 

    // --- PART 2: THE MENU LOOP ---
    public static void startQuickChat(Scanner inputDevice) {
        boolean running = true;
        int messageCounter = 0;

        while (running) {
            System.out.println("\n--- QUICKCHAT MENU ---");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Choose an option: ");

            String option = inputDevice.nextLine();

            switch (option) {
                case "1":
                    System.out.print("How many messages do you wish to enter? ");
                    int numMessages = 0;
                    try {
                        numMessages = Integer.parseInt(inputDevice.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        break;
                    }

                    for (int i = 0; i < numMessages; i++) {
                        System.out.println("\n--- MESSAGE " + (i + 1) + " ---");
                        messageCounter++;

                        // Recipient Validation
                        String recipient = "";
                        boolean validRecipient = false;
                        Message tempMessage = null;

                        while (!validRecipient) {
                            System.out.print("Enter recipient cell number (e.g. +27718693002): ");
                            recipient = inputDevice.nextLine();
                            
                            tempMessage = new Message(messageCounter, recipient, "");
                            String validationMsg = tempMessage.checkRecipientCell();
                            System.out.println(validationMsg);
                            
                            if (validationMsg.equals("Cell phone number successfully captured.")) {
                                validRecipient = true;
                            }
                        }

                        // Message Validation
                        String messageText = "";
                        boolean validMessage = false;

                        while (!validMessage) {
                            System.out.print("Enter your message (max 250 characters): ");
                            messageText = inputDevice.nextLine();
                            
                            if (messageText.length() <= 250) {
                                validMessage = true;
                                System.out.println("Message sent"); 
                            } else {
                                int excess = messageText.length() - 250;
                                System.out.println("Please enter a message of less than 250 characters. (Exceeded by " + excess + ")");
                            }
                        }

                        // Create the message
                        Message finalMessage = new Message(messageCounter, recipient, messageText);

                        // Show Details
                        System.out.println("\n--- Message Details ---");
                        System.out.println("Message ID: " + finalMessage.getMessageID());
                        System.out.println("Message Hash: " + finalMessage.getMessageHash());
                        System.out.println("Recipient: " + finalMessage.getRecipient());
                        System.out.println("Message: " + finalMessage.getMessageText());

                        // Call the method in Message.java to handle the options
                        String actionResult = finalMessage.SentMessage(inputDevice);
                        System.out.println(actionResult);
                    }
                    System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());
                    break;

                case "2":
                    System.out.println("Coming Soon."); 
                    break;

                case "3":
                    System.out.println("Exiting QuickChat. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please enter 1, 2, or 3.");
            }
        }
    } 
}