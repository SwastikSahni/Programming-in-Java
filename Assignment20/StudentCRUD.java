import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StudentCRUD {

    public static void main(String[] args) throws Exception {
        String db = "jdbc:mysql://127.0.0.1:3306/jdbc_demo";
        String user = "root";
        String pass = "root";

        Connection con = DriverManager.getConnection(db, user, pass);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- STUDENT MANAGEMENT SYSTEM ---");
            System.out.println("1. CREATE (Insert)");
            System.out.println("2. READ (View All)");
            System.out.println("3. UPDATE (Marks)");
            System.out.println("4. DELETE");
            System.out.println("5. EXIT");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            if (choice == 1) {
                
                System.out.print("Enter Roll No: ");
                int rollno = sc.nextInt();
                sc.nextLine(); 

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Course: ");
                String course = sc.nextLine();

                System.out.print("Enter Marks: ");
                int marks = sc.nextInt();

                String query = "INSERT INTO students (rollno, name, course, marks) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, rollno);
                stmt.setString(2, name);
                stmt.setString(3, course);
                stmt.setInt(4, marks);

                stmt.executeUpdate();
                System.out.println("Student record created successfully!");
                stmt.close();

            } else if (choice == 2) {
                
                String query = "SELECT * FROM students";
                PreparedStatement stmt = con.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                System.out.println("\n--- STUDENT RECORDS ---");
                System.out.println("ROLL NO | NAME | COURSE | MARKS");
                System.out.println("------------------------------------");
                while (rs.next()) {
                    System.out.println(rs.getInt("rollno") + " | " +
                                       rs.getString("name") + " | " +
                                       rs.getString("course") + " | " +
                                       rs.getInt("marks"));
                }
                rs.close();
                stmt.close();

            } else if (choice == 3) {
                
                System.out.print("Enter Roll No to update: ");
                int rollno = sc.nextInt();

                System.out.print("Enter New Marks: ");
                int marks = sc.nextInt();

                String query = "UPDATE students SET marks=? WHERE rollno=?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, marks);
                stmt.setInt(2, rollno);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Marks updated successfully!");
                } else {
                    System.out.println("Student record not found!");
                }
                stmt.close();

            } else if (choice == 4) {
                
                System.out.print("Enter Roll No to delete: ");
                int rollno = sc.nextInt();

                String query = "DELETE FROM students WHERE rollno=?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setInt(1, rollno);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("Student record deleted successfully!");
                } else {
                    System.out.println("Student record not found!");
                }
                stmt.close();

            } else if (choice == 5) {
                System.out.println("Exiting system...");
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }

        con.close();
        sc.close();
    }
}