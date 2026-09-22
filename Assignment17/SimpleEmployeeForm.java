import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleEmployeeForm {
    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Employee Registration");
        frame.setSize(350, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        frame.setLayout(new GridLayout(5, 2, 10, 10));
        JLabel lblId = new JLabel(" Employee ID:");
        JTextField tfId = new JTextField();

        JLabel lblName = new JLabel(" Name:");
        JTextField tfName = new JTextField();

        JLabel lblDept = new JLabel(" Department:");
        JTextField tfDept = new JTextField();

        JLabel lblSalary = new JLabel(" Salary:");
        JTextField tfSalary = new JTextField();

        JButton btnSubmit = new JButton("Submit");

       
        frame.add(lblId);
        frame.add(tfId);
        
        frame.add(lblName);
        frame.add(tfName);
        
        frame.add(lblDept);
        frame.add(tfDept);
        
        frame.add(lblSalary);
        frame.add(tfSalary);
        
        frame.add(new JLabel()); 
        frame.add(btnSubmit);

        
        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String info = "Employee ID: " + tfId.getText() +
                             "\nName: " + tfName.getText() +
                             "\nDepartment: " + tfDept.getText() +
                             "\nSalary: " + tfSalary.getText();

                
                JOptionPane.showMessageDialog(frame, info, "Employee Details", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
}