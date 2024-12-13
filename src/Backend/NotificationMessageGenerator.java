
package Backend;

import Groups.Group;

public  class NotificationMessageGenerator {

    // Overloaded method for messages that require both sender and group
    public static String generateMessage(NotificationType type, User senderUser, Group group) {
        switch (type) {
            case GROUP_INVITE_ACCEPTED:
                return senderUser.getUsername() + " accepted your group invite to this group " + group.getName();
            case GROUP_INVITE_REJECTED:
                return senderUser.getUsername() + " rejected your group invite to this group " + group.getName();
            case GROUP_REMOVED:
                return "You were removed from this group " + group.getName() + " by " + senderUser.getUsername();
            case POST_ADDED:
                return senderUser.getUsername() + " has posted in this group " + group.getName();
            case MY_POST_EDITED:
                return senderUser.getUsername() + " edited your post in this group " + group.getName();
            case MY_POST_DELETED:
                return senderUser.getUsername() + " deleted your post in this group " + group.getName();
            case PROMOTED:
                return "You were promoted in this group " + group.getName() + " by " + senderUser.getUsername();
            case DEMOTED:
                return "You were demoted in this group " + group.getName() + " by " + senderUser.getUsername();
            default:
                throw new IllegalArgumentException("Unsupported notification type for this method: " + type);
        }
    }
    public static String generateMessage(NotificationType type, User senderUser) {
        switch (type) {
            case FRIEND_REQUEST_RECEIVED:
                return senderUser.getUsername() + " has sent you a friend request.";
            case FRIEND_REQUEST_ACCEPTED:
                return senderUser.getUsername() + " has accepted your friend request.";
            default:
                throw new IllegalArgumentException("Unsupported notification type for this method: " + type);
        }
    }
}
