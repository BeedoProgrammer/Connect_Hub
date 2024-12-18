/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NewsFeed;

import Database.UserDatabase;
import Database.StoryDatabase;
import Database.PostDatabase;
import Backend.*;
import Database.GroupDatabase;
import Groups.Group;
import Groups.GroupDetails;
import java.util.*;

/**
 *
 * @author Abdel
 */
public class NewsFeed {
    private ArrayList<Content> postList;
    private ArrayList<Content> storyList;
    private ArrayList<User> allUsers;
    private ArrayList<User> friendList;
    private ArrayList<User> friendSuggestions;
    private ArrayList<Group> groupsList;
    private User currentUser;
    private Loader dataLoader;
    
    public NewsFeed(long userID) {
        this.friendSuggestions = new ArrayList<>();
        this.friendList = new ArrayList<>();
        this.postList = new ArrayList<>();
        this.storyList = new ArrayList<>();
        this.groupsList = new ArrayList<>();
        refresh(userID);
    }
    
    public void refresh(long userID){
        load(userID);
        assignUsers();
        
    }
    
    public void load(long userID){
        dataLoader = new Loader();
        currentUser = dataLoader.getCurrentUser(userID);
        allUsers = dataLoader.getUsers();
        updatePostList(dataLoader);
        updateStoryList(dataLoader);
        updateGroupList(dataLoader);
    }
    
    public void assignUsers(){
        for (User user : this.allUsers) {
            if (!currentUser.hasRelationshipWith(user.getUserId()) && currentUser.getUserId() != user.getUserId()) {
                this.friendSuggestions.add(user); 
            }else if(currentUser.getRelationshipStatus(user.getUserId()) == FriendshipStatus.ACCEPTED){
                this.friendList.add(user);
            }
        }
    }
    
    public User getCurrentUser() {
        return currentUser;
    }

    private boolean doShowContent(Content myContent){
        if(currentUser.getRelationshipStatus(myContent.getAuthorId()) == FriendshipStatus.ACCEPTED){
            return true;
        }
        return false;
    }
    
    private void  updateGroupList(Loader dataLoader) {
        ArrayList<Group> tempList = dataLoader.getGroups();
        for (Group group : tempList) {
            GroupDetails relationWithGroup = this.currentUser.getGroupRelationStatus(group.getGroupID());
            if (relationWithGroup == GroupDetails.CREATOR || relationWithGroup == GroupDetails.ADMIN || relationWithGroup == GroupDetails.USER) {
                this.groupsList.add(group);
            }
        }
    }
    
    private void updatePostList(Loader dataLoader){
        ArrayList<Content> tempList = dataLoader.getPosts();
        for(Content i : tempList){
            if(doShowContent(i)){
                this.postList.add(i);
            }
        }
    }
    
    private void updateStoryList(Loader dataLoader){
        ArrayList<Content> tempList = dataLoader.getSories();
        for(Content i : tempList){
            if(doShowContent(i)){
                this.storyList.add(i);
            }
        }
    }

    public ArrayList<Group> getGroupsList() {
        
        return groupsList;
    }
    
    public ArrayList<Content> getPostList() {
        return postList;
    }

    public ArrayList<Content> getStoryList() {
        return storyList;
    }

    public ArrayList<User> getFriendList() {
        return friendList;
    }

    public ArrayList<User> getFriendSuggestions() {
        return friendSuggestions;
    }
    
    class Loader{
        private UserDatabase userDatabase;
        private PostDatabase postDatabase;
        private StoryDatabase storyDatabase;
        private GroupDatabase groupDatabase;
        
        public Loader() {
            try {
                userDatabase = UserDatabase.getInstance();
                postDatabase = PostDatabase.getInstance();
                storyDatabase = StoryDatabase.getInstance();
                groupDatabase = GroupDatabase.getInstance();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        public ArrayList<Content> getPosts(){
            return this.postDatabase.getPosts();
        }
        public ArrayList<Content> getSories(){
            return this.storyDatabase.getStories();
        }
        public ArrayList<User> getUsers(){
            return this.userDatabase.getUsers();
        }
        public ArrayList<Group> getGroups(){
            return this.groupDatabase.getGroups();
        }
        
        public User getCurrentUser(long userID){
            return this.userDatabase.getUserFromId(userID);
        }
    }
}
