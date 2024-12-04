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
    private String profilePhoto;
    private ArrayList<Long> friendUserId;

    public User(long userId, String email, String username, char[] password, LocalDate dateOfBirth, boolean status) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.status = status;
        friendUserId = new ArrayList<>();
    }
    
    public void changeStatus(){
        if(status)
            status = false;
        else
            status = true;
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

    public ArrayList<Long> getFriendUserId() {
        return friendUserId;
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

    public void setPassword(char [] password) {
        this.password = password;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setFriendUserId(ArrayList<Long> friendUserId) {
        this.friendUserId = friendUserId;
    }
}
