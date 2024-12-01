package Backend;

import java.util.*;

public class FriendManagement {
    private User userDetails;

    public FriendManagement(User userDetails) {
        this.userDetails = userDetails;
    }
    
    public boolean sendFriendRequest(String message){
        if("accept".equals(message))
            return true;
        else
            return false;
    }
    
    public void addFriend(User friend){
        userDetails.getFriendUserId().add(friend.getUserId());
        friend.getFriendUserId().add(userDetails.getUserId());
    } 
}
