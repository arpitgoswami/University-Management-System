import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class Login {

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JRadioButton adminRadio;
    private final JRadioButton facultyRadio;
    private final JRadioButton studentRadio;

    public static String roleStatus;
    public static String userName;
    public JFrame loginFrame;

    private static final String CSV_FILE_PATH = "./csv/registration.csv";

    public Login() {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        loginFrame = new JFrame();
        loginFrame.setTitle("Login Interface");
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginFrame.setSize(360, 570);
        loginFrame.setResizable(false);
        //setUndecorated(true);

        // Image Creation
        ImageIcon originalIcon = new ImageIcon("./csv/banner.jpg"); // Replace with the actual path to your image
        Image originalImage = originalIcon.getImage();

        int targetWidth = 360; // Set your desired width
        int targetHeight = 360; // Set your desired height

        Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel(resizedIcon);
        loginFrame.add(imageLabel, BorderLayout.NORTH);

        loginFrame.setLocationRelativeTo(null);

        JPanel loginCredentials = new JPanel();
        loginCredentials.setLayout(new GridLayout(6,2));

        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        adminRadio = new JRadioButton("Admin");
        facultyRadio = new JRadioButton("Faculty");
        studentRadio = new JRadioButton("Student");
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(adminRadio);
        roleGroup.add(facultyRadio);
        roleGroup.add(studentRadio);

        loginCredentials.add(new JLabel("Username:"));
        loginCredentials.add(usernameField);
        loginCredentials.add(new JLabel("Password:"));
        loginCredentials.add(passwordField);
        loginCredentials.add(new JLabel("Role:"));
        loginCredentials.add(adminRadio);
        loginCredentials.add(new JLabel());
        loginCredentials.add(facultyRadio);
        loginCredentials.add(new JLabel());
        loginCredentials.add(studentRadio);
        loginCredentials.add(loginButton);
        loginCredentials.add(registerButton);

        EmptyBorder emptyBorder = new EmptyBorder(10,10,10,10);
        loginCredentials.setBorder(emptyBorder);

        loginFrame.add(loginCredentials, BorderLayout.SOUTH);
        loginFrame.setForeground(Color.BLACK);
        loginButton.addActionListener(this::performLoginOrRegister);
        registerButton.addActionListener(this::performLoginOrRegister);
    }

    private void performLoginOrRegister(ActionEvent e) {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
        String role = "";

        if (adminRadio.isSelected()) role = UserRole.ADMIN.name();
        else if (facultyRadio.isSelected()) role = UserRole.FACULTY.name();
        else if (studentRadio.isSelected()) role = UserRole.STUDENT.name();

        if (username.isEmpty() || password.length == 0 || role.isEmpty()) {
            showError("Please enter all fields");
            return;
        }

        if (e.getActionCommand().equals("Login")) {
            performLogin(username, new String(password), role);
        } else {
            performRegister(username, new String(password), role);
        }

        passwordField.setText("");
    }

    private void performLogin(String username, String password, String role) {
        if (authenticate(username, password, role)) {
            showSuccess("Login successful as " + role.toLowerCase());
            MainFrame mainFrame = new MainFrame();
            roleStatus = role;
            userName = username;
            mainFrame.main();
            loginFrame.dispose();
        } else {
            showError("Invalid login credentials");
        }
    }

    private void performRegister(String username, String password, String role) {
        saveRegistrationData(username, password, role);
    }

    private boolean authenticate(String username, String password, String role) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3 && data[0].equals(username) && data[1].equals(password) && data[2].equals(role)) {
                    return true;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private void saveRegistrationData(String username, String password, String role) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            writer.write(username + "," + password + "," + role + "\n");
            showSuccess("Registration successful!");
        } catch (IOException ex) {
            showError("Error saving registration data");
            ex.printStackTrace();
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(loginFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(loginFrame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login frame = new Login();
            frame.loginFrame.setVisible(true);
        });
    }

    private enum UserRole {
        ADMIN,
        FACULTY,
        STUDENT
    }
}
