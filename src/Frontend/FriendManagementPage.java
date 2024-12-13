/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Backend.FriendManagement;
import Backend.FriendshipStatus;
import Backend.User;
import Database.UserDatabase;
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

    private User currentUser;
    private UserDatabase Database = UserDatabase.getInstance(); 
    private JPanel requestsPanel;
    private JPanel SuggestionsPanel;
    private JPanel FriendsPanel;
    private JFrame parent;
    private ArrayList<Long> suggestionCandidates = new ArrayList<>(); 

    public FriendManagementPage(JFrame parent, User user) throws IOException, FileNotFoundException, ParseException {
        this.parent = parent;
        this.setPreferredSize(parent.getSize());
        this.currentUser = user;
        setTitle("Friend Management");
        setLayout(new BorderLayout());
        requestsPanel = new JPanel();
        requestsPanel.setLayout(new BoxLayout(requestsPanel, BoxLayout.Y_AXIS));
        populateRequestsPanel();
        FriendsPanel = new JPanel();
        FriendsPanel.setLayout(new BoxLayout(FriendsPanel, BoxLayout.Y_AXIS));
        populateFriendsPanel();
        SuggestionsPanel = new JPanel();
        SuggestionsPanel.setLayout(new BoxLayout(SuggestionsPanel, BoxLayout.Y_AXIS));
        populateSuggestionsPanel();
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
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
    }

    private JPanel createLabeledSection(String title, JScrollPane content) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BorderLayout());
        JLabel sectionLabel = new JLabel(title, JLabel.CENTER);
        sectionPanel.add(sectionLabel, BorderLayout.NORTH);
        sectionPanel.add(content, BorderLayout.CENTER);
        return sectionPanel;
    }

    private void populateRequestsPanel() {
        saveData();
        requestsPanel.removeAll();
        requestsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        HashMap<Long, FriendshipStatus> relationships = currentUser.getRelationships();
        for (Map.Entry<Long, FriendshipStatus> entry : relationships.entrySet()) {
            if (entry.getValue() == FriendshipStatus.PENDINGRECIEVER) {
                User SentRequestFriend = Database.getUserFromId(entry.getKey());
                JPanel requestPanel = createRequestPanel(SentRequestFriend);
                requestsPanel.add(requestPanel);
            }
        }
          requestsPanel.revalidate();
        requestsPanel.repaint();
    }

    private JPanel createRequestPanel(User friend) {
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
    FriendManagement.acceptFriendRequest(getUpdatedUser(friend), getUpdatedUser(currentUser));
    populateRequestsPanel(); 
    populateFriendsPanel();
});
declineButton.addActionListener(e -> {
    FriendManagement.declineFriendRequest(getUpdatedUser(friend), getUpdatedUser(currentUser));
    populateRequestsPanel();
});
        requestPanel.add(MYPhotoPanel);
        requestPanel.add(friendLabel);
        requestPanel.add(acceptButton);
        requestPanel.add(declineButton);
        return requestPanel;
    }
    private void populateFriendsPanel() {
        saveData();
        FriendsPanel.removeAll();
        FriendsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        for (Map.Entry<Long, FriendshipStatus> entry : currentUser.getRelationships().entrySet()) {
            if (entry.getValue() == FriendshipStatus.ACCEPTED) {
                System.out.println(entry.getKey());
                User friend = Database.getUserFromId(entry.getKey());  //ERROR ARISE HERE get user from id doesnt function properly
                JPanel friendPanel = createFriendPanelForBlocking(friend); //you are accepted and in my friends so i need 2 buttons beside u blocked,removed
                FriendsPanel.add(friendPanel);
            }
        }
        FriendsPanel.revalidate();
        FriendsPanel.repaint();
    }

 private JPanel createFriendPanelForBlocking(User friend) {
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
        FriendManagement.blockFriend(getUpdatedUser(currentUser), getUpdatedUser(friend));
        System.out.println(friend.getUsername() + " blocked successfully.");
        populateFriendsPanel();
    });
    removeButton.addActionListener(e -> {
        FriendManagement.removeFriend(getUpdatedUser(currentUser), getUpdatedUser(friend));
        System.out.println(friend.getUsername() + " removed successfully.");
        populateFriendsPanel();
    });
       MYPhotoPanel.setPreferredSize(new Dimension(120,120)); 
    friendPanel.add(MYPhotoPanel);
    friendPanel.add(friendLabel);
    friendPanel.add(blockButton);
    friendPanel.add(removeButton);
    return friendPanel;
}
    private void initializeSuggestionCandidates() {
        suggestionCandidates.clear();
        ArrayList<User> SystemUsers = Database.getUsers();
        for (User user : SystemUsers) { 
            if (!currentUser.hasRelationshipWith(user.getUserId()) && currentUser.getUserId() != user.getUserId()) {
                suggestionCandidates.add(user.getUserId()); 
            }
        }
    }

    private void populateSuggestionsPanel() {
        initializeSuggestionCandidates();
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
      System.out.println(currentUser);
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
        FriendManagement.sendFriendRequest(getUpdatedUser(currentUser), getUpdatedUser(suggestedUser)); // Send friend request
        System.out.println("Friend request sent to " + getUpdatedUser(suggestedUser).getUsername());
        suggestionCandidates.remove(suggestedUser.getUserId()); // Remove from candidates
        populateSuggestionsPanel(); // Refresh suggestions panel
    });

    declineButton.addActionListener(e -> {
        System.out.println("Declined suggestion: " + suggestedUser.getUsername());
        suggestionCandidates.remove(suggestedUser.getUserId()); // Remove from candidates
        populateSuggestionsPanel(); // Refresh suggestions panel
    });
    suggestionPanel.add(MYPhotoPanel); // Add photo panel
    suggestionPanel.add(usernameLabel); // Add username label
    suggestionPanel.add(addFriendButton); // Add "Add Friend" button
    suggestionPanel.add(declineButton);
    return suggestionPanel;
}
  
    private User getUpdatedUser(User myUser){
        return this.Database.getUserFromId(myUser.getUserId());
    }
  
    private void saveData(){
        try {
            this.Database.saveToFile();
            this.currentUser = getUpdatedUser(currentUser);
        } catch (Exception e) {
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

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

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        saveData();
        this.parent.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      
java.awt.EventQueue.invokeLater(new Runnable() {
    public void run() {
        
    }
});
      
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
