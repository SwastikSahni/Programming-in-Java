import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetDemo {

    // Database connection details (same as your CRUD app)
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/jdbc_demo";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String SQL_SELECT = "SELECT ID, NAME, AGE, ADDRESS, SALARY FROM COMPANY";

    /**
     * Main method to run all demonstrations.
     */
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found!");
            return;
        }

        // Use try-with-resources for the main connection
        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            System.out.println("Connection established successfully.\n");
            
            // 1. Set up a clean table and data for our demos
            setupDatabase(con);

            // 2. Demonstrate all ResultSet types
            demoForwardOnly(con);
            demoScrollInsensitive(con);
            demoScrollSensitive(con);
            demoConcurrency(con);

            System.out.println("\n--- All Demos Complete ---");

        } catch (SQLException e) {
            System.err.println("Database Error:");
            e.printStackTrace();
        }
    }

    /**
     * Creates a clean COMPANY table with sample data.
     */
    static void setupDatabase(Connection con) throws SQLException {
        try (Statement st = con.createStatement()) {
            st.executeUpdate("DROP TABLE IF EXISTS COMPANY");
            st.executeUpdate("CREATE TABLE COMPANY (ID INT PRIMARY KEY NOT NULL, NAME VARCHAR(50) NOT NULL, AGE INT NOT NULL, ADDRESS VARCHAR(50), SALARY INT)");
            System.out.println("--- Database Setup ---");
            System.out.println("Table 'COMPANY' created.");
            
            st.executeUpdate("INSERT INTO COMPANY VALUES (1, 'Alice', 30, 'New York', 50000)");
            st.executeUpdate("INSERT INTO COMPANY VALUES (2, 'Bob', 42, 'California', 70000)");
            st.executeUpdate("INSERT INTO COMPANY VALUES (3, 'Charlie', 25, 'Texas', 60000)");
            System.out.println("Inserted 3 sample records.");
            readAllData(con); // Show initial state
        }
    }

    static void demoForwardOnly(Connection con) throws SQLException {
        System.out.println("\n--- DEMO: TYPE_FORWARD_ONLY ---");
        
        try (PreparedStatement ps = con.prepareStatement(SQL_SELECT,
                                     ResultSet.TYPE_FORWARD_ONLY,
                                     ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Moving forward...");
            while (rs.next()) {
                System.out.println("  Read: " + rs.getString("NAME"));
            }

            
            try {
                System.out.println("Attempting rs.previous()...");
                rs.previous(); 
            } catch (SQLException e) {
                System.out.println("  SUCCESS (Expected Error): " + e.getMessage());
            }
        }
    }


    static void demoScrollInsensitive(Connection con) throws SQLException {
        System.out.println("\n--- DEMO: TYPE_SCROLL_INSENSITIVE ---");
        
        try (PreparedStatement ps = con.prepareStatement(SQL_SELECT,
                                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                                     ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Scrolling arbitrarily...");
            rs.last();
            System.out.println("  Last Record: " + rs.getString("NAME"));
            rs.first();
            System.out.println("  First Record: " + rs.getString("NAME"));
            rs.absolute(2);
            System.out.println("  Second Record: " + rs.getString("NAME") + ", Salary=" + rs.getInt("SALARY"));

            // Now, update the database from an external statement
            System.out.println("  Updating Bob's salary to 99999 in DB (externally)...");
            try (Statement external_st = con.createStatement()) {
                external_st.executeUpdate("UPDATE COMPANY SET SALARY = 99999 WHERE ID = 2");
            }

            // Go back to the ResultSet and read the same row.
            // The salary should NOT have changed in this ResultSet.
            rs.absolute(2);
            System.out.println("  Re-reading Second Record from INSENSITIVE ResultSet...");
            System.out.println("  Name: " + rs.getString("NAME") + ", Salary=" + rs.getInt("SALARY"));
            if (rs.getInt("SALARY") == 70000) {
                System.out.println("  SUCCESS: ResultSet is INSENSITIVE and still shows old salary (70000).");
            } else {
                System.out.println("  NOTE: ResultSet showed the new salary (99999).");
            }
        }
        
        // Reset change for next demo
        try (Statement st = con.createStatement()) {
            st.executeUpdate("UPDATE COMPANY SET SALARY = 70000 WHERE ID = 2");
        }
    }

    /**
     * DEMO 3: TYPE_SCROLL_SENSITIVE
     * Tries to show sensitivity to external changes.
     */
    static void demoScrollSensitive(Connection con) throws SQLException {
        System.out.println("\n--- DEMO: TYPE_SCROLL_SENSITIVE ---");
        System.out.println("  NOTE: MySQL may downgrade this type to INSENSITIVE.");
        System.out.println("  This will likely behave just like TYPE_SCROLL_INSENSITIVE.");

        try (PreparedStatement ps = con.prepareStatement(SQL_SELECT,
                                     ResultSet.TYPE_SCROLL_SENSITIVE,
                                     ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = ps.executeQuery()) {
            
            rs.absolute(2);
            System.out.println("  Second Record: " + rs.getString("NAME") + ", Salary=" + rs.getInt("SALARY"));

            // Update externally
            System.out.println("  Updating Bob's salary to 88888 in DB (externally)...");
            try (Statement external_st = con.createStatement()) {
                external_st.executeUpdate("UPDATE COMPANY SET SALARY = 88888 WHERE ID = 2");
            }

            // Re-read from the "SENSITIVE" ResultSet
            // Ideally, this would show 88888, but it probably won't.
            rs.absolute(2); // Re-fetch the row
            System.out.println("  Re-reading Second Record from SENSITIVE ResultSet...");
            System.out.println("  Name: " + rs.getString("NAME") + ", Salary=" + rs.getInt("SALARY"));
            if (rs.getInt("SALARY") == 88888) {
                System.out.println("  SUCCESS: Driver supports SENSITIVE! Salary updated to 88888.");
            } else {
                System.out.println("  AS EXPECTED: Driver downgraded to INSENSITIVE. Salary is still 70000.");
            }
        }
        
        // Reset change for next demo
        try (Statement st = con.createStatement()) {
            st.executeUpdate("UPDATE COMPANY SET SALARY = 70000 WHERE ID = 2");
        }
    }

    /**
     * DEMO 4: CONCUR_READ_ONLY vs CONCUR_UPDATABLE
     * Shows how to update the DB *through* the ResultSet.
     */
    static void demoConcurrency(Connection con) throws SQLException {
        System.out.println("\n--- DEMO: CONCUR_READ_ONLY ---");
        
        try (PreparedStatement ps = con.prepareStatement(SQL_SELECT,
                                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                                     ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = ps.executeQuery()) {

            rs.absolute(1); // Go to Alice
            
            // Try to update (this will fail)
            try {
                System.out.println("Attempting rs.updateInt() on READ_ONLY ResultSet...");
                rs.updateInt("SALARY", 55000); // This line will throw an exception
                rs.updateRow();
            } catch (SQLException e) {
                System.out.println("  SUCCESS (Expected Error): " + e.getMessage());
            }
        }

        System.out.println("\n--- DEMO: CONCUR_UPDATABLE ---");
        // NOTE: The SELECT statement MUST include the Primary Key for this to work!
        try (PreparedStatement ps = con.prepareStatement(SQL_SELECT,
                                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                                     ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = ps.executeQuery()) {

            // 1. UPDATE a row
            rs.absolute(2); // Go to Bob
            System.out.println("  Updating Bob's salary (ID=2) from 70000 to 75000 via ResultSet...");
            rs.updateInt("SALARY", 75000);
            rs.updateRow();
            System.out.println("  Update complete.");

            // 2. DELETE a row
            rs.absolute(3); // Go to Charlie
            System.out.println("  Deleting Charlie (ID=3) via ResultSet...");
            rs.deleteRow();
            System.out.println("  Delete complete.");

            // 3. INSERT a row
            System.out.println("  Inserting new record (ID=4, David) via ResultSet...");
            rs.moveToInsertRow();
            rs.updateInt("ID", 4);
            rs.updateString("NAME", "David");
            rs.updateInt("AGE", 35);
            rs.updateString("ADDRESS", "Florida");
            rs.updateInt("SALARY", 45000);
            rs.insertRow();
            System.out.println("  Insert complete.");
            
        } catch (SQLException e) {
            System.err.println("  ERROR: CONCUR_UPDATABLE failed. This can happen if the");
            System.err.println("  driver doesn't support it or the query is too complex.");
            e.printStackTrace();
        }

        // 4. Verify all changes
        System.out.println("\n  --- Verifying UPATABLE changes in DB ---");
        readAllData(con);
        System.out.println("  (You should see Bob's new salary, David added, and Charlie gone)");
    }
    
    /**
     * Helper method to print all current data from the table.
     */
    static void readAllData(Connection con) throws SQLException {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM COMPANY ORDER BY ID")) {
            
            System.out.println("  --- Current Table Data ---");
            int count = 0;
            while (rs.next()) {
                System.out.printf("  ID: %d, Name: %s, Age: %d, Salary: %d\n",
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getInt("AGE"),
                        rs.getInt("SALARY"));
                count++;
            }
            if (count == 0) System.out.println("  Table is empty.");
            System.out.println("  --------------------------");
        }
    }
}