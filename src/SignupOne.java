import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement; // Changed to PreparedStatement
import java.sql.SQLException;
import java.util.*;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;

public class SignupOne extends JFrame implements ActionListener {
    long random;
    JTextField nameTextField, fnameTextField, emailTextField, addressTextField, cityTextField, stateTextField,
            pincodeTextField;
    JButton next, back;
    JRadioButton male, female, genderOther, married, unmarried, maritalOther;
    JDateChooser dateChooser;

    SignupOne() {
        setTitle("SIGN UP");
        setSize(850, 800);
        setLocation(350, 10);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);
        JLabel formno = new JLabel("APPLICATION FORM NO. " + random);
        formno.setFont(new Font("Raleway", Font.BOLD, 38));
        formno.setBounds(140, 20, 600, 40);
        add(formno);

        JLabel personaldetails = new JLabel("PAGE 1 : Personal Details");
        personaldetails.setFont(new Font("Raleway", Font.BOLD, 22));
        personaldetails.setBounds(290, 80, 400, 30);
        add(personaldetails);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        nameLabel.setBounds(100, 140, 100, 30);
        add(nameLabel);
        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        nameTextField.setBounds(300, 140, 400, 30);
        add(nameTextField);

        // Father's Name
        JLabel fnameLabel = new JLabel("Father's Name:");
        fnameLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        fnameLabel.setBounds(100, 190, 200, 30);
        add(fnameLabel);
        fnameTextField = new JTextField();
        fnameTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        fnameTextField.setBounds(300, 190, 400, 30);
        add(fnameTextField);

