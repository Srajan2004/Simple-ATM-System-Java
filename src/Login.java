import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException; 

import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    JButton loginButton, clearButton, signupButton;
    JTextField cardTextField;
    JPasswordField pinTextField;

    Login() {
        setTitle("Login");
        setSize(800, 480);
        setLocation(350, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);

        // Logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        JLabel logoLabel = new JLabel(new ImageIcon(i2));
        logoLabel.setBounds(70, 10, 100, 100);
        add(logoLabel);

        // Welcome Text
        JLabel welcomeText = new JLabel("Welcome To ATM");
        welcomeText.setFont(new Font("Osward", Font.BOLD, 30));
        welcomeText.setBounds(200, 50, 400, 40);
        add(welcomeText);

        // Card Number
        JLabel cardLabel = new JLabel("Card No:");
        cardLabel.setFont(new Font("Raleway", Font.BOLD, 28));
        cardLabel.setBounds(120, 150, 150, 30);
        add(cardLabel);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);

        // PIN
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setFont(new Font("Raleway", Font.BOLD, 28));
        pinLabel.setBounds(120, 220, 250, 30);
        add(pinLabel);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);

        // Buttons
        loginButton = new JButton("SIGN IN");
        loginButton.setBounds(300, 300, 100, 30);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        add(loginButton);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(430, 300, 100, 30);
        clearButton.setBackground(Color.BLACK);
        clearButton.setForeground(Color.WHITE);
        clearButton.addActionListener(this);
        add(clearButton);

        signupButton = new JButton("SIGN UP");
        signupButton.setBounds(300, 350, 230, 30);
        signupButton.setBackground(Color.BLACK);
        signupButton.setForeground(Color.WHITE);
        signupButton.addActionListener(this);
        add(signupButton);

        setVisible(true);
    }
   @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == clearButton) {
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == loginButton) {
            String cardnumber = cardTextField.getText();
            String pinnumber = new String(pinTextField.getPassword());

            if (cardnumber.isEmpty() || pinnumber.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Card Number and PIN are required.", "Input Error",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            Conn conn = null;
            try {
                conn = new Conn();
                String query = "SELECT * FROM signupthree WHERE card_number = ? AND pin_number = ?";
                try (PreparedStatement pst = conn.c.prepareStatement(query)) {
                    pst.setString(1, cardnumber);
                    pst.setString(2, pinnumber);

                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            setVisible(false);
                            new Transaction(pinnumber, cardnumber).setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(this, "Invalid card number or PIN.", "Login Failed",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(this, "Database error occurred during login. Please try again.",
                        "Database Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("SQLException during login: " + se.getMessage());
                se.printStackTrace();
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(this, "A required field was left empty or not initialized.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("NullPointerException: " + npe.getMessage());
                npe.printStackTrace();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred during login.", "Unexpected Error",
                        JOptionPane.ERROR_MESSAGE);
                System.err.println("General Exception during login: " + e.getMessage());
                e.printStackTrace();
            } finally {

                if (conn != null) {
                    conn.close();
                }
            }
        } else if (ae.getSource() == signupButton) {
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}