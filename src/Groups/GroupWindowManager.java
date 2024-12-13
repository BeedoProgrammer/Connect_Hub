/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Groups;

import Backend.*;
import Database.*;
import java.util.ArrayList;

/**
 *
 * @author Abdel
 */
public class GroupWindowManager {
    Group myGroup;
    ArrayList<Content> myPosts;
    ArrayList<Content> myStories;
    ArrayList<User> groupMembers;
    Loader dataLoader;

    public GroupWindowManager(Group myGroup) {
        this.myGroup = myGroup;
        myPosts = new ArrayList<>();
        myStories = new ArrayList<>();
        groupMembers = new ArrayList<>();
        dataLoader = new Loader();
        
        
    }
    
    public ArrayList<User> getMembers(){
        return groupMembers;
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
        
        public ArrayList<User> getUsers(long groupID){
            ArrayList<Long> userIDs = this.groupDatabase.getGroupFromId(groupID).getUsers();
            ArrayList<User> users = new ArrayList<>();
            for(Long i : userIDs){
                users.add(userDatabase.getUserFromId(i));
            }
            return users;
        }
        
        public User getCurrentUser(long userID){
            return this.userDatabase.getUserFromId(userID);
        }
    }
}
