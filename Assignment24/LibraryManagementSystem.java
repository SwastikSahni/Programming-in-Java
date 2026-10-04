import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class LibraryManagementSystem extends JFrame {

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/jdbc_demo";
    private static final String USER = "root";
    private static final String PASS = "root";

    private JTextField txtBookId, txtTitle, txtAuthor, txtGenre, txtPrice;
    private JTable bookTable;
    private DefaultTableModel tableModel;

    public LibraryManagementSystem() {
        setTitle("Library Management System");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));

        inputPanel.add(new JLabel("Book ID (Auto-generated/For Search):"));
        txtBookId = new JTextField();
        txtBookId.setEditable(false);
        inputPanel.add(txtBookId);

        inputPanel.add(new JLabel("Book Title:"));
        txtTitle = new JTextField();
        inputPanel.add(txtTitle);

        inputPanel.add(new JLabel("Author:"));
        txtAuthor = new JTextField();
        inputPanel.add(txtAuthor);

        inputPanel.add(new JLabel("Genre:"));
        txtGenre = new JTextField();
        inputPanel.add(txtGenre);

        inputPanel.add(new JLabel("Price (₹):"));
        txtPrice = new JTextField();
        inputPanel.add(txtPrice);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        JButton btnAdd = new JButton("Add Book");
        JButton btnUpdate = new JButton("Update Book");
        JButton btnDelete = new JButton("Delete Book");
        JButton btnClear = new JButton("Clear / Refresh");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        tableModel = new DefaultTableModel(new String[]{"ID", "Title", "Author", "Genre", "Price"}, 0);
        bookTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(bookTable);

        JPanel topContainer = new JPanel(new BorderLayout());
        topContainer.add(inputPanel, BorderLayout.CENTER);
        topContainer.add(buttonPanel, BorderLayout.SOUTH);

        add(topContainer, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);

        btnAdd.addActionListener(e -> addBook());
        btnUpdate.addActionListener(e -> updateBook());
        btnDelete.addActionListener(e -> deleteBook());
        btnClear.addActionListener(e -> clearFields());

        bookTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = bookTable.getSelectedRow();
                if (selectedRow != -1) {
                    txtBookId.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    txtTitle.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    txtAuthor.setText(tableModel.getValueAt(selectedRow, 2).toString());
                    txtGenre.setText(tableModel.getValueAt(selectedRow, 3).toString());
                    txtPrice.setText(tableModel.getValueAt(selectedRow, 4).toString());
                }
            }
        });

        loadBookData();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private void loadBookData() {
        tableModel.setRowCount(0);
        String query = "SELECT * FROM books";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("genre"),
                        rs.getDouble("price")
                });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error Loading Data: " + ex.getMessage());
        }
    }

    private void addBook() {
        if (txtTitle.getText().isEmpty() || txtAuthor.getText().isEmpty() || txtPrice.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in Title, Author, and Price!");
            return;
        }

        String query = "INSERT INTO books (title, author, genre, price) VALUES (?, ?, ?, ?)";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, txtTitle.getText());
            stmt.setString(2, txtAuthor.getText());
            stmt.setString(3, txtGenre.getText());
            stmt.setDouble(4, Double.parseDouble(txtPrice.getText()));

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book added successfully!");
            clearFields();
            loadBookData();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Adding Book: " + ex.getMessage());
        }
    }

    private void updateBook() {
        if (txtBookId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a book from the table to update!");
            return;
        }

        String query = "UPDATE books SET title = ?, author = ?, genre = ?, price = ? WHERE book_id = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, txtTitle.getText());
            stmt.setString(2, txtAuthor.getText());
            stmt.setString(3, txtGenre.getText());
            stmt.setDouble(4, Double.parseDouble(txtPrice.getText()));
            stmt.setInt(5, Integer.parseInt(txtBookId.getText()));

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Book updated successfully!");
                clearFields();
                loadBookData();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Updating Book: " + ex.getMessage());
        }
    }

    private void deleteBook() {
        if (txtBookId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a book from the table to delete!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this book?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        String query = "DELETE FROM books WHERE book_id = ?";

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, Integer.parseInt(txtBookId.getText()));

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Book deleted successfully!");
                clearFields();
                loadBookData();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error Deleting Book: " + ex.getMessage());
        }
    }

    private void clearFields() {
        txtBookId.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtGenre.setText("");
        txtPrice.setText("");
        loadBookData();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibraryManagementSystem().setVisible(true));
    }
}