import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800, 800));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(15, 2, 4,4));
        panel.setPreferredSize(new Dimension(500, 400));

        panel.add(new JLabel("Name"));
        panel.add(new JTextField());

        panel.add(new JLabel("Mobile Number"));
        panel.add(new JTextField());

        panel.add(new JLabel("Father's Name"));
        panel.add(new JTextField());

        panel.add(new JLabel("Father's Mobile"));
        panel.add(new JTextField());

        panel.add(new JLabel("Course"));
        panel.add(new JTextField());

        panel.add(new JLabel("Category"));
        panel.add(new JTextField());

        panel.add(new JLabel("Family's Income"));
        panel.add(new JTextField());

        panel.add(new JLabel("State"));
        panel.add(new JTextField());

        panel.add(new JLabel("Percentage"));
        panel.add(new JTextField());

        panel.add(new JLabel("Refering Person"));
        panel.add(new JTextField());

        panel.add(new JLabel("Counsellor"));
        panel.add(new JTextField());

        panel.add(new JLabel("Remarks"));
        panel.add(new JTextField());

        panel.add(new JLabel("Calling Person"));
        panel.add(new JTextField());

        panel.add(new JLabel("Time Stamp"));
        panel.add(new JTextField());

        panel.add(new JLabel("University Interest"));
        panel.add(new JTextField());

        frame.add(panel, BorderLayout.WEST);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3,2));
        panel2.setPreferredSize(new Dimension(400,100));

        panel2.add(new JLabel("Date"));
        panel2.add(new JTextField());

        panel2.add(new JLabel("Counsellor"));
        panel2.add(new JTextField());

        panel2.add(new JLabel("Status"));
        panel2.add(new JTextField());

        frame.add(panel2, BorderLayout.EAST);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1,4));
        panel3.setPreferredSize(new Dimension(100,50));

        panel3.add(new JButton("Reject"));
        panel3.add(new JButton("Accept"));
        panel3.add(new JButton("Update"));
        panel3.add(new JButton("Refresh"));

        frame.add(panel3, BorderLayout.SOUTH);
        frame.setVisible(true);
        frame.pack();

    }
}