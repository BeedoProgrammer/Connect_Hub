package Backend;

import Database.UserDatabase;
import Database.StoryDatabase;
import Database.PostDatabase;
import java.util.*;

public class ProfileManagement {
    private PostDatabase postDatabase;
    private UserDatabase userDatabase;
    private StoryDatabase storyDatabase;
    private User currentUser;
    
    public ProfileManagement(User currentUser) {
        this.currentUser = currentUser;
        try{
            postDatabase = PostDatabase.getInstance();
            userDatabase = UserDatabase.getInstance();
            storyDatabase = StoryDatabase.getInstance();
        }catch(Exception e){}
    }
    
    public ArrayList<Content> getPosts(){
        ArrayList<Content> postData = postDatabase.getPosts();
        ArrayList<Content> posts = new ArrayList<>();
        
        for(int i = 0; i < postData.size(); i++){
            if(postData.get(i).getAuthorId() == currentUser.getUserId())
                posts.add(postData.get(i));
        }
        
        return posts;
    }
    
    public ArrayList<Content> getStories(){
        ArrayList<Content> storyData = storyDatabase.getStories();
        ArrayList<Content> stories = new ArrayList<>();
        
        for(int i = 0; i < storyData.size(); i++){
            if(storyData.get(i).getAuthorId() == currentUser.getUserId())
                stories.add(storyData.get(i));
        }
        
        return stories;
    }
    
   public ArrayList<User> getListOfFriends(){
       ArrayList<User> userData = userDatabase.getUsers();
       ArrayList<User> friends = new ArrayList<>();
       
        for(int i = 0; i < userData.size(); i++){
           if(currentUser.getRelationshipStatus(userData.get(i).getUserId()) == FriendshipStatus.ACCEPTED)
               friends.add(userData.get(i));
        }
       
       return friends;
    }
}

