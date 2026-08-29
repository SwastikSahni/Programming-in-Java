import java.util.Scanner;

class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}

public class ATMPinVerification {
    private static final int CORRECT_PIN = 4321;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("--- ATM PIN VERIFICATION ---");
            System.out.print("Enter your 4-digit PIN: ");
            int enteredPin = scanner.nextInt();

            verifyPin(enteredPin);
            System.out.println("\nPIN Verified Successfully! Access Granted.");

        } catch (InvalidPinException e) {
            System.out.println("\nSecurity Alert: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nError: Please enter a valid numeric PIN.");
        } finally {
            System.out.println("\nVerification process has completed. Please take your card.");
            scanner.close();
        }
    }

    public static void verifyPin(int pin) throws InvalidPinException {
        if (pin != CORRECT_PIN) {
            throw new InvalidPinException("Incorrect PIN entered. Access Denied.");
        }
    }
}