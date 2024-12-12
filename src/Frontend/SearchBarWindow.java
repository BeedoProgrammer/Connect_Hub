/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Backend.FriendshipStatus;
import Groups.Group;
import Backend.GroupSearch;
import Backend.User;
import Backend.UserSearch;
import java.time.LocalDate;
import java.util.ArrayList;


import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @author user
 */
public class SearchBarWindow extends javax.swing.JFrame {
private UserSearch SearchOption;
private GroupSearch GroupOption;
   private User CurrentUser;
    public SearchBarWindow(User CurrentUser,ArrayList <User> AllUsers,ArrayList <Group> AllGroups) {
        initComponents();
       this.CurrentUser=CurrentUser;
       this.SearchOption=new UserSearch(CurrentUser,AllUsers);
       this.GroupOption=new GroupSearch(CurrentUser,AllGroups);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        UserSearch = new javax.swing.JButton();
        SearchInput = new javax.swing.JTextField();
        GroupSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        UserSearch.setText("User Search");
        UserSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserSearchActionPerformed(evt);
            }
        });

        GroupSearch.setText("GroupSearch");
        GroupSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GroupSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(GroupSearch)
                        .addGap(63, 63, 63)
                        .addComponent(UserSearch)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(UserSearch)
                    .addComponent(GroupSearch))
                .addGap(91, 91, 91))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UserSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UserSearchActionPerformed
 String searchTerm = SearchInput.getText();
    SearchOption.DesiredPeople(searchTerm);

    SearchResultsWindow resultsWindow = new SearchResultsWindow(CurrentUser, SearchOption);
    resultsWindow.setVisible(true);
    }//GEN-LAST:event_UserSearchActionPerformed

    private void GroupSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GroupSearchActionPerformed
      String searchTerm = SearchInput.getText();
      GroupOption.DesiredGroups(searchTerm);
      GroupSearchresultsWindow resultsWindow = new GroupSearchresultsWindow(CurrentUser, GroupOption);
    }//GEN-LAST:event_GroupSearchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Group g1=new Group("sens","hello");
         Group g2=new Group("senso","hello");
          Group g3=new Group("sen","hello");

         User user1 = new User.UserBuilder(1, "john.doe@example.com", "john_doe", BCrypt.hashpw("password123", BCrypt.gensalt()), LocalDate.of(1995, 5, 15), true)
                .bio("Just a regular guy who loves coding!")
                .profilePic("john_profile_pic.jpg").addGroup(g1)
                .coverPhoto("john_cover.jpg")
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
        user1.addRelationship(3, FriendshipStatus.ACCEPTED);   // user1 sent request to user3
        user3.addRelationship(1, FriendshipStatus.ACCEPTED);         // user2 and user3 are friends
 ArrayList<User> systemUsers = new ArrayList<>();
 ArrayList<Group> systemGroups=new ArrayList<>();
 systemGroups.add(g1);
  systemGroups.add(g2);
   systemGroups.add(g3);
        systemUsers.add(user1);
        systemUsers.add(user2);
        systemUsers.add(user3);
        // List of all users in the system
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SearchBarWindow(user1,systemUsers,systemGroups).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GroupSearch;
    private javax.swing.JTextField SearchInput;
    private javax.swing.JButton UserSearch;
    // End of variables declaration//GEN-END:variables
}
