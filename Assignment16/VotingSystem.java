import java.util.InputMismatchException;
import java.util.Scanner;

class UnderageException extends Exception {
    public UnderageException(String message) {
        super(message);
    }
}

public class VotingSystem {
    private static final int MINIMUM_VOTING_AGE = 18;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("--- VOTER ELIGIBILITY SYSTEM ---");
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your age: ");
            int age = scanner.nextInt();

            checkEligibility(age);
            System.out.println("\nWelcome, " + name + "! You are eligible to cast your vote.");

        } catch (UnderageException e) {
            System.out.println("\nEligibility Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("\nError: Please enter a valid integer for age.");
        } catch (Exception e) {
            System.out.println("\nUnexpected Error: " + e.getMessage());
        } finally {
            System.out.println("\nEligibility check process completed.");
            scanner.close();
        }
    }

    public static void checkEligibility(int age) throws UnderageException {
        if (age < MINIMUM_VOTING_AGE) {
            throw new UnderageException("Age must be at least " + MINIMUM_VOTING_AGE + " to vote. Provided age: " + age);
        }
    }
}
