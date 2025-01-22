package utils;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidation {

    private static final Scanner scanner = new Scanner(System.in);

    public static int validateIntInput(String prompt) {
        int input = 0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character left after nextInt()
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear the buffer
            }
        }
        return input;
    }

    public static String validateStringInput(String prompt) {
        String input = "";
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            input = scanner.nextLine().trim(); // Get the input and remove leading/trailing spaces

            if (!input.isEmpty()) {
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter a non-empty text.");
            }
        }
        return input;
    }

    public static String validateEmailInput(String prompt) {
        String input = "";
        boolean valid = false;
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern emailPattern = Pattern.compile(emailRegex);

        while (!valid) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();

            if (emailPattern.matcher(input).matches()) {
                valid = true;
            } else {
                System.out.println("Invalid email format. Please enter a valid email.");
            }
        }
        return input;
    }

    public static double validatePriceInput(String prompt) {
        double price = 0.0;
        boolean valid = false;

        while (!valid) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                price = scanner.nextDouble();
                if (price >= 0) {
                    valid = true;
                } else {
                    System.out.println("Price must be a positive number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid price.");
                scanner.next(); // Clear the buffer
            }
        }

        // Round to 2 decimal places
        price = Math.round(price * 100.0) / 100.0;
        return price;
    }

    public static void main(String[] args) {
        // Example of using the validation methods
        int number = validateIntInput("Enter a number: ");
        System.out.println("Number entered: " + number);

        String text = validateStringInput("Enter a text: ");
        System.out.println("Text entered: " + text);

        String email = validateEmailInput("Enter an email: ");
        System.out.println("Email entered: " + email);

        double price = validatePriceInput("Enter a price: ");
        System.out.println("Price entered: " + price);
    }
}
