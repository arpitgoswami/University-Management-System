import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class StudentManagement extends JFrame {

    private DefaultTableModel tableModel;
    private JTable studentTable;
    private final String CSV_FILE_PATH = "./csv/students.csv";
    private JLabel totalStudents;

    public StudentManagement() {
        super("Student Management System");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1200, 800);

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        JButton searchButton = new JButton("Search By Name");
        JButton refresh = new JButton("Refresh");
        totalStudents = new JLabel("Total Students: 0");

        topPanel.add(searchButton);
        topPanel.add(refresh);
        topPanel.add(totalStudents);
        add(topPanel, BorderLayout.NORTH);

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               updateTotalStudents();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSearchStudentFrame();
            }
        });

        // Create table model with column names
        tableModel = new DefaultTableModel(
                new Object[]{"Registration ID", "Student Name", "Gender", "Date of Birth", "Admission Date", "Course", "Current Semester", "Discount", "Mobile No."},
                0
        );
        studentTable = new JTable(tableModel) {
            @Override
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false; // Make all cells non-editable
            }
        };
        studentTable.setDefaultEditor(Object.class, null); // Set default editor to null
        JScrollPane scrollPane = new JScrollPane(studentTable);
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton editButton = new JButton("Edit Student");

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners to the buttons
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddStudentFrame();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.removeRow(selectedRow);
                    saveStudentsToCSV();
                    updateTotalStudents();
                } else {
                    JOptionPane.showMessageDialog(StudentManagement.this, "Please select a student to remove", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    openEditStudentFrame(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(StudentManagement.this, "Please select a student to edit", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Load students from CSV file on initialization
        loadStudentsFromCSV();
    }

    private void updateTotalStudents() {
        totalStudents.setText("Total Students: " + tableModel.getRowCount());
    }

    private void loadStudentsFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found. Starting with an empty list.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        updateTotalStudents();
    }

    private void saveStudentsToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                for (int col = 0; col < tableModel.getColumnCount(); col++) {
                    writer.write(tableModel.getValueAt(row, col) + ",");
                }
                writer.write("\n");
            }
            System.out.println("Students saved to CSV.");
            updateTotalStudents();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openAddStudentFrame() {
        JFrame addStudentFrame = new JFrame("Add Student");
        addStudentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addStudentFrame.setSize(400, 300);
        addStudentFrame.setLayout(new GridLayout(tableModel.getColumnCount() + 1, 2));

        JTextField[] fields = new JTextField[tableModel.getColumnCount()];
        for (int i = 0; i < fields.length; i++) {
            addStudentFrame.add(new JLabel(tableModel.getColumnName(i) + ":"));
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
                tableModel.addRow(details);
                saveStudentsToCSV();
                addStudentFrame.dispose();
            }
        });

        addStudentFrame.add(new JLabel());
        addStudentFrame.add(saveButton);
        addStudentFrame.setVisible(true);
    }

    private void openEditStudentFrame(int rowToEdit) {
        JFrame editStudentFrame = new JFrame("Edit Student");
        editStudentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editStudentFrame.setSize(400, 300);
        editStudentFrame.setLayout(new GridLayout(tableModel.getColumnCount() + 1, 2));

        JTextField[] fields = new JTextField[tableModel.getColumnCount()];
        for (int i = 0; i < fields.length; i++) {
            editStudentFrame.add(new JLabel(tableModel.getColumnName(i) + ":"));
            fields[i] = new JTextField(tableModel.getValueAt(rowToEdit, i).toString());
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
                    tableModel.setValueAt(details[i], rowToEdit, i);
                }
                saveStudentsToCSV();
                editStudentFrame.dispose();
            }
        });

        editStudentFrame.add(new JLabel());
        editStudentFrame.add(updateButton);
        editStudentFrame.setVisible(true);
    }

    private void openSearchStudentFrame() {
        JFrame searchStudentFrame = new JFrame("Search Student");
        searchStudentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchStudentFrame.setSize(400, 100);
        searchStudentFrame.setLayout(new FlowLayout());

        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchField.getText().toLowerCase();
                if (!searchTerm.isEmpty()) {
                    for (int row = 0; row < tableModel.getRowCount(); row++) {
                        for (int col = 0; col < tableModel.getColumnCount(); col++) {
                            String cellValue = tableModel.getValueAt(row, col).toString().toLowerCase();
                            if (cellValue.contains(searchTerm)) {
                                studentTable.changeSelection(row, col, false, false);
                                searchStudentFrame.dispose();
                                return;
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(searchStudentFrame, "No matching student found", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        searchStudentFrame.add(searchField);
        searchStudentFrame.add(searchButton);
        searchStudentFrame.setVisible(true);
    }

    public static void main() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentManagement().setVisible(true);
            }
        });
    }
}