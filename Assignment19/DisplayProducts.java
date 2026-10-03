import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DisplayProducts {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/jdbc_demo";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static void main(String[] args) {
        String query = "SELECT product_id, product_name, quantity, price FROM product";

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("------------------------------------------------------------------");
            System.out.printf("%-12s | %-22s | %-10s | %-10s\n", "PRODUCT ID", "PRODUCT NAME", "QUANTITY", "PRICE (₹)");
            System.out.println("------------------------------------------------------------------");

            while (rs.next()) {
                int id = rs.getInt("product_id");
                String name = rs.getString("product_name");
                int qty = rs.getInt("quantity");
                double price = rs.getDouble("price");

                System.out.printf("%-12d | %-22s | %-10d | %-10.2f\n", id, name, qty, price);
            }

            System.out.println("------------------------------------------------------------------");

        } catch (Exception e) {
            System.err.println("Database Error: " + e.getMessage());
        }
    }
}