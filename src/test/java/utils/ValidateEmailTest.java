package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidateEmailTest {

    @Test
    @DisplayName("Checking email validation with a valid address.")
    public void testValidateEmailTrue() {
        String validEmail = "username@domain.com";
        assertTrue(InputValidation.validateEmail(validEmail), "Valid email should return true.");
    }

    @Test
    @DisplayName("Checking email validation with an invalid address.")
    public void testValidateEmailFalse() {
        String invalidEmail = "invalid-email@com";
        assertFalse(InputValidation.validateEmail(invalidEmail), "Invalid email should return false.");
    }

    @Test
    @DisplayName("Checking email validation with a null input.")
    public void testValidateEmailNull() {
        String nullEmail = null;
        assertFalse(InputValidation.validateEmail(nullEmail), "Null email should return false.");
    }

    @Test
    @DisplayName("Checking email validation with an empty string.")
    public void testValidateEmailEmpty() {
        String emptyEmail = "";
        assertFalse(InputValidation.validateEmail(emptyEmail), "Empty email should return false.");
    }
}
