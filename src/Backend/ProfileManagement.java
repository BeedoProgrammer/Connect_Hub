package Backend;

import java.util.*;

public class ProfileManagement {
    private String profilePhoto;
    private String coverPhoto;
    private String bio; 
    private User userDetails;
    private ArrayList<User> friends;
    private PostDatabase postDatabase; 
    private UserDatabase userDatabase;
    private StoryDatabase storyDatabase;

    public ProfileManagement(String profilePhoto, String coverPhoto, String bio, User userDetails, PostDatabase postDatabase, UserDatabase userDatabase, StoryDatabase storyDatabase) {
        this.profilePhoto = profilePhoto;
        this.coverPhoto = coverPhoto;
        this.bio = bio;
        this.userDetails = userDetails;
        this.postDatabase = postDatabase;
        this.userDatabase = userDatabase;
        this.storyDatabase = storyDatabase;
    }
    
    public ArrayList<Post> getPosts(){
        ArrayList<Post> postData = postDatabase.getAllData();
        ArrayList<Post> posts = new ArrayList<>();
        
        for(int i = 0; i < postData.size(); i++){
            if(postData.get(i).getAuthorId() == userDetails.getUserId())
                posts.add(postData.get(i));
        }
        
        return posts;
    }
    
    public ArrayList<Story> getStories(){
        ArrayList<Story> storyData = storyDatabase.getAllData();
        ArrayList<Story> stories = new ArrayList<>();
        
        for(int i = 0; i < storyData.size(); i++){
            if(storyData.get(i).getAuthorId() == userDetails.getUserId())
                stories.add(storyData.get(i));
        }
        
        return stories;
    }
    
   public ArrayList<User> getListOfFriends(){
       ArrayList<User> userData = userDatabase.getAllData();
        for(int i = 0; i < userData.size(); i++){
            if(userDetails.getFriendUserId().contains(userData.get(i).getUserId()) && userData.get(i).getFriendUserId().contains(userDetails.getUserId()))
                friends.add(userData.get(i));
        }
        
        return friends;
    }
}
