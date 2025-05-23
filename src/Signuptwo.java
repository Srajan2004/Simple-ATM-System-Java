import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement; 
import java.sql.SQLException;

import javax.swing.*;

public class Signuptwo extends JFrame implements ActionListener {
    JTextField panField, aadharField;
    JButton nextButton, backButton; 
    JRadioButton seniorYes, seniorNo, existingYes, existingNo;
    JComboBox<String> religionBox, categoryBox, incomeBox, educationBox, occupationBox;

    String formno;

    Signuptwo(String formno) {
        this.formno = formno;

        setTitle("Signup Page 2 - Additional Details");
        setSize(850, 800);
        setLocation(350, 10);
        getContentPane().setBackground(Color.WHITE);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); 
        setLayout(null);

        JLabel header = new JLabel("PAGE 2: Additional Details", SwingConstants.CENTER);
        header.setFont(new Font("Raleway", Font.BOLD, 25));
        header.setBounds(200, 50, 450, 40);
        add(header);

        // Religion
        JLabel religionLabel = new JLabel("Religion:");
        religionLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        religionLabel.setBounds(100, 140, 200, 30);
        add(religionLabel);
        String[] religions = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religionBox = new JComboBox<>(religions);
        religionBox.setBounds(350, 140, 320, 30);
        religionBox.setBackground(Color.WHITE);
        religionBox.setFont(new Font("Raleway", Font.BOLD, 14));
        add(religionBox);

        // Category
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        categoryLabel.setBounds(100, 190, 200, 30);
        add(categoryLabel);
        String[] categories = {"General", "OBC", "SC", "ST", "Other"};
        categoryBox = new JComboBox<>(categories);
        categoryBox.setBounds(350, 190, 320, 30);
        categoryBox.setBackground(Color.WHITE);
        categoryBox.setFont(new Font("Raleway", Font.BOLD, 14));
        add(categoryBox);

        // Income
        JLabel incomeLabel = new JLabel("Income:");
        incomeLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        incomeLabel.setBounds(100, 240, 200, 30);
        add(incomeLabel);
        String[] incomes = {"Null", "< 1,50,000", "< 2,50,000", "< 5,00,000", "Upto 10,00,000", "Above 10,00,000"};
        incomeBox = new JComboBox<>(incomes);
        incomeBox.setBounds(350, 240, 320, 30);
        incomeBox.setBackground(Color.WHITE);
        incomeBox.setFont(new Font("Raleway", Font.BOLD, 14));
        add(incomeBox);

        // Educational
        JLabel educationLabel = new JLabel("Educational\nQualification:");
        educationLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        educationLabel.setBounds(100, 290, 200, 30);
        add(educationLabel);
        String[] educations = {"Non-Graduation", "Graduation", "Post-Graduation", "Doctrate", "Other"};
        educationBox = new JComboBox<>(educations);
        educationBox.setBounds(350, 290, 320, 30);
        educationBox.setBackground(Color.WHITE);
        educationBox.setFont(new Font("Raleway", Font.BOLD, 14));
        add(educationBox);

        // Occupation
        JLabel occupationLabel = new JLabel("Occupation:");
        occupationLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        occupationLabel.setBounds(100, 340, 200, 30);
        add(occupationLabel);
        String[] occupations = {"Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"};
        occupationBox = new JComboBox<>(occupations);
        occupationBox.setBounds(350, 340, 320, 30);
        occupationBox.setBackground(Color.WHITE);
        occupationBox.setFont(new Font("Raleway", Font.BOLD, 14));
        add(occupationBox);

        // PAN Number
        JLabel panLabel = new JLabel("PAN Number:");
        panLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        panLabel.setBounds(100, 390, 200, 30);
        add(panLabel);
        panField = new JTextField();
        panField.setFont(new Font("Raleway", Font.BOLD, 14));
        panField.setBounds(350, 390, 320, 30);
        add(panField);

        // Aadhar Number
        JLabel aadharLabel = new JLabel("Aadhar Number:");
        aadharLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        aadharLabel.setBounds(100, 440, 200, 30);
        add(aadharLabel);
        aadharField = new JTextField();
        aadharField.setFont(new Font("Raleway", Font.BOLD, 14));
        aadharField.setBounds(350, 440, 320, 30);
        add(aadharField);

