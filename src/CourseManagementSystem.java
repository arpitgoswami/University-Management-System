import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseManagementSystem extends JFrame {

    private DefaultTableModel tableModel;
    private JTable courseTable;
    private JLabel totalCoursesLabel;
    private JLabel averageCreditsLabel;

    public CourseManagementSystem() {
        super("Course Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Create table model with column names
        tableModel = new DefaultTableModel(new Object[]{"Course Name", "Course Code", "Credits"}, 0);
        courseTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(courseTable);

        // Create dashboard elements
        totalCoursesLabel = new JLabel("Total Courses: 0");
        averageCreditsLabel = new JLabel("Average Credits: 0");

        JButton addCourseButton = new JButton("Add Course");
        JButton removeCourseButton = new JButton("Remove Course");
        JButton editCourseButton = new JButton("Edit Course");

        // Set layout
        setLayout(new BorderLayout());

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);

        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new GridLayout(2, 1));
        dashboardPanel.add(totalCoursesLabel);
        dashboardPanel.add(averageCreditsLabel);

        add(dashboardPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addCourseButton);
        buttonPanel.add(removeCourseButton);
        buttonPanel.add(editCourseButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners to the buttons
        addCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });

        removeCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeCourse();
            }
        });

        editCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editCourse();
            }
        });
    }

    private void addCourse() {
        String name = JOptionPane.showInputDialog(this, "Enter course name:");
        if (name != null) {
            String code = JOptionPane.showInputDialog(this, "Enter course code:");
            if (code != null) {
                String creditsString = JOptionPane.showInputDialog(this, "Enter number of credits:");
                if (creditsString != null) {
                    try {
                        int credits = Integer.parseInt(creditsString);
                        tableModel.addRow(new Object[]{name, code, credits});
                        updateDashboard();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid number for credits", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    private void removeCourse() {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            updateDashboard();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a course to remove", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editCourse() {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow != -1) {
            String newName = JOptionPane.showInputDialog(this, "Enter new course name:");
            if (newName != null) {
                String newCode = JOptionPane.showInputDialog(this, "Enter new course code:");
                if (newCode != null) {
                    String newCreditsString = JOptionPane.showInputDialog(this, "Enter new number of credits:");
                    if (newCreditsString != null) {
                        try {
                            int newCredits = Integer.parseInt(newCreditsString);
                            tableModel.setValueAt(newName, selectedRow, 0);
                            tableModel.setValueAt(newCode, selectedRow, 1);
                            tableModel.setValueAt(newCredits, selectedRow, 2);
                            updateDashboard();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Please enter a valid number for credits", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a course to edit", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateDashboard() {
        int totalCourses = tableModel.getRowCount();
        totalCoursesLabel.setText("Total Courses: " + totalCourses);

        int totalCredits = 0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            totalCredits += (int) tableModel.getValueAt(row, 2);
        }

        double averageCredits = totalCourses > 0 ? (double) totalCredits / totalCourses : 0;
        averageCreditsLabel.setText(String.format("Average Credits: %.2f", averageCredits));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CourseManagementSystem().setVisible(true);
            }
        });
    }
}
