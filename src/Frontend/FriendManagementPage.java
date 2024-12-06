/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Backend.FriendManagement;
import Backend.FriendshipStatus;
import Backend.User;
import Backend.UserDatabase;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.json.simple.parser.ParseException;

/**
 *
 * @author user
 */
public class FriendManagementPage extends javax.swing.JFrame {

    private User user;
    private UserDatabase Database = UserDatabase.getInstance(); 
    private JPanel requestsPanel;
    private JPanel SuggestionsPanel;
    private JPanel FriendsPanel;
    
    private ArrayList<Long> suggestionCandidates = new ArrayList<>(); 

    public FriendManagementPage(User user) throws IOException, FileNotFoundException, ParseException {
       Database.readFromFile();
        this.user = user;
        setTitle("Friend Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        requestsPanel = new JPanel();
        requestsPanel.setLayout(new BoxLayout(requestsPanel, BoxLayout.Y_AXIS));
        populateRequestsPanel(user);
        FriendsPanel = new JPanel();
        FriendsPanel.setLayout(new BoxLayout(FriendsPanel, BoxLayout.Y_AXIS));
        populateFriendsPanel(user);
        SuggestionsPanel = new JPanel();
        SuggestionsPanel.setLayout(new BoxLayout(SuggestionsPanel, BoxLayout.Y_AXIS));
        initializeSuggestionCandidates(user);
        populateSuggestionsPanel(user);
        JPanel requestsSection = createLabeledSection("Friend Requests", new JScrollPane(requestsPanel));
        JPanel friendsSection = createLabeledSection("Friends", new JScrollPane(FriendsPanel));
        JPanel suggestionsSection = createLabeledSection("Suggestions", new JScrollPane(SuggestionsPanel));
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10)); 
        mainPanel.add(requestsSection);
        mainPanel.add(friendsSection);
        mainPanel.add(suggestionsSection);
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

    private void populateRequestsPanel(User user) {
        requestsPanel.removeAll();
        requestsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        HashMap<Long, FriendshipStatus> relationships = user.getRelationships();
        for (Map.Entry<Long, FriendshipStatus> entry : relationships.entrySet()) {
            if (entry.getValue() == FriendshipStatus.PENDINGRECIEVER) {
                User SentRequestFriend = Database.getUserFromId(entry.getKey());
                JPanel requestPanel = createRequestPanel(user, SentRequestFriend);
                requestsPanel.add(requestPanel);
            }
        }
          requestsPanel.revalidate();
        requestsPanel.repaint();
    }

