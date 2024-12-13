package Backend;

import java.util.*;

public class FriendManagement {
     public static boolean sendFriendRequest(User sender, User recipient) {
    if (sender.hasRelationshipWith(recipient.getUserId())) {
        return false; // Relationship already exists
    }
    sender.addRelationship(recipient.getUserId(), FriendshipStatus.PENDINGSENDER);
    recipient.addRelationship(sender.getUserId(), FriendshipStatus.PENDINGRECIEVER);
    return true;
}
    public static boolean declineFriendRequest(User sender, User recipient) {
        if (recipient.getRelationshipStatus(sender.getUserId()) == FriendshipStatus.PENDINGRECIEVER) {
            recipient.removeRelationship(sender.getUserId());
            sender.removeRelationship(recipient.getUserId());
            return true; 
        }
        return false;
    }
    public static boolean acceptFriendRequest(User sender, User recipient) {
        if (recipient.getRelationshipStatus(sender.getUserId()) == FriendshipStatus.PENDINGRECIEVER) {
            sender.addRelationship(recipient.getUserId(), FriendshipStatus.ACCEPTED);
            recipient.addRelationship(sender.getUserId(), FriendshipStatus.ACCEPTED);
            return true;
        }
        return false;
    }

    public static boolean blockFriend(User sender, User recipient) {
        if (recipient.getRelationshipStatus(sender.getUserId()) == FriendshipStatus.ACCEPTED) {
            sender.addRelationship(recipient.getUserId(), FriendshipStatus.BLOCKED);
            recipient.addRelationship(sender.getUserId(), FriendshipStatus.BLOCKED);
            return true;
        }
        return false;
    }
    public static boolean removeFriend(User sender, User recipient) {
        if (recipient.getRelationshipStatus(sender.getUserId()) == FriendshipStatus.ACCEPTED) {
            recipient.removeRelationship(sender.getUserId());
            sender.removeRelationship(recipient.getUserId());
            return true; 
        }
        return false;
    }
      public static boolean block(User sender, User recipient) {
        FriendshipStatus currentStatus = recipient.getRelationshipStatus(sender.getUserId());
        if (currentStatus != FriendshipStatus.BLOCKED) {
            sender.addRelationship(recipient.getUserId(), FriendshipStatus.BLOCKED);
            recipient.addRelationship(sender.getUserId(), FriendshipStatus.BLOCKED);
            return true; // Successfully blocked
        }
        return false; // Already blocked
    }
}
