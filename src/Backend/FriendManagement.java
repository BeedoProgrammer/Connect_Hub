package Backend;

import java.util.*;

public class FriendManagement {
    private User userDetails;

    public FriendManagement(User userDetails) {
        this.userDetails = userDetails;
    }
    
    public boolean sendFriendRequest(String message, User friend){
        if("accept".equals(message)){
            userDetails.getFriendUserId().add(friend.getUserId());
            friend.getFriendUserId().add(userDetails.getUserId());
            return true;
        }
        else
            return false;
    } 
}
