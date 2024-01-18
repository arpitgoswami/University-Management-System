import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class CourseManagement extends JFrame {
    DefaultTableModel model = new DefaultTableModel();
    private JTable studentTable;

    public CourseManagement() {

        setTitle("Student Registration Viewer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 800);

        initializeComponents();
        loadDataFromCSV("./csv/courses.csv");
    }

    private void initializeComponents() {

        // Create the table with the model
        studentTable = new JTable(model);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(studentTable);

        // Add the scroll pane to the frame
        add(scrollPane, BorderLayout.CENTER);

        // Add Edit and Delete buttons
        JButton addButton = new JButton("Add Course");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Add the button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners for Edit and Delete buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    editCourse(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(CourseManagement.this, "Please select a student to edit", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCourse();
            }
        });
    }

    private void addCourse() {
        JFrame addStudentFrame = new JFrame("Add Student");
        addStudentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addStudentFrame.setSize(400, 300);
        addStudentFrame.setLayout(new GridLayout(model.getColumnCount() + 1, 2));

        JTextField[] fields = new JTextField[model.getColumnCount()];
        for (int i = 0; i < fields.length; i++) {
            addStudentFrame.add(new JLabel(model.getColumnName(i) + ":"));
            fields[i] = new JTextField();
            addStudentFrame.add(fields[i]);
        }

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] details = new String[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    details[i] = fields[i].getText();
                    if (details[i].isEmpty()) {
                        JOptionPane.showMessageDialog(addStudentFrame, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                model.addRow(details);
                saveStudentsToCSV();
                addStudentFrame.dispose();
            }
        });

        addStudentFrame.add(new JLabel());
        addStudentFrame.add(saveButton);
        addStudentFrame.setVisible(true);
    }

    private void saveStudentsToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./csv/courses.csv"))) {
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    writer.write(model.getValueAt(row, col) + ",");
                }
                writer.write("\n");
            }
            System.out.println("Students saved to CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editCourse(int rowToEdit) {
        JFrame editStudentFrame = new JFrame("Edit Student");
        editStudentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editStudentFrame.setSize(400, 300);
        editStudentFrame.setLayout(new GridLayout(model.getColumnCount() + 1, 2));

        JTextField[] fields = new JTextField[model.getColumnCount()];
        for (int i = 0; i < fields.length; i++) {
            editStudentFrame.add(new JLabel(model.getColumnName(i) + ":"));
            fields[i] = new JTextField(model.getValueAt(rowToEdit, i).toString());
            editStudentFrame.add(fields[i]);
        }

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] details = new String[fields.length];
                for (int i = 0; i < fields.length; i++) {
                    details[i] = fields[i].getText();
                    if (details[i].isEmpty()) {
                        JOptionPane.showMessageDialog(editStudentFrame, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                for (int i = 0; i < details.length; i++) {
                    model.setValueAt(details[i], rowToEdit, i);
                }
                saveStudentsToCSV();
                editStudentFrame.dispose();
            }
        });

        editStudentFrame.add(new JLabel());
        editStudentFrame.add(updateButton);
        editStudentFrame.setVisible(true);
    }

    private void deleteCourse() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) studentTable.getModel();

            // Remove the selected row
            model.removeRow(selectedRow);

            // Save the changes to CSV
            saveDataToCSV("./csv/courses.csv");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a course to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadDataFromCSV(String csvFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;

            DefaultTableModel model = (DefaultTableModel) studentTable.getModel();

            // Read the header and add columns to the table model
            String[] headers = reader.readLine().split(",");
            for (String header : headers) {
                model.addColumn(header);
            }

            // Read the rest of the lines as usual
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                model.addRow(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDataToCSV(String csvFilePath) {
        try (FileWriter writer = new FileWriter(csvFilePath)) {
            DefaultTableModel model = (DefaultTableModel) studentTable.getModel();

            // Write the headers
            for (int i = 0; i < model.getColumnCount(); i++) {
                writer.write(model.getColumnName(i));
                if (i < model.getColumnCount() - 1) {
                    writer.write(",");
                }
            }
            writer.write("\n");

            // Write the data
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    writer.write(model.getValueAt(row, col).toString());
                    if (col < model.getColumnCount() - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }

            System.out.println("Data saved to CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main() {
        SwingUtilities.invokeLater(() -> {
            CourseManagement app = new CourseManagement();
            app.setVisible(true);
        });
    }
}