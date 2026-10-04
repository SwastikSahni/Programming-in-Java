import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class LoginApp {

    public static void main(String[] args) throws Exception {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/jdbc_demo";
        String dbUser = "root";
        String dbPass = "root";

        Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        Scanner sc = new Scanner(System.in);

        System.out.println("========== USER LOGIN ==========");
        System.out.print("Enter Username: ");
        String inputUsername = sc.nextLine();

        System.out.print("Enter Password: ");
        String inputPassword = sc.nextLine();

       
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, inputUsername);
        stmt.setString(2, inputPassword);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            System.out.println("\n----------------------------------------");
            System.out.println("Status: Login Successful!");
            System.out.println("Welcome, " + rs.getString("username") + "!");
            System.out.println("----------------------------------------");
        } else {
            System.out.println("\n----------------------------------------");
            System.out.println("Status: Login Failed!");
            System.out.println("Error: Invalid username or password.");
            System.out.println("----------------------------------------");
        }

        
        rs.close();
        stmt.close();
        con.close();
        sc.close();
    }
}