package Backend;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
public class UserDatabase extends Database {
    private ArrayList<User> users;

    public UserDatabase(String fileName) {
        super(fileName);
        this.users = new ArrayList<>();
    }
    public void readFromFile() throws FileNotFoundException, IOException, ParseException {    // modifies users arraylist
        this.users.clear(); // remove all previous elements from list
        JSONArray jsonUsers = new JSONArray();
        jsonUsers = (JSONArray) new JSONParser().parse(new FileReader(getFileName()));
        // parse each element in the jsonArray into a user object
        for (int i = 0; i < jsonUsers.size(); i++) {
            users.add(getUserFromMap((Map)jsonUsers.get(i)));
        }
    }
    private User getUserFromMap(Map<String,Object> mapOfUser) {
        long userId = (long)mapOfUser.get("userId");
            String email = (String)mapOfUser.get("email");
            String userName = (String)mapOfUser.get("userName");
            char[] password = (char[])mapOfUser.get("password");
            LocalDate dateOfBirth = (LocalDate)mapOfUser.get("dateOfBirth");
            boolean status = (boolean)mapOfUser.get("status");
            HashMap<Long,FriendshipStatus> relationships = (HashMap<Long,FriendshipStatus>)mapOfUser.get("status");
            User tempUser = new User(userId, email, userName, password, dateOfBirth, status);
            tempUser.setRelationships(relationships);
            return tempUser;
    }
    // impelement method to get user as hashmap??
    private Map<String,Object> getMapFromUser(User user) {
        Map<String,Object> tempUserMap = new HashMap<>();
        tempUserMap.put("userId", String.valueOf(user.getUserId()));
        tempUserMap.put("email", user.getEmail());
        tempUserMap.put("userName", user.getUsername());
        tempUserMap.put("password", new String(user.getPassword()));
        tempUserMap.put("dateOfBirth", user.getDateOfBirth().toString());
        tempUserMap.put("status", String.valueOf(user.isStatus())); // isStatus?? not getStatus or isOnline??
        tempUserMap.put("relationships", user.getRelationships());
        return tempUserMap;
    }
    public void saveToFile() throws FileNotFoundException, IOException, ParseException {    // saves useres arraylist into json format file
        JSONArray jsonUsers = new JSONArray();
        for (int i = 0; i < users.size(); i++) {
            jsonUsers.add(getMapFromUser(users.get(i)));
        }
        PrintWriter pw = new PrintWriter(getFileName()); 
        pw.write(jsonUsers.toJSONString()); 
        pw.flush(); 
        pw.close(); 
    }
    public void addUser(User user) throws IOException, FileNotFoundException, ParseException {
        users.add(user);
        saveToFile();
    }
    public ArrayList<User> getUsers() {
        return users;
    }
    public User getUserFromId(long userId) {    // returns user object if found and null if not found
        for (int i = 0; i < users.size(); i++) {
            if (userId == users.get(i).getUserId()) {
                return users.get(i);
            }
        }
        return null;
    }
    
}
