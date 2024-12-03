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
        //////////--------////
       requestsPanel = new JPanel();
       // requestsPanel.setLayout(new BoxLayout(requestsPanel, BoxLayout.Y_AXIS));
        populateRequestsPanel(user);   /////////functin for requests
        requestsPanel.setMaximumSize(new java.awt.Dimension(500, 150)); 
        //////////////////////////////////////////////////////////////
//      friendsListPanel = new JPanel();
//        friendsListPanel.setLayout(new BoxLayout(friendsListPanel, BoxLayout.X_AXIS));
         //  populateFriendsListPanel(user);
        ///////////////////////////////////////////////
          blockFriendsPanel = new JPanel();
        blockFriendsPanel.setLayout(new BoxLayout(blockFriendsPanel, BoxLayout.Y_AXIS));
        populateBlockFriendsPanel(user);
       // JScrollPane blockFriendsScrollPane = new JScrollPane(blockFriendsPanel);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JScrollPane(requestsPanel), BorderLayout.NORTH); //Requests at top
        mainPanel.add(new JScrollPane(blockFriendsPanel), BorderLayout.CENTER); //Friends in center
 //   mainPanel.add(blockFriendsScrollPane);
        add(mainPanel, BorderLayout.CENTER); // Add mainPanel to frame

        pack();
        setVisible(true);
    
    }
 private void populateBlockFriendsPanel(User user) {
    blockFriendsPanel.removeAll();

    BoxLayout boxLayout = new BoxLayout(blockFriendsPanel, BoxLayout.Y_AXIS);
    blockFriendsPanel.setLayout(boxLayout);

    for (Map.Entry<Long, FriendshipStatus> entry : user.getRelationships().entrySet()) {
        if (entry.getValue() == FriendshipStatus.ACCEPTED) {
            User friend = getFriendById(entry.getKey());
            if (friend != null) {
                JPanel friendPanel = createFriendPanelForBlocking(user, friend);
                friendPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the friendPanel horizontally
                blockFriendsPanel.add(friendPanel);
                blockFriendsPanel.add(Box.createVerticalStrut(10)); // Add vertical spacing between friend panels
            }
        }
    }

   // blockFriendsPanel.add(Box.createVerticalGlue()); // Add vertical glue to center the friend panels vertically

    blockFriendsPanel.revalidate();
    blockFriendsPanel.repaint();
}
 private JPanel createFriendPanelForBlocking(User currentUser, User friend) {
    // Create a panel for a single friend
    JPanel friendPanel = new JPanel();
    friendPanel.setLayout(new BoxLayout(friendPanel, BoxLayout.Y_AXIS)); // Vertically stack name and button

    // Create the label and button
    JLabel friendLabel = new JLabel(friend.getUsername());
    JButton blockButton = new JButton("Block");
     JButton RemoveButton = new JButton("Remove");

    // Action listener for the block button
    blockButton.addActionListener(e -> {
        boolean success = FriendManagement.blockFriend(currentUser, friend);
        if (success) {
            System.out.println(friend.getUsername() + " blocked successfully.");
            populateBlockFriendsPanel(currentUser); // Refresh the block friends panel
        } else {
            System.err.println("Error blocking " + friend.getUsername());
        }
    });
     RemoveButton.addActionListener(e -> {
        boolean success = FriendManagement.blockFriend(currentUser, friend);
        if (success) {
            System.out.println(friend.getUsername() + " blocked successfully.");
            populateBlockFriendsPanel(currentUser); // Refresh the block friends panel
        } else {
            System.err.println("Error blocking " + friend.getUsername());
        }
    });

    // Add components to the friend panel
    friendPanel.add(friendLabel);
    friendPanel.add(blockButton);
    friendPanel.setPreferredSize(new Dimension(200, 60)); //Example: 200 wide, 30 high
    return friendPanel;
}
      private void populateRequestsPanel(User user) {
    requestsPanel.removeAll(); // Clear existing requests

    // Set layout for horizontal alignment
    requestsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // spacing bet components

    HashMap<Long, FriendshipStatus> relationships = user.getRelationships();
    for (Map.Entry<Long, FriendshipStatus> entry : relationships.entrySet()) {
        if (entry.getValue() == FriendshipStatus.PENDING) {
            User friend = getFriendById(entry.getKey());
            JPanel requestPanel = createRequestPanel(user, friend); //makes panel for new friend
            requestsPanel.add(requestPanel); // Add it to the big Requests panel
        }
    }

    requestsPanel.revalidate();
    requestsPanel.repaint();
}
        
private JPanel createRequestPanel(User currentUser, User friend) {
    JPanel requestPanel = new JPanel(); // Main panel for a single friend
    requestPanel.setLayout(new BoxLayout(requestPanel, BoxLayout.Y_AXIS)); // column sort
    JLabel friendLabel = new JLabel(friend.getUsername());
    friendLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Center align for better aesthetics

    // Buttons
    JButton acceptButton = new JButton("Accept ");
    JButton declineButton = new JButton("Decline");

    acceptButton.addActionListener(e -> {
        FriendManagement.acceptFriendRequest(currentUser, friend);
        populateRequestsPanel(currentUser);
        populateFriendsListPanel(currentUser);
        populateBlockFriendsPanel(currentUser);
    });

    declineButton.addActionListener(e -> {
        declineFriendRequest(currentUser, friend);
        populateRequestsPanel(currentUser);
    });

    // Add components to the request panel
    requestPanel.add(friendLabel); // Add friend's name
   // requestPanel.add(Box.createVerticalStrut(5)); // Add some spacing
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
        User user1 = new User(1L, "user1@example.com", "SENSO", "password123", "1990-01-01", true);
        User user2 = new User(2L, "user2@example.com", "BEEDO", "password456", "1992-05-15", true);
        User user3 = new User(3L, "user3@example.com", "JOE", "password789", "1995-07-25", false);
        UserDatabase.addUser(user1);
        UserDatabase.addUser(user2);
        UserDatabase.addUser(user3);
        user1.getRelationships().put(user2.getUserId(), FriendshipStatus.ACCEPTED);
        user1.getRelationships().put(user3.getUserId(), FriendshipStatus.ACCEPTED);
        user2.getRelationships().put(user1.getUserId(), FriendshipStatus.ACCEPTED);
        user3.getRelationships().put(user1.getUserId(), FriendshipStatus.ACCEPTED);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FriendManagementPage(user1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
