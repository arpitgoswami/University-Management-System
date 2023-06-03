import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import com.formdev.flatlaf.FlatLightLaf;

public class Main extends JFrame {

    public static int width = 160;

    /* Launch the application. */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                    frame.setTitle("Management System");

                    FileMenu FileMenu = new FileMenu();
                    FileMenu.create(frame);

                    DBConnection DatabaseConnection = new DBConnection();
                    DatabaseConnection.create();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* Create the frame. */
    public Main() throws SQLException {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(width, width, 1050, 725);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel firstTab = new JPanel(new BorderLayout());
        tabbedPane.addTab("First Tab", firstTab);

        JPanel secondTab = new JPanel(new GridBagLayout());
        tabbedPane.addTab("Second Tab", secondTab);

        JPanel thirdTab = new JPanel(new BorderLayout());
        tabbedPane.addTab("Third Tab", thirdTab);

        JPanel fourthTab = new JPanel(new GridBagLayout());
        tabbedPane.addTab("Fourth Tab", fourthTab);

        JPanel fifthTab = new JPanel(new BorderLayout());
        tabbedPane.addTab("Fifth Tab", fifthTab);

        add(tabbedPane, BorderLayout.CENTER);

        FirstTab FirstTab = new FirstTab();
        FirstTab.create(firstTab);

        SecondTab SecondTab = new SecondTab();
        SecondTab.create(secondTab);

        JPanel sideBar = new JPanel();
        sideBar.setPreferredSize(new Dimension(200, 100));
        sideBar.setBackground(Color.darkGray);
        tabbedPane.add(sideBar, BorderLayout.EAST);

    }
}