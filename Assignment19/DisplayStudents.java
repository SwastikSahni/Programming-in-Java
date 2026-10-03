import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DisplayStudents {

    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/jdbc_demo";
        String user = "root";
        String password = "root";

        String query = "SELECT rollno, name FROM student";

        try (Connection con = DriverManager.getConnection(dbUrl, user, password);
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("-------------------------");
            System.out.println("ROLL NO  |  NAME");
            System.out.println("-------------------------");

            while (rs.next()) {
                int rollno = rs.getInt("rollno");
                String name = rs.getString("name");
                System.out.println(rollno + "      |  " + name);
            }

            System.out.println("-------------------------");

        } catch (Exception e) {
            System.err.println("Database Error: " + e.getMessage());
        }
    }
}