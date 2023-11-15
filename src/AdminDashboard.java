import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {

    private JLabel totalStudentsLabel;
    private JLabel totalFacultyLabel;
    private JLabel totalCoursesLabel;

    public AdminDashboard() {
        super("Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        // Create dashboard elements
        totalStudentsLabel = new JLabel("Total Students: 0");
        totalFacultyLabel = new JLabel("Total Faculty: 0");
        totalCoursesLabel = new JLabel("Total Courses: 0");

        JButton generateReportButton = new JButton("Generate Report");

        // Set layout
        setLayout(new GridLayout(4, 1));

        // Add components to the frame
        add(totalStudentsLabel);
        add(totalFacultyLabel);
        add(totalCoursesLabel);
        add(generateReportButton);

        // Add action listener to the button
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });
    }

    private void generateReport() {
        // Simulate generating a report
        JOptionPane.showMessageDialog(this, "Report generated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Update dashboard with actual data (you can call this method when data changes)
    public void updateDashboard(int totalStudents, int totalFaculty, int totalCourses) {
        totalStudentsLabel.setText("Total Students: " + totalStudents);
        totalFacultyLabel.setText("Total Faculty: " + totalFaculty);
        totalCoursesLabel.setText("Total Courses: " + totalCourses);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdminDashboard adminDashboard = new AdminDashboard();
                // Update dashboard with actual data (replace with real data)
                adminDashboard.updateDashboard(100, 20, 30);
                adminDashboard.setVisible(true);
            }
        });
    }
}
