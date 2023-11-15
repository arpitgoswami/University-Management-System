import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;

    public Main() {
        super("Login Interface");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        // Create components
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        roleComboBox = new JComboBox<>(new String[]{"Admin", "Faculty", "Student"});
        JButton loginButton = new JButton("Login");

        // Set layout
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        // Add components to the panel
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Role:"));
        panel.add(roleComboBox);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        // Add panel to the frame
        add(panel, BorderLayout.CENTER);
    }

    private void login() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
        String role = (String) roleComboBox.getSelectedItem();

        // Simple validation
        if (username.isEmpty() || password.length == 0) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check login credentials
        if (authenticate(username, new String(password), role)) {
            JOptionPane.showMessageDialog(this, "Login successful as " + role, "Success", JOptionPane.INFORMATION_MESSAGE);
            // Perform role-specific actions here
        } else {
            JOptionPane.showMessageDialog(this, "Invalid login credentials", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Clear the password field after login attempt
        passwordField.setText("");
    }

    private boolean authenticate(String username, String password, String role) {
        // Simple authentication logic (replace with your authentication logic)
        // For demonstration purposes, the authentication is always successful
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}