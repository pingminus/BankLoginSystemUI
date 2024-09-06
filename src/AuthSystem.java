import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * AuthSystem class handles user registration and login functionalities.
 */
public class AuthSystem {
    // A HashMap to store usernames and their corresponding passwords
    private HashMap<String, String> users = new HashMap<>();

    /**
     * Constructor initializes the AuthSystem with a default user for testing.
     */
    public AuthSystem() {
        // Adding a default user for testing purposes
        users.put("pingplus", "123");
    }

    /**
     * Registers a new user if the username and password are valid.
     * 
     */
    public void register(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "You must provide both a username and a password.");
        } else if (users.containsKey(username)) {
            JOptionPane.showMessageDialog(null, "Username: " + username + " is already taken.");
        } else {
            users.put(username, password);
            JOptionPane.showMessageDialog(null, "Registration successful.");
        }
    }

    /**
     * Logs in a user by verifying the username and password.
     *
     */
    public boolean login(String username, String password) {
        if (!users.containsKey(username)) {
            JOptionPane.showMessageDialog(null, "Username not found.");
            return false;
        }
        if (!users.get(username).equals(password)) {
            JOptionPane.showMessageDialog(null, "Incorrect password.");
            return false;
        }
       System.out.println("Login successful");
        return true;
    }

    /**
     * Exits the application.
     */
    public void exit() {
        System.exit(0);
    }

   
    public static void main(String[] args) {
        Gui gui = new Gui();
        gui.showGui();
    }
}

// Login System without GUI (Commented Out for Reference)
/*======================================================================================*/
/*======================================================================================*/

/*
public String[] askForInfo() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter username:");
    String username = scanner.nextLine();
    System.out.println("Enter your password:");
    String password = scanner.nextLine();
    return new String[]{username, password};
}

public void showMainMenu() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("---MAIN MENU---");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");

        int option = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left-over

        switch (option) {
            case 1:
                String[] loginInfo = askForInfo();
                login(loginInfo[0], loginInfo[1]);
                break;

            case 2:
                String[] registerInfo = askForInfo();
                register(registerInfo[0], registerInfo[1]);
                break;

            case 3:
                exit();
                break;

            default:
                System.out.println("Invalid option. Please choose again.");
                break;
        }
    }
}
*/