        // Date of Birth
        JLabel dobLabel = new JLabel("Date of Birth:");
        dobLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        dobLabel.setBounds(100, 240, 200, 30);
        add(dobLabel);
        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 240, 400, 30);
        dateChooser.setForeground(new Color(105, 105, 105));
        add(dateChooser);

        // Gender
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        genderLabel.setBounds(100, 290, 200, 30);
        add(genderLabel);
        male = new JRadioButton("Male");
        male.setBounds(300, 290, 80, 30);
        male.setBackground(Color.WHITE);
        add(male);
        female = new JRadioButton("Female");
        female.setBounds(450, 290, 100, 30);
        female.setBackground(Color.WHITE);
        add(female);
        genderOther = new JRadioButton("Other");
        genderOther.setBounds(580, 290, 100, 30);
        genderOther.setBackground(Color.WHITE);
        add(genderOther);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(genderOther);

        // Email Address
        JLabel emailLabel = new JLabel("Email Address:");
        emailLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        emailLabel.setBounds(100, 340, 200, 30);
        add(emailLabel);
        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        emailTextField.setBounds(300, 340, 400, 30);
        add(emailTextField);

        // Marital Status
        JLabel maritalLabel = new JLabel("Marital Status:");
        maritalLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        maritalLabel.setBounds(100, 390, 200, 30);
        add(maritalLabel);
        married = new JRadioButton("Married");
        married.setBounds(300, 390, 100, 30);
        married.setBackground(Color.WHITE);
        add(married);
        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(450, 390, 120, 30);
        unmarried.setBackground(Color.WHITE);
        add(unmarried);
        maritalOther = new JRadioButton("Other");
        maritalOther.setBounds(580, 390, 100, 30);
        maritalOther.setBackground(Color.WHITE);
        add(maritalOther);
        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(married);
        maritalGroup.add(unmarried);
        maritalGroup.add(maritalOther);

        // Address
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        addressLabel.setBounds(100, 440, 200, 30);
        add(addressLabel);
        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        addressTextField.setBounds(300, 440, 400, 30);
        add(addressTextField);

        // City
        JLabel cityLabel = new JLabel("City:");
        cityLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        cityLabel.setBounds(100, 490, 200, 30);
        add(cityLabel);
        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        cityTextField.setBounds(300, 490, 400, 30);
        add(cityTextField);

        // State
        JLabel stateLabel = new JLabel("State:");
        stateLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        stateLabel.setBounds(100, 540, 200, 30);
        add(stateLabel);
        stateTextField = new JTextField();
        stateTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        stateTextField.setBounds(300, 540, 400, 30);
        add(stateTextField);

        // Pincode
        JLabel pincodeLabel = new JLabel("Pincode:");
        pincodeLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        pincodeLabel.setBounds(100, 590, 200, 30);
        add(pincodeLabel);
        pincodeTextField = new JTextField();
        pincodeTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        pincodeTextField.setBounds(300, 590, 400, 30);
        add(pincodeTextField);

        // Next Button
        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this);
        add(next);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("Raleway", Font.BOLD, 14));
        back.setBounds(500, 660, 80, 30);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            String formno = "" + random;
            String name = nameTextField.getText();
            String fname = fnameTextField.getText();
            String dob = "";
            if (dateChooser.getDate() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                dob = sdf.format(dateChooser.getDate());
            }

            String gender = "";
            if (male.isSelected()) {
                gender = "Male";
            } else if (female.isSelected()) {
                gender = "Female";
            } else if (genderOther.isSelected()) {
                gender = "Other";
            }

            String email = emailTextField.getText();
            String marital = "";
            if (married.isSelected()) {
                marital = "Married";
            } else if (unmarried.isSelected()) {
                marital = "Unmarried";
            } else if (maritalOther.isSelected()) {
                marital = "Other";
            }

            String address = addressTextField.getText();
            String city = cityTextField.getText();
            String state = stateTextField.getText();
            String pincode = pincodeTextField.getText();

             if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name is Required");
            } else if (fname.equals("")) {
                JOptionPane.showMessageDialog(null, "Father's Name is Required");
            } else if (dob == null) {
                JOptionPane.showMessageDialog(null, "Date of Birth is Required");
            } else if (email.equals("")) {
                JOptionPane.showMessageDialog(null, "Email is Required");
            } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
                JOptionPane.showMessageDialog(null, "Invalid Email Format");
            } else if (address.equals("")) {
                JOptionPane.showMessageDialog(null, "Address is Required");
            } else if (city.equals("")) {
                JOptionPane.showMessageDialog(null, "City is Required");
            } else if (state.equals("")) {
                JOptionPane.showMessageDialog(null, "State is Required");
            } else if (pincode.equals("")) {
                JOptionPane.showMessageDialog(null, "Pincode is Required");
            } else if (!pincode.matches("\\d{6}")) {
                JOptionPane.showMessageDialog(null, "Pincode must be 6 digits");
            }

            Conn c1 = null;
            try {
                c1 = new Conn();
                String query = "INSERT INTO signupone (formno, name, father_name, dob, gender, email, maritalstatus, address, city, state, pincode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pst = c1.c.prepareStatement(query)) {
                    pst.setString(1, formno);
                    pst.setString(2, name);
                    pst.setString(3, fname);
                    pst.setString(4, dob);
                    pst.setString(5, gender);
                    pst.setString(6, email);
                    pst.setString(7, marital);
                    pst.setString(8, address);
                    pst.setString(9, city);
                    pst.setString(10, state);
                    pst.setString(11, pincode);
                    pst.executeUpdate();
                }

                //Here we call the constructor of Signuptwo class
                setVisible(false);
                new Signuptwo(formno).setVisible(true);
            } catch (SQLException sqlEx) {
                JOptionPane.showMessageDialog(this,
                        "Database error occurred while saving your details:\n" + sqlEx.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
                System.err.println("SQLException in SignupOne: " + sqlEx.getMessage());
                sqlEx.printStackTrace();
            } catch (NullPointerException nullEx) {
                JOptionPane.showMessageDialog(this,
                        "A required field was missing or null. Please ensure all inputs are filled correctly.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
                System.err.println("NullPointerException in SignupOne: " + nullEx.getMessage());
                nullEx.printStackTrace();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "An unexpected error occurred:\n" + e.getMessage(),
                        "Unexpected Error",
                        JOptionPane.ERROR_MESSAGE);
                System.err.println("General Exception in SignupOne: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (c1 != null) {
                    c1.close();
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new SignupOne();
    }
}