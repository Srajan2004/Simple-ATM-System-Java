import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {
    String pinnumber,cardnumber;
    JButton back;
    Conn conn;

    BalanceEnquiry(String pinnumber,String cardnumber) {
        this.pinnumber = pinnumber;
        this.cardnumber = cardnumber;

        // Frame Settings
        setSize(900, 900);
        setLocationRelativeTo(null); // Center the frame
        setLayout(null);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon originalIcon = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon backgroundImage = new ImageIcon(scaledImage);
        JLabel image = new JLabel(backgroundImage);
        image.setBounds(0, 0, 900, 900);
        add(image);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        try {
            conn = new Conn();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database Connection Failed: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        int balance = calculateBalance(pinnumber);
        JLabel text = new JLabel("Your Current Account Balance is RS " + balance);
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170, 400, 400, 30);
        image.add(text);

        setVisible(true);
    }

    private int calculateBalance(String pin) {
        int balance = 0;

        String query = "SELECT * FROM bank WHERE Pin = ?";
        try (PreparedStatement ps = conn.c.prepareStatement(query)) {
            ps.setString(1, pin);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String type = rs.getString("Type");
                Double amount = Double.parseDouble(rs.getString("Amount"));

                if ("Deposit".equalsIgnoreCase(type)) {
                    balance += amount;
                } else if ("Withdrawal".equalsIgnoreCase(type)) {
                    balance -= amount;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount format in database.");
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unexpected Error: " + e.getMessage());
            e.printStackTrace();
        }

        return balance;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Transaction(pinnumber,cardnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new BalanceEnquiry("2004","");
    }
}
