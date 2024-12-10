/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Backend.FriendManagement;
import Backend.FriendshipStatus;
import Backend.User;
import Backend.UserSearch;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author user
 */
public class SearchResultsWindow extends javax.swing.JFrame {

    private ArrayList<User> suggestedUsers; // Users who are suggested based on the friendship status
    private ArrayList<User> acceptedUsers;  // Users who are friends (ACCEPTED status)
    private ArrayList<User> pendingUsers;
    private User CurrentUser;
    private UserSearch UserSearching;
    private JPanel SuggestionPanel;
    private JPanel AcceptedPanel;
    private JPanel PendingPanel;

    public SearchResultsWindow(User CurrentUser, UserSearch UserSearching) {
        initComponents();
        this.CurrentUser = CurrentUser;
        this.UserSearching = UserSearching;
        suggestedUsers = new ArrayList<>();
        acceptedUsers = new ArrayList<>();
        pendingUsers = new ArrayList<>();
        suggestedUsers=UserSearching.getUsersWithNoRelationship();
         acceptedUsers=UserSearching.getAcceptedFriends();
          pendingUsers=UserSearching.getPendingFriends();
//        categorizeUsers(UserSearching.getSearchResults()); ///maybe be used
        setTitle("Search Results");
        setLayout(new BorderLayout());
        SuggestionPanel = new JPanel();
        AcceptedPanel = new JPanel();
        PendingPanel = new JPanel();
         displayAcceptedFriends();
         displaySuggestedFriends();
           displayPendingFriends();
        SuggestionPanel.setLayout(new BoxLayout(SuggestionPanel, BoxLayout.Y_AXIS));
        AcceptedPanel.setLayout(new BoxLayout(AcceptedPanel, BoxLayout.Y_AXIS));
        PendingPanel.setLayout(new BoxLayout(PendingPanel, BoxLayout.Y_AXIS));
        JPanel suggestionSection = createLabeledSection("suggestion", new JScrollPane(SuggestionPanel));
        JPanel AcceptedSection = createLabeledSection("Friends", new JScrollPane(AcceptedPanel));
        JPanel PendingSection = createLabeledSection("pending", new JScrollPane(PendingPanel));
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainPanel.add(suggestionSection);
        mainPanel.add(AcceptedSection);
        mainPanel.add(PendingSection);
        add(mainPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    private JPanel createLabeledSection(String title, JScrollPane content) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BorderLayout());
        JLabel sectionLabel = new JLabel(title, JLabel.CENTER);
        sectionPanel.add(sectionLabel, BorderLayout.NORTH);
        sectionPanel.add(content, BorderLayout.CENTER);
        return sectionPanel;
    }

   private void categorizeUsers(ArrayList<User> searchedUsers) {
    for (User user : searchedUsers) {
        FriendshipStatus status = CurrentUser.getRelationshipStatus(user.getUserId());

        // Check if status is null (no relationship exists)
        if (status == null) {
            System.out.println("No relationship with user: " + user.getUsername());
            suggestedUsers.add(user);
            continue; // Skip processing this user if there's no relationship
        }

        // Process based on the relationship status
        switch (status) {
            case PENDINGRECIEVER:
                pendingUsers.add(user);
                break;
            case PENDINGSENDER:
                pendingUsers.add(user);
                break;
            case ACCEPTED:
                acceptedUsers.add(user);
                break;
            default:
                break;
        }
    }
}

   private void displayAcceptedFriends() {
    AcceptedPanel.removeAll(); 
    for (User user : acceptedUsers) {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
        userPanel.add(new JLabel(user.getUsername())); 
        
        JButton viewProfileButton = new JButton("View Profile");
        JButton blockButton = new JButton("Block");
        JButton removeButton = new JButton("Remove");
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // viewProfile(user); 
                viewProfileButton.setEnabled(false); 
            }
        });
        blockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
              FriendManagement.blockFriend(CurrentUser, user);
                    JOptionPane.showMessageDialog(null, "User blocked successfully.", "Block Status", JOptionPane.INFORMATION_MESSAGE);
                blockButton.setEnabled(false);
                   removeButton.setEnabled(false);
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
 FriendManagement.removeFriend(CurrentUser, user);
                    JOptionPane.showMessageDialog(null, "User removed successfully.", "Remove Status", JOptionPane.INFORMATION_MESSAGE);
                removeButton.setEnabled(false); // Disable after click
            }
        });
        userPanel.add(viewProfileButton);
        userPanel.add(blockButton);
        userPanel.add(removeButton);
        AcceptedPanel.add(userPanel);
    }
    AcceptedPanel.revalidate();
    AcceptedPanel.repaint();
}

private void displaySuggestedFriends() {
    SuggestionPanel.removeAll(); // Clear the panel
    for (User user : suggestedUsers) {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
        userPanel.add(new JLabel(user.getUsername())); 
        JButton viewProfileButton = new JButton("View Profile");
        JButton blockButton = new JButton("Block");
        JButton addButton = new JButton("Add");
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                JOptionPane.showMessageDialog(SuggestionPanel, "View Profile: " + user.getUsername());
            }
        });
        blockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (FriendManagement.block(CurrentUser, user)) {
                    JOptionPane.showMessageDialog(SuggestionPanel, "User " + user.getUsername() + " successfully blocked.");
                    blockButton.setEnabled(false);
                    addButton.setEnabled(false); 
                } else {
                    JOptionPane.showMessageDialog(SuggestionPanel, "User " + user.getUsername() + " is already blocked.");
                }
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (FriendManagement.sendFriendRequest(CurrentUser, user)) {
                    JOptionPane.showMessageDialog(SuggestionPanel, "Friend request sent to " + user.getUsername() + ".");
                    addButton.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(SuggestionPanel, "Friend request already sent to " + user.getUsername() + ".");
                }
            }
        });
        userPanel.add(viewProfileButton);
        userPanel.add(blockButton);
        userPanel.add(addButton);
        SuggestionPanel.add(userPanel);
    }
    SuggestionPanel.revalidate();
    SuggestionPanel.repaint();
}

private void displayPendingFriends() {
    PendingPanel.removeAll(); // Clear the panel
    for (User user : pendingUsers) {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));
        userPanel.add(new JLabel(user.getUsername()));
        JButton viewProfileButton = new JButton("View Profile");
        viewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
              ///beedo will implement
            }
        });
        userPanel.add(viewProfileButton);
        PendingPanel.add(userPanel);
    }
    PendingPanel.revalidate();
    PendingPanel.repaint();
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
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
