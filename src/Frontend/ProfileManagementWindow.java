package Frontend;

import Database.UserDatabase;
import javax.swing.*;
import java.awt.*;
import Backend.*;
import NewsFeed.contentPanel;
import NewsFeed.friendsPanel;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;
import org.mindrot.jbcrypt.BCrypt;

public class ProfileManagementWindow extends javax.swing.JFrame {
    static User user;
    JFrame parent;
    UserDatabase myD;
    ProfileManagement manager;
    JScrollPane contentPane;
    JScrollPane friendsPane;

    public ProfileManagementWindow(String title, JFrame parent, User user) {
        super(title);
        try{
            myD = UserDatabase.getInstance();
            this.user = myD.getUserFromId(user.getUserId());
        }catch(Exception e){}
        manager = new ProfileManagement(this.user);
        System.out.println(myD.getUserFromId(this.user.getUserId()));
        this.parent = parent;
        contentPane = new contentPanel(new Dimension(100, 100), manager.getPosts()).getContentScrollable();
        friendsPane = new friendsPanel(new Dimension(170, 200), manager.getListOfFriends()).getFriendsScrollable();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon(user.getCoverPhoto());
                Image coverPhoto = img.getImage();

                if(coverPhoto != null)
                g.drawImage(coverPhoto, 0, 0, getWidth(), getHeight(), this);
            }};
            panel2 = new javax.swing.JPanel(){
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon img = new ImageIcon(user.getProfilePic());
                    Image profilePic = img.getImage();

                    if(profilePic != null)
                    g.drawImage(profilePic, 0, 0, getWidth(), getHeight(), this);
                }};
                coverPhoto = new javax.swing.JButton();
                profilePic = new javax.swing.JButton();
                updatePassword = new javax.swing.JButton();
                logout = new javax.swing.JButton();
                bio = new javax.swing.JButton();
                bioText = new java.awt.TextArea();
                Jpanel1 = new javax.swing.JLabel();
                jScrollPane1 = contentPane;
                jScrollPane2 = friendsPane;
                seeMore = new javax.swing.JButton();

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 100, Short.MAX_VALUE)
                );
                jPanel1Layout.setVerticalGroup(
                    jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 100, Short.MAX_VALUE)
                );

                setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
                addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent evt) {
                        formWindowClosing(evt);
                    }
                });

                panel1.setBackground(new java.awt.Color(255, 255, 255));

                javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
                panel1.setLayout(panel1Layout);
                panel1Layout.setHorizontalGroup(
                    panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 0, Short.MAX_VALUE)
                );
                panel1Layout.setVerticalGroup(
                    panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 137, Short.MAX_VALUE)
                );

                panel2.setBackground(new java.awt.Color(255, 255, 255));

                javax.swing.GroupLayout panel2Layout = new javax.swing.GroupLayout(panel2);
                panel2.setLayout(panel2Layout);
                panel2Layout.setHorizontalGroup(
                    panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 151, Short.MAX_VALUE)
                );
                panel2Layout.setVerticalGroup(
                    panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 100, Short.MAX_VALUE)
                );

                coverPhoto.setBackground(new java.awt.Color(0, 0, 0));
                coverPhoto.setForeground(new java.awt.Color(255, 255, 255));
                coverPhoto.setText("Change Cover Photo");
                coverPhoto.setFocusPainted(false);
                coverPhoto.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        coverPhotoActionPerformed(evt);
                    }
                });

                profilePic.setBackground(new java.awt.Color(0, 0, 0));
                profilePic.setForeground(new java.awt.Color(255, 255, 255));
                profilePic.setText("Change Profile Picture");
                profilePic.setFocusPainted(false);
                profilePic.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        profilePicActionPerformed(evt);
                    }
                });

                updatePassword.setBackground(new java.awt.Color(0, 0, 0));
                updatePassword.setForeground(new java.awt.Color(255, 255, 255));
                updatePassword.setText("Update Password");
                updatePassword.setFocusPainted(false);
                updatePassword.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        updatePasswordActionPerformed(evt);
                    }
                });

                logout.setBackground(new java.awt.Color(0, 0, 0));
                logout.setForeground(new java.awt.Color(255, 255, 255));
                logout.setText("Logout");
                logout.setFocusPainted(false);
                logout.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        logoutActionPerformed(evt);
                    }
                });

                bio.setBackground(new java.awt.Color(0, 0, 0));
                bio.setForeground(new java.awt.Color(255, 255, 255));
                bio.setText("Edit Bio");
                bio.setFocusPainted(false);
                bio.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        bioActionPerformed(evt);
                    }
                });

                bioText.setEditable(false);
                bioText.setText(user.getBio());

                Jpanel1.setBackground(new java.awt.Color(255, 255, 255));
                Jpanel1.setForeground(new java.awt.Color(0, 0, 0));
                Jpanel1.setText("      Bio");
                Jpanel1.setOpaque(true);

                seeMore.setBackground(new java.awt.Color(0, 0, 0));
                seeMore.setForeground(new java.awt.Color(255, 255, 255));
                seeMore.setText("See more");
                seeMore.setFocusPainted(false);
                seeMore.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        seeMoreActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(profilePic))
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(coverPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(Jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(bio)
                                        .addGap(0, 43, Short.MAX_VALUE))
                                    .addComponent(bioText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 140, Short.MAX_VALUE))
                                    .addComponent(jScrollPane1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(updatePassword, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                    .addComponent(jScrollPane2)))
                            .addComponent(panel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(seeMore, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                );
                layout.setVerticalGroup(
                    layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(profilePic))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(coverPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bio, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bioText, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(updatePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seeMore)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                );

                pack();
            }// </editor-fold>//GEN-END:initComponents

    private void profilePicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profilePicActionPerformed
        // TODO add your handling code here:
        JFileChooser choose = new JFileChooser();
        choose.setDialogTitle("Select a Photo");
        choose.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg", "gif"));
   
        int result = choose.showDialog(null, "Choose image");
        if (result == JFileChooser.APPROVE_OPTION) {
            File img = choose.getSelectedFile();
            String img_path = img.getAbsolutePath();
            user.setProfilePic(img_path);
            repaint();
        }
    }//GEN-LAST:event_profilePicActionPerformed

    private void coverPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coverPhotoActionPerformed
        // TODO add your handling code here:
        JFileChooser choose = new JFileChooser();
        choose.setDialogTitle("Select a Photo");
        choose.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg", "gif"));
   
        int result = choose.showDialog(null, "Choose image");
        if (result == JFileChooser.APPROVE_OPTION) {
            File img = choose.getSelectedFile();
            String img_path = img.getAbsolutePath();
            user.setCoverPhoto(img_path);
            repaint();
        }
    }//GEN-LAST:event_coverPhotoActionPerformed

    private String getPassword(String message) {
        String password;
        do {
            password = JOptionPane.showInputDialog(null, message, "Update Password", JOptionPane.PLAIN_MESSAGE);
            
            if(password == null)
                return null;
            
            if (password.equals(""))
                JOptionPane.showMessageDialog(this, "Enter Password", "ERROR", JOptionPane.ERROR_MESSAGE);
            else
                return password;
        }while(true);
    }

    
    private void updatePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePasswordActionPerformed
        // TODO add your handling code here:
        String password1;
        String password2;
        
        password1 = getPassword("Enter new password:");
        if(password1 == null)
            return;
            
     
        if(password1.equals(user.getPassword().toString())){
            JOptionPane.showMessageDialog(this, "Password is the same as old password", "ERROR", JOptionPane.ERROR_MESSAGE);
            password1 = getPassword("Enter new password:");
            if(password1 == null)
                return;
        } 
        else{
            password2 = getPassword("Confirm password:");
            if(password2 == null)
                return;
            do{
                if(!password1.equals(password2)){
                    JOptionPane.showMessageDialog(this, "Please enter the same password", "ERROR", JOptionPane.ERROR_MESSAGE);
                    password2 = getPassword("Confirm password:");
                    if(password2 == null)
                        return;
                }
                else
                    break;
            }while(true);
            
            user.setPassword(BCrypt.hashpw(password2, BCrypt.gensalt()));
            JOptionPane.showMessageDialog(rootPane, "Password updated");
        }      
    }//GEN-LAST:event_updatePasswordActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        ConnectHub connectHub = new ConnectHub("Connect Hub");
        connectHub.setLocationRelativeTo(null);
        connectHub.setVisible(true);
        saveUser();
        this.dispose();
    }//GEN-LAST:event_logoutActionPerformed

    private void bioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bioActionPerformed
        // TODO add your handling code here:
        String input = JOptionPane.showInputDialog(null, "Enter new bio: ", "Bio", JOptionPane.PLAIN_MESSAGE);
        user.setBio(input);
        bioText.setText(input);
    }//GEN-LAST:event_bioActionPerformed

    private void saveUser(){
        try {
            this.myD.saveToFile();
        } catch (Exception e) {
            Logger.getLogger(ProfileManagementWindow.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        saveUser();
        parent.setLocationRelativeTo(null);
        parent.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void seeMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeMoreActionPerformed
        try {
            // TODO add your handling code here:
            FriendManagementPage friendManagementPage = new FriendManagementPage(this, user);
            friendManagementPage.setLocationRelativeTo(null);
            friendManagementPage.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(ProfileManagementWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ProfileManagementWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_seeMoreActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jpanel1;
    private javax.swing.JButton bio;
    private java.awt.TextArea bioText;
    private javax.swing.JButton coverPhoto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton logout;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JButton profilePic;
    private javax.swing.JButton seeMore;
    private javax.swing.JButton updatePassword;
    // End of variables declaration//GEN-END:variables
}
