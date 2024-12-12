package Backend;

import java.util.*;

public class Group {
    private long groupID;
    private String name;
    private String groupPhoto;
    private String describtion;
    private ArrayList<Long> users = new ArrayList<>();

    public Group(long groupID, String name, String groupPhoto, String describtion) {
        this.groupID = groupID;
        this.name = name;
        this.groupPhoto = groupPhoto;
        this.describtion = describtion;
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

    public String getDescribtion() {
        return describtion;
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

    public void setDescribtion(String describtion, User moderator) {
        GroupDetails groupRelationStatus = moderator.getGroupRelationStatus(groupID);
        if(groupRelationStatus == GroupDetails.ADMIN || groupRelationStatus == GroupDetails.CREATOR)
            this.describtion = describtion;
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
}
