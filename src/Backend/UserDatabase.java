package Backend;

import java.util.ArrayList;
import org.json.simple.JSONObject;
public class UserDatabase extends Database {
    private ArrayList<User> users;

    public UserDatabase(String fileName) {
        super(fileName);
        this.users = new ArrayList<>();
    }
    public void readFromFile() {
        
    }
    
}
