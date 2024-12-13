package Frontend;

import Backend.*;
import java.util.*;
import java.io.*;
import org.json.simple.parser.ParseException;
import Database.*;
import Groups.Group;

public class SearchBarWindow extends javax.swing.JFrame {
    
    private UserSearch SearchOption;
    private GroupSearch GroupOption;
    private User CurrentUser;
    private UserDatabase userDatabase;
    
    public SearchBarWindow(User CurrentUser) throws IOException, FileNotFoundException, ParseException {
        initComponents();
        this.CurrentUser = CurrentUser;
        userDatabase = UserDatabase.getInstance();
        ArrayList<User> AllUsers = userDatabase.getUsers();
        this.SearchOption = new UserSearch(CurrentUser, AllUsers);
        this.GroupOption = new GroupSearch(CurrentUser);
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
     ArrayList<Group>est=GroupOption.MyDesiredGroups;
        System.out.println(est.get(0));
        
        GroupSearchresultsWindow resultsWindow = new GroupSearchresultsWindow(CurrentUser, GroupOption);
    }//GEN-LAST:event_GroupSearchActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GroupSearch;
    private javax.swing.JTextField SearchInput;
    private javax.swing.JButton UserSearch;
    // End of variables declaration//GEN-END:variables
}
