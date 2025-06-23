import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.*;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField pinField, repinField; 
    JButton change, back;
    String pinnumber, cardnumber;

    PinChange(String pinnumber, String cardnumber) {
        this.pinnumber = pinnumber;
        this.cardnumber = cardnumber;

        setSize(900, 900);
        setLocation(300, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(250, 300, 500, 35);
        image.add(text);

        JLabel new_pin = new JLabel("New PIN:");
        new_pin.setForeground(Color.white);
        new_pin.setFont(new Font("System", Font.BOLD, 16));
        new_pin.setBounds(160, 360, 150, 30);
        image.add(new_pin);

        pinField = new JPasswordField();
        pinField.setFont(new Font("Raleway", Font.BOLD, 25));
        pinField.setBounds(330, 360, 180, 25);
        image.add(pinField);

        JLabel re_enter_pin = new JLabel("Re-Enter New PIN:");
        re_enter_pin.setForeground(Color.white);
        re_enter_pin.setFont(new Font("System", Font.BOLD, 16));
        re_enter_pin.setBounds(160, 400, 200, 30);
        image.add(re_enter_pin);

        repinField = new JPasswordField();
        repinField.setFont(new Font("Raleway", Font.BOLD, 25));
        repinField.setBounds(330, 400, 180, 25);
        image.add(repinField);

        change = new JButton("CHANGE");
        change.setBounds(355, 485, 150, 30);
        change.addActionListener(this);
        image.add(change);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {
            String npin = new String(pinField.getPassword());
            String rpin = new String(repinField.getPassword());

            if (npin.isEmpty() || rpin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both new PIN and re-enter new PIN.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!npin.equals(rpin)) {
                JOptionPane.showMessageDialog(null, "Entered PIN does not match.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (npin.length() != 4 || !npin.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "PIN must be a 4-digit number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Conn conn = null; 
            try {
                conn = new Conn(); 

                //*Update 'bank' table
                String query1 = "UPDATE bank SET Pin = ? WHERE CardNumber = ?";
                try (PreparedStatement ps1 = conn.c.prepareStatement(query1)) {
                    ps1.setString(1, npin);
                    ps1.setString(2, cardnumber);
                    ps1.executeUpdate();
                }

                //*Update 'login' table
                String query2 = "UPDATE login SET pin_number = ? WHERE card_number = ?";
                try (PreparedStatement ps2 = conn.c.prepareStatement(query2)) {
                    ps2.setString(1, npin);
                    ps2.setString(2, cardnumber);
                    ps2.executeUpdate();
                }

                //*Update 'signupthree' table
                String query3 = "UPDATE signupthree SET pin_number = ? WHERE card_number = ?";
                try (PreparedStatement ps3 = conn.c.prepareStatement(query3)) {
                    ps3.setString(1, npin);
                    ps3.setString(2, cardnumber);
                    ps3.executeUpdate();
                }

                JOptionPane.showMessageDialog(null, "PIN Changed Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                new Transaction(npin, cardnumber).setVisible(true); 
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, "Database error: " + se.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("SQLException in PinChange: " + se.getMessage());
                se.printStackTrace();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("General Exception in PinChange: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.close(); 
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transaction(pinnumber, cardnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new PinChange("", ""); 
    }
}