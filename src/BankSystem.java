import java.util.HashMap;

/**
 * Manages user balances using a HashMap.
 */
public class BankSystem {

    // Stores user balances with usernames as keys
    HashMap<String, String> usersCash;

    /**
     * Initializes the BankSystem with an empty balance map.
     */
    public BankSystem() {
        usersCash = new HashMap<>();
    }

    /**
     * Adds a new user with an initial balance of "0".
     * @param username The username of the new user.
     * @return The initial balance, which is "0".
     */
    public int insertUserBalance(String username) {
        usersCash.put(username, "0");
        return Integer.parseInt(usersCash.get(username));
    }

    /**
     * Retrieves the balance of a user.
     * @param username The username of the user.
     * @return The user's balance.
     */
    public String getMoney(String username) {
        return usersCash.get(username);
    }

    /**
     * Updates the balance by adding or withdrawing money.
     * @param username The username of the user.
     * @param amount The amount to add or withdraw.
     * @param addMoney True to add money, false to withdraw.
     * @return The updated balance or null if there's an error.
     */
    public String calcMoney(String username, String amount, boolean addMoney) {
        try {
            int currentBalance = Integer.parseInt(usersCash.get(username));
            int change = Integer.parseInt(amount);

            if (addMoney) {
                currentBalance += change;
            } else {
                currentBalance -= change;
            }

            usersCash.put(username, String.valueOf(currentBalance));
            return String.valueOf(currentBalance);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount");
            return null;
        }
    }
}
