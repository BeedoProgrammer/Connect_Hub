package Backend;

import java.util.ArrayList;
import java.util.HashMap;
//import org.json.simple.JSONObject;
public class UserDatabase {
//    private ArrayList<User> users;
//
//    public UserDatabase(String fileName) {
//        super(fileName);
//        this.users = new ArrayList<>();
//    }
//    public void readFromFile() {
//        
//    }

    // A HashMap to store users, where the key is the user ID and the value is the User object
    private static HashMap<Long, User> usersDatabase = new HashMap<>();

    // Method to add a user to the database
    public static boolean addUser(User user) {
        if (usersDatabase.containsKey(user.getUserId())) {
            return false;  // User already exists
        }
        usersDatabase.put(user.getUserId(), user);
        return true;  // User successfully added
    }

    // Method to retrieve a user by their ID
    public static User getUserById(long userId) {
        return usersDatabase.get(userId);  // Return the user object or null if not found
    }

    // Method to check if a user exists in the database
    public static boolean userExists(long userId) {
        return usersDatabase.containsKey(userId);
    }

    // Method to remove a user from the database
    public static boolean removeUser(long userId) {
        if (usersDatabase.containsKey(userId)) {
            usersDatabase.remove(userId);
            return true;  // User successfully removed
        }
        return false;  // User not found
    }

    // Method to print all users in the database (for testing)
    public static void printAllUsers() {
        for (User user : usersDatabase.values()) {
            System.out.println(user);
        }
    }
}

