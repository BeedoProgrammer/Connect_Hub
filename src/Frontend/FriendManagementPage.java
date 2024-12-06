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
    private JPanel blockFriendsPanel;
    private JPanel friendsListPanel;
    private List<Long> suggestionCandidates = new ArrayList<>();

    public FriendManagementPage(User user) {
        setTitle("Friend Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panels for sections
        requestsPanel = new JPanel();
        requestsPanel.setLayout(new BoxLayout(requestsPanel, BoxLayout.Y_AXIS));
        populateRequestsPanel(user);

        blockFriendsPanel = new JPanel();
        blockFriendsPanel.setLayout(new BoxLayout(blockFriendsPanel, BoxLayout.Y_AXIS));
        populateBlockFriendsPanel(user);

        SuggestionsPanel = new JPanel();
        SuggestionsPanel.setLayout(new BoxLayout(SuggestionsPanel, BoxLayout.Y_AXIS));
        initializeSuggestionCandidates(user);
        populateSuggestionsPanel(user);

        // Create labeled sub-panels
        JPanel requestsSection = createLabeledSection("Friend Requests", new JScrollPane(requestsPanel));
        JPanel friendsSection = createLabeledSection("Friends", new JScrollPane(blockFriendsPanel));
        JPanel suggestionsSection = createLabeledSection("Suggestions", new JScrollPane(SuggestionsPanel));

        // Main panel with GridLayout for dividing into three equal sections
        JPanel mainPanel = new JPanel(new GridLayout(1, 3, 10, 10)); // 1 row, 3 columns with spacing
        mainPanel.add(requestsSection);  // Left: Friend Requests
        mainPanel.add(friendsSection);  // Center: Friends
        mainPanel.add(suggestionsSection); // Right: Suggestions

        // Add the main panel to the frame
        add(mainPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }

    private void initializeSuggestionCandidates(User currentUser) {
        suggestionCandidates.clear();
        HashMap<Long, User> usersDatabase = UserDatabase.getUsersDatabase();
        HashMap<Long, FriendshipStatus> relationships = currentUser.getRelationships();

        for (Long userId : usersDatabase.keySet()) {
            if (!relationships.containsKey(userId) && !userId.equals(currentUser.getUserId())) {
                suggestionCandidates.add(userId); // array of no relations user ID
                System.out.println(1);
            }
        }
    }

    private void populateSuggestionsPanel(User currentUser) {
        SuggestionsPanel.removeAll();

        int suggestionCount = 0;

        // Iterate over suggestion candidates
        for (Long userId : suggestionCandidates) {
            if (suggestionCount == 1) {
                break; 
            }
            UserDatabase userDatabase = UserDatabase.getInstance();
            User suggestedUser = userDatabase.getUserFromId(userId); // get User of suggestion with id 
            if (suggestedUser != null) {
                JPanel suggestionPanel = createSuggestionPanel(currentUser, suggestedUser);
                SuggestionsPanel.add(suggestionPanel);
                suggestionCount++;
            }
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
            boolean success = FriendManagement.sendFriendRequest(currentUser, suggestedUser); //ALTERING TO PENDING RELATION
            if (success) {
                System.out.println("Friend request sent to " + suggestedUser.getUsername());
                suggestionCandidates.remove(suggestedUser.getUserId()); // Remove from candidates
                populateSuggestionsPanel(currentUser); // Refresh suggestions

            }
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
    private JPanel createLabeledSection(String title, JScrollPane content) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BorderLayout());
        JLabel sectionLabel = new JLabel(title, JLabel.CENTER);
        sectionPanel.add(sectionLabel, BorderLayout.NORTH); // Add label at the top
        sectionPanel.add(content, BorderLayout.CENTER);     // Add scrollable content in the center
        return sectionPanel;
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
            if (entry.getValue() == FriendshipStatus.PENDINGRECIEVER) {
                User friend = getFriendById(entry.getKey());
                JPanel requestPanel = createRequestPanel(user, friend);
                requestsPanel.add(requestPanel);
                System.out.println("zz");
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
            FriendManagement.acceptFriendRequest(friend, currentUser);
            populateRequestsPanel(currentUser);
            populateBlockFriendsPanel(currentUser);
        });

        declineButton.addActionListener(e -> {
            declineFriendRequest(friend, currentUser);
            populateRequestsPanel(currentUser);
        });
        requestPanel.add(friendLabel); // Add friend name
        requestPanel.add(acceptButton); // Add "Accept" button
        requestPanel.add(declineButton); // Add "Decline" button

        return requestPanel;
    }

   

    private User getFriendById(long userId) {
        UserDatabase userDatabase = UserDatabase.getInstance();
        return userDatabase.getUserFromId(userId);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
