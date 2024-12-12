/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author user
 */
public class NotificationMessageGenerator {

    // Method to generate custom messages based on notification type and senderId
    public static String generateMessage(NotificationType type, User senderUser, Group group) {
        String message = "";

        switch (type) {
            case FRIEND_REQUEST_RECEIVED:
                message =   senderUser.getUsername() + " has sent you a friend request.";
                break;
            case FRIEND_REQUEST_ACCEPTED:
                message =   senderUser.getUsername() + " has accepted your  friend request.";
                break;
            case GROUP_INVITE_ACCEPTED:
                message = senderUser.getUsername()+" accepted your group invite to this group" + group.getName();
                break;
            case GROUP_INVITE_REJECTED:
                message = senderUser.getUsername()+ " rejected your group invite to this group"+ group.getName();
                break;
            case GROUP_REMOVED:
                message = "You were removed from this group"+ group.getName()+" by " + senderUser.getUsername() ;
                break;
            case  POST_ADDED:
                message = senderUser.getUsername() + " has posted in this group "+group.getName();
                break;
            case  MY_POST_EDITED:
                message = senderUser.getUsername()+ " edited your post in this group."+group.getName();
                break;
            case  MY_POST_DELETED:
                message = senderUser.getUsername()+ " deleted your post in this group."+group.getName();
                break;
            case PROMOTED:
                message = "You were promoted in this group" + group.getName() + "by" + senderUser.getUsername();
                break;
            case DEMOTED:
                message = "You were demoted in this group" + group.getName() + "by" + senderUser.getUsername();
                break;
        }

        return message;
    }
}

