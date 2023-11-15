import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class FacultyManagement extends JFrame {

    private DefaultTableModel tableModel;
    private JTable facultyTable;
    private JLabel totalFacultyLabel;
    private JLabel averageExperienceLabel;
    private final String CSV_FILE_PATH = "faculty.csv";

    public FacultyManagement() {
        super("Faculty Management System");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1200, 800);

        // Create table model with column names
        tableModel = new DefaultTableModel(new Object[]{"Faculty Name", "Experience (years)"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Set cells to be uneditable
            }
        };

        facultyTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(facultyTable);

        // Create dashboard elements
        totalFacultyLabel = new JLabel("Total Faculty: 0");
        averageExperienceLabel = new JLabel("Average Experience: 0 years");

        JButton addFacultyButton = new JButton("Add Faculty");
        JButton removeFacultyButton = new JButton("Remove Faculty");
        JButton editFacultyButton = new JButton("Edit Faculty");

        // Set layout
        setLayout(new BorderLayout());

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);

        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new GridLayout(2, 1));
        dashboardPanel.add(totalFacultyLabel);
        dashboardPanel.add(averageExperienceLabel);

        add(dashboardPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addFacultyButton);
        buttonPanel.add(removeFacultyButton);
        buttonPanel.add(editFacultyButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners to the buttons
        addFacultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFaculty();
            }
        });

        removeFacultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeFaculty();
            }
        });

        editFacultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editFaculty();
            }
        });

        // Load faculty from CSV file on initialization
        loadFacultyFromCSV();
        updateDashboard();
    }

    private void addFaculty() {
        String name = JOptionPane.showInputDialog(this, "Enter faculty name:");
        if (name != null) {
            String experienceString = JOptionPane.showInputDialog(this, "Enter years of experience:");
            if (experienceString != null) {
                try {
                    int experience = Integer.parseInt(experienceString);
                    tableModel.addRow(new Object[]{name, experience});
                    updateDashboard();
                    saveFacultyToCSV();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid number for experience", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void removeFaculty() {
        int selectedRow = facultyTable.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
            updateDashboard();
            saveFacultyToCSV();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a faculty member to remove", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editFaculty() {
        int selectedRow = facultyTable.getSelectedRow();
        if (selectedRow != -1) {
            String newName = JOptionPane.showInputDialog(this, "Enter new name:");
            if (newName != null) {
                String newExperienceString = JOptionPane.showInputDialog(this, "Enter new years of experience:");
                if (newExperienceString != null) {
                    try {
                        int newExperience = Integer.parseInt(newExperienceString);
                        tableModel.setValueAt(newName, selectedRow, 0);
                        tableModel.setValueAt(newExperience, selectedRow, 1);
                        updateDashboard();
                        saveFacultyToCSV();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Please enter a valid number for experience", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a faculty member to edit", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateDashboard() {
        int totalFaculty = tableModel.getRowCount();
        totalFacultyLabel.setText("Total Faculty: " + totalFaculty);

        int totalExperience = 0;
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            totalExperience += (int) tableModel.getValueAt(row, 1);
        }

        double averageExperience = totalFaculty > 0 ? (double) totalExperience / totalFaculty : 0;
        averageExperienceLabel.setText(String.format("Average Experience: %.2f years", averageExperience));
    }

    private void loadFacultyFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    String name = data[0];
                    int experience = Integer.parseInt(data[1]);
                    tableModel.addRow(new Object[]{name, experience});
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found. Starting with an empty list.");
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void saveFacultyToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH))) {
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                writer.write(tableModel.getValueAt(row, 0) + "," + tableModel.getValueAt(row, 1) + "\n");
            }
            System.out.println("Faculty data saved to CSV.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FacultyManagement().setVisible(true);
            }
        });
    }
}
