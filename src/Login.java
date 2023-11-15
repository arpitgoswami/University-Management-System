import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Login extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JRadioButton adminRadio;
    private JRadioButton facultyRadio;
    private JRadioButton studentRadio;

    public Login() {
        super("Login Interface");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 200);

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

        setLayout(new GridLayout(6, 2));

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Role:"));
        add(adminRadio);
        add(new JLabel());
        add(facultyRadio);
        add(new JLabel());
        add(studentRadio);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(e -> login());
        registerButton.addActionListener(e -> register());
    }

    private void login() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
        String role = "";

        if (adminRadio.isSelected()) role = "Admin";
        else if (facultyRadio.isSelected()) role = "Faculty";
        else if (studentRadio.isSelected()) role = "Student";

        if (username.isEmpty() || password.length == 0 || role.isEmpty()) {
            showError("Please enter all fields");
            return;
        }

        if (authenticate(username, new String(password), role)) {
            showSuccess("Login successful as " + role);
            MainFrame mainFrame = new MainFrame();
            //mainFrame.main();
            dispose();
            // Open new window or perform actions
        } else {
            showError("Invalid login credentials");
        }

        passwordField.setText("");
    }

    private boolean authenticate(String username, String password, String role) {
        try (BufferedReader reader = new BufferedReader(new FileReader("./csv/registration.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3 && data[0].equals(username) && data[1].equals(password) && data[2].equals(role)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void register() {
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
        String role = "";

        if (adminRadio.isSelected()) role = "Admin";
        else if (facultyRadio.isSelected()) role = "Faculty";
        else if (studentRadio.isSelected()) role = "Student";

        if (username.isEmpty() || password.length == 0 || role.isEmpty()) {
            showError("Please enter all fields");
            return;
        }

        saveRegistrationData(username, new String(password), role);
    }

    private void saveRegistrationData(String username, String password, String role) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./csv/registration.csv", true))) {
            writer.write(username + "," + password + "," + role + "\n");
            showSuccess("Registration successful!");
        } catch (IOException e) {
            showError("Error saving registration data");
            e.printStackTrace();
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login frame = new Login();
            frame.setVisible(true);
        });
    }

}
