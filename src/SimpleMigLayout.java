import com.formdev.flatlaf.FlatIntelliJLaf;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class SimpleMigLayout extends JFrame {

    public SimpleMigLayout() {
        super("MigForm Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Set the MigLayout layout manager
        setLayout(new MigLayout("wrap 2", "[][]", "[][][]")); // Added an extra row for the footer

        // Create components
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);

        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField(5);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);

        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(20);

        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField(10);

        // Add components to the frame using MigLayout constraints
        add(nameLabel);
        add(nameField, "growx");

        add(ageLabel);
        add(ageField);

        add(emailLabel);
        add(emailField, "growx");

        add(addressLabel);
        add(addressField, "growx");

        add(phoneLabel);
        add(phoneField);

        // Create the footer panel
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(UIManager.getColor("Panel.background")); // Set the background color to match the theme

        JLabel footerLabel = new JLabel("FlatLaf Footer");
        footerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        footerPanel.add(footerLabel);

        // Add the footer panel to the frame
        add(footerPanel, "span, growx");

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimpleMigLayout();
        });
    }
}
