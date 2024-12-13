/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Groups.Group;
import Backend.*;
import Backend.NotificationType;
import Backend.User;
import Database.NotificationDatabase;
import Database.UserDatabase;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.json.simple.parser.ParseException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author user
 */
public class NotificationFront extends javax.swing.JFrame {
 private    NotificationDatabase ourNotifcations;
    private JPanel mainPanel;
    ArrayList<Notification> Mynotifications;
    public NotificationFront() throws IOException, FileNotFoundException, ParseException { // nothing i will just load from all arraylist of notifcations upcoming from database
this.ourNotifcations=NotificationDatabase.getInstance();
Mynotifications=ourNotifcations.getNotifications();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
        initComponents();
        // Initialize mainPanel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);
        displayNotifications();
    }

    private void displayNotifications() throws IOException, FileNotFoundException, ParseException {
        for (Notification notification : Mynotifications) {
            // Only process notifications that are unread (status = false)
            if (!notification.isIsRead()) {
                processNotification(notification);
            }
        }
    }

    private void processNotification(Notification notification) throws IOException, FileNotFoundException, ParseException {
        NotificationType type = notification.getType();

        switch (type) {
            case FRIEND_REQUEST_ACCEPTED:
                handleFriendRequestAccepted(notification);
                break;
            case FRIEND_REQUEST_RECEIVED:
                handleFriendRequestReceived(notification);
                break;
            case GROUP_INVITE_ACCEPTED:
                handleGroupInviteAccepted(notification);
                break;
            case GROUP_INVITE_REJECTED:
                handleGroupInviteRejected(notification);
                break;
            case GROUP_REMOVED:
                handleGroupRemoved(notification);
                break;
            case PROMOTED:
                handlePromoted(notification);
                break;
            case DEMOTED:
                handleDemoted(notification);
                break;
            case POST_ADDED:
                handlePostAdded(notification);
                break;
            case MY_POST_EDITED:
                handleMyPostEdited(notification);
                break;
            case MY_POST_DELETED:
                handleMyPostDeleted(notification);
                break;
            default:
                System.out.println("Unknown notification type: " + type);
        }
    }

