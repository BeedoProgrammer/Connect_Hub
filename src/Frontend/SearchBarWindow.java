/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Backend.FriendshipStatus;
import Backend.User;
import Backend.UserSearch;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public class SearchBarWindow extends javax.swing.JFrame {
private UserSearch SearchOption;
   private User CurrentUser;
    public SearchBarWindow(User CurrentUser,ArrayList <User> AllUsers) {
        initComponents();
       this.CurrentUser=CurrentUser;
       this.SearchOption=new UserSearch(CurrentUser,AllUsers);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Search = new javax.swing.JButton();
        SearchInput = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Search.setText("Search");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(207, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Search)
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(SearchInput, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(SearchInput, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(Search)
                .addGap(91, 91, 91))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
 String searchTerm = SearchInput.getText();
    SearchOption.DesiredPeople(searchTerm);

    SearchResultsWindow resultsWindow = new SearchResultsWindow(CurrentUser, SearchOption);
    resultsWindow.setVisible(true);
    }//GEN-LAST:event_SearchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         User user1 = new User.UserBuilder(1, "john.doe@example.com", "john_doe", "password123".toCharArray(), LocalDate.of(1995, 5, 15), true)
                .bio("Just a regular guy who loves coding!")
                .profilePic("john_profile_pic.jpg")
                .coverPhoto("john_cover.jpg")
                .build();

        User user2 = new User.UserBuilder(2, "jane.smith@example.com", "mike_smith", "password456".toCharArray(), LocalDate.of(1997, 8, 20), true)
                .bio("Adventurer, photographer, and foodie.")
                .profilePic("jane_profile_pic.jpg")
                .coverPhoto("jane_cover.jpg")
                .build();

        User user3 = new User.UserBuilder(3, "mike.jones@example.com", "mike_jones", "password789".toCharArray(), LocalDate.of(1993, 11, 25), false)
                .bio("Fitness enthusiast. Let's connect!")
                .profilePic("mike_profile_pic.jpg")
                .coverPhoto("mike_cover.jpg")
                .build();
        user1.addRelationship(3, FriendshipStatus.ACCEPTED);   // user1 sent request to user3
        user3.addRelationship(1, FriendshipStatus.ACCEPTED);         // user2 and user3 are friends
 ArrayList<User> systemUsers = new ArrayList<>();
        systemUsers.add(user1);
        systemUsers.add(user2);
        systemUsers.add(user3);
        // List of all users in the system
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchBarWindow(user1,systemUsers).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Search;
    private javax.swing.JTextField SearchInput;
    // End of variables declaration//GEN-END:variables
}
