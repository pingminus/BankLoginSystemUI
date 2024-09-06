
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The Gui class handles the graphical user interface of the authentication system and bank operations.
 */
public class Gui {
    private AuthSystem authSystem; // Authentication system instance
    private JFrame frame; // Main application window
    private BankSystem bankSystem; // Bank system instance

    /**
     * Constructor initializes the AuthSystem and BankSystem instances.
     */
    public Gui() {
        authSystem = new AuthSystem();
        bankSystem = new BankSystem();
        bankSystem.insertUserBalance("pingplus"); // Initialize balance for a test user
    }

    /**
     * Sets up and displays the main GUI for user login and registration.
     */
    public void showGui() {
        frame = new JFrame("Bank Authentication System");
        frame.setSize(400, 500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.white);

        // Username text field
        JTextField textField = new JTextField();
        textField.setBounds(40, 20, 300, 55);
        textField.setFont(new Font("Arial", Font.PLAIN, 30));
        textField.setBorder(BorderFactory.createTitledBorder("Name"));

        // Password text field (censored)
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(40, 120, 300, 55);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 30));
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));

        // Login button
        JButton buttonLogin = new JButton("Login");
        buttonLogin.setBounds(40, 200, 150, 55);
        buttonLogin.setFont(new Font("Arial", Font.BOLD, 16));
        buttonLogin.setContentAreaFilled(false);

        // Register button
        JButton buttonRegister = new JButton("Register");
        buttonRegister.setBounds(190, 200, 150, 55);
        buttonRegister.setFont(new Font("Arial", Font.BOLD, 16));
        buttonRegister.setContentAreaFilled(false);

        // Exit button
        JButton buttonExit = new JButton("X");
        buttonExit.setBounds(165, 400, 50, 50);
        buttonExit.setFont(new Font("Arial", Font.BOLD, 20));
        buttonExit.setOpaque(false);
        buttonExit.setContentAreaFilled(false);
        buttonExit.setFocusPainted(false);
        buttonExit.setBorderPainted(true);

        // Action listeners for buttons
        buttonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = new String(passwordField.getPassword()); // Get password from JPasswordField
                boolean loginSuccess = authSystem.login(username, password);

                if (loginSuccess) {
                    showMainMenu(username, bankSystem.getMoney(username));
                } else {
                    JOptionPane.showMessageDialog(frame, "Login Failed");
                }
            }
        });

        buttonRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String password = new String(passwordField.getPassword()); // Get password from JPasswordField
                authSystem.register(username, password);
                textField.setText("");
                passwordField.setText(""); // Clear the password field
                bankSystem.insertUserBalance(username);
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                authSystem.exit();
            }
        });
        

        // Add components to the frame
        frame.add(buttonExit);
        frame.add(buttonRegister);
        frame.add(buttonLogin);
        frame.add(passwordField); 
        frame.add(textField);
        frame.setVisible(true);
    }

    /**
     * Displays the main menu after successful login.
     * @param username The logged-in user's username
     * @param money The user's current balance
     */
    public void showMainMenu(String username, String money) {
        frame.dispose();//dispose old JFrame

        JFrame welcoFrame = new JFrame("Bank");
        welcoFrame.setLayout(null);
        welcoFrame.setSize(400, 500);
        welcoFrame.setLocationRelativeTo(null);
        welcoFrame.setVisible(true);
        welcoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcoFrame.setResizable(false);
        welcoFrame.getContentPane().setBackground(Color.white);

        // Welcome label
        JLabel label = new JLabel("Welcome " + username);
        label.setBounds(50, 10, 400, 55);
        label.setFont(new Font("Arial", Font.PLAIN, 40));

        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(100, 400, 150, 50);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 30));
        logoutButton.setContentAreaFilled(false);

        // Balance label
        JLabel labelBalance = new JLabel("Your balance: " + money);
        labelBalance.setBounds(80, 100, 400, 55);
        labelBalance.setFont(new Font("Arial", Font.BOLD, 20));

        // Deposit text field and button
        JTextField textAreaDeposit = new JTextField();
        textAreaDeposit.setBounds(80, 145, 200, 50);
        textAreaDeposit.setFont(new Font("Arial", Font.BOLD, 28));
        textAreaDeposit.setBorder(BorderFactory.createTitledBorder("Deposit"));

        JButton depositButton = new JButton("+");
        depositButton.setBounds(280, 145, 55, 55);
        depositButton.setFont(new Font("Arial", Font.BOLD, 30));
        depositButton.setContentAreaFilled(false);
        depositButton.setBorderPainted(false);
        depositButton.setFocusPainted(false);

        // Withdraw text field and button
        JTextField textAreaWithdraw = new JTextField();
        textAreaWithdraw.setBounds(80, 220, 200, 50);
        textAreaWithdraw.setFont(new Font("Arial", Font.BOLD, 28));
        textAreaWithdraw.setBorder(BorderFactory.createTitledBorder("Withdraw"));

        JButton withdrawButton = new JButton("-");
        withdrawButton.setBounds(280, 220, 55, 55);
        withdrawButton.setFont(new Font("Arial", Font.BOLD, 30));
        withdrawButton.setContentAreaFilled(false);
        withdrawButton.setBorderPainted(false);
        withdrawButton.setFocusPainted(false);

        // Add components to the main menu frame
        welcoFrame.add(withdrawButton);
        welcoFrame.add(textAreaWithdraw);
        welcoFrame.add(depositButton);
        welcoFrame.add(textAreaDeposit);
        welcoFrame.add(labelBalance);
        welcoFrame.add(label);
        welcoFrame.add(logoutButton);

        // Action listeners for main menu buttons
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logout(welcoFrame);
            }
        });

        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String addedMoney = textAreaDeposit.getText();
                labelBalance.setText("Your balance: " + bankSystem.calcMoney(username, addedMoney, true));
                textAreaDeposit.setText("");
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String withdrewMoney = textAreaWithdraw.getText();
                labelBalance.setText("Your balance: " + bankSystem.calcMoney(username, withdrewMoney, false));
                textAreaWithdraw.setText("");
            }
        });
    }

    /**
     * Handles user logout by closing the current frame and reopening the login GUI.
     * @param welcoFrame The main menu frame to be closed
     */
    public void logout(JFrame welcoFrame) {
        welcoFrame.dispose();
        showGui();
    }
}
