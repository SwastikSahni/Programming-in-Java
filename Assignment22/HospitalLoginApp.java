import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class HospitalLoginApp {

    public static void main(String[] args) throws Exception {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/jdbc_demo";
        String dbUser = "root";
        String dbPass = "root";

        Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        Scanner sc = new Scanner(System.in);

        System.out.println("========== HOSPITAL STAFF LOGIN ==========");
        System.out.print("Enter Login ID: ");
        String loginId = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        
        String query = "SELECT staff_name, role FROM hospital_staff WHERE login_id = ? AND password = ?";
        
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setString(1, loginId);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String name = rs.getString("staff_name");
            String role = rs.getString("role");

            System.out.println("\n------------------------------------------------");
            System.out.println("Status: Authentication Successful!");
            System.out.println("Welcome, " + name + " (" + role + ")");
            
            
            if (role.equalsIgnoreCase("Doctor")) {
                System.out.println("Access Granted: Electronic Health Records & Prescription Module.");
            } else if (role.equalsIgnoreCase("Nurse")) {
                System.out.println("Access Granted: Patient Vitals & Ward Management Module.");
            } else {
                System.out.println("Access Granted: Full Administrative Portal.");
            }
            System.out.println("------------------------------------------------");
        } else {
            System.out.println("\n------------------------------------------------");
            System.out.println("Status: Authentication Failed!");
            System.out.println("Error: Invalid Login ID or Password. Access Denied.");
            System.out.println("------------------------------------------------");
        }

        
        rs.close();
        stmt.close();
        con.close();
        sc.close();
    }
}