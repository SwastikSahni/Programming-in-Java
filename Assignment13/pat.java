import java.util.*;

public class pat {
    public static void main(String[] args) {
        int size = 5;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || i == size - 1 || j == 0 || j == size - 1) {
                    System.out.print("* ");
                } else {
                    System.out.print("  "); 
                }
            }
            System.out.println();
        }

        int rows = 5;
        int prod = 1;

        for (int i = 0; i < rows; i++) {
            for (int space = 0; space < rows - i - 1; space++) {
                System.out.print(" ");
            }

            String str = Integer.toString(prod);

            for (int j = 0; j < str.length(); j++) {
                System.out.print(str.charAt(j) + " ");
            }

            System.out.println();
            prod = prod * 11;
        }
    }
}