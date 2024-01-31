package frames;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import functionality.Footer;
import functionality.Header;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("University Management System");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        MainFrame2 mainFrame2 = new MainFrame2();
        add(mainFrame2, BorderLayout.CENTER);

        // Create functionalities.Header
        Header header = new Header();
        add(header, BorderLayout.NORTH);

        // Create functionalities.Footer
        Footer footer = new Footer();
        add(footer, BorderLayout.SOUTH);

        // JMenuBar Initialization
        JMenuBar menuBar = new JMenuBar();
        JMenu themesMenu = new JMenu("Themes");

        JMenuItem intelliJItem = new JMenuItem("IntelliJ Theme");
        JMenuItem darkItem = new JMenuItem("Dracula Theme");

        themesMenu.add(intelliJItem);
        themesMenu.add(darkItem);

        menuBar.add(themesMenu);
        setJMenuBar(menuBar);

        intelliJItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTheme(0);
            }
        });
        darkItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTheme(1);
            }
        });
    }

    private void changeTheme(int selectedIndex) {
        try {
            switch (selectedIndex) {
                case 0:
                    UIManager.setLookAndFeel(new FlatIntelliJLaf());
                    break;
                case 1:
                    UIManager.setLookAndFeel(new FlatDarculaLaf());
                    break;
            }
            SwingUtilities.updateComponentTreeUI(this);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

    public static void main() {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