    private void updateMainPanel(JPanel subPanel) {
        mainPanel.removeAll();
        mainPanel.add(subPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void handleFriendRequestAccepted(Notification notification) {

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        String Message = notification.getMessage();
        subPanel.add(new JLabel(Message));
        addMarkAsReadButton(subPanel, notification);
        updateMainPanel(subPanel);

    }

    private void handleFriendRequestReceived(Notification notification) throws IOException, FileNotFoundException, ParseException {
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        long senderid = notification.getSenderId();
        long recieverid = notification.getRecieverId();
        UserDatabase OurUsers = UserDatabase.getInstance(); ////////////////make it in constructor
        String SenderName = OurUsers.getUserFromId(senderid).getUsername();
        User Sender = OurUsers.getUserFromId(senderid);
        User Reciever = OurUsers.getUserFromId(recieverid);
        JLabel senderLabel = new JLabel("Friend request from: " + SenderName);
        subPanel.add(senderLabel, BorderLayout.NORTH);
        JButton acceptButton = new JButton("Accept");
        JButton rejectButton = new JButton("Reject");
        acceptButton.addActionListener(e -> {
            FriendManagement.acceptFriendRequest(Sender, Reciever);
            acceptButton.setEnabled(false);
            rejectButton.setEnabled(false);
        });
        rejectButton.addActionListener(e -> {
            FriendManagement.declineFriendRequest(Sender, Reciever);
            acceptButton.setEnabled(false);
            rejectButton.setEnabled(false);
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(acceptButton);
        buttonPanel.add(rejectButton);
        subPanel.add(buttonPanel, BorderLayout.SOUTH);
        addMarkAsReadButton(subPanel, notification);
        updateMainPanel(subPanel);
    }

    private void handlePostAdded(Notification notification) {
        // Create the sub-panel for "Post Added"
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        String Message = notification.getMessage();
        subPanel.add(new JLabel(Message));
        addMarkAsReadButton(subPanel, notification);
        updateMainPanel(subPanel);
    }

    private void handleGroupInviteAccepted(Notification notification) {

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        String Message = notification.getMessage();
        subPanel.add(new JLabel(Message));
        addMarkAsReadButton(subPanel, notification);
        updateMainPanel(subPanel);
    }

    private void handleGroupInviteRejected(Notification notification) {
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        String Message = notification.getMessage();
        subPanel.add(new JLabel(Message));
        addMarkAsReadButton(subPanel, notification);
        updateMainPanel(subPanel);
    }

    private void handleGroupRemoved(Notification notification) {
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        String Message = notification.getMessage();
        subPanel.add(new JLabel(Message));
        addMarkAsReadButton(subPanel, notification);
        updateMainPanel(subPanel);
    }

    private void handlePromoted(Notification notification) {
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        String Message = notification.getMessage();
        subPanel.add(new JLabel(Message));
        addMarkAsReadButton(subPanel, notification);
        updateMainPanel(subPanel);
    }

    private void handleDemoted(Notification notification) {
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        String Message = notification.getMessage();
        subPanel.add(new JLabel(Message));
        addMarkAsReadButton(subPanel, notification);
        updateMainPanel(subPanel);
    }

    private void handleMyPostEdited(Notification notification) {
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        String Message = notification.getMessage();
        subPanel.add(new JLabel(Message));
        addMarkAsReadButton(subPanel, notification);
        updateMainPanel(subPanel);
    }

    private void handleMyPostDeleted(Notification notification) {
        // Create the sub-panel for "My Post Deleted"
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        String Message = notification.getMessage();
        subPanel.add(new JLabel(Message));
        addMarkAsReadButton(subPanel, notification);
        updateMainPanel(subPanel);
    }

    private void addMarkAsReadButton(JPanel panel, Notification notification) {
        JButton markAsReadButton = new JButton("Mark as Read");
        markAsReadButton.addActionListener((ActionEvent e) -> {
            try {
                notification.setIsRead(true);
                Mynotifications.remove(notification);
                mainPanel.remove(panel);
                updateMainPanel(new JPanel());
                displayNotifications();
            } catch (IOException ex) {
                Logger.getLogger(NotificationFront.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(NotificationFront.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        panel.add(markAsReadButton, BorderLayout.SOUTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Group group1 = new Group.GroupBuilder(1001, "Tech Enthusiasts")
                .groupPhoto("tech_group_photo.jpg")
                .description("A group for tech lovers!")
                .build();

        User user2 = new User.UserBuilder(2, "jane.smith@example.com", "mike_ali", BCrypt.hashpw("password456", BCrypt.gensalt()), LocalDate.of(1997, 8, 20), true)
                .bio("Adventurer, photographer, and foodie.")
                .profilePic("jane_profile_pic.jpg")
                .coverPhoto("jane_cover.jpg")
                .build();
        User user3 = new User.UserBuilder(3, "mike.jones@example.com", "mike_jones", BCrypt.hashpw("password789", BCrypt.gensalt()), LocalDate.of(1993, 11, 25), false)
                .bio("Fitness enthusiast. Let's connect!")
                .profilePic("mike_profile_pic.jpg")
                .coverPhoto("mike_cover.jpg")
                .build();
        group1.addUser(user3, user2); // user 2 is admin
        NotificationType type = NotificationType.FRIEND_REQUEST_ACCEPTED;
        String message = NotificationMessageGenerator.generateMessage(type, user3);
        long notificationId = 22; //genrated
        Notification notification = new Notification(notificationId, user3.getUserId(), user2.getUserId(), type, message, false, LocalDateTime.now());

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             //   new Notification(notificationId, user3.getUserId(), user2.getUserId(), type, message, false, LocalDateTime.now()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
