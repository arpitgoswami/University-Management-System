import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CourseManagement extends JFrame {

    private DefaultTableModel tableModel;
    private JTable courseTable;
    private JLabel totalCoursesLabel;
    private JLabel averageCreditsLabel;
    private final String CSV_FILE_PATH = "./csv/courses.csv";

    public CourseManagement() {
        super("Course Management System");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1200, 800);

        // Create table model with column names
        tableModel = new DefaultTableModel(new Object[]{"Course Name", "Course Code", "Credits"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make the table uneditable
            }
        };
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

        // Load existing data from CSV file
        loadCoursesFromCSV();

        // Add action listeners to the buttons
        addCourseButton.addActionListener(e -> addCourse());
        removeCourseButton.addActionListener(e -> removeCourse());
        editCourseButton.addActionListener(e -> editCourse());
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
                        saveCoursesToCSV();
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
            saveCoursesToCSV();
            updateDashboard();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a course to remove", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editCourse() {
        int selectedRow = courseTable.getSelectedRow();
        if (selectedRow != -1) {
            JFrame editFrame = new JFrame("Edit Course");
            editFrame.setSize(400, 300);
            editFrame.setLayout(new GridLayout(4, 2));

            JLabel nameLabel = new JLabel("Course Name:");
            JTextField nameField = new JTextField(tableModel.getValueAt(selectedRow, 0).toString());

            JLabel codeLabel = new JLabel("Course Code:");
            JTextField codeField = new JTextField(tableModel.getValueAt(selectedRow, 1).toString());

            JLabel creditsLabel = new JLabel("Credits:");
            JTextField creditsField = new JTextField(tableModel.getValueAt(selectedRow, 2).toString());

            JButton saveButton = new JButton("Save");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newName = nameField.getText();
                    String newCode = codeField.getText();
                    String newCreditsString = creditsField.getText();
                    try {
                        int newCredits = Integer.parseInt(newCreditsString);
                        tableModel.setValueAt(newName, selectedRow, 0);
                        tableModel.setValueAt(newCode, selectedRow, 1);
                        tableModel.setValueAt(newCredits, selectedRow, 2);
                        saveCoursesToCSV();
                        updateDashboard();
                        editFrame.dispose();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(editFrame, "Please enter a valid number for credits", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            editFrame.add(nameLabel);
            editFrame.add(nameField);
            editFrame.add(codeLabel);
            editFrame.add(codeField);
            editFrame.add(creditsLabel);
            editFrame.add(creditsField);
            editFrame.add(new JLabel());
            editFrame.add(saveButton);

            editFrame.setVisible(true);
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

    private void loadCoursesFromCSV() {
        File file = new File(CSV_FILE_PATH);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 3) {
                        tableModel.addRow(new Object[]{data[0], data[1], Integer.parseInt(data[2])});
                    }
                }
                updateDashboard();
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveCoursesToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                String name = tableModel.getValueAt(row, 0).toString();
                String code = tableModel.getValueAt(row, 1).toString();
                String credits = tableModel.getValueAt(row, 2).toString();
                writer.write(name + "," + code + "," + credits + "\n");
            }
            System.out.println("Courses saved to CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main() {
        SwingUtilities.invokeLater(() -> new CourseManagement().setVisible(true));
    }
}
