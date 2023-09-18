import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
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
    public static int leftPadding = 20;
    public static int topPadding = 20;
    public static int paddingIncrement = 10;
    public static String[] coloumnNames = {"Student ID","Name", "Mobile Number", "Course", "Counsellor", "Status"};
    public static String[] coloumnNames2 = {"Attempt","Date", "Caller", "Remarks"};
    public static DefaultTableModel model = new DefaultTableModel(coloumnNames, 0);
    public static DefaultTableModel model2 = new DefaultTableModel(coloumnNames2, 0);
    public static int tempId = 10;
    public static int detailId=0;
    public static int width = 800;
    public static JTable sideTable;
    public static JTable table;
    public static int rowCount;

    // Calling Table ----
    public static JTextField name = new JTextField();
    public static JTextField studentId = new JTextField();
    public static JTextField mobile = new JTextField();
    public static JTextField address = new JTextField();
    public static JTextField qualification = new JTextField();
    public static JTextField course = new JTextField();
    public static JTextField counsellor = new JTextField();
    public static JTextField currenStatus = new JTextField();

    // callStatus table ---
    public static JTextField status = new JTextField();
    public static JTextField remark = new JTextField();


    public static void dbconnection() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/amitdb1";
        String username = "root";
        String password = "Ar@172530";

        conn = DriverManager.getConnection(url, username, password);

    };
    
    public static void search() {

        JFrame searchFrame = new JFrame();
        searchFrame.setTitle("Message");

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(3, 2,10,10));
        searchPanel.setBounds(leftPadding, topPadding, 2000, 200);

        searchPanel.add(new JLabel("Search By"));

        String[] elements = {"studentId"};
        JComboBox<String> searchby = new JComboBox<>(elements);
        searchPanel.add(searchby);

        searchPanel.add(new JLabel("Attribute"));
        JTextField attribute = new JTextField();
        searchPanel.add(attribute);

        JButton search = new JButton("Search");
        searchPanel.add(search);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String coloumn = (String) searchby.getSelectedItem();
                    String coloumn2 = attribute.getText();
                    search(coloumn, coloumn2);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                searchFrame.dispose();
            }
        });

        searchFrame.add(new JLabel(""));
        searchFrame.add(searchPanel);
        searchFrame.setVisible(true);
        searchFrame.pack();

    }

    public static void search(String c1, String c2) throws SQLException {

        System.out.println(c1 + c2);

        String sql = "SELECT * FROM contacts WHERE studentId >= ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        //stmt.setString(1, c1);
        stmt.setInt(1, Integer.parseInt(c2));

        ResultSet result = stmt.executeQuery();
        model.setRowCount(0);

        while (result.next()) {
            Object[] row = {
                    result.getString("studentId"),
                    result.getString("name"),
                    result.getString("mobile"),
                    result.getString("courseInterested"),
                    result.getString("counsellor"),
                    result.getString("status"),
            };

            model.addRow(row);
         }
    }

    public static void loadstatus(String message, String smallMessage, int statusId) throws SQLException {

        confirmation = JOptionPane.showConfirmDialog(null, "Do you want to " + smallMessage + " the student.", "User Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            String sql = "UPDATE contacts SET status = ? WHERE studentId = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, message);
            stmt.setInt(2, statusId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Student has been successfully " + message.toLowerCase() + ".");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to perform this operation.");
            }
        }
    }

    public static void loadModel() throws SQLException {
        String sql = "SELECT * FROM contacts";
        PreparedStatement stmt = conn.prepareStatement(sql);

        ResultSet result = stmt.executeQuery();
        model.setRowCount(0);

        while (result.next()) {
            Object[] row = {
                    result.getString("studentId"),
                    result.getString("name"),
                    result.getString("mobile"),
                    result.getString("courseInterested"),
                    result.getString("counsellor"),
                    result.getString("status"),
            };

            model.addRow(row);
        }
    }

    public static void loadDetail(int searchId) throws SQLException {

        String sql = "SELECT * FROM contacts WHERE studentId = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, searchId);

        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            // Retrieve the values from the result set
            String value0 = result.getString("studentId");
            String value1 = result.getString("name");
            String value2 = result.getString("mobile");
            String value3 = result.getString("address");
            String value4 = result.getString("qualification");
            String value5 = result.getString("courseInterested");
            String value6 = result.getString("counsellor");
            String value7 = result.getString("status");

            // Set the values to the JLabels
            studentId.setText(value0);
            name.setText(value1);
            mobile.setText(value2);
            address.setText(value3);
            qualification.setText(value4);
            course.setText(value5);
            counsellor.setText(value6);
            currenStatus.setText(value7);
        }

        sql = "SELECT * FROM attempt WHERE studentId = ?";
        stmt = conn.prepareStatement(sql);
        stmt.setInt(1, detailId);

        result = stmt.executeQuery();

        model2.setRowCount(0);

        while (result.next()) {
            Object[] row = {
                    result.getString("attempt"),
                    result.getString("date"),
                    result.getString("callby"),
                    result.getString("remarks"),
            };
            model2.addRow(row);
        }
        rowCount = sideTable.getRowCount();
        System.out.println(rowCount);
    }

    public static void updateremark() throws SQLException {

        if (rowCount >= 5) {
            JOptionPane.showMessageDialog(null, "Maximum amount of attempts reached.");
            return;
        } else {

            rowCount += 1;
            if (remark.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Remark field is empty.");
            } else {

                String sql = "INSERT INTO attempt (attempt, studentId, date, callby, remarks) VALUES (?,?,?,?,?)";
                PreparedStatement stmt = conn.prepareStatement(sql);

                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDateTime = currentDateTime.format(formatter);

                stmt.setInt(1, rowCount);
                stmt.setInt(2, Integer.parseInt(String.valueOf(detailId)));
                stmt.setString(3, formattedDateTime);
                stmt.setString(4, String.valueOf(remark.getText()));
                stmt.setString(5, String.valueOf(remark.getText()));

                int rowsUpdate = stmt.executeUpdate();

                if (rowsUpdate > 0) {
                    JOptionPane.showMessageDialog(null, "Database has been updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update the data.");
                }
            }
        }
    }

    public static void create(JPanel thirdTab) throws SQLException {

        dbconnection();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 4));
        panel.setBounds(leftPadding, topPadding ,width,160);
        topPadding += 160 + paddingIncrement;

        panel.add(new JLabel("Name"));
        panel.add(name);

        panel.add(new JLabel("Student ID"));
        panel.add(studentId);

        panel.add(new JLabel("Mobile Number"));
        panel.add(mobile);

        panel.add(new JLabel("Address"));
        panel.add(address);

        panel.add(new JLabel("Qualification"));
        panel.add(qualification);

        panel.add(new JLabel("Course Interested"));
        panel.add(course);

        panel.add(new JLabel("Counsellor"));
        panel.add(counsellor);

        panel.add(new JLabel("Current Status"));
        panel.add(currenStatus);

        thirdTab.add(panel);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(1, 1));
        panel2.setBounds(leftPadding, topPadding, width,400);

        loadModel();

        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };;
        JScrollPane scrollPane = new JScrollPane(table);

        panel2.add(scrollPane);
        thirdTab.add(panel2);

        topPadding += 400 + paddingIncrement;

        JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayout(1, 6, 10,0));
        panel3.setBounds(leftPadding, topPadding, width, 30);

        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });
        panel3.add(search);

        JButton showDetails = new JButton("Show Details");
        showDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    detailId = Integer.parseInt(table.getValueAt(row, 0).toString());
                }

                try {
                    loadDetail(detailId);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel3.add(showDetails);

        JButton update = new JButton("Update");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updateremark();
                    loadDetail(detailId);
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
                    int row = table.getSelectedRow();
                    int statusId = 0;
                    if (row >= 0) {
                        statusId = Integer.parseInt(table.getValueAt(row, 0).toString());
                    }
                    loadstatus("Admission Accepted", "accept", statusId);
                    loadModel();
                    loadDetail(statusId);
                    detailId = statusId;
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
                    int row = table.getSelectedRow();
                    int statusId = 0;
                    if (row >= 0) {
                        statusId = Integer.parseInt(table.getValueAt(row, 0).toString());
                    }
                    loadstatus("Admission Rejected", "reject", statusId);
                    loadModel();
                    loadDetail(detailId);
                    detailId = statusId;
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

        thirdTab.add(panel3);

        leftPadding += width + leftPadding;
        topPadding = 20;
        width = 500;

        JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayout(1, 6));
        panel4.setBounds(leftPadding, topPadding, width,160);

        sideTable = new JTable(model2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane sideScrollPane = new JScrollPane(sideTable);

        panel4.add(sideScrollPane);
        thirdTab.add(panel4);

        topPadding += 160 + paddingIncrement;

        JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayout(2, 1));
        panel5.setBounds(leftPadding, topPadding,500,160);
        panel5.setBackground(Color.lightGray);

        panel5.add(new JLabel("Remark"));
        panel5.add(remark);

        thirdTab.add(panel5);
        thirdTab.add(new JLabel(""));

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                Object colorValue = table.getValueAt(row, 5);

                if (colorValue.equals("Admission Accepted")) {
                    component.setBackground(Color.orange);
                } else {
                    component.setBackground(Color.green);
                }
                return component;
            }
        });

    }
}