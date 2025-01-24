import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class StudentManagementUI {
    private final StudentService studentService;

    public StudentManagementUI() {
        studentService = new StudentService();
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        UIManager.put("Panel.background", new Color(230, 230, 250)); 
        UIManager.put("Button.background", new Color(255, 182, 193)); 
        UIManager.put("Button.foreground", Color.BLACK);
        UIManager.put("Label.foreground", Color.BLACK);
        UIManager.put("TextField.background", new Color(255, 255, 240));
        UIManager.put("TextField.foreground", Color.BLACK);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:", SwingConstants.CENTER);
        nameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        JTextField nameField = new JTextField();

        JLabel ageLabel = new JLabel("Age:", SwingConstants.CENTER);
        ageLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        JTextField ageField = new JTextField();

        JLabel gradeLabel = new JLabel("Grade:", SwingConstants.CENTER);
        gradeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        JTextField gradeField = new JTextField();


        JButton addButton = new JButton("Add Student");
        JButton viewButton = new JButton("View Students");

        addButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        viewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(ageLabel);
        panel.add(ageField);
        panel.add(gradeLabel);
        panel.add(gradeField);
        panel.add(addButton);
        panel.add(viewButton);

        frame.add(panel);
        frame.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String grade = gradeField.getText();
                studentService.addStudent(name, age, grade);
                JOptionPane.showMessageDialog(frame, "🌟 Student added successfully! 🌟");
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String students = studentService.getAllStudents();
                JTextArea textArea = new JTextArea(students);
                textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                JOptionPane.showMessageDialog(frame, scrollPane, "Student List", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