        // Senior Citizen
        JLabel seniorCitizenLabel = new JLabel("Senior Citizen:");
        seniorCitizenLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        seniorCitizenLabel.setBounds(100, 490, 200, 30);
        add(seniorCitizenLabel);
        seniorYes = new JRadioButton("Yes");
        seniorYes.setBounds(350, 490, 80, 30);
        seniorYes.setBackground(Color.WHITE);
        add(seniorYes);
        seniorNo = new JRadioButton("No");
        seniorNo.setBounds(450, 490, 80, 30);
        seniorNo.setBackground(Color.WHITE);
        add(seniorNo);
        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(seniorYes);
        seniorGroup.add(seniorNo);

        // Existing Account
        JLabel existingAccountLabel = new JLabel("Existing Account:");
        existingAccountLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        existingAccountLabel.setBounds(100, 540, 200, 30);
        add(existingAccountLabel);
        existingYes = new JRadioButton("Yes");
        existingYes.setBounds(350, 540, 80, 30);
        existingYes.setBackground(Color.WHITE);
        add(existingYes);
        existingNo = new JRadioButton("No");
        existingNo.setBounds(450, 540, 80, 30);
        existingNo.setBackground(Color.WHITE);
        add(existingNo);
        ButtonGroup existingGroup = new ButtonGroup();
        existingGroup.add(existingYes);
        existingGroup.add(existingNo);

        // Next Button
        nextButton = new JButton("Next");
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);
        nextButton.setFont(new Font("Raleway", Font.BOLD, 14));
        nextButton.setBounds(590, 620, 80, 30);
        nextButton.addActionListener(this);
        add(nextButton);

        // Back Button (Added for better navigation)
        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Raleway", Font.BOLD, 14));
        backButton.setBounds(480, 620, 80, 30);
        backButton.addActionListener(this);
        add(backButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == nextButton) {
            String religion = (String) religionBox.getSelectedItem();
            String category = (String) categoryBox.getSelectedItem();
            String income = (String) incomeBox.getSelectedItem();
            String education = (String) educationBox.getSelectedItem();
            String occupation = (String) occupationBox.getSelectedItem();

            String pan = panField.getText();
            String aadhar = aadharField.getText();

            String seniorCitizen = "";
            if (seniorYes.isSelected()) {
                seniorCitizen = "Yes";
            } else if (seniorNo.isSelected()) {
                seniorCitizen = "No";
            }

            String existingAccount = "";
            if (existingYes.isSelected()) {
                existingAccount = "Yes";
            } else if (existingNo.isSelected()) {
                existingAccount = "No";
            }

            if (pan.isEmpty() || aadhar.isEmpty() || religion == null || category == null || income == null ||
                    education == null || occupation == null || seniorCitizen.isEmpty() || existingAccount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all the required fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Conn conn = null; 
            try {
                conn = new Conn(); 
                String query = "INSERT INTO signuptwo (religion, category, income, education, occupation, PAN, Adhar, seniorCitezen, Existing_account, formno) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pst = conn.c.prepareStatement(query)) {
                    pst.setString(1, religion);
                    pst.setString(2, category);
                    pst.setString(3, income);
                    pst.setString(4, education);
                    pst.setString(5, occupation);
                    pst.setString(6, pan);
                    pst.setString(7, aadhar);
                    pst.setString(8, seniorCitizen);
                    pst.setString(9, existingAccount);
                    pst.setString(10, formno);
                    pst.executeUpdate();
                }

                setVisible(false);
                new Signupthree(formno).setVisible(true); 
            } catch (SQLException sqlEx) {
                JOptionPane.showMessageDialog(this,
                        "Database error occurred:\n" + sqlEx.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE);
                System.err.println("SQLException in Signuptwo: " + sqlEx.getMessage());
                sqlEx.printStackTrace();
            } catch (NullPointerException nullEx) {
                JOptionPane.showMessageDialog(this,
                        "A null value was encountered. Please ensure all fields are correctly filled.",
                        "Null Value Error",
                        JOptionPane.ERROR_MESSAGE);
                System.err.println("NullPointerException in Signuptwo: " + nullEx.getMessage());
                nullEx.printStackTrace();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "An unexpected error occurred:\n" + e.getMessage(), "Unexpected Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("General Exception in Signuptwo: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.close(); 
                }
            }
        } else if (ae.getSource() == backButton) {
            setVisible(false);
            new SignupOne().setVisible(true); 
        }
    }

    public static void main(String[] args) {
        new Signuptwo("");
    }
}