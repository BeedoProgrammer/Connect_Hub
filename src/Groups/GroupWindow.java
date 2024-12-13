package Groups;

import Backend.*;
import Database.*;
import Frontend.CreateContentWindow;
import NewsFeed.NewsFeedWindow;
import NewsFeed.contentPanel;
import java.awt.*;
import javax.swing.*;

public class GroupWindow extends javax.swing.JFrame {
    private JFrame parent;
    private Group group;
    private GroupWindowManager myManager;
    private User currentUser;
    private JScrollPane postsPane;
    private JScrollPane StoriesPane;
    private JScrollPane membersPane;
    

    public GroupWindow(User currentUser, Group myGroup,JFrame parent) {
        super(myGroup.getName());
        this.currentUser = currentUser;
        this.group = myGroup;
        this.parent = parent;
        customInit();
        initComponents();
    }
    
    private void customInit(){
        myManager = new GroupWindowManager(group);
        postsPane = new contentPanel(new Dimension(200, 400), this.myManager.getPosts()).getContentScrollable();
        StoriesPane = new contentPanel(new Dimension(200, 400), this.myManager.getStories()).getContentScrollable();
        membersPane = new membersPanel(new Dimension(200, 400), myManager.getMembers()).getFriendsScrollable();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        profilePicPanel = new javax.swing.JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon(group.getGroupPhoto());
                Image profilePic = img.getImage();

                if(profilePic != null)
                g.drawImage(profilePic, 0, 0, getWidth(), getHeight(), this);
            }};
            refresh = new javax.swing.JButton();
            jLabel1 = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            newContentButton = new javax.swing.JButton();
            leave = new javax.swing.JButton();
            descriptionTextbox = new java.awt.TextArea();
            nameTextfield = new javax.swing.JTextField();
            jLabel3 = new javax.swing.JLabel();
            jScrollPane1 = new javax.swing.JScrollPane();
            jScrollPane2 = new javax.swing.JScrollPane();
            jScrollPane3 = new javax.swing.JScrollPane();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            profilePicPanel.setBackground(new java.awt.Color(255, 255, 255));

            javax.swing.GroupLayout profilePicPanelLayout = new javax.swing.GroupLayout(profilePicPanel);
            profilePicPanel.setLayout(profilePicPanelLayout);
            profilePicPanelLayout.setHorizontalGroup(
                profilePicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 100, Short.MAX_VALUE)
            );
            profilePicPanelLayout.setVerticalGroup(
                profilePicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 100, Short.MAX_VALUE)
            );

            refresh.setBackground(new java.awt.Color(0, 0, 0));
            refresh.setForeground(new java.awt.Color(255, 255, 255));
            refresh.setText("Refresh");
            refresh.setFocusPainted(false);
            refresh.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    refreshActionPerformed(evt);
                }
            });

            jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel1.setText("Group Members");

            jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel2.setText("Posts");

            newContentButton.setBackground(new java.awt.Color(0, 0, 0));
            newContentButton.setForeground(new java.awt.Color(255, 255, 255));
            newContentButton.setText("Create New Post/Story");
            newContentButton.setFocusPainted(false);
            newContentButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    newContentButtonActionPerformed(evt);
                }
            });

            leave.setBackground(new java.awt.Color(0, 0, 0));
            leave.setForeground(new java.awt.Color(255, 255, 255));
            leave.setText("Leave Group");
            leave.setFocusPainted(false);
            leave.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    leaveActionPerformed(evt);
                }
            });

            descriptionTextbox.setEditable(false);
            descriptionTextbox.setText(group.getDescription());

            nameTextfield.setEditable(false);
            nameTextfield.setText(group.getName());
            nameTextfield.setBackground(new java.awt.Color(255, 255, 255));

            jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
            jLabel3.setText("Stories");

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(refresh)
                                    .addGap(38, 38, 38))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(profilePicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(nameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel1)
                                    .addGap(57, 57, 57))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(descriptionTextbox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addContainerGap())))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jScrollPane2)
                                    .addGap(41, 41, 41)))
                            .addGap(187, 187, 187))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(leave, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(57, 57, 57)
                                    .addComponent(newContentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(232, 232, 232))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(0, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(profilePicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(refresh))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(nameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(descriptionTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(51, 51, 51)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(newContentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 14, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addComponent(leave, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(14, 14, 14))
            );

            pack();
        }// </editor-fold>//GEN-END:initComponents
    
    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_refreshActionPerformed

    private void newContentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newContentButtonActionPerformed
        // TODO add your handling code here:
        CreateContentWindow contentWindow = new CreateContentWindow(this, currentUser);
        contentWindow.setLocationRelativeTo(null);
        contentWindow.setVisible(true);
    }//GEN-LAST:event_newContentButtonActionPerformed

    private void leaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_leaveActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewsFeedWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewsFeedWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewsFeedWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewsFeedWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UserDatabase myD = UserDatabase.getInstance();
                    GroupDatabase myGroupData = GroupDatabase.getInstance();
                    new GroupWindow(myD.getUsers().get(0), myGroupData.getGroups().get(0), null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.TextArea descriptionTextbox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton leave;
    private javax.swing.JTextField nameTextfield;
    private javax.swing.JButton newContentButton;
    private javax.swing.JPanel profilePicPanel;
    private javax.swing.JButton refresh;
    // End of variables declaration//GEN-END:variables
}
