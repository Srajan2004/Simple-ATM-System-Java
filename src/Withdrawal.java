import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Withdrawal extends JFrame implements ActionListener {
    JTextField amount;
    JButton withdrawal, back;
    String pinnumber,cardnumber;

    Withdrawal(String pinnumber,String cardnumber) {
        this.pinnumber = pinnumber;
        this.cardnumber= cardnumber;
        // *Frame properties
        setSize(900, 900);
        setLocation(300, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setUndecorated(true);

        // *Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // *Adding lables to the Frame */

        JLabel text = new JLabel("Enter the ammount you want to Withdrawal");
        text.setForeground(Color.white);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(180, 300, 400, 20);
        image.add(text);

        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 16));
        amount.setBounds(170, 350, 320, 25);
        amount.addActionListener(this);
        image.add(amount);

        withdrawal = new JButton("Withdrawa");
        withdrawal.setBounds(355, 485, 150, 30);
        withdrawal.addActionListener(this);
        image.add(withdrawal);

        back = new JButton("Back");
        back.setBounds(355, 520, 150, 30);
        back.addActionListener(this);
        image.add(back);

        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == withdrawal) {
            String number = amount.getText();
            Date date = new Date(System.currentTimeMillis());

            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the ammount you want to withdrawal");
            } else {
                Conn conn = null;
                try {
                     conn = new Conn();
                    String query = "INSERT INTO bank (Pin,Date,Type,Amount,CardNumber) VALUES (?, ?, ?, ?,?)";
                    PreparedStatement ps = conn.c.prepareStatement(query);
                    ps.setString(1, pinnumber);
                    ps.setString(2, date.toString());
                    ps.setString(3, "Withdrawal");
                    ps.setString(4, number);
                    ps.setString(5, cardnumber);
                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Rs. " + number + " Withdrawed Successfully");
                    setVisible(false);
                    new Transaction(pinnumber,cardnumber).setVisible(true);

                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric amount.");
                    nfe.printStackTrace();
                } catch (SQLException se) {
                    JOptionPane.showMessageDialog(null, "Database error: " + se.getMessage());
                    se.printStackTrace();
                } catch (HeadlessException he) {
                    JOptionPane.showMessageDialog(null, "UI error: " + he.getMessage());
                    he.printStackTrace();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + e.getMessage());
                    e.printStackTrace();
                }
                finally
                {
                    if (conn!=null) {
                        conn.close();
                    }

                }

            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transaction(pinnumber,cardnumber).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Withdrawal("2004", "2481157071329795");
    }
}
