import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class Signupthree extends JFrame implements ActionListener {

    JRadioButton savingaccount, currentaccount, fixed_deposit, recurring_deposit;
    JCheckBox atm, internet_banking, mobile_banking, email_alerts, cheque_book, e_statement, clarification;
    JButton submit, cancel;
    String formno;

    Signupthree(String formno) {
        this.formno = formno;

        setLayout(null);
        setSize(850, 800); 
        setLocation(350, 10); 
        getContentPane().setBackground(Color.WHITE); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        JLabel heading = new JLabel("Page-3 : Account Details");
        heading.setFont(new Font("Raleway", Font.BOLD, 22));
        heading.setBounds(280, 0, 400, 30);
        add(heading);

        JLabel type = new JLabel("Account Type");
        type.setFont(new Font("Raleway", Font.BOLD, 22));
        type.setBounds(100, 140, 400, 30);
        add(type);

        savingaccount = new JRadioButton("Savings Account");
        savingaccount.setFont(new Font("Raleway", Font.BOLD, 16));
        savingaccount.setBounds(100, 180, 180, 20);
        savingaccount.setBackground(Color.WHITE);
        add(savingaccount);

        currentaccount = new JRadioButton("Current Account");
        currentaccount.setFont(new Font("Raleway", Font.BOLD, 16));
        currentaccount.setBounds(350, 180, 180, 20);
        currentaccount.setBackground(Color.WHITE);
        add(currentaccount);

        fixed_deposit = new JRadioButton("Fixed Deposit Account");
        fixed_deposit.setFont(new Font("Raleway", Font.BOLD, 16));
        fixed_deposit.setBounds(100, 220, 200, 20);
        fixed_deposit.setBackground(Color.WHITE);
        add(fixed_deposit);

        recurring_deposit = new JRadioButton("Recurring Deposit Account");
        recurring_deposit.setFont(new Font("Raleway", Font.BOLD, 16));
        recurring_deposit.setBounds(350, 220, 250, 20);
        recurring_deposit.setBackground(Color.WHITE);
        add(recurring_deposit);

        ButtonGroup accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(savingaccount);
        accountTypeGroup.add(currentaccount);
        accountTypeGroup.add(fixed_deposit);
        accountTypeGroup.add(recurring_deposit);

        JLabel card_number = new JLabel("Card Number:");
        card_number.setFont(new Font("Raleway", Font.BOLD, 22));
        card_number.setBounds(100, 300, 200, 30);
        add(card_number);

        JLabel number = new JLabel("XXXX-XXXX-XXXX-4184");
        number.setFont(new Font("Raleway", Font.BOLD, 22));
        number.setBounds(330, 300, 300, 30);
        add(number);

        JLabel card_detail = new JLabel("Your 16-digit Card Number");
        card_detail.setFont(new Font("Raleway", Font.BOLD, 12));
        card_detail.setBounds(100, 330, 300, 15);
        add(card_detail);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 22));
        pin.setBounds(100, 370, 200, 30);
        add(pin);

        JLabel pin_number = new JLabel("XXXX"); 
        pin_number.setFont(new Font("Raleway", Font.BOLD, 22));
        pin_number.setBounds(330, 370, 300, 30);
        add(pin_number);

        JLabel pin_detail = new JLabel("Your 4-digit PIN Number");
        pin_detail.setFont(new Font("Raleway", Font.BOLD, 12));
        pin_detail.setBounds(100, 400, 300, 15);
        add(pin_detail);

        JLabel services = new JLabel("Services Required:");
        services.setFont(new Font("Raleway", Font.BOLD, 22));
        services.setBounds(100, 450, 250, 30);
        add(services);

        atm = new JCheckBox("ATM Card");
        atm.setBackground(Color.WHITE);
        atm.setFont(new Font("Raleway", Font.BOLD, 16));
        atm.setBounds(100, 500, 200, 30);
        add(atm);

        internet_banking = new JCheckBox("Internet Banking");
        internet_banking.setBackground(Color.WHITE);
        internet_banking.setFont(new Font("Raleway", Font.BOLD, 16));
        internet_banking.setBounds(350, 500, 200, 30);
        add(internet_banking);

        mobile_banking = new JCheckBox("Mobile Banking");
        mobile_banking.setBackground(Color.WHITE);
        mobile_banking.setFont(new Font("Raleway", Font.BOLD, 16));
        mobile_banking.setBounds(100, 550, 200, 30);
        add(mobile_banking);

        email_alerts = new JCheckBox("Email Alerts");
        email_alerts.setBackground(Color.WHITE);
        email_alerts.setFont(new Font("Raleway", Font.BOLD, 16));
        email_alerts.setBounds(350, 550, 200, 30);
        add(email_alerts);

        cheque_book = new JCheckBox("Cheque Book");
        cheque_book.setBackground(Color.WHITE);
        cheque_book.setFont(new Font("Raleway", Font.BOLD, 16));
        cheque_book.setBounds(100, 600, 200, 30);
        add(cheque_book);

        e_statement = new JCheckBox("E-Statement");
        e_statement.setBackground(Color.WHITE);
        e_statement.setFont(new Font("Raleway", Font.BOLD, 16));
        e_statement.setBounds(350, 600, 200, 30);
        add(e_statement);

        clarification = new JCheckBox("I hereby declare that the above entered details are correct to the best of my knowledge.");
        clarification.setBackground(Color.WHITE);
        clarification.setFont(new Font("Raleway", Font.BOLD, 12));
        clarification.setBounds(100, 680, 600, 20);
        add(clarification);

        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Raleway", Font.BOLD, 14));
        submit.setBounds(250, 720, 100, 30);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Raleway", Font.BOLD, 14));
        cancel.setBounds(420, 720, 100, 30);
        cancel.addActionListener(this);
        add(cancel);

        setVisible(true); 
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String accountType = "";
            if (savingaccount.isSelected()) {
                accountType = "Savings Account";
            } else if (currentaccount.isSelected()) {
                accountType = "Current Account";
            } else if (fixed_deposit.isSelected()) {
                accountType = "Fixed Deposit Account";
            } else if (recurring_deposit.isSelected()) {
                accountType = "Recurring Deposit Account";
            }

            StringBuilder services = new StringBuilder();
            if (atm.isSelected()) {
                services.append("ATM Card,");
            }
            if (internet_banking.isSelected()) {
                services.append("Internet Banking,");
            }
            if (mobile_banking.isSelected()) {
                services.append("Mobile Banking,");
            }
            if (email_alerts.isSelected()) {
                services.append("Email Alerts,");
            }
            if (cheque_book.isSelected()) {
                services.append("Cheque Book,");
            }
            if (e_statement.isSelected()) {
                services.append("E-Statement,");
            }

            
            if (services.length() > 0) {
                services.setLength(services.length() - 1);
            }

            if (!clarification.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please confirm the declaration.", "Declaration Required", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (accountType.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select an account type.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            
            String cardnumber = generateCardNumber();
            String pinnumber = generatePinNumber();

            Conn conn = null; 
            try {
                conn = new Conn(); 
                String query1 = "INSERT INTO signupthree (formno, account_type, card_number, pin_number, services) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps1 = conn.c.prepareStatement(query1)) {
                    ps1.setString(1, formno);
                    ps1.setString(2, accountType);
                    ps1.setString(3, cardnumber);
                    ps1.setString(4, pinnumber);
                    ps1.setString(5, services.toString());
                    ps1.executeUpdate();
                }

                
                String query2 = "INSERT INTO login (formno, card_number, pin_number) VALUES (?, ?, ?)";
                try (PreparedStatement ps2 = conn.c.prepareStatement(query2)) {
                    ps2.setString(1, formno);
                    ps2.setString(2, cardnumber);
                    ps2.setString(3, pinnumber);
                    ps2.executeUpdate();
                }

                JOptionPane.showMessageDialog(null,
                        "Account Created Successfully\nCard No: " + cardnumber + "\nPIN: " + pinnumber);
                setVisible(false);
                new Login().setVisible(true);
            } catch (SQLException se) {
                JOptionPane.showMessageDialog(null, "Database error: " + se.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("SQLException in Signupthree: " + se.getMessage());
                se.printStackTrace();
            } catch (NullPointerException npe) {
                JOptionPane.showMessageDialog(null, "Some required fields are missing: " + npe.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("NullPointerException in Signupthree: " + npe.getMessage());
                npe.printStackTrace();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Unexpected error: " + e.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("General Exception in Signupthree: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.close(); // Close the connection
                }
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true); 
        }
    }

    // method to generate a random 16-digit card number
    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    // method to generate a random 4-digit PIN
    private String generatePinNumber() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }

    public static void main(String[] args) {
        new Signupthree("");
    }
}