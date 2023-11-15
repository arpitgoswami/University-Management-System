import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Vector;

public class StudentManagementSystem extends JFrame {

    private DefaultTableModel tableModel;
    private JTable studentTable;
    private final String CSV_FILE_PATH = "students.csv";

    public StudentManagementSystem() {
        super("Student Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);

        // Create table model with column names
        tableModel = new DefaultTableModel(new Object[]{"Student Name"}, 0);
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        JButton addButton = new JButton("Add Student");
        JButton removeButton = new JButton("Remove Student");
        JButton editButton = new JButton("Edit Student");

        // Set layout
        setLayout(new BorderLayout());

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
                addStudent();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editStudent();
            }
        });

        // Load students from CSV file on initialization
        loadStudentsFromCSV();
    }

    private void addStudent() {
        String name = JOptionPane.showInputDialog(this, "Enter student name:");
        if (name != null) {
            tableModel.addRow(new Object[]{name});
            saveStudentsToCSV();
        }
    }

    private void removeStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            saveStudentsToCSV();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to remove", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            String newName = JOptionPane.showInputDialog(this, "Enter new name:");
            if (newName != null) {
                tableModel.setValueAt(newName, selectedRow, 0);
                saveStudentsToCSV();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to edit", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadStudentsFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(new Object[]{data[0]});
            }
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found. Starting with an empty list.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveStudentsToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                writer.write(tableModel.getValueAt(row, 0) + "\n");
            }
            System.out.println("Students saved to CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StudentManagementSystem().setVisible(true);
            }
        });
    }
}
