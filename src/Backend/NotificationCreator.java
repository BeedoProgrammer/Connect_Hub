/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Groups.*;
import java.time.LocalDateTime;

/**
 *
 * @author user
 */
public class NotificationCreator {
    //public Notification(long id, long recieverId, long senderId, NotificationType type, String message, boolean isRead, LocalDateTime timestamp) {
     private static long nextNotificationId = 1;
    public static void createNotification(User Reciever, User Sender, NotificationType type) {
         long Id = generateNotificationId();
        String message = NotificationMessageGenerator.generateMessage(type, Sender);
        Notification noti = new Notification(Id, Reciever.getUserId(), Sender.getUserId(), type, message, false, LocalDateTime.now());
    }

    public static void createGroupNotification(User Reciever, User Sender, NotificationType type, Group group) {
         long Id = generateNotificationId();
        String message = NotificationMessageGenerator.generateMessage(type, Sender, group);
        Notification noti = new Notification(Id, Reciever.getUserId(), Sender.getUserId(), type, message, false, LocalDateTime.now());

    }
 private static synchronized long generateNotificationId() {
        return nextNotificationId++;
    }
}
