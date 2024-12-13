package Groups;

import Backend.*;
import Database.*;
import java.io.*;
import java.util.*;
import org.json.simple.parser.ParseException;

public class Group {
    private long groupID;
    private String name;
    private String groupPhoto;
    private String description;
    private ArrayList<Long> users;

    private Group(GroupBuilder builder, ArrayList<Long> users) {
        this.groupID = builder.groupID;
        this.name = builder.name;
        this.groupPhoto = builder.groupPhoto;
        this.description = builder.description;
        this.users = users;
    }

    public long getGroupID() {
        return groupID;
    }

    public String getName() {
        return name;
    }

    public String getGroupPhoto() {
        return groupPhoto;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Long> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Long> users) {
        this.users = users;
    }

    public void setName(String name, User moderator) {
        GroupDetails groupRelationStatus = moderator.getGroupRelationStatus(groupID);
        if(groupRelationStatus == GroupDetails.ADMIN || groupRelationStatus == GroupDetails.CREATOR)
            this.name = name;
    }

    public void setGroupPhoto(String groupPhoto, User moderator) {
        GroupDetails groupRelationStatus = moderator.getGroupRelationStatus(groupID);
        if(groupRelationStatus == GroupDetails.ADMIN || groupRelationStatus == GroupDetails.CREATOR)
            this.groupPhoto = groupPhoto;
    }

    public void setDescription(String description, User moderator) {
        GroupDetails groupRelationStatus = moderator.getGroupRelationStatus(groupID);
        if(groupRelationStatus == GroupDetails.ADMIN || groupRelationStatus == GroupDetails.CREATOR)
            this.description = description;
    }
    
    public void addUser(User user, User moderator){
        GroupDetails groupRelationStatus = moderator.getGroupRelationStatus(groupID);
        if(user.getGroupRelationStatus(groupID) == GroupDetails.PENDING && (groupRelationStatus == GroupDetails.ADMIN || groupRelationStatus == GroupDetails.CREATOR)){
            user.deleteGroupRelation(groupID);
            user.addGroupRelation(groupID, GroupDetails.USER);
            users.add(user.getUserId());
        }
    }
    
    public void removeUser(User user, User moderator){
        GroupDetails groupRelationStatus = moderator.getGroupRelationStatus(groupID);
        if(users.contains(user.getUserId()) && (groupRelationStatus == GroupDetails.ADMIN || groupRelationStatus == GroupDetails.CREATOR)){
            user.deleteGroupRelation(groupID);
            user.addGroupRelation(groupID, GroupDetails.REMOVED);
            users.remove(user.getUserId());
        }   
    }
    
    public void banUser(User user, User moderator){
        GroupDetails groupRelationStatus = moderator.getGroupRelationStatus(groupID);
        if(user.getGroupRelationStatus(groupID) == GroupDetails.USER && (groupRelationStatus == GroupDetails.ADMIN || groupRelationStatus == GroupDetails.CREATOR)){
            user.deleteGroupRelation(groupID);
            user.addGroupRelation(groupID, GroupDetails.BANNED);
            users.remove(user.getUserId());
        }
    }
    
    public void leaveGroup(User user){
        GroupDetails groupRelationStatus = user.getGroupRelationStatus(groupID);
        if(groupRelationStatus == GroupDetails.ADMIN || groupRelationStatus == GroupDetails.CREATOR || groupRelationStatus == GroupDetails.USER){
            user.deleteGroupRelation(groupID);
            user.addGroupRelation(groupID, GroupDetails.REMOVED);
            users.remove(user.getUserId());
        }
    }
    
    public Group createGroup(String name, User creater) throws IOException, FileNotFoundException, ParseException{
        GroupDatabase groupDatabase = GroupDatabase.getInstance();
        Random random = new Random();
        long uniqueId;
                    
        do {
            uniqueId = Math.abs(random.nextLong());
        }while(groupDatabase.getGroupFromId(uniqueId) != null);
        
        Group group = new GroupBuilder(uniqueId, name).build();
        creater.addGroupRelation(uniqueId, GroupDetails.CREATOR);
        
        return group;
    }
    
    public static class GroupBuilder {
        private long groupID;
        private String name;
        private String groupPhoto = "";
        private String description = "";
        private ArrayList<Long> users = new ArrayList<>();

        public GroupBuilder(long groupID, String name) {
            this.groupID = groupID;
            this.name = name;
        }
        public GroupBuilder(long groupID, String name, User creator) {
            this.groupID = groupID;
            this.name = name;
            users.add(creator.getUserId());
        }

        public ArrayList<Long> getUsers() {
            return users;
        }
        
        public GroupBuilder groupPhoto(String groupPhoto) {
            this.groupPhoto = groupPhoto;
            return this;
        }

        public GroupBuilder description(String description) {
            this.description = description;
            return this;
        }
        
        public Group build() {
            return new Group(this,this.users);
        }
    }
}
