import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class StudentManagement extends JFrame {

    public String csvPath = "./csv/students.csv";
    private final NonEditableTableModel tableModel;
    private final JTable csvTable;
    public boolean status;
    private JTextField searchField;

    public StudentManagement() {
        setTitle("Student Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 800);

        tableModel = new NonEditableTableModel(new Object[]{}, 0);
        csvTable = new JTable(tableModel);
        csvTable.setRowSelectionAllowed(true);

        initializeComponents();
        status = loadDataFromCSV(csvPath);
    }

    private void initializeComponents() {
        JScrollPane scrollPane = new JScrollPane(csvTable);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addButtonToPanel(buttonPanel, "Add", this::addEntry);
        addButtonToPanel(buttonPanel, "Delete", this::deleteSelectedRow);
        addButtonToPanel(buttonPanel, "Edit", this::editSelectedEntry);
        addButtonToPanel(buttonPanel, "Refresh", this::refreshTable);
        addButtonToPanel(buttonPanel, "Print", this::printCSV);
        addButtonToPanel(buttonPanel, "Logout", this::logout);

        JLabel statusLabel = new JLabel(!status? "Server Running" : "Server Failed");
        statusLabel.setForeground(Color.BLACK);
        statusLabel.setBackground(!status? Color.GREEN : Color.RED);
        statusLabel.setOpaque(true);
        buttonPanel.add(statusLabel);

        add(buttonPanel, BorderLayout.SOUTH);

        searchField = new JTextField();
        searchField.setColumns(20);
        JButton searchButton = new JButton("Search By ID");
        searchButton.addActionListener(this::searchData);

        JTextField searchFieldm = new JTextField();
        searchFieldm.setColumns(20);
        JButton searchmButton = new JButton("Search By Name");
        searchmButton.addActionListener(this::searchData);

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        searchPanel.add(searchFieldm);
        searchPanel.add(searchmButton);

        add(searchPanel, BorderLayout.NORTH);
    }

    private void searchData(ActionEvent actionEvent) {
        String searchTerm = searchField.getText().toLowerCase();

        if (searchTerm.isEmpty()) {
            // If the search term is empty, refresh the table to show all data
            refreshTable(actionEvent);
            return;
        }

        DefaultTableModel filteredModel = new NonEditableTableModel(new Object[]{}, 0);

        for (int col = 0; col < tableModel.getColumnCount(); col++) {
            filteredModel.addColumn(tableModel.getColumnName(col));
        }

        for (int row = 0; row < tableModel.getRowCount(); row++) {
            Object cellValue = tableModel.getValueAt(row, 0); // Assuming ID is in the first column
            if (cellValue != null && cellValue.toString().toLowerCase().contains(searchTerm)) {
                Vector<Object> rowData = new Vector<>();
                for (int col = 0; col < tableModel.getColumnCount(); col++) {
                    rowData.add(tableModel.getValueAt(row, col));
                }
                filteredModel.addRow(rowData);
            }
        }

        // Update the table with the filtered model
        csvTable.setModel(filteredModel);
    }

    private void addButtonToPanel(JPanel panel, String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }

    private void addEntry(ActionEvent actionEvent) {
        JFrame addEntryFrame = createEntryFrame("Add Entry", true);
        addEntryFrame.setVisible(true);
    }

    private void editSelectedEntry(ActionEvent actionEvent) {
        int selectedRow = csvTable.getSelectedRow();
        if (selectedRow != -1) {
            JFrame editEntryFrame = createEntryFrame("Edit Entry", false);
            populateFieldsWithRowData(editEntryFrame, selectedRow);
            editEntryFrame.setVisible(true);
        } else {
            showError("Please select a row to edit");
        }
    }

    private JFrame createEntryFrame(String title, boolean isNewEntry) {
        JFrame entryFrame = new JFrame(title);
        entryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        entryFrame.setSize(400, 300);
        entryFrame.setLayout(new GridLayout(tableModel.getColumnCount() + 1, 2));

        JTextField[] fields = new JTextField[tableModel.getColumnCount()];
        for (int i = 0; i < fields.length; i++) {
            entryFrame.add(new JLabel(tableModel.getColumnName(i) + ":"));
            fields[i] = new JTextField();
            entryFrame.add(fields[i]);
        }

        JButton saveOrUpdateButton = new JButton(isNewEntry ? "Save" : "Update");
        saveOrUpdateButton.addActionListener(e -> {
            String[] data = new String[fields.length];
            for (int i = 0; i < fields.length; i++) {
                data[i] = fields[i].getText();
                if (data[i].isEmpty()) {
                    showError("Please fill all fields");
                    return;
                }
            }

            if (isNewEntry) {
                tableModel.addRow(data);
            } else {
                int selectedRow = csvTable.getSelectedRow();
                for (int i = 0; i < data.length; i++) {
                    tableModel.setValueAt(data[i], selectedRow, i);
                }
            }

            saveDataToCSV(csvPath);
            entryFrame.dispose();
        });

        entryFrame.add(new JLabel());
        entryFrame.add(saveOrUpdateButton);

        return entryFrame;
    }

    private void populateFieldsWithRowData(JFrame entryFrame, int selectedRow) {
        JTextField[] fields = new JTextField[tableModel.getColumnCount()];
        for (int i = 0; i < fields.length; i++) {
            entryFrame.add(new JLabel(tableModel.getColumnName(i) + ":"));
            fields[i] = new JTextField(tableModel.getValueAt(selectedRow, i).toString());
            entryFrame.add(fields[i]);
        }
    }

    public boolean loadDataFromCSV(String csvFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line = reader.readLine();

            String[] headers = line.split(",");
            if (tableModel.getColumnCount() == 0) {
                for (String header : headers) {
                    tableModel.addColumn(header);
                }
            }

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void deleteSelectedRow(ActionEvent actionEvent) {
        int selectedRow = csvTable.getSelectedRow();
        if (selectedRow != -1) {
            String selectedCourseCode = tableModel.getValueAt(selectedRow, 0).toString();
            int csvRowIndex = findRowIndexByCourseCode(selectedCourseCode);
            if (csvRowIndex != -1) {
                tableModel.removeRow(selectedRow);
                saveDataToCSV(csvPath);
            } else {
                showError("Failed to find corresponding row in CSV");
            }
        } else {
            showError("Please select a row to delete");
        }
    }

    private void refreshTable(ActionEvent actionEvent) {
        tableModel.setRowCount(0);
        csvTable.setModel(tableModel);
        loadDataFromCSV(csvPath);
    }

    private void printCSV(ActionEvent actionEvent) {
        CsvPrinter csvPrinter = new CsvPrinter();
        csvPrinter.printCSV(csvPath);
    }

    private void logout(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(null, "You have been successfully logged out.");
        Login login = new Login();
        login.setVisible(true);
        dispose();
    }

    private int findRowIndexByCourseCode(String courseCode) {
        for (int row = 0; row < tableModel.getRowCount(); row++) {
            if (tableModel.getValueAt(row, 0).toString().equals(courseCode)) {
                return row;
            }
        }
        return -1;
    }

    private void saveDataToCSV(String csvFilePath) {
        try (FileWriter writer = new FileWriter(csvFilePath)) {
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                writer.write(tableModel.getColumnName(i));
                if (i < tableModel.getColumnCount() - 1) {
                    writer.write(",");
                }
            }
            writer.write("\n");

            for (int row = 0; row < tableModel.getRowCount(); row++) {
                for (int col = 0; col < tableModel.getColumnCount(); col++) {
                    writer.write(tableModel.getValueAt(row, col).toString());
                    if (col < tableModel.getColumnCount() - 1) {
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

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagement app = new StudentManagement();
            app.setVisible(true);
        });
    }

    public static class NonEditableTableModel extends DefaultTableModel {

        public NonEditableTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }
}
