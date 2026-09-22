import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankBalanceCalculator {
    private static double currentBalance = 0.0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bank Balance Calculator");
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel lblInitial = new JLabel(" Initial Balance:");
        JTextField tfInitial = new JTextField();

        JLabel lblAmount = new JLabel(" Transaction Amount:");
        JTextField tfAmount = new JTextField();

        JButton btnDeposit = new JButton("Deposit (+)");
        JButton btnWithdraw = new JButton("Withdraw (-)");

        JLabel lblBalance = new JLabel(" Current Balance:");
        JLabel lblDisplayBalance = new JLabel("$0.00");

        frame.add(lblInitial);
        frame.add(tfInitial);

        frame.add(lblAmount);
        frame.add(tfAmount);

        frame.add(btnDeposit);
        frame.add(btnWithdraw);

        frame.add(lblBalance);
        frame.add(lblDisplayBalance);

        btnDeposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!tfInitial.getText().isEmpty()) {
                        currentBalance = Double.parseDouble(tfInitial.getText());
                        tfInitial.setText("");
                    }

                    double amount = Double.parseDouble(tfAmount.getText());
                    currentBalance += amount;
                    lblDisplayBalance.setText("$" + String.format("%.2f", currentBalance));
                    tfAmount.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Enter a valid numeric amount!");
                }
            }
        });

        btnWithdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!tfInitial.getText().isEmpty()) {
                        currentBalance = Double.parseDouble(tfInitial.getText());
                        tfInitial.setText("");
                    }

                    double amount = Double.parseDouble(tfAmount.getText());
                    
                    if (amount > currentBalance) {
                        JOptionPane.showMessageDialog(frame, "Insufficient funds!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        currentBalance -= amount;
                        lblDisplayBalance.setText("$" + String.format("%.2f", currentBalance));
                        tfAmount.setText("");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Enter a valid numeric amount!");
                }
            }
        });

        frame.setVisible(true);
    }
}