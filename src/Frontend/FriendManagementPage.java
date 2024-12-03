/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Backend.FriendManagement;
import static Backend.FriendManagement.acceptFriendRequest;
import static Backend.FriendManagement.declineFriendRequest;
import Backend.FriendshipStatus;
import Backend.User;
import Backend.UserDatabase;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author user
 */
public class FriendManagementPage extends javax.swing.JFrame {

    private JPanel requestsPanel;
    private JPanel blockFriendsPanel;
    private JPanel friendsListPanel;
    private JLabel Friendlabel;

    public FriendManagementPage(User user) {

        setTitle("Friend Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Friendlabel = new JLabel("Friend Requests");
        add(Friendlabel, BorderLayout.NORTH);
        requestsPanel = new JPanel();
        requestsPanel.setLayout(new BoxLayout(requestsPanel, BoxLayout.Y_AXIS));
        populateRequestsPanel(user);
        blockFriendsPanel = new JPanel();
        blockFriendsPanel.setLayout(new BoxLayout(blockFriendsPanel, BoxLayout.Y_AXIS));
        populateBlockFriendsPanel(user);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(requestsPanel), BorderLayout.NORTH); //Requests at top
        mainPanel.add(new JScrollPane(blockFriendsPanel), BorderLayout.CENTER); //Friends in center
        add(mainPanel, BorderLayout.CENTER); // Add mainPanel to frame

        pack();
        setVisible(true);

    }

    private void populateBlockFriendsPanel(User user) {
        blockFriendsPanel.removeAll();
        blockFriendsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        for (Map.Entry<Long, FriendshipStatus> entry : user.getRelationships().entrySet()) {
            if (entry.getValue() == FriendshipStatus.ACCEPTED) {
                User friend = getFriendById(entry.getKey());
                if (friend != null) {
                    JPanel friendPanel = createFriendPanelForBlocking(user, friend);
                    blockFriendsPanel.add(friendPanel);
                }
            }
        }
        blockFriendsPanel.revalidate();
        blockFriendsPanel.repaint();
    }

    private JPanel createFriendPanelForBlocking(User currentUser, User friend) {
        JPanel friendPanel = new JPanel();
        friendPanel.setLayout(new BoxLayout(friendPanel, BoxLayout.Y_AXIS));
        JLabel friendLabel = new JLabel(friend.getUsername());
        JButton blockButton = new JButton("  Block  ");
        JButton removeButton = new JButton("Remove");
        blockButton.addActionListener(e -> {
            boolean success = FriendManagement.blockFriend(currentUser, friend);
            if (success) {
                System.out.println(friend.getUsername() + " blocked successfully.");
                populateBlockFriendsPanel(currentUser); // Refresh the block friends panel
            } else {
                System.err.println("Error blocking " + friend.getUsername());
            }
        });
        removeButton.addActionListener(e -> {
            boolean success = FriendManagement.removeFriend(currentUser, friend);
            if (success) {
                System.out.println(friend.getUsername() + " removed successfully.");
                populateBlockFriendsPanel(currentUser); // Refresh the block friends panel
            } else {
                System.err.println("Error removing " + friend.getUsername());
            }
        });
        friendPanel.add(friendLabel);
        friendPanel.add(blockButton);
        friendPanel.add(removeButton);
        friendPanel.setPreferredSize(new Dimension(200, 120)); // Adjust panel size (width and height)
        return friendPanel;
    }
    private void populateRequestsPanel(User user) {
        requestsPanel.removeAll(); 
        requestsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); 

        HashMap<Long, FriendshipStatus> relationships = user.getRelationships();
        for (Map.Entry<Long, FriendshipStatus> entry : relationships.entrySet()) {
            if (entry.getValue() == FriendshipStatus.PENDING) {
                User friend = getFriendById(entry.getKey());
                JPanel requestPanel = createRequestPanel(user, friend);
                requestsPanel.add(requestPanel);
            }
        }
        requestsPanel.revalidate();
        requestsPanel.repaint();
    }

    private JPanel createRequestPanel(User currentUser, User friend) {
        JPanel requestPanel = new JPanel();
        requestPanel.setLayout(new BoxLayout(requestPanel, BoxLayout.Y_AXIS));
        JLabel friendLabel = new JLabel(friend.getUsername());
        friendLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton acceptButton = new JButton("Accept ");
        JButton declineButton = new JButton("Decline");

        acceptButton.addActionListener(e -> {
            FriendManagement.acceptFriendRequest(currentUser, friend);
            populateRequestsPanel(currentUser);
            populateBlockFriendsPanel(currentUser);
        });

        declineButton.addActionListener(e -> {
            declineFriendRequest(currentUser, friend);
            populateRequestsPanel(currentUser);
        });
        requestPanel.add(friendLabel); // Add friend name
        requestPanel.add(acceptButton); // Add "Accept" button
        requestPanel.add(declineButton); // Add "Decline" button

        return requestPanel;
    }

    private void populateFriendsListPanel(User user) {
        friendsListPanel.removeAll();
        HashMap<Long, FriendshipStatus> relationships = user.getRelationships();
        for (Map.Entry<Long, FriendshipStatus> entry : relationships.entrySet()) {
            if (entry.getValue() == FriendshipStatus.ACCEPTED) {
                System.out.println("accepted");
                User friend = getFriendById(entry.getKey());
                if (friend != null) {
                    JLabel friendLabel = new JLabel(friend.getUsername());
                    friendsListPanel.add(friendLabel);
                    friendsListPanel.add(Box.createVerticalStrut(5)); // Add 5 pixels of vertical space

                }
            }
        }
        friendsListPanel.revalidate();
        friendsListPanel.repaint();
    }

    private User getFriendById(long userId) {
        return UserDatabase.getUserById(userId);
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
        System.out.println("hi");
        User user1 = new User(1L, "user1@example.com", "SENSO", "password123", "1990-01-01", true);
        User user2 = new User(2L, "user2@example.com", "BEEDO", "password456", "1992-05-15", true);
        User user3 = new User(3L, "user3@example.com", "JOE", "password789", "1995-07-25", false);
        User user4 = new User(4L, "user3@example.com", "abdo", "password789", "1995-07-25", false);
        User user5 = new User(5L, "user3@example.com", "essam", "password789", "1995-07-25", false);
        UserDatabase.addUser(user1);
        UserDatabase.addUser(user2);
        UserDatabase.addUser(user3);
        UserDatabase.addUser(user4);
        UserDatabase.addUser(user5);
        user1.getRelationships().put(user2.getUserId(), FriendshipStatus.PENDING);
        user1.getRelationships().put(user3.getUserId(), FriendshipStatus.ACCEPTED);
        user1.getRelationships().put(user4.getUserId(), FriendshipStatus.ACCEPTED);
        user1.getRelationships().put(user5.getUserId(), FriendshipStatus.ACCEPTED);
        user2.getRelationships().put(user1.getUserId(), FriendshipStatus.PENDING);
        user3.getRelationships().put(user1.getUserId(), FriendshipStatus.ACCEPTED);
        user4.getRelationships().put(user1.getUserId(), FriendshipStatus.ACCEPTED);
        user5.getRelationships().put(user1.getUserId(), FriendshipStatus.ACCEPTED);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FriendManagementPage(user1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
