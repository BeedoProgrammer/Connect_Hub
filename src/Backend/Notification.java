/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.time.LocalDateTime;

/**
 *
 * @author user
 */
public class Notification {
    private long id; 
    private long recieverId;  
    private long senderId;
    private NotificationType type;  
    private String message;   /////notifcation message generator handled it
    private boolean isRead;  
    private LocalDateTime timestamp; 
    public Notification(long id, long recieverId, long senderId, NotificationType type, String message, boolean isRead, LocalDateTime timestamp) {
        this.id = id; /// randomly generated
        this.recieverId = recieverId;
        this.senderId=senderId;
        this.type = type;
        this.message = message; /// the string message is generated from the static clas Notification message Generator
        this.isRead = isRead;
        this.timestamp = timestamp;
    }

    public void setIsRead(boolean isRead) { // after user change status of the notifcation to be removed 
        this.isRead = isRead;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(long recieverId) {
        this.recieverId = recieverId;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isIsRead() {
        return isRead;
    }
     
     
}
