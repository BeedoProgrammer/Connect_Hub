package Backend;

import java.util.*;

public class FriendManagement {
    public static boolean sendFriendRequest(User sender, User recipient) {
        if (recipient.hasRelationshipWith(sender.getUserId())) {
                return false;
            }

        // Add the pending relationship if we dont have a relation
        sender.addRelationship(recipient.getUserId(), FriendshipStatus.PENDING);
        recipient.addRelationship(sender.getUserId(), FriendshipStatus.PENDING);
        return true; // Friend request sent 
    }

    public static boolean declineFriendRequest(User sender, User recipient) {
        // need to ensure relation is pending
        if (recipient.hasRelationshipWith(sender.getUserId())&& recipient.getRelationshipStatus(sender.getUserId()) == FriendshipStatus.PENDING) {
            
            recipient.getRelationships().remove(sender.getUserId());
            sender.getRelationships().remove(recipient.getUserId());
            return true; //  declined
        }
        return false; // we didnt have a pending relation
    }

    public static boolean acceptFriendRequest(User sender, User recipient) {
        // need to ensure relation is pending
        if (recipient.hasRelationshipWith(sender.getUserId())
                && recipient.getRelationshipStatus(sender.getUserId()) == FriendshipStatus.PENDING) {
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
    if (!recipient.hasRelationshipWith(sender.getUserId()) || recipient.getRelationshipStatus(sender.getUserId())!=FriendshipStatus.ACCEPTED) {
        return false;
    }

    // we have relationship accepted so remove from both of us 
    sender.getRelationships().remove(recipient.getUserId());
    recipient.getRelationships().remove(sender.getUserId());
    return true; //  removed 
}
}
