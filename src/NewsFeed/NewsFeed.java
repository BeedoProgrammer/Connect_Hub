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
    ArrayList<Content> postList;
    ArrayList<Content> storyList;
    ArrayList<User> friendList;
    ArrayList<User> friendSuggestions;
    Loader dataLoader;
    
    public NewsFeed() {
        load();
    }
    
    public void load(){
        dataLoader = new Loader();
        postList = dataLoader.getPosts();
        storyList = dataLoader.getSories();
        friendList = dataLoader.getFriends();
        friendSuggestions = dataLoader.getSuggestions();
    }
    
    public void refresh(){
        load();
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
        
        public ArrayList<Content> getPosts(){
            return null;
        }
        public ArrayList<Content> getSories(){
            return null;
        }
        public ArrayList<User> getFriends(){
            return null;
        }
        public ArrayList<User> getSuggestions(){
            return null;
        }
    }
}
