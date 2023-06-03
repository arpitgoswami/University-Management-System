import javax.swing.*;
import java.awt.*;

public class Main {

    public static JPanel panel = new JPanel();

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800,400));

        panel.setLayout(new GridLayout(9, 4));

        panel.add(new JLabel("ID Number"));
        JTextField fid = new JTextField();
        panel.add(fid);

        panel.add(new JLabel("10 Percentage"));
        JTextField per10 = new JTextField();
        panel.add(per10);

        panel.add(new JLabel("12 Percentage"));
        JTextField per12 = new JTextField();
        panel.add(per12);

        panel.add(new JLabel("Stream"));
        JTextField stream = new JTextField();
        panel.add(stream);

        panel.add(new JLabel("Graduation"));
        JTextField graduation = new JTextField();
        panel.add(graduation);

        panel.add(new JLabel("Graduation Percentage"));
        JTextField gradper = new JTextField();
        panel.add(gradper);

        panel.add(new JLabel("Scholarship"));
        JTextField sholarship = new JTextField();
        panel.add(sholarship);

        panel.add(new JLabel("Gender"));
        JTextField gender = new JTextField();
        panel.add(gender);

        panel.add(new JLabel("Address"));
        JTextField address = new JTextField();
        panel.add(address);

        panel.add(new JLabel("District"));
        JTextField distt = new JTextField();
        panel.add(distt);

        panel.add(new JLabel("Panchayat"));
        JTextField panchayat = new JTextField();
        panel.add(panchayat);

        panel.add(new JLabel("School"));
        JTextField school = new JTextField();
        panel.add(school);

        panel.add(new JLabel("Aadhar Number"));
        JTextField adhar = new JTextField();
        panel.add(adhar);

        panel.add(new JLabel("Counsellor"));
        JTextField counsellor = new JTextField();
        panel.add(counsellor);

        panel.add(new JLabel("Time Stamp"));
        JTextField timestamp = new JTextField();
        panel.add(timestamp);

        panel.add(new JLabel("University Interest"));
        JTextField hcat = new JTextField();
        panel.add(hcat);

        JButton submit = new JButton("Save Details");
        panel.add(submit);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.pack();

    }
}