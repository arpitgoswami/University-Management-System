import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import java.time.format.DateTimeFormatter;

public class Header extends javax.swing.JPanel {

    /**
     * Creates new form Header
     */
    public Header() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        currentTime = new javax.swing.JLabel();
        currentValue = new javax.swing.JLabel();
        lastLogin = new javax.swing.JLabel();
        lastValue = new javax.swing.JLabel();
        help = new javax.swing.JButton();
        profile = new javax.swing.JButton();
        update = new javax.swing.JButton();
        logout = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 0, 51));
        setPreferredSize(new java.awt.Dimension(1200, 50));

        currentTime.setForeground(new java.awt.Color(255, 255, 255));
        currentTime.setText("Current Time ");

        currentValue.setForeground(new java.awt.Color(255, 255, 255));

        lastLogin.setForeground(new java.awt.Color(255, 255, 255));
        lastLogin.setText("Login Time");

        lastValue.setForeground(new java.awt.Color(255, 255, 255));

        help.setText("Help");
        help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });

        profile.setText("Profile");
        profile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileActionPerformed(evt);
            }
        });

        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        logout.setText("Log Out");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(currentTime)
                                        .addComponent(lastLogin))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lastValue)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 531, Short.MAX_VALUE)
                                                .addComponent(help)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(profile))
                                        .addComponent(currentValue))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(update)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logout)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(currentTime)
                                        .addComponent(currentValue))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lastLogin)
                                        .addComponent(lastValue))
                                .addContainerGap(13, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(update)
                                        .addComponent(logout)
                                        .addComponent(profile)
                                        .addComponent(help))
                                .addContainerGap())
        );
    }// </editor-fold>

    private void helpActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(null, helpContent);
    }

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "Update Here!");
    }

    private void profileActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "Profile Here!");
    }

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(null, "You have been successfully logged out.");

        // Close the MainFrame
        MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(this);
        mainFrame.dispose();

        // Open the Login frame
        Login login = new Login();
        login.loginFrame.setVisible(true);
    }

    private void init() {
        // Create a Timer to update the time every second
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCurrentTime();
            }
        });
        timer.start();
        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        capitalizedDay = dayOfWeek.name().substring(0, 1) + dayOfWeek.name().substring(1).toLowerCase();
        lastValue.setText(": " + currentTimeValue.format(formatter) + " | " + currentDate + " | " + capitalizedDay);
    }

    private void updateCurrentTime() {
        LocalTime currentTimeValue = LocalTime.now();
        currentValue.setText(": " + currentTimeValue.format(formatter) + " | " + "Indian Standard Time Zone");
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel currentTime;
    private javax.swing.JLabel currentValue;
    private javax.swing.JButton help;
    private javax.swing.JLabel lastLogin;
    private javax.swing.JLabel lastValue;
    private javax.swing.JButton logout;
    private javax.swing.JButton profile;
    private javax.swing.JButton update;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private String capitalizedDay;
    LocalTime currentTimeValue = LocalTime.now();
    // End of variables declaration

    private static String helpContent = "<html>" +
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

}
