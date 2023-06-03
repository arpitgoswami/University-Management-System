import java.sql.*;

public class DBConnection {

    public static void create() {
/*
        // Database credentials
        String url = "jdbc:mysql://127.0.0.1:3306/amitdb1";
        String username = "root";
        String password = "Pokhrel@0309";

        // Database connection
        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database!");

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
            while (rs.next()) {
                int id = rs.getInt("EmpID");
                String name = rs.getString("EmpName");
                int age = rs.getInt("EmpAge");
                String dept = rs.getString("EmpDept");
                System.out.printf("\nid: %d, name: %s, age: %d, dept: %s", id, name, age, dept);
            }

            String sql = "INSERT INTO employee" + "(EmpID, EmpName, EmpAge, EmpDept)" + "Values('5', 'Chou', '45', 'Organisation')";
            stmt.execute(sql);
            System.out.printf("Insert Complete");

        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }*/
    }

}