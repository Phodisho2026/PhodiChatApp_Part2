package com.mycompany.phodichatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        String validMessage = "Hi Mike, can you join us for dinner tonight?";
        Message msg = new Message(1, "+27718693002", validMessage);
        assertTrue(msg.getMessageText().length() <= 250);
    }

    @Test
    public void testRecipientCellSuccess() {
        Message msg = new Message(1, "+27718693002", "Test message");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFailure() {
        Message msg = new Message(1, "08575975889", "Test message");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }
}
