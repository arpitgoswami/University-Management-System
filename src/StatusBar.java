import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusBar {
    public static JPanel statusPanel;
    public static void main(MainFrame mainFrame){
        statusPanel = new JPanel();
        statusPanel.setSize(1200,200);

        JPanel statusLeftPanel = new JPanel();
        statusLeftPanel.setLayout(new BoxLayout(statusLeftPanel, BoxLayout.PAGE_AXIS));

        statusLeftPanel.add(new JLabel("Name: Your Name"));
        statusLeftPanel.add(new JLabel("Role: Your Role"));
        statusLeftPanel.add(new JLabel("Connection: Successful"));

        statusPanel.add(statusLeftPanel, BorderLayout.WEST);

        JPanel statusRightPanel = new JPanel();
        statusRightPanel.setLayout(new BoxLayout(statusRightPanel, BoxLayout.LINE_AXIS));

        JButton Help = new JButton("Help");
        Help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(mainFrame, "Docs and Other Help!");
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

        statusPanel.add(statusRightPanel, BorderLayout.EAST);

    }
}
