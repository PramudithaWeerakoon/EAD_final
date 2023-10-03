package bakery;

import java.awt.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectToDatabase {
    public ConnectToDatabase() {
    }

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the driver class
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bakery1", "root", "");
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog((Component) null, "MySQL JDBC Driver not found: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog((Component) null, "Connection cannot be established: " + e.getMessage());
            return null;
        }
    }
}
