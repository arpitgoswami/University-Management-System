import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SecondTab {

    public JLabel per10 = new JLabel("Empty");
    public JLabel per12 = new JLabel("Empty");
    public JLabel stream12 = new JLabel("Empty");
    public JLabel graduate = new JLabel("Empty");
    public JLabel gradper = new JLabel("Empty");
    public JLabel scholarship = new JLabel("Empty");
    public JLabel address = new JLabel("Empty");
    public JLabel distt = new JLabel("Empty");
    public JLabel panchayat = new JLabel("Empty");
    public JLabel school = new JLabel("Empty");
    public JLabel aadhar = new JLabel("Empty");
    public JLabel counsellor = new JLabel("Empty");
    public JLabel timestamp = new JLabel("Empty");
    public JLabel university = new JLabel("Empty");

    public int mid;
    public String[] coloumnNames = {
            "ID",
            "Name",
            "Mobile",
            "Father's Name",
            "Father's Mobile",
            "Course",
            "Category",
            "Family's Income",
            "State"
    };
    public DefaultTableModel model = new DefaultTableModel(coloumnNames, 0);

    public int searchId;

    public GridBagConstraints cons = new GridBagConstraints();

    public void cons_set(int x, int y) {
        cons.gridx = x;
        cons.gridy = y;
    }

    public void cons_weight(int x, int y) {
        cons.weightx = x;
        cons.weighty = y;
    }

    public void loadModel(Connection conn) throws SQLException {

        String sql = "SELECT * FROM callcenter WHERE id >= ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, searchId);

        ResultSet result = stmt.executeQuery();

        model.setRowCount(0);
        while (result.next()) {
            Object[] row = {
                    result.getInt("id"),
                    result.getString("name"),
                    result.getBigDecimal("mobile"),
                    result.getString("fatherName"),
                    result.getBigDecimal("fatherMobile"),
                    result.getString("course"),
                    result.getString("category"),
                    result.getString("familyIncome"),
                    result.getString("state")
            };
            model.addRow(row);
        }

    }

    public void loadDetail(Connection conn) throws SQLException {

        String sql = "SELECT * FROM details WHERE fid = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, mid);

        ResultSet result = stmt.executeQuery();
        if (result.next()) {
            // Retrieve the values from the result set
            String value2 = result.getString("10per");
            String value3 = result.getString("12per");
            String value4 = result.getString("stream");
            String value5 = result.getString("graduation");
            String value6 = result.getString("graduationper");
            String value7 = result.getString("scholarship");
            String value8 = result.getString("gender");
            String value9 = result.getString("address");
            String value10 = result.getString("distt");
            String value11 = result.getString("panchayat");
            String value12 = result.getString("school");
            String value13 = result.getString("adhar");
            String value14 = result.getString("counsellor");
            String value15 = result.getString("timestamp");
            String value16 = result.getString("hcat");


            // Set the values to the JLabels
            per10.setText(value2);
            per12.setText(value3);
            stream12.setText(value4);
            graduate.setText(value5);
            gradper.setText(value6);
            scholarship.setText(value7);
            address.setText(value8);
            distt.setText(value9);
            panchayat.setText(value10);
            school.setText(value11);
            aadhar.setText(value12);
            counsellor.setText(value13);
            timestamp.setText(value14);
            university.setText(value15);

        }
    }
    public void editFrame(Connection conn) throws SQLException {
        JPanel panel = new JPanel();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(800,400));
        frame.setTitle("Management System - Edit Details");

        panel.setLayout(new GridLayout(10, 2,2,2));

        String sql = "SELECT * FROM callcenter WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, mid);

        ResultSet result = stmt.executeQuery();
        result.next();

        // Retrieve the values from the result set
            String value0 = result.getString("id");
            String value1 = result.getString("name");
            String value2 = result.getString("mobile");
            String value3 = result.getString("fatherName");
            String value4 = result.getString("fatherMobile");
            String value5 = result.getString("Course");
            String value6 = result.getString("category");
            String value7 = result.getString("familyIncome");
            String value8 = result.getString("state");

            panel.add(new JLabel("ID Number"));
            JLabel id = new JLabel(value0);
            panel.add(id);

            panel.add(new JLabel("Name"));
            JTextField name = new JTextField(value1);
            panel.add(name);

            panel.add(new JLabel("Mobile Number"));
            JTextField mobile = new JTextField(value2);
            panel.add(mobile);

            panel.add(new JLabel("Father's Name"));
            JTextField fatherName = new JTextField(value3);
            panel.add(fatherName);

            panel.add(new JLabel("Father's Mobile"));
            JTextField fatherMobile = new JTextField(value4);
            panel.add(fatherMobile);

            panel.add(new JLabel("Course Interested"));
            JTextField course = new JTextField(value5);
            panel.add(course);

            panel.add(new JLabel("Category"));
            JTextField category = new JTextField(value6);
            panel.add(category);

            panel.add(new JLabel("Family's Income"));
            JTextField familyIncome = new JTextField(value7);
            panel.add(familyIncome);

            panel.add(new JLabel("State"));
            JTextField state = new JTextField(value8);
            panel.add(state);

        JButton submit = new JButton("Save Details");

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "UPDATE callcenter SET name = ?, mobile = ?, fatherName = ?, fatherMobile = ?, course = ?, category = ?, familyIncome = ?, state = ? WHERE id = ?";
                try {
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, String.valueOf(name.getText()));
                    stmt.setLong(2, Long.parseLong(String.valueOf(mobile.getText())));
                    stmt.setString(3, String.valueOf(fatherName.getText()));
                    stmt.setLong(4, Long.parseLong(String.valueOf(fatherMobile.getText())));
                    stmt.setString(5, String.valueOf(course.getText()));
                    stmt.setString(6, String.valueOf(category.getText()));
                    stmt.setString(7, String.valueOf(familyIncome.getText()));
                    stmt.setString(8, String.valueOf(state.getText()));
                    stmt.setInt(9, Integer.parseInt(String.valueOf(id.getText())));

                    int rowsUpdated = stmt.executeUpdate();

                    if (rowsUpdated == 0){
                        System.out.println("No rows were updated");
                    } else {
                        System.out.println(rowsUpdated + "row(s) updated successfully");
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        panel.add(submit);

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
        frame.pack();
    }

    public void create(JPanel secondTab) {

        cons.gridy = 0;
        cons.anchor = GridBagConstraints.NORTHWEST;
        cons.insets = new Insets(5, 6, 5, 5);

        cons_set(0, 1);
        secondTab.add(new JLabel("Search ID"), cons);

        cons_set(1, 1);
        cons_weight(1, 0);
        cons.fill = GridBagConstraints.BOTH;
        JTextField search = new JTextField();
        secondTab.add(search, cons);
        cons_weight(0, 0);
        cons.fill = GridBagConstraints.NONE;

        // Database Log
        String url = "jdbc:mysql://127.0.0.1:3306/amitdb1";
        String username = "root";
        String password = "Ar@172530";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Successful!");

            loadModel(conn);

            JButton searchButton = new JButton("Search");
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchId = Integer.parseInt(search.getText());
                    try {
                        loadModel(conn);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            cons_set(2, 1);
            secondTab.add(searchButton, cons);

            JButton searchallButton = new JButton("Search All");
            searchallButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    searchId = 0;
                    try {
                        loadModel(conn);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });

            cons_set(3, 1);
            secondTab.add(searchallButton, cons);

            // Create a JTable with the table model
            JTable table = new JTable(model) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            cons_set(0, 2);
            cons_weight(1, 1);
            cons.gridwidth = 10;
            cons.fill = GridBagConstraints.BOTH;

            JScrollPane sp = new JScrollPane(table);
            secondTab.add(sp, cons);

            cons_weight(0, 0);
            cons.gridwidth = 1;
            cons.fill = GridBagConstraints.NONE;

            JButton refreshButton = new JButton("Refresh");
            refreshButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        loadModel(conn);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            cons_set(0, 3);
            secondTab.add(refreshButton, cons);


            JButton showButton = new JButton("Show Details");
            showButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = table.getSelectedRow();
                    if (row >= 0) {
                        mid = Integer.parseInt(table.getValueAt(row, 0).toString());

                        try {
                            loadDetail(conn);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        /*
                        String query = "UPDATE callcenter SET contact = ? WHERE id = ?";
                        try {
                            PreparedStatement stmt = conn.prepareStatement(query);
                            stmt.setInt(1, Integer.valueOf(a));
                            stmt.setInt(2, Integer.parseInt(name));
                            int rowsUpdated = stmt.executeUpdate();

                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                       */
                    }
                }
            });
            cons_set(1, 3);
            secondTab.add(showButton, cons);

            JButton editButton = new JButton("Edit Details");
            editButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int row = table.getSelectedRow();
                    if (row >= 0) {
                        mid = Integer.parseInt(table.getValueAt(row, 0).toString());
                        try {
                            editFrame(conn);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });
            cons_set(2,3 );
            secondTab.add(editButton, cons);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        cons_set(0, 1);
        panel.add(new JLabel("10th Percentage :"), cons);
        cons_set(1, 1);
        panel.add(per10, cons);

        cons_set(0, 2);
        panel.add(new JLabel("12th Percentage :"), cons);
        cons_set(1, 2);
        panel.add(per12, cons);

        cons_set(0, 3);
        panel.add(new JLabel("12th Stream :"), cons);
        cons_set(1, 3);
        panel.add(stream12, cons);

        cons_set(0, 4);
        panel.add(new JLabel("Graduation :"), cons);
        cons_set(1, 4);
        panel.add(graduate, cons);

        cons_set(0, 5);
        panel.add(new JLabel("Graduation Percentage :"), cons);
        cons_set(1, 5);
        panel.add(gradper, cons);

        cons_set(0, 6);
        panel.add(new JLabel("Scholarship :"), cons);
        cons_set(1, 6);
        panel.add(scholarship, cons);

        cons_set(2, 0);
        panel.add(new JLabel("  "), cons);

        cons_set(3, 1);
        panel.add(new JLabel("Address :"), cons);
        cons_set(4, 1);
        panel.add(address, cons);

        cons_set(3, 2);
        panel.add(new JLabel("District :"), cons);
        cons_set(4, 2);
        panel.add(distt, cons);

        cons_set(3, 3);
        panel.add(new JLabel("Panchayat :"), cons);
        cons_set(4, 3);
        panel.add(panchayat, cons);

        cons_set(3, 4);
        panel.add(new JLabel("School :"), cons);
        cons_set(4, 4);
        panel.add(school, cons);

        cons_set(3, 5);
        panel.add(new JLabel("Aadhar Card :"), cons);
        cons_set(4, 5);
        panel.add(aadhar, cons);

        cons_set(5, 0);
        panel.add(new JLabel("  "), cons);

        cons_set(6, 1);
        panel.add(new JLabel("Counsellor :"), cons);
        cons_set(7, 1);
        panel.add(counsellor, cons);

        cons_set(6, 2);
        panel.add(new JLabel("Timestamp :"), cons);
        cons_set(7, 2);
        panel.add(timestamp, cons);

        cons_set(6, 3);
        panel.add(new JLabel("University Interest :"), cons);
        cons_set(7, 3);
        panel.add(university, cons);

        cons_weight(1, 0);
        cons.gridwidth = 2;
        cons_set(0, 4);
        secondTab.add(panel, cons);

    }
}