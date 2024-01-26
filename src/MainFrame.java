import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

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
        setLocationRelativeTo(null);

        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

        JPanel dashboardPanel = new JPanel(new BorderLayout());
        Dashboard dashboard = new Dashboard();
        dashboardPanel.add(dashboard, BorderLayout.CENTER);

        JPanel studentPanel = new JPanel(new BorderLayout());
        StudentManagement studentManagement = new StudentManagement();
        studentPanel.add(studentManagement, BorderLayout.CENTER);

        JPanel facultyPanel = new JPanel(new BorderLayout());
        FacultyManagement facultyManagement = new FacultyManagement();
        facultyPanel.add(facultyManagement, BorderLayout.CENTER);

        JPanel coursePanel = new JPanel(new BorderLayout());
        CourseManagement courseManagement = new CourseManagement();
        coursePanel.add(courseManagement, BorderLayout.CENTER);

        JPanel attendancePanel = new JPanel(new BorderLayout());
        AttendanceManagement attendanceManagement = new AttendanceManagement();
        attendancePanel.add(attendanceManagement, BorderLayout.CENTER);

        JPanel feePanel = new JPanel(new BorderLayout());
        FeeManagement feeManagement = new FeeManagement();
        feePanel.add(feeManagement, BorderLayout.CENTER);

        tabbedPane.addTab("Dashboard", dashboardPanel);
        tabbedPane.addTab("Student Management", studentPanel);
        tabbedPane.addTab("Faculty Management", facultyPanel);
        tabbedPane.addTab("Course Management", coursePanel);
        tabbedPane.addTab("Attendance Management", attendancePanel);
        tabbedPane.addTab("Fee Management", feePanel);

        tabbedPane.setBorder(new EmptyBorder(10,10,10,10));

        setLayout(new BorderLayout());
        add(tabbedPane, BorderLayout.CENTER);

        // Create Header
        Header header = new Header();
        add(header, BorderLayout.NORTH);

        // Create Footer
        Footer footer = new Footer();
        add(footer, BorderLayout.SOUTH);

        // JMenuBar Initialization
        JMenuBar menuBar = new JMenuBar();
        JMenu themesMenu = new JMenu("Themes");
        JMenu fileMenu = new JMenu("File");

        JMenuItem intelliJItem = new JMenuItem("Flat IntelliJ");
        JMenuItem darkItem = new JMenuItem("Flat Dark");
        JMenuItem lightItem = new JMenuItem("Flat Light");

        themesMenu.add(intelliJItem);
        themesMenu.add(darkItem);
        themesMenu.add(lightItem);

        menuBar.add(fileMenu);
        menuBar.add(themesMenu);
        setJMenuBar(menuBar);

        intelliJItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTheme(0);
            }
        });

        darkItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTheme(1);
            }
        });

        lightItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTheme(2);
            }
        });
    }

    private void changeTheme(int selectedIndex) {
        try {
            switch (selectedIndex) {
                case 0:
                    UIManager.setLookAndFeel(new FlatIntelliJLaf());
                    break;
                case 1:
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                    break;
                case 2:
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    break;
            }
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public static void main() {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
