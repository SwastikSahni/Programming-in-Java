import java.util.Scanner;

class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}

public class LoginProgram {
    private static final String CORRECT_USERNAME = "swastik";
    private static final String CORRECT_PASSWORD = "swaspass@123";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("--- USER LOGIN SYSTEM ---");
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            authenticate(username, password);
            System.out.println("\nLogin Successful! Welcome, " + username + ".");

        } catch (InvalidPasswordException e) {
            System.out.println("\nAuthentication Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nUnexpected Error: " + e.getMessage());
        } finally {
            System.out.println("Session terminated. Resources released.");
            scanner.close();
        }
    }

    public static void authenticate(String username, String password) throws InvalidPasswordException {
        if (!CORRECT_USERNAME.equals(username) || !CORRECT_PASSWORD.equals(password)) {
            throw new InvalidPasswordException("Invalid username or password entered.");
        }
    }
}