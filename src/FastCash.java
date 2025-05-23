import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter; 

public class FastCash extends JFrame implements ActionListener {

    JButton ammount_hundred, ammount_fivehundred, ammount_onethousand, ammount_twothousand, ammount_fivethousand,
            ammount_tenthousand, back;
    String pinNumber, cardnumber;

    FastCash(String pinNumber, String cardnumber) {
        this.pinNumber = pinNumber;
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

        // *Heading
        JLabel text = new JLabel("Please select your Withdrawal amount");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(210, 300, 700, 35);
        image.add(text);

        //*Buttons for different amounts
        ammount_hundred = new JButton("Rs 100");
        ammount_hundred.setBounds(170, 420, 150, 30);
        ammount_hundred.addActionListener(this);
        image.add(ammount_hundred);

        ammount_fivehundred = new JButton("Rs 500");
        ammount_fivehundred.setBounds(355, 420, 150, 30);
        ammount_fivehundred.addActionListener(this);
        image.add(ammount_fivehundred);

        ammount_onethousand = new JButton("Rs 1000");
        ammount_onethousand.setBounds(170, 455, 150, 30);
        ammount_onethousand.addActionListener(this);
        image.add(ammount_onethousand);

        ammount_twothousand = new JButton("Rs 2000");
        ammount_twothousand.setBounds(355, 455, 150, 30);
        ammount_twothousand.addActionListener(this);
        image.add(ammount_twothousand);

        ammount_fivethousand = new JButton("Rs 5000");
        ammount_fivethousand.setBounds(170, 490, 150, 30);
        ammount_fivethousand.addActionListener(this);
        image.add(ammount_fivethousand);

        ammount_tenthousand = new JButton("Rs 10000");
        ammount_tenthousand.setBounds(355, 490, 150, 30);
        ammount_tenthousand.addActionListener(this);
        image.add(ammount_tenthousand);

        back = new JButton("Back");
        back.setBounds(355, 525, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String amountText = ((JButton) ae.getSource()).getText().replace("Rs ", "");
        int amount = Integer.parseInt(amountText);

        if (ae.getSource() == back) {
            setVisible(false);
            new Transaction(pinNumber, cardnumber).setVisible(true);
            return;
        }

        Conn conn = null; 
        try {
            conn = new Conn(); 
            String balanceQuery = "SELECT * FROM bank WHERE Pin = ?";
            int balance = 0;
            try (PreparedStatement psBalance = conn.c.prepareStatement(balanceQuery)) {
                psBalance.setString(1, pinNumber);
                try (ResultSet rs = psBalance.executeQuery()) {
                    while (rs.next()) {
                        String type = rs.getString("Type");
                        double transactionAmount = rs.getDouble("Amount"); 

                        if ("Deposit".equalsIgnoreCase(type)) {
                            balance += transactionAmount;
                        } else if ("Withdrawal".equalsIgnoreCase(type)) {
                            balance -= transactionAmount;
                        }
                    }
                }
            }

            if (balance < amount) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            
            String txnDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String withdrawalQuery = "INSERT INTO bank (Pin, TxnDate, Type, Amount, CardNumber) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement psWithdrawal = conn.c.prepareStatement(withdrawalQuery)) {
                psWithdrawal.setString(1, pinNumber);
                psWithdrawal.setString(2, txnDate);
                psWithdrawal.setString(3, "Withdrawal");
                psWithdrawal.setDouble(4, amount);
                psWithdrawal.setString(5, cardnumber); 
                psWithdrawal.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "₹" + amount + " Debited Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
            new Transaction(pinNumber, cardnumber).setVisible(true);

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Database error occurred while processing withdrawal: " + se.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("SQLException: " + se.getMessage());
            se.printStackTrace();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Invalid amount format detected.", "Input Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("NumberFormatException: " + nfe.getMessage());
            nfe.printStackTrace();
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(null, "Unexpected null value encountered: " + npe.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("NullPointerException: " + npe.getMessage());
            npe.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("General Exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close(); // Close the connection
            }
        }
    }

    public static void main(String[] args) {
        new FastCash("7099", ""); // Example usage
    }
}