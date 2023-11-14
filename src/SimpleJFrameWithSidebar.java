import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleJFrameWithSidebar extends JFrame {

    private JTextArea mainTextArea;

    public SimpleJFrameWithSidebar() {
        super("Simple JFrame with Sidebar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Create components
        mainTextArea = new JTextArea();
        mainTextArea.setEditable(false);

        // Create sidebar buttons
        JButton option1Button = createSidebarButton("Option 1");
        JButton option2Button = createSidebarButton("Option 2");
        JButton option3Button = createSidebarButton("Option 3");
        JButton option4Button = createSidebarButton("Option 4");
        JButton option5Button = createSidebarButton("Option 5");

        // Create menu bar and menu items
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        // Add action listener to the exit menu item
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add menu items to the menu bar
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        // Set layout
        setLayout(new BorderLayout());

        // Add components to the frame
        setJMenuBar(menuBar);

        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new GridLayout(5, 1));
        sidebarPanel.add(option1Button);
        sidebarPanel.add(option2Button);
        sidebarPanel.add(option3Button);
        sidebarPanel.add(option4Button);
        sidebarPanel.add(option5Button);

        add(sidebarPanel, BorderLayout.WEST);
        add(new JScrollPane(mainTextArea), BorderLayout.CENTER);
    }

    private JButton createSidebarButton(String buttonText) {
        JButton button = new JButton(buttonText);

        // Add action listener to the buttons
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayOptionMessage(buttonText);
            }
        });

        return button;
    }

    private void displayOptionMessage(String option) {
        mainTextArea.setText("Selected Option: " + option);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleJFrameWithSidebar().setVisible(true);
            }
        });
    }
}
