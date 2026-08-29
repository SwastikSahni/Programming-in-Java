import java.util.InputMismatchException;
import java.util.Scanner;

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class InvalidAmountException extends Exception {
    public InvalidAmountException(String message) {
        super(message);
    }
}

public class ATMPROGRAM {
    private static double balance = 5000.00;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean sessionActive = true;

        while (sessionActive) {
            System.out.println("\nATM SYSTEM");
            System.out.printf("Current balance: $%.2f%n", balance);
            System.out.println("Press 1.] Withdraw Cash");
            System.out.println("Press 2.] Exit");
            System.out.print("Select an option: ");

            try {
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount to withdraw: ");
                        double amount = sc.nextDouble(); 
                        withdraw(amount);
                        break;

                    case 2:
                        System.out.println("Exiting the Application...");
                        sessionActive = false;
                        break; 

                    default:
                        System.out.println("Invalid menu option...");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Non-numeric input detected.");
                sc.nextLine(); 
            } catch (InvalidAmountException | InsufficientFundsException e) {
                System.out.println("Transaction Failed: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            } finally {
                System.out.println("[Notice] Transaction cycle completed.");
            }
        }

        sc.close();
        System.out.println("Thank You for using our ATM service.");
    }

    public static void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
        }
        if (amount % 100 != 0) {
            throw new InvalidAmountException("Amount must be in multiples of 100.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds. Available balance: $" + balance);
        }

        balance -= amount;
        System.out.printf("Withdrawal successful! Remaining balance: $%.2f%n", balance);
    }
}