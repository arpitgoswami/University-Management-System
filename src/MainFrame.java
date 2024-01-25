import javax.swing.*;
import javax.swing.border.EmptyBorder;
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
        sidebar.setLayout(new GridLayout(10, 1));
        sidebar.setPreferredSize(new Dimension(240, 0));

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

        sidebar.setBackground(Color.DARK_GRAY);
        sidebar.setOpaque(true);

        // Add buttons to the sidebar
        sidebar.add(studentButton);
        sidebar.add(facultyButton);
        sidebar.add(courseButton);
        sidebar.add(attendanceButton);
        sidebar.add(feeButton);

        // Create content panel with cards and notifications
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10,10,10,10));

        setLayout(new BorderLayout());
        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        StatusBar statusBar = new StatusBar();
        StatusBar.main(this);
        contentPanel.add(statusBar.statusPanel, BorderLayout.NORTH);

        Dashboard dashboard = new Dashboard();
        contentPanel.add(dashboard, BorderLayout.CENTER);
    }

    private JButton createSidebarButton(String buttonText) {
        JButton button = new JButton(buttonText);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
