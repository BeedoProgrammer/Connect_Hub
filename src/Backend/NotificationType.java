/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author user
 */
public enum NotificationType {
    FRIEND_REQUEST_ACCEPTED, // the other person accepted my friend request
    FRIEND_REQUEST_RECIEVED,// Friend request notifications
    GROUP_INVITE_ACCEPTED, // i was accepted  in the group
    GROUP_INVITE_REJECTED, // i was rejected  from the group
    GROUP_REMOVED, // i was removed from a group
    PROMOTED, // i was promoted in a group
    DEMOTED, //  i was demoted in a group
    POST_ADDED, // New post added to a group
    MY_POST_EDITED, // my Post edited in a group
    MY_POST_DELETED       //  my Post deleted in a group
}