    private JPanel createRequestPanel(User currentUser, User friend) {
        JPanel requestPanel = new JPanel();
         requestPanel.setLayout(new BoxLayout(requestPanel, BoxLayout.Y_AXIS));
        JPanel MYPhotoPanel = new JPanel() {
              @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon img = new ImageIcon(friend.getProfilePic());
            Image coverPhoto = img.getImage();

            if (coverPhoto != null) {
                int imageWidth = coverPhoto.getWidth(null);
                int imageHeight = coverPhoto.getHeight(null);
                int panelSize = Math.min(getWidth(), getHeight());
                double aspectRatio = (double) imageWidth / imageHeight;
                int newWidth, newHeight;
                if (aspectRatio > 1) {
                    newWidth = panelSize;
                    newHeight = (int) (panelSize / aspectRatio);
                } else {
                    newHeight = panelSize;
                    newWidth = (int) (panelSize * aspectRatio);
                }
                int x = (getWidth() - newWidth) / 2;
                int y = (getHeight() - newHeight) / 2;
                g.drawImage(coverPhoto, x, y, newWidth, newHeight, this);
            }
        }
    };
    MYPhotoPanel.setPreferredSize(new Dimension(120,120)); 
    JLabel friendLabel = new JLabel(friend.getUsername());
    friendLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    JButton acceptButton = new JButton("  Accept  ");
    JButton declineButton = new JButton(" Decline ");
    acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
    declineButton.setAlignmentX(Component.CENTER_ALIGNMENT);
       acceptButton.addActionListener(e -> {
    FriendManagement.acceptFriendRequest(friend, currentUser);
    populateRequestsPanel(currentUser); 
    populateFriendsPanel(currentUser);
});
declineButton.addActionListener(e -> {
    FriendManagement.declineFriendRequest(friend, currentUser);
    populateRequestsPanel(currentUser);
});
        requestPanel.add(MYPhotoPanel);
        requestPanel.add(friendLabel);
        requestPanel.add(acceptButton);
        requestPanel.add(declineButton);
        return requestPanel;
    }
    private void populateFriendsPanel(User user) {
        FriendsPanel.removeAll();
        FriendsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        for (Map.Entry<Long, FriendshipStatus> entry : user.getRelationships().entrySet()) {
            if (entry.getValue() == FriendshipStatus.ACCEPTED) {
                System.out.println(entry.getKey());
                User friend = Database.getUserFromId(entry.getKey());  //ERROR ARISE HERE get user from id doesnt function properly
                JPanel friendPanel = createFriendPanelForBlocking(user, friend); //you are accepted and in my friends so i need 2 buttons beside u blocked,removed
                FriendsPanel.add(friendPanel);
            }
        }
        FriendsPanel.revalidate();
        FriendsPanel.repaint();
    }

 private JPanel createFriendPanelForBlocking(User currentUser, User friend) {
    JPanel friendPanel = new JPanel();
    friendPanel.setLayout(new BoxLayout(friendPanel, BoxLayout.Y_AXIS));
    JPanel MYPhotoPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon img = new ImageIcon(friend.getProfilePic());
            Image coverPhoto = img.getImage();
            if (coverPhoto != null) {
                int imageWidth = coverPhoto.getWidth(null);
                int imageHeight = coverPhoto.getHeight(null);
                int panelSize = Math.min(getWidth(), getHeight());
                double aspectRatio = (double) imageWidth / imageHeight;
                int newWidth, newHeight;
                if (aspectRatio > 1) {
                    newWidth = panelSize;
                    newHeight = (int) (panelSize / aspectRatio);
                } else {
                    newHeight = panelSize;
                    newWidth = (int) (panelSize * aspectRatio);
                }
                int x = (getWidth() - newWidth) / 2;
                int y = (getHeight() - newHeight) / 2;
                g.drawImage(coverPhoto, x, y, newWidth, newHeight, this);
            }
        }
    };
    MYPhotoPanel.setPreferredSize(new Dimension(100, 100)); 
    JLabel friendLabel = new JLabel(friend.getUsername());
    friendLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    JButton blockButton = new JButton("  Block  ");
    JButton removeButton = new JButton("Remove");
    blockButton.setAlignmentX(Component.CENTER_ALIGNMENT); 
    removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    blockButton.addActionListener(e -> {
        FriendManagement.blockFriend(currentUser, friend);
        System.out.println(friend.getUsername() + " blocked successfully.");
        populateFriendsPanel(currentUser);
    });
    removeButton.addActionListener(e -> {
        FriendManagement.removeFriend(currentUser, friend);
        System.out.println(friend.getUsername() + " removed successfully.");
        populateFriendsPanel(currentUser);
    });
       MYPhotoPanel.setPreferredSize(new Dimension(120,120)); 
    friendPanel.add(MYPhotoPanel);
    friendPanel.add(friendLabel);
    friendPanel.add(blockButton);
    friendPanel.add(removeButton);
    return friendPanel;
}
    private void initializeSuggestionCandidates(User currentUser) {
        suggestionCandidates.clear();
        ArrayList<User> SystemUsers = Database.getUsers();
        for (User user : SystemUsers) { 
            if (!currentUser.hasRelationshipWith(user.getUserId()) && currentUser.getUserId() != user.getUserId()) {
                suggestionCandidates.add(user.getUserId()); 
            }
        }
    }

    private void populateSuggestionsPanel(User currentUser) {
        SuggestionsPanel.removeAll();
        SuggestionsPanel.setLayout(new GridBagLayout());
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
            User suggestedUser = Database.getUserFromId(userId);
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
    JPanel MYPhotoPanel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon img = new ImageIcon(suggestedUser.getProfilePic());
            Image coverPhoto = img.getImage();

            if (coverPhoto != null) {
                int imageWidth = coverPhoto.getWidth(null);
                int imageHeight = coverPhoto.getHeight(null);
                int panelSize = Math.min(getWidth(), getHeight());
                double aspectRatio = (double) imageWidth / imageHeight;
                int newWidth, newHeight;
                if (aspectRatio > 1) {
                    newWidth = panelSize;
                    newHeight = (int) (panelSize / aspectRatio);
                } else {
                    newHeight = panelSize;
                    newWidth = (int) (panelSize * aspectRatio);
                }
                int x = (getWidth() - newWidth) / 2;
                int y = (getHeight() - newHeight) / 2;
                g.drawImage(coverPhoto, x, y, newWidth, newHeight, this);
            }
        }
    };
    MYPhotoPanel.setPreferredSize(new Dimension(120, 120));
    JLabel usernameLabel = new JLabel(suggestedUser.getUsername());
    usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    JButton addFriendButton = new JButton("Add Friend");
    JButton declineButton = new JButton("   Decline   ");
    addFriendButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    declineButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    addFriendButton.addActionListener(e -> {
        FriendManagement.sendFriendRequest(currentUser, suggestedUser); // Send friend request
        System.out.println("Friend request sent to " + suggestedUser.getUsername());
        suggestionCandidates.remove(suggestedUser.getUserId()); // Remove from candidates
        populateSuggestionsPanel(currentUser); // Refresh suggestions panel
    });

    declineButton.addActionListener(e -> {
        System.out.println("Declined suggestion: " + suggestedUser.getUsername());
        suggestionCandidates.remove(suggestedUser.getUserId()); // Remove from candidates
        populateSuggestionsPanel(currentUser); // Refresh suggestions panel
    });
    suggestionPanel.add(MYPhotoPanel); // Add photo panel
    suggestionPanel.add(usernameLabel); // Add username label
    suggestionPanel.add(addFriendButton); // Add "Add Friend" button
    suggestionPanel.add(declineButton);
    return suggestionPanel;
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
        try {
            char[] charArray = {'H', 'e', 'l', 'l', 'o'};
            User user1 = new User.UserBuilder(1234L, "john.doe@example.com", "johndoe", charArray, LocalDate.of(1990, 5, 20), true)
                    .bio("Just a regular guy.").profilePic("otajpg.jpg")
                    .build();
          
            User user2 = new User.UserBuilder(5678L, "jane.smith@example.com", "janesmith", new char[]{'p', 'a', 's', 's'}, LocalDate.of(1992, 7, 15), true)
                    .bio("Adventurer and tech enthusiast.")
                    .build();
            
            User user3 = new User.UserBuilder(9101L, "mark.wilson@example.com", "markw", new char[]{'s', 'e', 'c', 'r', 'e', 't'}, LocalDate.of(1985, 3, 10), false)
                    .bio("Coffee lover and bookworm.").profilePic("otajpg.jpg")
                    .build();
            
            User user4 = new User.UserBuilder(1121, "linda.brown@example.com", "lindab", new char[]{'1', '2', '3', '4'}, LocalDate.of(1995, 11, 25), true)
                    .bio("Traveler and photographer.").profilePic("otajpg.jpg")
                    .build();
            
           user1.addRelationship(5678L, FriendshipStatus.PENDINGSENDER);
            user2.addRelationship(1234L, FriendshipStatus.PENDINGRECIEVER);
             user3.addRelationship(5678L, FriendshipStatus.PENDINGSENDER);
            user2.addRelationship(9101L, FriendshipStatus.PENDINGRECIEVER);
            UserDatabase Database = UserDatabase.getInstance();
            Database.addUser(user1);
            Database.addUser(user2);
             Database.addUser(user3);
              Database.addUser(user4);
            Database.saveToFile();
java.awt.EventQueue.invokeLater(new Runnable() {
    public void run() {
        try {
            new FriendManagementPage(user2).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(FriendManagementPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(FriendManagementPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
});
        } catch (IOException ex) {
            Logger.getLogger(FriendManagementPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(FriendManagementPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
