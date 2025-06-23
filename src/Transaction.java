import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transaction extends JFrame implements ActionListener {

    JButton deposit, withdrawal, fastCash, miniStatement, pinChange, balanceEnquiry, exit;
    String pinNumber,cardnumber;

    Transaction(String pinNumber,String cardnumber) {
        this.pinNumber = pinNumber;
        this.cardnumber=cardnumber;

        //*Frame properties
        setSize(900, 900);
        setLocation(300, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);

        //*Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        //*Heading
        JLabel text = new JLabel("Please Select Your Transaction");
        text.setBounds(220, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        //*Buttons
        deposit = new JButton("Deposit");
        deposit.setBounds(170, 415, 150, 30);
        deposit.addActionListener(this);
        image.add(deposit);

        withdrawal = new JButton("Cash Withdrawal");
        withdrawal.setBounds(355, 415, 150, 30);
        withdrawal.addActionListener(this);
        image.add(withdrawal);

        fastCash = new JButton("Fast Cash");
        fastCash.setBounds(170, 450, 150, 30);
        fastCash.addActionListener(this);
        image.add(fastCash);

        miniStatement = new JButton("Mini Statement");
        miniStatement.setBounds(355, 450, 150, 30);
        miniStatement.addActionListener(this);
        image.add(miniStatement);

        pinChange = new JButton("Pin Change");
        pinChange.setBounds(170, 485, 150, 30);
        pinChange.addActionListener(this);
        image.add(pinChange);

        balanceEnquiry = new JButton("Balance Enquiry");
        balanceEnquiry.setBounds(355, 485, 150, 30);
        balanceEnquiry.addActionListener(this);
        image.add(balanceEnquiry);

        exit = new JButton("Exit");
        exit.setBounds(355, 520, 150, 30);
        exit.addActionListener(this);
        image.add(exit);

        setVisible(true); //*...set visibility of the Frame
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                setVisible(false);
                new Login().setVisible(true); 
            }
        }
 
        if (ae.getSource() == deposit) {
            setVisible(false);
            new Deposit(pinNumber,cardnumber).setVisible(true);
            
        }
        else if (ae.getSource() == withdrawal) {
            setVisible(false);
            new Withdrawal(pinNumber,cardnumber).setVisible(true);
        }
        else if (ae.getSource()==fastCash) {
            setVisible(false);
            new FastCash(pinNumber,cardnumber).setVisible(true);
            
        }
        else if (ae.getSource() == pinChange) {
            setVisible(false);
            new PinChange(pinNumber,cardnumber).setVisible(true);
            
        }
        else if(ae.getSource()==balanceEnquiry)
        {
            setVisible(false);
            new BalanceEnquiry(pinNumber,cardnumber).setVisible(true);
            
        }
        else if (ae.getSource()==miniStatement) {
        setVisible(false);
        new MiniStatement(pinNumber,cardnumber).setVisible(true);
        
        }
    }

    public static void main(String[] args) {
        
        new Transaction("2004","0939289885232131");
    }
}
