package utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class EmailValidationTest {

    @Test
    @DisplayName("Validating correct email format.")
    public void testValidateEmailTrue() {
        String validEmail = "valid-email@itacademy.com";
        assertTrue(InputValidation.validateEmail(validEmail), "Valid email should return true.");
    }

    @Test
    @DisplayName("Validating incorrect email format.")
    public void testValidateEmailFalse() {
        String invalidEmail = "invalid-email@com";
        assertFalse(InputValidation.validateEmail(invalidEmail), "Invalid email should return false.");
    }

    @Test
    @DisplayName("Validating email with null input.")
    public void testValidateEmailNull() {
        String nullEmail = null;
        assertFalse(InputValidation.validateEmail(nullEmail), "Null email should return false.");
    }

    @Test
    @DisplayName("Validating email with empty string.")
    public void testValidateEmailEmpty() {
        String emptyEmail = "";
        assertFalse(InputValidation.validateEmail(emptyEmail), "Empty email should return false.");
    }
}
