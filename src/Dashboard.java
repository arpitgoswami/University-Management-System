import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Dashboard extends JPanel {
    public Dashboard(){
        setLayout(new BorderLayout());
        
        // Function Calls
        String totalStudents;
        String totalEmployees;
        String totalCourses;
        try {
            totalStudents = String.valueOf(countLines("./csv/students.csv"));
            totalEmployees = String.valueOf(countLines("./csv/faculty.csv"));
            totalCourses = String.valueOf(countLines("./csv/courses.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        JPanel cardPanel = new JPanel(new GridLayout(2, 2));
        cardPanel.add(createCard("Total Students", totalStudents));
        cardPanel.add(createCard("Total Employees", totalEmployees));
        cardPanel.add(createCard("Revenue", totalCourses));
        cardPanel.add(createCard("Total Profit", "$500,000"));

        JPanel notificationPanel = new JPanel(new BorderLayout());
        notificationPanel.setBorder(BorderFactory.createTitledBorder("Notifications"));
        JTextArea notificationArea = new JTextArea(15, 20);
        JScrollPane scrollPane = new JScrollPane(notificationArea);
        notificationPanel.add(scrollPane, BorderLayout.CENTER);

        add(cardPanel, BorderLayout.CENTER);
        add(notificationPanel, BorderLayout.SOUTH);
    }

    private JPanel createCard(String title, String value) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createTitledBorder(title));
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 18));
        valueLabel.setHorizontalAlignment(JLabel.CENTER);
        card.add(valueLabel, BorderLayout.CENTER);
        return card;
    }

    public static int countLines(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int lineCount = 0;
            while (reader.readLine() != null) {
                lineCount++;
            }
            return lineCount-1;
        }
    }
}
