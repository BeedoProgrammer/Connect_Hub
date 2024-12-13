/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Groups;

import Backend.*;
import Database.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Abdel
 */
public class GroupWindowManager {
    Group myGroup;
    ArrayList<Content> myPosts;
    ArrayList<Content> myStories;
    ArrayList<User> groupMembers;
    ArrayList<User> pendingMembers;
    Loader dataLoader;

    public GroupWindowManager(Group myGroup) {
        this.myGroup = myGroup;
        myPosts = new ArrayList<>();
        myStories = new ArrayList<>();
        groupMembers = new ArrayList<>();
        pendingMembers = new ArrayList<>();
        dataLoader = new Loader();
        
        load();
    }
    
    public void load(){
        dataLoader.update();
        updateMembers();
        updatePending();
        updatePosts();
        updateStories();
    }
    
    public void acceptPending(User acceptedUser){
        if(acceptedUser.getGroupRelationStatus(myGroup.getGroupID()) == GroupDetails.PENDING){
            acceptedUser.deleteGroupRelation(myGroup.getGroupID());
            acceptedUser.addGroupRelation(myGroup.getGroupID(), GroupDetails.USER);
        }
    }
    
    public void updateMembers(){
        groupMembers = dataLoader.getMembers(myGroup.getGroupID());
    }
    
    public void updatePending(){
        pendingMembers.clear();
        ArrayList<User> allUsers = dataLoader.getUsers();
        for(User i : allUsers){
            if(i.getGroupRelationStatus(myGroup.getGroupID()) == GroupDetails.PENDING){
                pendingMembers.add(i);
            }
        }
    }
    
    public void updatePosts(){
        myPosts.clear();
        ArrayList<Content> allPosts = dataLoader.getPosts();
        for(Content i : allPosts){
            if(i.getGroupID() == myGroup.getGroupID()){
                myPosts.add(i);
            }
        }
    }
    
    public void updateStories(){
        myStories.clear();
        ArrayList<Content> allStories = dataLoader.getSories();
        for(Content i : allStories){
            if(i.getGroupID() == myGroup.getGroupID()){
                myStories.add(i);
            }
        }
    }
    
    public ArrayList<User> getMembers(){
        return groupMembers;
    }

    public ArrayList<User> getPending(){
        return pendingMembers;
    }
    
    public ArrayList<Content> getPosts(){
        return myPosts;
    }
    
    public ArrayList<Content> getStories(){
        return myStories;
    }
    
    class Loader{
        private PostDatabase postDatabase;
        private StoryDatabase storyDatabase;
        private GroupDatabase groupDatabase;
        private UserDatabase userDatabase;
        
        public Loader() {
            update();
        }
        
        private void update(){
            try {
                postDatabase = PostDatabase.getInstance();
                storyDatabase = StoryDatabase.getInstance();
                userDatabase = UserDatabase.getInstance();
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
        
        public ArrayList<User> getMembers(long groupID){
            ArrayList<Long> userIDs = this.groupDatabase.getGroupFromId(groupID).getUsers();
            ArrayList<User> users = new ArrayList<>();
            for(Long i : userIDs){
                users.add(userDatabase.getUserFromId(i));
            }
            return users;
        }
        
        public ArrayList<User> getUsers(){
            return this.userDatabase.getUsers();
        }
        
        public User getCurrentUser(long userID){
            return this.userDatabase.getUserFromId(userID);
        }
    }
}
