import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class BookIssueTracker extends JFrame {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/jdbc_demo";
    private static final String USER = "root";
    private static final String PASS = "root";

    private JTextField txtIssueId, txtBookId, txtStudentName, txtIssueDate, txtReturnDate;
    private JTable table;
    private DefaultTableModel tableModel;

    public BookIssueTracker() {
        setTitle("Book Issue Tracking System");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Issue Details"));

        inputPanel.add(new JLabel("Issue ID (Auto):"));
        txtIssueId = new JTextField();
        txtIssueId.setEditable(false);
        inputPanel.add(txtIssueId);

        inputPanel.add(new JLabel("Book ID:"));
        txtBookId = new JTextField();
        inputPanel.add(txtBookId);

        inputPanel.add(new JLabel("Student Name:"));
        txtStudentName = new JTextField();
        inputPanel.add(txtStudentName);

        inputPanel.add(new JLabel("Issue Date (YYYY-MM-DD):"));
        txtIssueDate = new JTextField();
        inputPanel.add(txtIssueDate);

        inputPanel.add(new JLabel("Return Date (YYYY-MM-DD):"));
        txtReturnDate = new JTextField();
        inputPanel.add(txtReturnDate);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton btnAdd = new JButton("Issue Book");
        JButton btnUpdate = new JButton("Update Record");
        JButton btnDelete = new JButton("Delete Record");
        JButton btnClear = new JButton("Clear / Refresh");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        tableModel = new DefaultTableModel(new String[]{"Issue ID", "Book ID", "Student Name", "Issue Date", "Return Date"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(inputPanel, BorderLayout.CENTER);
        topContainer.add(buttonPanel, BorderLayout.SOUTH);

        add(topContainer, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnAdd.addActionListener(e -> addRecord());
        btnUpdate.addActionListener(e -> updateRecord());
        btnDelete.addActionListener(e -> deleteRecord());
        btnClear.addActionListener(e -> clearFields());

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    txtIssueId.setText(tableModel.getValueAt(row, 0).toString());
                    txtBookId.setText(tableModel.getValueAt(row, 1).toString());
                    txtStudentName.setText(tableModel.getValueAt(row, 2).toString());
                    txtIssueDate.setText(tableModel.getValueAt(row, 3).toString());
                    txtReturnDate.setText(tableModel.getValueAt(row, 4).toString());
                }
            }
        });

        loadData();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private void loadData() {
        tableModel.setRowCount(0);
        String query = "SELECT * FROM book_issues";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("issue_id"),
                        rs.getInt("book_id"),
                        rs.getString("student_name"),
                        rs.getDate("issue_date"),
                        rs.getDate("return_date")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error Loading Data: " + ex.getMessage());
        }
    }

    private void addRecord() {
        if (txtBookId.getText().isEmpty() || txtStudentName.getText().isEmpty() ||
                txtIssueDate.getText().isEmpty() || txtReturnDate.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!");
            return;
        }

        String query = "INSERT INTO book_issues (book_id, student_name, issue_date, return_date) VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, Integer.parseInt(txtBookId.getText()));
            stmt.setString(2, txtStudentName.getText());
            stmt.setDate(3, Date.valueOf(txtIssueDate.getText()));
            stmt.setDate(4, Date.valueOf(txtReturnDate.getText()));

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book issued successfully!");
            clearFields();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Adding Record: " + ex.getMessage());
        }
    }

    private void updateRecord() {
        if (txtIssueId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a record from the table to update!");
            return;
        }

        String query = "UPDATE book_issues SET book_id = ?, student_name = ?, issue_date = ?, return_date = ? WHERE issue_id = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, Integer.parseInt(txtBookId.getText()));
            stmt.setString(2, txtStudentName.getText());
            stmt.setDate(3, Date.valueOf(txtIssueDate.getText()));
            stmt.setDate(4, Date.valueOf(txtReturnDate.getText()));
            stmt.setInt(5, Integer.parseInt(txtIssueId.getText()));

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Record updated successfully!");
                clearFields();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Updating Record: " + ex.getMessage());
        }
    }

    private void deleteRecord() {
        if (txtIssueId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a record from the table to delete!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this issue record?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        String query = "DELETE FROM book_issues WHERE issue_id = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, Integer.parseInt(txtIssueId.getText()));

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Record deleted successfully!");
                clearFields();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Deleting Record: " + ex.getMessage());
        }
    }

    private void clearFields() {
        txtIssueId.setText("");
        txtBookId.setText("");
        txtStudentName.setText("");
        txtIssueDate.setText("");
        txtReturnDate.setText("");
        loadData();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookIssueTracker().setVisible(true));
    }
}