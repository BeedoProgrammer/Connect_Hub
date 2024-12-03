package Backend;

import java.util.*;

public class FriendManagement {
    public static boolean sendFriendRequest(User sender, User recipient) {
        if (recipient.hasRelationshipWith(sender.getUserId())) {
                return false;
            }

        // Add the pending relationship if we dont have a relation
        sender.addRelationship(recipient.getUserId(), FriendshipStatus.PENDINGSENDER);
        recipient.addRelationship(sender.getUserId(), FriendshipStatus.PENDINGRECIEVER);
        return true; // Friend request sent 
    }

    public static boolean declineFriendRequest(User sender, User recipient) { ///
        //reciver is pending with sender
        if ( recipient.getRelationshipStatus(sender.getUserId()) == FriendshipStatus.PENDINGRECIEVER) {
             recipient.removeRelationship(sender.getUserId());
             sender.removeRelationship(recipient.getUserId());
            return true; //  declined
        }
        return false; // we didnt have a pending relation
    }

    public static boolean acceptFriendRequest(User sender, User recipient) {  ///
        // reciever is pending with sender
        if ( recipient.getRelationshipStatus(sender.getUserId()) == FriendshipStatus.PENDINGRECIEVER) {
            sender.addRelationship(recipient.getUserId(), FriendshipStatus.ACCEPTED);
            recipient.addRelationship(sender.getUserId(), FriendshipStatus.ACCEPTED);
            return true; //  accepted
        }
        return false; //we didnt have a pending relation
    }

    public static boolean blockFriend(User sender, User recipient) {
        // If no existing relationship or already blocked or pending , he needs to be in my friend list
        if (!recipient.hasRelationshipWith(sender.getUserId())|| recipient.getRelationshipStatus(sender.getUserId())!=FriendshipStatus.ACCEPTED) {
            return false;
        }

        // we have relation accepted so change it to blocked
        sender.addRelationship(recipient.getUserId(), FriendshipStatus.BLOCKED);
        recipient.addRelationship(sender.getUserId(), FriendshipStatus.BLOCKED);
        return true; //  blocked
    }

public static boolean removeFriend(User sender, User recipient) {
    // If no existing relationship, or existing but not accepted
    if (recipient.getRelationshipStatus(sender.getUserId())==FriendshipStatus.ACCEPTED) {
        recipient.removeRelationship(sender.getUserId());
    sender.removeRelationship(recipient.getUserId());
    return true; //  removed 
    }
  return false;
}
}
