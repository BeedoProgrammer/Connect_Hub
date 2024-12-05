package Backend;


import com.google.gson.Gson;
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
//    private ArrayList<User> users;
    
    private static UserDatabase instance = null;
    
    public static UserDatabase getInstance() {
        if (instance == null) {
            instance = new UserDatabase("files/users.json");
        }
        return instance;
    }
    
    private UserDatabase(String fileName) {
        super(fileName);
//        this.users = new ArrayList<>();
    }
//    public void readFromFile() throws FileNotFoundException, IOException, ParseException {    // modifies users arraylist
//        this.users.clear(); // remove all previous elements from list
//        JSONArray jsonUsers = new JSONArray();
//        jsonUsers = (JSONArray) new JSONParser().parse(new FileReader(getFileName()));
//        // parse each element in the jsonArray into a user object
//        for (int i = 0; i < jsonUsers.size(); i++) {
//            users.add(getUserFromMap((Map)jsonUsers.get(i)));
//        }
//    }
    @Override
    protected User getRecordFromMap(Map<String,Object> mapOfUser) {
        long userId = (long)mapOfUser.get("userId");                // saved in json as long
        String email = (String)mapOfUser.get("email");              // saved in json as string
        String userName = (String)mapOfUser.get("userName");        // saved in json as string
        String password = (String)mapOfUser.get("password");        // saved in json as string
        String dateOfBirth = (String)mapOfUser.get("dateOfBirth");  // saved in json as string
        String bio = (String)mapOfUser.get("bio");
        String coverPic = (String)mapOfUser.get("coverPic");
        String profilePic = (String)mapOfUser.get("profilePic");
        boolean status = (boolean)mapOfUser.get("status");          // saved in json as boolean
        // parsing string into hashmap
            Gson gson = new Gson();
            String jsonString = (String)mapOfUser.get("relationships"); // saved in json as string (JSONString)
            JSONObject jsonObject = gson.fromJson(jsonString, JSONObject.class);
            HashMap<Long,FriendshipStatus> relationships = (HashMap<Long,FriendshipStatus>) jsonObject; // casting from JSONObject to hashmap
        //
        User tempUser = new User.UserBuilder(userId, email, userName, password.toCharArray(), LocalDate.parse(dateOfBirth), status)
            .bio(bio)
            .coverPhoto(coverPic)
            .profilePic(profilePic)
            .build();
//        User tempUser = new User(userId, email, userName, password.toCharArray(), LocalDate.parse(dateOfBirth), status);
        tempUser.setRelationships(relationships);
        
        return tempUser;
    }
    @Override
    protected Map<String,Object> getMapFromRecord(Object user) {
        Map<String,Object> tempUserMap = new HashMap<>();
        User tempUser = (User)user;
        tempUserMap.put("userId", tempUser.getUserId());                        // saved in json as long
        tempUserMap.put("email", tempUser.getEmail());                          // saved in json as string
        tempUserMap.put("userName", tempUser.getUsername());                    // saved in json as string  
        
        tempUserMap.put("password", new String(tempUser.getPassword()));        // saved in json as string
        
        tempUserMap.put("dateOfBirth", tempUser.getDateOfBirth().toString());   // saved in json as string
        tempUserMap.put("bio", tempUser.getBio());                              // saved in json as string
        tempUserMap.put("coverPic", tempUser.getCoverPhoto());                  // saved in json as string
        tempUserMap.put("profilePic", tempUser.getProfilePic());                // saved in json as string
        tempUserMap.put("status", tempUser.isStatus());                         // saved in json as boolean

        JSONObject relation = new JSONObject(tempUser.getRelationships());
        tempUserMap.put("relationships", relation.toJSONString());              // saved in json as string (JSONString)
        return tempUserMap;
    }
//    public void saveToFile() throws FileNotFoundException, IOException, ParseException {    // saves users arraylist into json format file
//        JSONArray jsonUsers = new JSONArray();
//        for (int i = 0; i < users.size(); i++) {
//            jsonUsers.add(getMapFromUser(users.get(i)));
//        }
//        PrintWriter pw = new PrintWriter(getFileName()); 
//        pw.write(jsonUsers.toJSONString()); 
//        pw.flush(); 
//        pw.close(); 
//    }
    public void addUser(User user) throws IOException, FileNotFoundException, ParseException {
        super.addRecord(user);
    }
    public ArrayList<User> getUsers() {
        ArrayList<Object> tempObjects = super.getRecords();
        ArrayList<User> users = new ArrayList<>();
        for (Object tempObject : tempObjects) {
            users.add((User)tempObject);
        }
        return users;
    }
    public User getUserFromId(long userId) {    // returns user object if found and null if not found
        ArrayList<Object> tempObjects = super.getRecords();
        for (int i = 0; i < tempObjects.size(); i++) {
            if (userId == ((User)tempObjects.get(i)).getUserId()) {
                return (User)tempObjects.get(i);
            }
        }
        return null;
    }
    
}

