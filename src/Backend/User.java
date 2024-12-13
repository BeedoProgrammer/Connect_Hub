package Backend;

import Database.GroupDatabase;
import Groups.*;
import Groups.Group.GroupBuilder;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.*;
import java.util.*;
import org.json.simple.parser.ParseException;

public class User {

    private long userId;
    private String email;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private boolean status;
    private String profilePic;
    private String coverPhoto;
    private String bio;
    private HashMap<Long, FriendshipStatus> relationships;
    private HashMap<Long, GroupDetails> groupRelation;

    private User(UserBuilder builder) {
        this.userId = builder.userId;
        this.email = builder.email;
        this.username = builder.username;
        this.password = builder.password;
        this.dateOfBirth = builder.dateOfBirth;
        this.status = builder.status;
        this.profilePic = builder.profilePic;
        this.coverPhoto = builder.coverPhoto;
        this.bio = builder.bio;
        this.relationships = builder.relationships;
        this.groupRelation = builder.groupRelation;
    }
    
    public void changeStatus(){
        status = !status;
    }

    public String getBio() {
        return bio;
    }
    
    public long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public boolean isStatus() {
        return status;
    }
    
    public HashMap<Long, FriendshipStatus> getRelationships() {
        return relationships;
    }

    public HashMap<Long, GroupDetails> getGroupRelation() {
        return groupRelation;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public void setRelationships(HashMap<Long, FriendshipStatus> relationships) {
        this.relationships = relationships;
    }

    public void setGroupRelation(HashMap<Long, GroupDetails> groupRelation) {
        this.groupRelation = groupRelation;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
    
    public void addRelationship(long userId, FriendshipStatus status) {
        this.relationships.put(userId, status);
    }
    
    public boolean hasRelationshipWith(long userId) {
        return this.relationships.containsKey(userId);
    }
       
    public FriendshipStatus getRelationshipStatus(long userId) {
        return this.relationships.get(userId);
    }
     
    public void removeRelationship(long userId) {  // no longer both exist in each other hashmaps
        this.relationships.remove(userId);
    }
    
    public void addGroupRelation(long userID, GroupDetails groupDetails){
        groupRelation.put(userID, groupDetails);
    }
    
    public GroupDetails getGroupRelationStatus(long groupID){
        return groupRelation.get(groupID);
    }
    
    public boolean hasGroupRelation(long groupID){
        return groupRelation.containsKey(groupID);
    }
    
    public void deleteGroupRelation(long groupID){
        groupRelation.remove(groupID);
    }
    
     public Group createGroup(String name) throws IOException, FileNotFoundException, ParseException{
        GroupDatabase groupDatabase = GroupDatabase.getInstance();
        Random random = new Random();
        long uniqueId;
                    
        do {
            uniqueId = Math.abs(random.nextLong());
        }while(groupDatabase.getGroupFromId(uniqueId) != null);
        
        Group group = new GroupBuilder(uniqueId, name).build();
        this.addGroupRelation(this.getUserId(), GroupDetails.CREATOR);
        
        return group;
    }

    public static class UserBuilder {
        private long userId;
        private String email;
        private String username;
        private String password;
        private LocalDate dateOfBirth;
        private boolean status;
        private HashMap<Long, FriendshipStatus> relationships = new HashMap<>();
        private HashMap<Long, GroupDetails> groupRelation = new HashMap<>();
        private String profilePic = "";
        private String coverPhoto = "";
        private String bio = "";

        public UserBuilder(long userId, String email, String username, String password, LocalDate dateOfBirth, boolean status) {
            this.userId = userId;
            this.email = email;
            this.username = username;
            this.password = password;
            this.dateOfBirth = dateOfBirth;
            this.status = status;
        }
    
        public UserBuilder bio(String bio) {
            this.bio = bio;
            return this;
        }

        public UserBuilder profilePic(String profilePic) {
            this.profilePic = profilePic;
            return this;
        }

        public UserBuilder coverPhoto(String coverPhoto) {
            this.coverPhoto = coverPhoto;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
