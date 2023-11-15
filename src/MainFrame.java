import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("University Management System");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1200, 800);

        // Create cards to display different information
        JPanel cardPanel = new JPanel(new GridLayout(2, 2));
        cardPanel.add(createCard("Total Students", "1000"));
        cardPanel.add(createCard("Total Employees", "500"));
        cardPanel.add(createCard("Revenue", "$1,000,000"));
        cardPanel.add(createCard("Total Profit", "$500,000"));

        // Create notifications column
        JPanel notificationPanel = new JPanel(new BorderLayout());
        notificationPanel.setBorder(BorderFactory.createTitledBorder("Notifications"));
        JTextArea notificationArea = new JTextArea(15, 20);
        JScrollPane scrollPane = new JScrollPane(notificationArea);
        notificationPanel.add(scrollPane, BorderLayout.CENTER);

        // Create buttons for different functionalities
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        JButton studentButton = new JButton("Student Management");
        JButton facultyButton = new JButton("Faculty Management");
        JButton courseButton = new JButton("Course Management");
        JButton attendanceButton = new JButton("Attendance Management");
        buttonPanel.add(studentButton);
        buttonPanel.add(facultyButton);
        buttonPanel.add(courseButton);
        buttonPanel.add(attendanceButton);

        // Add action listeners to the buttons
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StudentManagement studentManagement = new StudentManagement();
                studentManagement.main();
            }
        });

        facultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FacultyManagement facultyManagement = new FacultyManagement();
                facultyManagement.main();
            }
        });

        courseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CourseManagement courseManagement = new CourseManagement();
                courseManagement.main();
            }
        });

        attendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AttendanceManagement attendanceManagement = new AttendanceManagement();
                attendanceManagement.main();}
        });

        // Set layout for the main frame
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
        add(notificationPanel, BorderLayout.SOUTH);
    }

    private JPanel createCard(String title, String value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createTitledBorder(title));
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 18));
        valueLabel.setHorizontalAlignment(JLabel.CENTER);
        card.add(valueLabel, BorderLayout.CENTER);
        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}