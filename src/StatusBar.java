import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusBar {
    public static JPanel statusPanel;
    public static boolean status = true;
    public static boolean updateStatus = true;
    public static String helpContent = "<html>" +
            "The College Student Management System serves as a comprehensive platform that caters to the needs of both students and administrators. <br>" +
            "Whether you are a student seeking information about courses and grades or an administrator responsible for maintaining accurate records, <br>" +
            "this system provides the tools and features you need to streamline and enhance the overall academic experience. The College Student <br>" +
            "Management System is designed to be user-friendly and efficient, offering a wide range of functionalities that address various <br>" +
            "aspects of academic life.<br><br>" +
            "<b>User Registration and Login</b>:<br>" +
            "&nbsp;&nbsp;&nbsp;&nbsp; - To register, go to the registration page and fill in the required information.<br>" +
            "&nbsp;&nbsp;&nbsp;&nbsp; - After registration, log in with your credentials on the login page.<br><br>" +
            "<b>Dashboard Overview</b>:<br>" +
            "&nbsp;&nbsp;&nbsp;&nbsp; - Describe the main elements of the user dashboard.<br>" +
            "&nbsp;&nbsp;&nbsp;&nbsp; - Highlight key features and navigation options.<br><br>" +
            "<b>Profile Management</b>:<br>" +
            "&nbsp;&nbsp;&nbsp;&nbsp; - Explain how users can update their profiles.<br>" +
            "&nbsp;&nbsp;&nbsp;&nbsp; - Provide guidance on changing passwords and other account settings.<br><br>" +
            "<b>Attendance Tracking</b>:<br>" +
            "&nbsp;&nbsp;&nbsp;&nbsp; - Detail how to record and view student attendance.<br>" +
            "&nbsp;&nbsp;&nbsp;&nbsp; - Explain any attendance policies.<br><br>" +
            "For further information <a href=\"https://example.com\">click here.</a>" +
            "</html>";
    public static void main(MainFrame mainFrame){
        statusPanel = new JPanel();
        statusPanel.setLayout(new GridLayout(1,1));

        JPanel statusLeftPanel = new JPanel();
        statusLeftPanel.setLayout(new GridLayout(4,4));

        Login login = new Login();

        statusLeftPanel.add(new JLabel("Username: "));
        statusLeftPanel.add(new JLabel(login.userName));
        statusLeftPanel.add(new JLabel("Role:"));
        statusLeftPanel.add(new JLabel(login.roleStatus));

        // Server Status Remaning
        statusLeftPanel.add(new JLabel("Server Status: "));
        JLabel statusLabel = new JLabel(status ? "Running" : "Failed");
        statusLeftPanel.add(statusLabel);

        // Update Status Remaning
        statusLeftPanel.add(new JLabel("Update Status: "));
        JLabel updateLabel = new JLabel(updateStatus ? "Up-to-date" : "Never Updated");
        statusLeftPanel.add(updateLabel);

        statusPanel.add(statusLeftPanel);

        JPanel statusRightPanel = new JPanel();
        statusRightPanel.setLayout(new GridLayout(2,4));

        JButton Help = new JButton("Help");
        Help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainFrame, helpContent);
            }
        });
        statusRightPanel.add(Help);

        JButton Profile = new JButton("Profile");
        Profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainFrame, "Profile Here!");
            }
        });
        statusRightPanel.add(Profile);

        JButton Update = new JButton("Update");
        Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainFrame, "Updates Here!");
            }
        });
        statusRightPanel.add(Update);

        JButton logOut = new JButton("Log Out");
        logOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainFrame, "You have been successfully logged out.");
                mainFrame.dispose();

                Login login = new Login();
                login.setVisible(true);
            }
        });
        statusRightPanel.add(logOut);
        statusPanel.add(statusRightPanel);
    }
}
