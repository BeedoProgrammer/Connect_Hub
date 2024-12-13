package Backend;

import Groups.*;
import java.util.*;
import Database.*;
import java.io.*;
import org.json.simple.parser.ParseException;

public class GroupSearch {
    
    private User CurrentUser;
    private GroupDatabase groupDatabase;
    public ArrayList<Group> MyDesiredGroups;  // groups with this search
    
    public GroupSearch (User CurrentUser) throws IOException, FileNotFoundException, ParseException {
        this.CurrentUser = CurrentUser;
        this.groupDatabase = GroupDatabase.getInstance();
        MyDesiredGroups = new ArrayList<>();
    }
    
    public void DesiredGroups(String searchName) {
        ArrayList<Group> groups = groupDatabase.getGroups();
    
        for(int i = 0; i < groups.size(); i++) {
            Group group = groups.get(i);
            if (group.getName().toLowerCase().contains(searchName.toLowerCase()))
                MyDesiredGroups.add(group);
        }        
    }
    
    public ArrayList<Group> joinedGroups() {
        ArrayList<Group> joinedGroups = new ArrayList<>();
        for (int i = 0; i < MyDesiredGroups.size(); i++) {
            long groupID = MyDesiredGroups.get(i).getGroupID();
            GroupDetails status = CurrentUser.getGroupRelationStatus(groupID);
            if(status == GroupDetails.ADMIN || status == GroupDetails.USER || status == GroupDetails.CREATOR)
                joinedGroups.add(MyDesiredGroups.get(i));
        }
        return joinedGroups;
    }
    
    public ArrayList<Group> unjoinedGroups() {
        ArrayList<Group> unjoinedGroups = new ArrayList<>();
        for(int i = 0; i < MyDesiredGroups.size(); i++) {
            long groupID = MyDesiredGroups.get(i).getGroupID();
            GroupDetails status = CurrentUser.getGroupRelationStatus(groupID);
            if (status == GroupDetails.PENDING || status == GroupDetails.BANNED || status == GroupDetails.REMOVED || status == null)
                unjoinedGroups.add(MyDesiredGroups.get(i));
        }
        return unjoinedGroups;
    }
}
