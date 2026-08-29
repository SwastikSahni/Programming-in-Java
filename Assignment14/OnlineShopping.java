import java.util.InputMismatchException;
import java.util.Scanner;

class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}

public class OnlineShopping {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] products = {"Laptop", "Smartphone", "Headphones", "Smartwatch", "Keyboard"};
        double[] prices = {850.00, 450.00, 50.00, 120.00, 35.00};
        int[] stock = {5, 10, 15, 8, 20};

        boolean shopping = true;

        while (shopping) {
            System.out.println("\n--- AVAILABLE PRODUCTS ---");
            for (int i = 0; i < products.length; i++) {
                System.out.printf("%d. %s - $%.2f (Available: %d)%n", (i + 1), products[i], prices[i], stock[i]);
            }
            System.out.println("6. Exit");

            try {
                System.out.print("\nEnter product number (1-6): ");
                int productChoice = scanner.nextInt();

                if (productChoice == 6) {
                    System.out.println("Exiting store...");
                    shopping = false;
                    continue;
                }

                if (productChoice < 1 || productChoice > products.length) {
                    throw new InvalidQuantityException("Invalid product selection. Please choose a number between 1 and 6.");
                }

                int index = productChoice - 1;

                System.out.print("Enter quantity to purchase: ");
                int requestedQuantity = scanner.nextInt();

                validatePurchase(requestedQuantity, stock[index]);

                double total = requestedQuantity * prices[index];
                stock[index] -= requestedQuantity;

                System.out.println("\nOrder placed successfully!");
                System.out.println("Purchased: " + requestedQuantity + "x " + products[index]);
                System.out.printf("Total Amount: $%.2f%n", total);
                System.out.println("Remaining Stock: " + stock[index]);

            } catch (InputMismatchException e) {
                System.out.println("\nError: Please enter numbers only.");
                scanner.nextLine();
            } catch (InvalidQuantityException e) {
                System.out.println("\nError: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\nUnexpected Error: " + e.getMessage());
            } finally {
                System.out.println("[Notice] Purchase cycle processed.");
            }
        }

        scanner.close();
        System.out.println("\nThank you for shopping with us!");
    }

    public static void validatePurchase(int quantity, int availableStock) throws InvalidQuantityException {
        if (quantity <= 0) {
            throw new InvalidQuantityException("Quantity must be greater than zero.");
        }
        if (quantity > availableStock) {
            throw new InvalidQuantityException("Requested quantity exceeds available stock (" + availableStock + ").");
        }
    }
}