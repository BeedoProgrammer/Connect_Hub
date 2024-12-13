/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Database.NotificationDatabase;
import Database.UserDatabase;
import Groups.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;

/**
 *
 * @author user
 */
public class NotificationCreator {

    private static long nextNotificationId = 1;

    public static void createNotification(User Reciever, User Sender, NotificationType type) {
        try {
            NotificationDatabase ourNotifcations = NotificationDatabase.getInstance();
            long Id = generateNotificationId();
            String message = NotificationMessageGenerator.generateMessage(type, Sender);
            Notification noti = new Notification(Id, Reciever.getUserId(), Sender.getUserId(), type, message, false, LocalDateTime.now());
            ourNotifcations.addNotification(noti);

        } catch (IOException ex) {
            Logger.getLogger(NotificationCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(NotificationCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createGroupNotification(User Reciever, User Sender, NotificationType type, Group group) {
        try {
            NotificationDatabase ourNotifcations = NotificationDatabase.getInstance();
            long Id = generateNotificationId();
            String message = NotificationMessageGenerator.generateMessage(type, Sender, group);
            Notification noti = new Notification(Id, Reciever.getUserId(), Sender.getUserId(), type, message, false, LocalDateTime.now());
            ourNotifcations.addNotification(noti);
        } catch (IOException ex) {
            Logger.getLogger(NotificationCreator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(NotificationCreator.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static synchronized long generateNotificationId() {
        return nextNotificationId++;
    }
}
