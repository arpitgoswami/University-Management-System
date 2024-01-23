import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("University Management System");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Management System");
        setSize(1200, 800);

        // Create sidebar with buttons for different functionalities
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(10,1));
        sidebar.setPreferredSize(new Dimension(240,0));

        JButton studentButton = createSidebarButton("Student Management");
        JButton facultyButton = createSidebarButton("Faculty Management");
        JButton courseButton = createSidebarButton("Course Management");
        JButton attendanceButton = createSidebarButton("Attendance Management");
        JButton feeButton = createSidebarButton("Fee Management");

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
                attendanceManagement.main();
            }
        });

        feeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FeeManagement feeManagement = new FeeManagement();
                feeManagement.main();
            }
        });

        // Add buttons to the sidebar
        sidebar.add(studentButton);
        sidebar.add(facultyButton);
        sidebar.add(courseButton);
        sidebar.add(attendanceButton);
        sidebar.add(feeButton);

        // Create content panel with cards and notifications
        JPanel contentPanel = new JPanel(new BorderLayout());
        JPanel cardPanel = new JPanel(new GridLayout(2, 2));
        cardPanel.add(createCard("Total Students", "1000"));
        cardPanel.add(createCard("Total Employees", "500"));
        cardPanel.add(createCard("Revenue", "$1,000,000"));
        cardPanel.add(createCard("Total Profit", "$500,000"));

        JPanel notificationPanel = new JPanel(new BorderLayout());
        notificationPanel.setBorder(BorderFactory.createTitledBorder("Notifications"));
        JTextArea notificationArea = new JTextArea(15, 20);
        JScrollPane scrollPane = new JScrollPane(notificationArea);
        notificationPanel.add(scrollPane, BorderLayout.CENTER);

        contentPanel.add(cardPanel, BorderLayout.CENTER);
        contentPanel.add(notificationPanel, BorderLayout.SOUTH);

        // Set layout for the main frame
        setLayout(new BorderLayout());
        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        StatusBar statusBar = new StatusBar();
        StatusBar.main(this);
        add(statusBar.statusPanel, BorderLayout.NORTH);
    }

    private JButton createSidebarButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
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
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
            //System.out.println(role);
        });
    }
}
