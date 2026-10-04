import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DisplayEmployeesSequential {

    public static void main(String[] args) throws Exception {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/jdbc_demo";
        String dbUser = "root";
        String dbPass = "root";

        Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        Scanner sc = new Scanner(System.in);

        String query = "SELECT emp_id, name, department, salary FROM employeee";
        PreparedStatement stmt = con.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        System.out.println("========== SEQUENTIAL EMPLOYEE VIEWER ==========");

        while (true) {
            System.out.println("\n1. Show NEXT Employee Record");
            System.out.println("2. Exit");
            System.out.print("Enter choice (1-2): ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    if (rs.next()) {
                        System.out.println("\n----------------------------------------");
                        System.out.println("Employee ID : " + rs.getInt("emp_id"));
                        System.out.println("Name        : " + rs.getString("name"));
                        System.out.println("Department  : " + rs.getString("department"));
                        System.out.println("Salary      : " + rs.getDouble("salary"));
                        System.out.println("----------------------------------------");
                    } else {
                        System.out.println("\n[!] End of records. No more employees to display.");
                    }
                    break;

                case 2:
                    System.out.println("Exiting application...");
                    rs.close();
                    stmt.close();
                    con.close();
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please select 1 or 2.");
                    break;
            }
        }
    }
}