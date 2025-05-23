import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException; 
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter; 

public class Deposit extends JFrame implements ActionListener {
    JTextField amountField; 
    JButton deposit, back;
    String pinnumber, cardnumber;

    Deposit(String pinnumber, String cardnumber) {
        this.pinnumber = pinnumber;
        this.cardnumber = cardnumber;

        // *Frame properties
        setSize(900, 900);
        setLocation(300, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); 

        // *Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // *Adding lables to the Frame */
        JLabel text = new JLabel("Enter the amount you want to Deposit");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170, 300, 400, 20);
        image.add(text); 

        amountField = new JTextField();
        amountField.setFont(new Font("Raleway", Font.BOLD, 22));
        amountField.setBounds(170, 350, 320, 25);
        image.add(amountField);

        deposit = new JButton("Deposit");
        deposit.setBounds(355, 485, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deposit) {
            String amountStr = amountField.getText();
            if (amountStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Conn conn = null; 
            try {
                Double amount = Double.parseDouble(amountStr);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Deposit amount must be positive.", "Input Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                conn = new Conn(); 
                String txnDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

               
                String query = "INSERT INTO bank (Pin,Date, Type, Amount, CardNumber) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement pst = conn.c.prepareStatement(query)) {
                    pst.setString(1, pinnumber);
                    pst.setString(2, txnDate);
                    pst.setString(3, "Deposit");
                    pst.setDouble(4, amount);
                    pst.setString(5, cardnumber);
                    pst.executeUpdate();
                }

                JOptionPane.showMessageDialog(null, "₹" + amount + " Deposited Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
                new Transaction(pinnumber, cardnumber).setVisible(true); 
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, "Database error occurred while processing deposit: " + se.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("SQLException: " + se.getMessage());
                se.printStackTrace();
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Invalid amount format. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("NumberFormatException: " + nfe.getMessage());
                nfe.printStackTrace();
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "An unexpected null value was encountered: " + npe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("NullPointerException: " + npe.getMessage());
                npe.printStackTrace();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("General Exception: " + e.getMessage());
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
        new Deposit("2004", "5040935987815612"); // Example usage
    }
}