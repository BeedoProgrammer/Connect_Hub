package Backend;

import java.time.*;
import java.util.*;

    public class User {

        private long userId;
        private String email;
        private String username;
        private char[] password;
        private LocalDate dateOfBirth;
        private String profilePic;
        private String CoverPhoto;
        private String bio;
        private boolean status;
        private HashMap<Long, FriendshipStatus> relationships;
        private UserDatabase userDatabase;

    public User(String email, String username, char[] password, LocalDate dateOfBirth, UserDatabase userDatabase) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.userDatabase = userDatabase;
        relationships = new HashMap<>();
        userId = generateID();
        bio = "";
        profilePic = "";
        CoverPhoto = "";
        status = true;
    }

    private long generateID(){
        ArrayList<User> userData = userDatabase.getUsers();
        boolean flag = true;
        Random random = new Random();
        long uniqueId;

        do {
            uniqueId = Math.abs(random.nextLong());
            boolean isUnique = true;
            
            for(int i = 0; i < userData.size(); i++){
                if (uniqueId == userData.get(i).getUserId()){
                    isUnique = false;
                    break;
                }
            }

            if(isUnique)
                break;    
        }while (true);

        return uniqueId;
    }
    
    public void changeStatus(){
        if(status)
            status = false;
        else 
            status = true;
    }

    public String getBio() {
        return bio;
    }

    public UserDatabase getUserDatabase() {
        return userDatabase;
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
        return CoverPhoto;
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

    public void setCoverPhoto(String CoverPhoto) {
        this.CoverPhoto = CoverPhoto;
    }

    public void setRelationships(HashMap<Long, FriendshipStatus> relationships) {
        this.relationships = relationships;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setUserDatabase(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
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
}
