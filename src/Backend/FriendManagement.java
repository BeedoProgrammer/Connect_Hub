package Backend;

import java.util.*;

public class FriendManagement {
    public static void sendFriendRequest(User sender, User recipient) { // if this occured means than we dont have relation
        sender.addRelationship(recipient.getUserId(), FriendshipStatus.PENDINGSENDER);
        recipient.addRelationship(sender.getUserId(), FriendshipStatus.PENDINGRECIEVER);

    }

    public static void declineFriendRequest(User sender, User recipient) { ///---------------
     
      //recipient.getRelationshipStatus(sender.getUserId()) == FriendshipStatus.PENDINGRECIEVER) 
             recipient.removeRelationship(sender.getUserId());
             sender.removeRelationship(recipient.getUserId());
        
        
    
    }

    public static void acceptFriendRequest(User sender, User recipient) {  ///-----------------
        // reciever is pending with sender
//        if ( recipient.getRelationshipStatus(sender.getUserId()) == FriendshipStatus.PENDINGRECIEVER) {
            sender.addRelationship(recipient.getUserId(), FriendshipStatus.ACCEPTED);
            recipient.addRelationship(sender.getUserId(), FriendshipStatus.ACCEPTED);
//            return true; //  accepted
//        }
//        return false; //we didnt have a pending relation
    }

    public static void blockFriend(User sender, User recipient) {
//        // If no existing relationship or already blocked or pending , he needs to be in my friend list
//        if (!recipient.hasRelationshipWith(sender.getUserId())|| recipient.getRelationshipStatus(sender.getUserId())!=FriendshipStatus.ACCEPTED) {
//            return false;
//        }
        sender.addRelationship(recipient.getUserId(), FriendshipStatus.BLOCKED);
        recipient.addRelationship(sender.getUserId(), FriendshipStatus.BLOCKED);
      //  return true; //  blocked
    }

public static void removeFriend(User sender, User recipient) {
    // If no existing relationship, or existing but not accepted
//    if (recipient.getRelationshipStatus(sender.getUserId())==FriendshipStatus.ACCEPTED) {
        recipient.removeRelationship(sender.getUserId());
    sender.removeRelationship(recipient.getUserId());
//    return true; //  removed 
//    }
//  return false;
}
}
