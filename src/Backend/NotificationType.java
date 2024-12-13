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
    FRIEND_REQUEST_ACCEPTED,  // The other person accepted my friend request
    FRIEND_REQUEST_RECEIVED,  // Friend request notifications
    GROUP_INVITE_ACCEPTED,    // I was accepted into the group
    GROUP_INVITE_REJECTED,    // I was rejected from the group
    GROUP_REMOVED,            // I was removed from a group
    PROMOTED,                 // I was promoted in a group
    DEMOTED,                  // I was demoted in a group
    POST_ADDED,               // New post added to a group
    MY_POST_EDITED,           // My post edited in a group
    MY_POST_DELETED;         // My post deleted in a group

   
}
