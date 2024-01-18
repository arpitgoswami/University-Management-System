import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FeeManagement extends JFrame {

    private JTable studentTable;

    public FeeManagement() {
        setTitle("Student Registration Viewer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 800);

        initializeComponents();
        loadDataFromCSV("./csv/fees.csv");
    }

    private void initializeComponents() {
        // Create a default table model without specifying column names
        DefaultTableModel model = new DefaultTableModel();

        // Create the table with the model
        studentTable = new JTable(model);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(studentTable);

        // Add the scroll pane to the frame
        add(scrollPane, BorderLayout.CENTER);
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

    public static void main() {
        SwingUtilities.invokeLater(() -> {
            FeeManagement app = new FeeManagement();
            app.setVisible(true);
        });
    }
}
