
package Backend;

import java.util.*;

public class ProfileManagement {
    private User userDetails;
    private ArrayList<User> friends;
    private PostDatabase postDatabase; 
    private UserDatabase userDatabase;
    private StoryDatabase storyDatabase;

    public ProfileManagement(User userDetails, ArrayList<User> friends, PostDatabase postDatabase, UserDatabase userDatabase, StoryDatabase storyDatabase) {
        this.userDetails = userDetails;
        this.friends = friends;
        this.postDatabase = postDatabase;
        this.userDatabase = userDatabase;
        this.storyDatabase = storyDatabase;
    }
    
    public ArrayList<Post> getPosts(){
        ArrayList<Post> postData = postDatabase.getPosts();
        ArrayList<Post> posts = new ArrayList<>();
        
        for(int i = 0; i < postData.size(); i++){
            if(postData.get(i).getAuthorId() == userDetails.getUserId())
                posts.add(postData.get(i));
        }
        
        return posts;
    }
    
    public ArrayList<Story> getStories(){
        ArrayList<Story> storyData = storyDatabase.getStories();
        ArrayList<Story> stories = new ArrayList<>();
        
        for(int i = 0; i < storyData.size(); i++){
            if(storyData.get(i).getAuthorId() == userDetails.getUserId())
                stories.add(storyData.get(i));
        }
        
        return stories;
    }
    
   public ArrayList<User> getListOfFriends(){
       ArrayList<User> userData = userDatabase.getUsers();
       ArrayList<User> friends = new ArrayList<>();
       
        for(int i = 0; i < userData.size(); i++){
           if(userDetails.getRelationshipStatus(userData.get(i).getUserId()) == FriendshipStatus.ACCEPTED)
               friends.add(userData.get(i));
        }
       
       return friends;
    }
}

