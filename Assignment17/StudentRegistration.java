import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentRegistration {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Registration");
        frame.setSize(300,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER , 10 , 15));

        JLabel lblName = new JLabel("Name:");
        JTextField tfName = new JTextField(15);

        JLabel lblEmail = new JLabel("\nEmail:");
        JTextField tfEmail = new JTextField(15);

        JLabel lblCourse = new JLabel("\nCourse:");
        String[] courses = {"CS" , "IT" , "Mechanical" , "Civil"};
        JComboBox<String> cbCourse = new JComboBox<>(courses);

        JButton btnSubmit = new JButton("Submit");

        frame.add(lblName);
        frame.add(tfName);
        frame.add(lblEmail);
        frame.add(tfEmail);
        frame.add(lblCourse);
        frame.add(cbCourse);
        frame.add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String message = "Name:  " + tfName.getText() +
                "\nEmail: " + tfEmail.getText() +
                "\nCourse: " + cbCourse.getSelectedItem();

                JOptionPane.showMessageDialog(frame, message);
            }
        });
        frame.setVisible(true);
    }
}

