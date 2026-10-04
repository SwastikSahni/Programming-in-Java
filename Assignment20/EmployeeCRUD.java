import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class EmployeeCRUD {

    public static void main(String[] args) throws Exception {
        String db = "jdbc:mysql://127.0.0.1:3306/jdbc_demo";
        String user = "root";
        String pass = "root";

        Connection con = DriverManager.getConnection(db, user, pass);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. CREATE | 2. READ | 3. UPDATE | 4. DELETE | 5. EXIT");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                
                sc.nextLine(); // clear buffer
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Department: ");
                String dept = sc.nextLine();
                System.out.print("Enter Salary: ");
                double salary = sc.nextDouble();

                String query = "INSERT INTO employee (name, department, salary) VALUES (?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, name);
                stmt.setString(2, dept);
                stmt.setDouble(3, salary);

                stmt.executeUpdate();
                System.out.println("Employee created successfully!");
                stmt.close();

            } else if (choice == 2) {
                
                String query = "SELECT * FROM employee";
                PreparedStatement stmt = con.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                System.out.println("\n--- EMPLOYEE RECORDS ---");
                while (rs.next()) {
                    System.out.println(rs.getInt("emp_id") + " | " + 
                                       rs.getString("name") + " | " + 
                                       rs.getString("department") + " | " + 
                                       rs.getDouble("salary"));
                }
                rs.close();
                stmt.close();

            } else if (choice == 3) {
                
                System.out.print("Enter Employee ID to update: ");
                int id = sc.nextInt();
                System.out.print("Enter New Salary: ");
                double salary = sc.nextDouble();

                String query = "UPDATE employee SET salary=? WHERE emp_id=?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setDouble(1, salary);
                stmt.setInt(2, id);

                stmt.executeUpdate();
                System.out.println("Salary updated successfully!");
                stmt.close();

            } else if (choice == 4) {
                
                System.out.print("Enter Employee ID to delete: ");
                int id = sc.nextInt();

                String query = "DELETE FROM employee WHERE emp_id=?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, id);

                stmt.executeUpdate();
                System.out.println("Employee deleted successfully!");
                stmt.close();

            } else if (choice == 5) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }

        con.close();
        sc.close();
    }
}