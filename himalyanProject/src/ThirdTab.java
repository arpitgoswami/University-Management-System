import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ThirdTab {

    // Sample Variables ----
    public static Connection conn;
    public static int historyId;
    public static int confirmation;

    // Calling Table ----
    public static JTextField name = new JTextField();
    public static JTextField mobile = new JTextField();
    public static JTextField fatherName = new JTextField();
    public static JTextField fatherMobile = new JTextField();
    public static JTextField course = new JTextField();
    public static JTextField category = new JTextField();
    public static JTextField familyIncome = new JTextField();
    public static JTextField state = new JTextField();
    public static JTextField percentage = new JTextField();
    public static JTextField referredBy = new JTextField();
    public static JTextField counselor = new JTextField();
    public static JTextField remark = new JTextField();
    public static JTextField calledBy = new JTextField();
    public static JTextField timestamp = new JTextField();
    public static JTextField universityInterest = new JTextField();

    // callStatus table ---
    public static JTextField status = new JTextField();
    public static JTextField attempt1 = new JTextField();
    public static JTextField attempt2 = new JTextField();
    public static JTextField attempt3 = new JTextField();
    public static JTextField attempt4 = new JTextField();
    public static JTextField attempt5 = new JTextField();

    public static JTextField remark1 = new JTextField();
    public static JTextField remark2 = new JTextField();
    public static JTextField remark3 = new JTextField();
    public static JTextField remark4 = new JTextField();
    public static JTextField remark5 = new JTextField();


    public static void dbconnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/amitdb1";
        String username = "root";
        String password = "Ar@172530";

        conn = DriverManager.getConnection(url, username, password);

    }

    public static void loadmodel(int id) throws SQLException {

        String sql = "SELECT * FROM callingstatus WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        ResultSet result = stmt.executeQuery();
        result.next();

        String value1 = result.getString("name");
        String value2 = result.getString("mobile");
        String value3 = result.getString("fatherName");
        String value4 = result.getString("fatherMobile");
        String value5 = result.getString("course");
        String value6 = result.getString("category");
        String value7 = result.getString("familyIncome");
        String value8 = result.getString("state");
        String value9 = result.getString("percentage");
        String value10 = result.getString("referedBy");
        String value11 = result.getString("councellor");
        String value12 = result.getString("remark");
        String value13 = result.getString("calledBy");
        String value14 = result.getString("timeStamp");
        String value15 = result.getString("universityInterest");

        name.setText(value1);
        mobile.setText(value2);
        fatherName.setText(value3);
        fatherMobile.setText(value4);
        course.setText(value5);
        category.setText(value6);
        familyIncome.setText(value7);
        state.setText(value8);
        percentage.setText(value9);
        referredBy.setText(value10);
        counselor.setText(value11);
        remark.setText(value12);
        calledBy.setText(value13);
        timestamp.setText(value14);
        universityInterest.setText(value15);

        sql = "SELECT * FROM callstatus WHERE id = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);

        result = stmt.executeQuery();
        result.next();

        String value16 = result.getString("status");
        String value17 = result.getString("attempt1");
        String value18 = result.getString("attempt2");
        String value19 = result.getString("attempt3");
        String value20 = result.getString("attempt4");
        String value21 = result.getString("attempt5");

        String value22 = result.getString("remark1");
        String value23 = result.getString("remark2");
        String value24 = result.getString("remark3");
        String value25 = result.getString("remark4");
        String value26 = result.getString("remark5");

        status.setText(value16);
        attempt1.setText(value17);
        attempt2.setText(value18);
        attempt3.setText(value19);
        attempt4.setText(value20);
        attempt5.setText(value21);

        remark1.setText(value22);
        remark2.setText(value23);
        remark3.setText(value24);
        remark4.setText(value25);
        remark5.setText(value26);

    }

    public static void updatetable() throws SQLException {

        String sql = "UPDATE callstatus SET attempt1 = ?, attempt2 = ?, attempt3 = ?, attempt4 = ?, attempt5 = ?, remark1 = ?, remark2 = ?, remark3 = ?, remark4 = ?, remark5 = ? WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setString(1, String.valueOf(attempt1.getText()));
        stmt.setString(2, String.valueOf(attempt2.getText()));
        stmt.setString(3, String.valueOf(attempt3.getText()));
        stmt.setString(4, String.valueOf(attempt4.getText()));
        stmt.setString(5, String.valueOf(attempt5.getText()));

        stmt.setString(6, String.valueOf(remark1.getText()));
        stmt.setString(7, String.valueOf(remark2.getText()));
        stmt.setString(8, String.valueOf(remark3.getText()));
        stmt.setString(9, String.valueOf(remark4.getText()));
        stmt.setString(10, String.valueOf(remark5.getText()));

        stmt.setInt(11, historyId);

        int rowsUpdate = stmt.executeUpdate();

        sql = "UPDATE callingStatus SET timeStamp = ? WHERE id = ?";
        stmt = conn.prepareStatement(sql);

        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        stmt.setString(1, formattedDateTime);
        stmt.setInt(2, historyId);

        rowsUpdate = stmt.executeUpdate();

        if (rowsUpdate > 0) {
            JOptionPane.showMessageDialog(null, "Database has been updated successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update the data.");
        }

    }

    public static void loadstatus(String message, String smallMessage) throws SQLException {

        confirmation = JOptionPane.showConfirmDialog(null, "Do you want to " + smallMessage + " the student.", "User Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            String sql = "UPDATE callstatus SET status = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, message);
            stmt.setInt(2, historyId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Student has been successfully " + message.toLowerCase() + ".");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to perform this operation.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Operation has been cancelled successfully.");
        }
    }

    public static void create(JPanel thirdTab) throws SQLException {

        dbconnection();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(16, 2, 4, 4));
        panel.setPreferredSize(new Dimension(500, 400));

        panel.add(new JLabel("Student ID"));
        JTextField id = new JTextField();
        panel.add(id);

        panel.add(new JLabel("Name"));
        panel.add(name);

        panel.add(new JLabel("Mobile Number"));
        panel.add(mobile);

        panel.add(new JLabel("Father's Name"));
        panel.add(fatherName);

        panel.add(new JLabel("Father's Mobile"));
        panel.add(fatherMobile);

        panel.add(new JLabel("Course"));
        panel.add(course);

        panel.add(new JLabel("Category"));
        panel.add(category);

        panel.add(new JLabel("Family's Income"));
        panel.add(familyIncome);

        panel.add(new JLabel("State"));
        panel.add(state);

        panel.add(new JLabel("Percentage"));
        panel.add(percentage);

        panel.add(new JLabel("Refering Person"));
        panel.add(referredBy);

        panel.add(new JLabel("Counsellor"));
        panel.add(counselor);

        panel.add(new JLabel("Remarks"));
        panel.add(remark);

        panel.add(new JLabel("Calling Person"));
        panel.add(calledBy);

        panel.add(new JLabel("Time Stamp"));
        panel.add(timestamp);

        panel.add(new JLabel("University Interest"));
        panel.add(universityInterest);

        thirdTab.add(panel, BorderLayout.WEST);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(6, 4));
        panel2.setPreferredSize(new Dimension(400,200));
        panel2.setBackground(Color.lightGray);

        panel2.add(new JLabel("Attempt1"));
        panel2.add(attempt1);

        panel2.add(new JLabel("Remark1"));
        panel2.add(remark1);

        panel2.add(new JLabel("Attempt2"));
        panel2.add(attempt2);

        panel2.add(new JLabel("Remark2"));
        panel2.add(remark2);

        panel2.add(new JLabel("Attempt3"));
        panel2.add(attempt3);

        panel2.add(new JLabel("Remark3"));
        panel2.add(remark3);

        panel2.add(new JLabel("Attempt4"));
        panel2.add(attempt4);

        panel2.add(new JLabel("Remark4"));
        panel2.add(remark4);

        panel2.add(new JLabel("Attempt5"));
        panel2.add(attempt5);

        panel2.add(new JLabel("Remark5"));
        panel2.add(remark5);

        panel2.add(new JLabel("Status"));
        panel2.add(status);

        thirdTab.add(panel2, BorderLayout.EAST);

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1, 6));
        panel3.setPreferredSize(new Dimension(100, 50));

        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int searchId = Integer.parseInt(id.getText());
                historyId = searchId;
                try {
                    loadmodel(searchId);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel3.add(search);

        JButton refresh = new JButton("Refresh");
        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadmodel(historyId);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel3.add(refresh);

        JButton update = new JButton("Update");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updatetable();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel3.add(update);

        JButton accept = new JButton("Accept");
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadstatus("Accepted for Admission", "accept");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel3.add(accept);

        JButton reject = new JButton("Reject");
        reject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadstatus("Rejected for Admission", "reject");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel3.add(reject);

        JButton print = new JButton("Print");
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel3.add(print);

        thirdTab.add(panel3, BorderLayout.SOUTH);
        thirdTab.add(new JLabel(""));

    }
}