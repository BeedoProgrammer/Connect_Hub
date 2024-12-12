package Database;

import Backend.Notification;
import Backend.NotificationType;
import Backend.User;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.parser.ParseException;

public class NotificationDatabase extends Database {
    private static NotificationDatabase instance = null;
    public static NotificationDatabase getInstance() throws IOException, FileNotFoundException, ParseException {
        if (instance == null) {
            instance = new NotificationDatabase("files/notifications.json");
            instance.readFromFile();
        }
        
        return instance;
    }

    public NotificationDatabase(String fileName) {
        super(fileName);
    }

    @Override
    protected Map<String, Object> getMapFromRecord(Object notification) {
        Map<String,Object> tempNotificationMap = new HashMap<>();
        Notification tempNotification = (Notification)notification;
        tempNotificationMap.put("notificationId", tempNotification.getId());
        tempNotificationMap.put("recieverId", tempNotification.getRecieverId());
        tempNotificationMap.put("senderId", tempNotification.getSenderId());
        
        tempNotificationMap.put("type", tempNotification.getType().toString());
        
        tempNotificationMap.put("message", tempNotification.getMessage());
        tempNotificationMap.put("status", tempNotification.isIsRead());
        tempNotificationMap.put("timeStamp", tempNotification.getTimestamp().toString());
        return tempNotificationMap;
    }

    @Override
    protected Notification getRecordFromMap(Map<String, Object> mapOfNotification) {
        long notificationId = (long)mapOfNotification.get("notificationId");
        long recieverId = (long)mapOfNotification.get("recieverId");
        long senderId = (long)mapOfNotification.get("senderId");
        String typeString = (String)mapOfNotification.get("type");
        NotificationType type = null;
        for (NotificationType tempTtype : NotificationType.values()) {
            if (tempTtype.name().equalsIgnoreCase(typeString)) {
                type = tempTtype;
            }
        }
        String messgae = (String)mapOfNotification.get("message");
        boolean status = (boolean)mapOfNotification.get("status");
        String timeStamp = (String)mapOfNotification.get("timeStamp");
        
        Notification tempNotification = new Notification(notificationId, recieverId, senderId, type, messgae, status, LocalDateTime.parse(timeStamp));
        return tempNotification;
    }
    
    public void addNotification(Notification notification) throws IOException, FileNotFoundException, ParseException {
        super.addRecord(notification);
    }
    public ArrayList<Notification> getNotifications() {
        ArrayList<Object> tempObjects = super.getRecords();
        ArrayList<Notification> notifications = new ArrayList<>();
        for (Object tempObject : tempObjects) {
            notifications.add((Notification)tempObject);
        }
        return notifications;
    }
    public Notification getNotificationFromId(long notificationId) {    // returns user object if found and null if not found
        ArrayList<Object> tempObjects = super.getRecords();
        for (int i = 0; i < tempObjects.size(); i++) {
            if (notificationId == ((Notification)tempObjects.get(i)).getId()) {
                return (Notification)tempObjects.get(i);
            }
        }
        return null;
    }
}
