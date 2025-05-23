import java.sql.*;
import javax.swing.JOptionPane;

public class Conn {
    Connection c;
    Statement s;

    public Conn() {
        try {
            
            c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root", "srajan2004");
            s = c.createStatement();
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("SQL Exception during connection: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred during connection: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("General Exception during connection: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void close() {
        try {
            if (s != null) s.close();
            if (c != null) c.close();
        } catch (SQLException e) {
            System.err.println("Error closing database resources: " + e.getMessage());
            e.printStackTrace();
        }
    }
}