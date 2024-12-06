/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NewsFeed;

import Backend.*;
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
    private User currentUser;
    private Loader dataLoader;
    
    public NewsFeed(User current) {
        this.friendSuggestions = new ArrayList<>();
        this.friendList = new ArrayList<>();
        this.currentUser = current;
        refresh();
    }
    
    public void refresh(){
        load();
        assignUsers();
    }
    
    public void load(){
        dataLoader = new Loader();
        postList = dataLoader.getPosts();
        storyList = dataLoader.getSories();
        allUsers = dataLoader.getUsers();
    }
    
    public void assignUsers(){
        for (User user : this.allUsers) { 
            if (!currentUser.hasRelationshipWith(user.getUserId()) && currentUser.getUserId() != user.getUserId()) {
                this.friendSuggestions.add(user); 
                System.out.println("Suggestions: " + user.getUsername());
            }else if(currentUser.getRelationshipStatus(user.getUserId()) == FriendshipStatus.ACCEPTED){
                this.friendList.add(user);
                System.out.println("Friends: " + user.getUsername());
            }
        }
    }
    
    public User getCurrentUser() {
        return currentUser;
    }

    public ArrayList<Content> getPostList() {
        return postList;
    }

    public ArrayList<Content> getStoryList() {
        return storyList;
    }

    public ArrayList<User> getFriendList() {
        this.friendList.add(this.allUsers.get(0));
        this.friendList.add(this.allUsers.get(1));
        this.friendList.add(this.allUsers.get(0));
        this.friendList.add(this.allUsers.get(1));
        this.friendList.add(this.allUsers.get(0));
        this.friendList.add(this.allUsers.get(1));
        this.friendList.add(this.allUsers.get(0));
        this.friendList.add(this.allUsers.get(1));
        this.friendList.add(this.allUsers.get(0));
        this.friendList.add(this.allUsers.get(1));
        this.friendList.add(this.allUsers.get(0));
        this.friendList.add(this.allUsers.get(1));
        return friendList;
    }

    public ArrayList<User> getFriendSuggestions() {
        return friendSuggestions;
    }
    
    class Loader{
        private UserDatabase userDatabase;
        private PostDatabase postDatabase;
        private StoryDatabase storyDatabase;
        
        public Loader() {
            userDatabase = UserDatabase.getInstance();
            postDatabase = PostDatabase.getInstance();
            storyDatabase = StoryDatabase.getInstance();
            try {
                userDatabase.readFromFile();
                postDatabase.readFromFile();
                storyDatabase.readFromFile();
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
    }
}
