import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Calculator");
        frame.setSize(300, 220);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));

        JTextField tfNum1 = new JTextField(8);
        JTextField tfNum2 = new JTextField(8);
        
        JButton btnAdd = new JButton("+");
        JButton btnSub = new JButton("-");
        
        JLabel lblResult = new JLabel("Result: ");

        frame.add(new JLabel("Number 1:"));
        frame.add(tfNum1);
        frame.add(new JLabel("Number 2:"));
        frame.add(tfNum2);
        frame.add(btnAdd);
        frame.add(btnSub);
        frame.add(lblResult);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double n1 = Double.parseDouble(tfNum1.getText());
                    double n2 = Double.parseDouble(tfNum2.getText());
                    lblResult.setText("Result: " + (n1 + n2));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers!");
                }
            }
        });

        btnSub.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double n1 = Double.parseDouble(tfNum1.getText());
                    double n2 = Double.parseDouble(tfNum2.getText());
                    lblResult.setText("Result: " + (n1 - n2));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers!");
                }
            }
        });

        frame.setVisible(true);
    }
}