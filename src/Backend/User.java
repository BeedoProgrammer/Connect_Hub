package Backend;

import java.time.*;
import java.util.*;

public class User {

    private long userId;
    private String email;
    private String username;
    private char[] password;
    private LocalDate dateOfBirth;
    private boolean status;
    private String profilePic;
    private String coverPhoto;
    private String bio;
    private HashMap<Long, FriendshipStatus> relationships;

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

    public char[] getPassword() {
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

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(char [] password) {
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
    
    public static class UserBuilder{
        private long userId;
        private String email;
        private String username;
        private char[] password;
        private LocalDate dateOfBirth;
        private boolean status;
        private HashMap<Long, FriendshipStatus> relationships = new HashMap<>();
        private String profilePic = "";
        private String coverPhoto = "";
        private String bio = "";

        public UserBuilder(long userId, String email, String username, char[] password, LocalDate dateOfBirth, boolean status) {
            this.userId = userId;
            this.email = email;
            this.username = username;
            this.password = password;
            this.dateOfBirth = dateOfBirth;
            this.status = status;
        }
        
        public UserBuilder bio(String bio){
            this.bio = bio;
            return this;
        }
        
        public UserBuilder profilePic(String profilePic){
            this.profilePic = profilePic;
            return this;
        }
        
        public UserBuilder coverPhoto(String coverPhoto){
            this.coverPhoto = coverPhoto;
            return this;
        }
        
        public User build(){
            return new User(this);
        }
    }
}
