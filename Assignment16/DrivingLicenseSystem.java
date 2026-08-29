import java.util.InputMismatchException;
import java.util.Scanner;

class UnderageForLicenseException extends Exception {
    public UnderageForLicenseException(String message) {
        super(message);
    }
}

public class DrivingLicenseSystem {
    private static final int MIN_DRIVING_AGE = 18;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("--- DRIVING LICENSE APPLICATION SYSTEM ---");
            System.out.print("Enter applicant name: ");
            String name = scanner.nextLine();

            System.out.print("Enter applicant age: ");
            int age = scanner.nextInt();

            checkEligibility(age);
            System.out.println("\nApplication Accepted! " + name + " is eligible for a driving license.");

        } catch (UnderageForLicenseException e) {
            System.out.println("\nEligibility Rejected: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\nInput Error: Please enter a valid numerical age.");
        } catch (Exception e) {
            System.out.println("\nUnexpected Error: " + e.getMessage());
        } finally {
            System.out.println("\nApplication verification process finished.");
            scanner.close();
        }
    }

    public static void checkEligibility(int age) throws UnderageForLicenseException {
        if (age < MIN_DRIVING_AGE) {
            throw new UnderageForLicenseException("Applicant must be at least " + MIN_DRIVING_AGE + " years old. Provided age: " + age);
        }
    }
}