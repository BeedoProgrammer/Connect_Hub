package Backend;

import java.util.*;

public class User {

    private long userId;
    private String email;
    private String username;
    private String password;
    private String dateOfBirth;
    private boolean status;
 private HashMap<Long, FriendshipStatus> relationships;
    public User(long userId, String email, String username, String password, String dateOfBirth, boolean status) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        this.relationships = new HashMap<>();

    }

    public void changeStatus() {
        if (status) {
            status = false;
        } else {
            status = true;
        }
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean isStatus() {
        return status;
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

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

   public HashMap<Long, FriendshipStatus> getRelationships() {
        return relationships;
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
}
