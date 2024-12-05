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
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
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
    private JPanel SuggestionsPanel;
    private JPanel FriendsPanel;
   // private JPanel friendsListPanel;
    private List<Long> suggestionCandidates = new ArrayList<>(); ///returns user id of all users

    public FriendManagementPage(User user) {
        setTitle("Friend Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Panels for sections
        requestsPanel = new JPanel();
        requestsPanel.setLayout(new BoxLayout(requestsPanel, BoxLayout.X_AXIS));
        populateRequestsPanel(user);
//////////////////////////////////////////
        FriendsPanel = new JPanel();
        FriendsPanel.setLayout(new BoxLayout(FriendsPanel, BoxLayout.Y_AXIS));
        populateFriendsPanel(user);
///////////////////////////////////////////
        SuggestionsPanel = new JPanel();
        SuggestionsPanel.setLayout(new BoxLayout(SuggestionsPanel, BoxLayout.Y_AXIS));
        initializeSuggestionCandidates(user);
        populateSuggestionsPanel(user);
        // Create labeled sub-panels///////////////////////
        JPanel requestsSection = createLabeledSection("Friend Requests", new JScrollPane(requestsPanel));
        JPanel friendsSection = createLabeledSection("Friends", new JScrollPane(FriendsPanel));
        JPanel suggestionsSection = createLabeledSection("Suggestions", new JScrollPane(SuggestionsPanel));
///////////////////////////
        // Main panel with GridLayout for dividing into three equal sections
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // 1 row, 3 columns with spacing
        mainPanel.add(requestsSection); 
        mainPanel.add(friendsSection); 
        mainPanel.add(suggestionsSection); // Right: Suggestions
        // Add the main panel to the frame
        add(mainPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }
      private JPanel createLabeledSection(String title, JScrollPane content) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BorderLayout());
        JLabel sectionLabel = new JLabel(title, JLabel.CENTER);
        sectionPanel.add(sectionLabel, BorderLayout.NORTH); // Add label at the top
        sectionPanel.add(content, BorderLayout.CENTER);     // Add scrollable content in the center
        return sectionPanel;
    }
      private void populateRequestsPanel(User user) {
        requestsPanel.removeAll();
        requestsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        HashMap<Long, FriendshipStatus> relationships = user.getRelationships();
 for (Map.Entry<Long, FriendshipStatus> entry : relationships.entrySet()) {
    if (entry.getValue() == FriendshipStatus.PENDINGRECIEVER) {
        User SentRequestFriend = getFriendById(entry.getKey());
        JPanel requestPanel = createRequestPanel(user, SentRequestFriend);

        // Create an empty JPanel to add top spacing
        JPanel topSpacingPanel = new JPanel();
        topSpacingPanel.setPreferredSize(new Dimension(0, 200)); // Adjust 10 as needed for spacing

        requestsPanel.add(topSpacingPanel); // Add spacing before request panel
        requestsPanel.add(requestPanel);
        System.out.println("zz");
    }
 }
}
       private JPanel createRequestPanel(User currentUser, User friend) {
        JPanel requestPanel = new JPanel();
        requestPanel.setLayout(new BoxLayout(requestPanel, BoxLayout.Y_AXIS));
        JLabel friendLabel = new JLabel(friend.getUsername());
        friendLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton acceptButton = new JButton("Accept ");
        JButton declineButton = new JButton("Decline");
        acceptButton.addActionListener(e -> {
            FriendManagement.acceptFriendRequest(friend, currentUser);
            populateRequestsPanel(currentUser); /////// TO REMOVE HIM FROM REQUEST THE RELATION BETWEEN US BOTH WAYS ACCEPTED
            populateFriendsPanel(currentUser); //// SHOW ALL ACCEPTED FRIENDS
        });

        declineButton.addActionListener(e -> {
            declineFriendRequest(friend, currentUser); ///REMOVE ANY ENTRY IN BOTH HASHMAPS SO CAN APPEAR IN SUGGESTONS
            populateRequestsPanel(currentUser); //REMOVE HIM FROM REQEUST AND RELATION BETWEEN US NON EXIST
        });
        requestPanel.add(friendLabel); // Add friend name
        requestPanel.add(acceptButton); // Add "Accept" button
        requestPanel.add(declineButton); // Add "Decline" button

        return requestPanel;
    }
          private void populateFriendsPanel(User user) {
        FriendsPanel.removeAll();
        FriendsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        for (Map.Entry<Long, FriendshipStatus> entry : user.getRelationships().entrySet()) {
            if (entry.getValue() == FriendshipStatus.ACCEPTED) {
                User friend = getFriendById(entry.getKey());
                    JPanel friendPanel = createFriendPanelForBlocking(user, friend); //you are accepted and in my friends so i need 2 buttons beside u blocked,removed
                   
                     JPanel topSpacingPanel = new JPanel();
        topSpacingPanel.setPreferredSize(new Dimension(0, 300)); // Adjust 10 as needed for spacing
        FriendsPanel.add(topSpacingPanel);
         FriendsPanel.add(friendPanel);
            }
        }
        FriendsPanel.revalidate();
        FriendsPanel.repaint();
    }
          private JPanel createFriendPanelForBlocking(User currentUser, User friend) {
        JPanel friendPanel = new JPanel();
        friendPanel.setLayout(new BoxLayout(friendPanel, BoxLayout.Y_AXIS));
        JLabel friendLabel = new JLabel(friend.getUsername());
        JButton blockButton = new JButton("  Block  ");
        JButton removeButton = new JButton("Remove");
        blockButton.addActionListener(e -> {
               FriendManagement.blockFriend(currentUser, friend);
                System.out.println(friend.getUsername() + " blocked successfully.");
                populateFriendsPanel(currentUser); // Refresh the block friends panel
           
        });
        removeButton.addActionListener(e -> {
          FriendManagement.removeFriend(currentUser, friend);
                System.out.println(friend.getUsername() + " removed successfully.");
                populateFriendsPanel(currentUser); // Refresh the block friends panel
           
        });
        friendPanel.add(friendLabel);
        friendPanel.add(blockButton);
        friendPanel.add(removeButton);
        friendPanel.setPreferredSize(new Dimension(200, 120)); // Adjust panel size (width and height)
        return friendPanel;
    }
    private void initializeSuggestionCandidates(User currentUser) {
        suggestionCandidates.clear();
    HashMap<Long, User> usersDatabase = UserDatabase.getUsersDatabase(); //temporary
//    HashMap<Long, FriendshipStatus> relationships = currentUser.getRelationships();

    for (User user : usersDatabase.values()) { // Iterate through User objects
        if (!currentUser.hasRelationshipWith(user.getUserId()) && currentUser.getUserId() != user.getUserId()) {
            suggestionCandidates.add(user.getUserId()); // Add userId to the list
            System.out.println(1);
        }
    }
}
   private void populateSuggestionsPanel(User currentUser) {
    SuggestionsPanel.removeAll();
    SuggestionsPanel.setLayout(new GridBagLayout()); // Set GridBagLayout
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.anchor = GridBagConstraints.CENTER; 
    gbc.fill = GridBagConstraints.HORIZONTAL; 
    int suggestionCount = 0;
    for (Long userId : suggestionCandidates) {
        if (suggestionCount == 1) {
            break;
        }
        User suggestedUser = UserDatabase.getUsersDatabase().get(userId);
        JPanel suggestionPanel = createSuggestionPanel(currentUser, suggestedUser);
        SuggestionsPanel.add(suggestionPanel, gbc); 
        suggestionCount++;
    }
    SuggestionsPanel.revalidate();
    SuggestionsPanel.repaint();
}

    private JPanel createSuggestionPanel(User currentUser, User suggestedUser) {
        JPanel suggestionPanel = new JPanel();
        suggestionPanel.setLayout(new BoxLayout(suggestionPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel(suggestedUser.getUsername());
        JButton addFriendButton = new JButton("Add Friend");
        JButton declineButton = new JButton("    Decline  ");

        // Action for "Add Friend" button
        addFriendButton.addActionListener(e -> {
            
       FriendManagement.sendFriendRequest(currentUser, suggestedUser); //ALTERING TO PENDING RELATION
           
                System.out.println("Friend request sent to " + suggestedUser.getUsername());
                suggestionCandidates.remove(suggestedUser.getUserId()); // Remove from candidates
                populateSuggestionsPanel(currentUser); // Refresh suggestions

            
        });
        declineButton.addActionListener(e -> {
            System.out.println("Declined suggestion: " + suggestedUser.getUsername());
            suggestionCandidates.remove(suggestedUser.getUserId()); // Remove from candidates
            populateSuggestionsPanel(currentUser); // Refresh suggestions
        });

        suggestionPanel.add(usernameLabel);
        suggestionPanel.add(addFriendButton);
        suggestionPanel.add(declineButton);
        suggestionPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        return suggestionPanel;
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
        user1.getRelationships().put(user2.getUserId(), FriendshipStatus.PENDINGRECIEVER);
        user1.getRelationships().put(user3.getUserId(), FriendshipStatus.ACCEPTED);
//       user1.getRelationships().put(user4.getUserId(), FriendshipStatus.ACCEPTED);
//       user1.getRelationships().put(user5.getUserId(), FriendshipStatus.ACCEPTED);
        user2.getRelationships().put(user1.getUserId(), FriendshipStatus.PENDINGSENDER);
        user3.getRelationships().put(user1.getUserId(), FriendshipStatus.ACCEPTED);
//       user4.getRelationships().put(user1.getUserId(), FriendshipStatus.ACCEPTED);
//       user5.getRelationships().put(user1.getUserId(), FriendshipStatus.ACCEPTED);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FriendManagementPage(user1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
