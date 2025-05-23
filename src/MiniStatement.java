import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException; 

public class MiniStatement extends JFrame {

    String pinnumber, cardnumber;

    MiniStatement(String pinnumber, String cardnumber) {
        this.pinnumber = pinnumber;
        this.cardnumber = cardnumber;

        // Frame Settings
        setTitle("Mini Statement");
        setSize(400, 600);
        setLocation(20, 20);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel bank_name = new JLabel("State Bank Of India");
        bank_name.setBounds(100, 20, 200, 20);
        bank_name.setFont(new Font("Raleway", Font.BOLD, 16));
        add(bank_name);

        JLabel cardLabel = new JLabel(); 
        cardLabel.setBounds(20, 60, 350, 20);
        add(cardLabel);

        
        JTextArea miniStatementArea = new JTextArea(); 
        miniStatementArea.setEditable(false);
        miniStatementArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(miniStatementArea);
        scrollPane.setBounds(20, 100, 350, 400);
        add(scrollPane);

        
        JButton back = new JButton("Exit"); 
        back.setBounds(150, 520, 100, 25);
        add(back);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login().setVisible(true);
              
            }
        });

        Conn conn = null; 
        try {
            conn = new Conn(); // Initialize connection

            
            String cardQuery = "SELECT CardNumber FROM bank WHERE Pin = ?";
            String fetchedCardNumber = "";
            try (PreparedStatement psCard = conn.c.prepareStatement(cardQuery)) {
                psCard.setString(1, pinnumber);
                try (ResultSet rsCard = psCard.executeQuery()) {
                    if (rsCard.next()) {
                        fetchedCardNumber = rsCard.getString("CardNumber");
                    }
                }
            }

            if (!fetchedCardNumber.isEmpty() && fetchedCardNumber.length() >= 16) {
                String maskedCard = fetchedCardNumber.substring(0, 4) + "XXXXXXXX" + fetchedCardNumber.substring(12);
                cardLabel.setText("Card Number: " + maskedCard);
            } else {
                cardLabel.setText("Card Number: N/A"); 
            }

           
            String query = "SELECT Date, Type, Amount FROM bank WHERE Pin = ? ORDER BY Date DESC LIMIT 5"; // Get last 5
            try (PreparedStatement psTxn = conn.c.prepareStatement(query)) {
                psTxn.setString(1, pinnumber);
                try (ResultSet rsTxn = psTxn.executeQuery()) {
                    StringBuilder statement = new StringBuilder();
                    while (rsTxn.next()) {
                        statement.append(rsTxn.getString("Date"))
                                .append("    ")
                                .append(rsTxn.getString("Type"))
                                .append("    ₹")
                                .append(rsTxn.getString("Amount"))
                                .append("\n");
                    }
                    miniStatementArea.setText(statement.toString());
                }
            }

        } catch (SQLException se) {
            JOptionPane.showMessageDialog(this, "Database error: " + se.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("SQLException in MiniStatement: " + se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + e.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("General Exception in MiniStatement: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close(); 
            }
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        new MiniStatement("2004", "5040936000000000"); 
    }
}