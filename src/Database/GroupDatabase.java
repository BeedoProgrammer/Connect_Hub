package Database;
import Groups.Group;
import com.google.gson.*;
import java.io.*;
import java.util.*;
import org.json.simple.parser.ParseException;

public class GroupDatabase extends Database {
    private static GroupDatabase instance = null;
    public static GroupDatabase getInstance() throws IOException, FileNotFoundException, ParseException {
        if (instance == null) {
            instance = new GroupDatabase("files/groups.json");
            instance.readFromFile();
        }
        return instance;
    }
    public GroupDatabase(String fileName) {
        super(fileName);
    }

    @Override
    protected Map<String, Object> getMapFromRecord(Object group) {
        Map<String,Object> tempGroupMap = new HashMap<>();
        Group tempGroup = (Group)group;
        tempGroupMap.put("grouId", tempGroup.getGroupID());
        tempGroupMap.put("groupName", tempGroup.getName());
        tempGroupMap.put("groupPhoto", tempGroup.getGroupPhoto());
        tempGroupMap.put("groupDescription", tempGroup.getDescription());
        
            Gson gson = new Gson();
            JsonArray jsonArray = gson.toJsonTree(tempGroup.getUsers()).getAsJsonArray();
            tempGroupMap.put("userIds", jsonArray.toString());
        
        return tempGroupMap;
    }

    @Override
    protected Group getRecordFromMap(Map<String, Object> mapOfGroup) {
        long groupId = (long)mapOfGroup.get("grouId");
        String groupName = (String)mapOfGroup.get("groupName");
        String groupPhoto = (String)mapOfGroup.get("groupPhoto");
        String groupDescription = (String)mapOfGroup.get("groupDescription");
        // casting arraylist of users to a Group
            Gson gson = new Gson();
            JsonArray jsonArray = JsonParser.parseString((String)mapOfGroup.get("userIds")).getAsJsonArray();
            ArrayList<Double> usersDouble = gson.fromJson(jsonArray, ArrayList.class);
            ArrayList<Long> usersLong = new ArrayList<>();
            for (Double d : usersDouble) {
                usersLong.add(d.longValue());
            }
        Group tempGroup = new Group.GroupBuilder(groupId, groupName)
                .groupPhoto(groupPhoto)
                .description(groupDescription)
                .build();
        tempGroup.setUsers(usersLong);
        return tempGroup;

    }
    public void addGroup(Group group) throws IOException, FileNotFoundException, ParseException {
        super.addRecord(group);
    }
    public ArrayList<Group> getGroups() {
        ArrayList<Object> tempObjects = super.getRecords();
        ArrayList<Group> groups = new ArrayList<>();
        for (Object tempObject : tempObjects) {
            groups.add((Group)tempObject);
        }
        return groups;
    }
    public Group getGroupFromId(long groupId) {
        ArrayList<Object> tempObjects = super.getRecords();
        for (int i = 0; i < tempObjects.size(); i++) {
            if (groupId == ((Group)tempObjects.get(i)).getGroupID()) {
                return (Group)tempObjects.get(i);
            }
        }
        return null;
    }
    void modifyGroup(Group group) throws IOException, FileNotFoundException, ParseException {
        ArrayList<Object> records = super.getRecords();
        for (int i = 0; i < records.size(); i++) {
            if (group.getGroupID() == ((Group)records.get(i)).getGroupID()) {
                records.set(i, group);
                saveToFile();
                return;
            }
        }
    }
    void removeGroup(Group group) throws IOException, FileNotFoundException, ParseException {
        ArrayList<Object> records = super.getRecords();
        for (int i = 0; i < records.size(); i++) {
            if (group.getGroupID()== ((Group)records.get(i)).getGroupID()) {
                records.remove(i);
                saveToFile();
                return;
            }
        }
    }
}
