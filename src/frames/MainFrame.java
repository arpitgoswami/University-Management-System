package frames;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import components.*;
import functionality.Footer;
import functionality.Header;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("University Management System");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

        tabbedPane.addTab("components.Dashboard", dashboardPanel);
        tabbedPane.addTab("Student Management", studentPanel);
        tabbedPane.addTab("Faculty Management", facultyPanel);
        tabbedPane.addTab("Course Management", coursePanel);
        tabbedPane.addTab("Attendance Management", attendancePanel);
        tabbedPane.addTab("Fee Management", feePanel);

        tabbedPane.setBorder(new EmptyBorder(10,10,10,10));

        setLayout(new BorderLayout());
        //add(tabbedPane, BorderLayout.CENTER);
        MainFrame2 mainFrame2 = new MainFrame2();
        add(mainFrame2, BorderLayout.CENTER);

        // Create functionalities.Header
        Header header = new Header();
        add(header, BorderLayout.NORTH);

        // Create functionalities.Footer
        Footer footer = new Footer();
        add(footer, BorderLayout.SOUTH);

        // JMenuBar Initialization
        JMenuBar menuBar = new JMenuBar();
        JMenu themesMenu = new JMenu("Themes");

        JMenuItem intelliJItem = new JMenuItem("IntelliJ Theme");
        JMenuItem darkItem = new JMenuItem("Dracula Theme");

        themesMenu.add(intelliJItem);
        themesMenu.add(darkItem);

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
    }

    private void changeTheme(int selectedIndex) {
        try {
            switch (selectedIndex) {
                case 0:
                    UIManager.setLookAndFeel(new FlatIntelliJLaf());
                    break;
                case 1:
                    UIManager.setLookAndFeel(new FlatDarculaLaf());
                    break;
            }
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
