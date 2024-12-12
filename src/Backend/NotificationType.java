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
    FRIEND_REQUEST_ACCEPTED(1),  // The other person accepted my friend request
    FRIEND_REQUEST_RECEIVED(2),  // Friend request notifications
    GROUP_INVITE_ACCEPTED(3),    // I was accepted into the group
    GROUP_INVITE_REJECTED(4),    // I was rejected from the group
    GROUP_REMOVED(5),            // I was removed from a group
    PROMOTED(6),                 // I was promoted in a group
    DEMOTED(7),                  // I was demoted in a group
    POST_ADDED(8),               // New post added to a group
    MY_POST_EDITED(9),           // My post edited in a group
    MY_POST_DELETED(10);         // My post deleted in a group

    private final int typeNumber;
    NotificationType(int typeNumber) {
        this.typeNumber = typeNumber;
    }

    // Getter method to access the type number
    public int getTypeNumber() {
        return this.typeNumber;
    }
}
