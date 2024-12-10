/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class UserSearch {
    private User CurrentUser;
  private  ArrayList <User> SystemUsers;
 private   ArrayList<User> SearchResults;
    public UserSearch (User CurrentUser ,ArrayList<User> SystemUsers)
    {
        this.CurrentUser=CurrentUser;
        this.SystemUsers=SystemUsers;
    }
  public void DesiredPeople(String searchName) {
        ArrayList<User> desiredUsers = new ArrayList<>();
        for (User user : SystemUsers) {
            // Check if the username contains the search term (case-insensitive)
            if (user.getUsername().toLowerCase().contains(searchName.toLowerCase())) {
                desiredUsers.add(user);
            }
        }
        SearchResults=desiredUsers;
      
    
  }  

    public ArrayList<User> getSearchResults() {
        return SearchResults;
    }
  
   public ArrayList<User> getAcceptedFriends() {
        ArrayList<User> acceptedFriends = new ArrayList<>();
        for (User user : SystemUsers) {
            if (CurrentUser.getRelationshipStatus(user.getUserId()) == FriendshipStatus.ACCEPTED) {
                acceptedFriends.add(user);
            }
        }
        return acceptedFriends;
    }
     public ArrayList<User> getUsersWithNoRelationship() {
        ArrayList<User> noRelationshipUsers = new ArrayList<>();
        for (User user : SystemUsers) {
            if (!CurrentUser.hasRelationshipWith(user.getUserId())) {
                noRelationshipUsers.add(user);
            }
        }
        return noRelationshipUsers;
    }
     public ArrayList<User> getPendingFriends() {
    ArrayList<User> pendingFriends = new ArrayList<>();
    for (User user : SystemUsers) {
        FriendshipStatus status = CurrentUser.getRelationshipStatus(user.getUserId());
        if (status != null && 
           (status == FriendshipStatus.PENDINGSENDER || status == FriendshipStatus.PENDINGRECIEVER)) {
            pendingFriends.add(user);
        }
    }
    return pendingFriends;
}

}
