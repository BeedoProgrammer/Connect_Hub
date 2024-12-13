/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package NewsFeed;

import Database.UserDatabase;
import Backend.*;
import Frontend.*;
import Database.GroupDatabase;
import Frontend.CreateContentWindow;
import Frontend.FriendManagementPage;
import Utilities.ImageFunctions;
import java.awt.Component;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.json.simple.parser.ParseException;

public class NewsFeedWindow extends javax.swing.JFrame {
    NewsFeed myFeed;
    
    public NewsFeedWindow(User currentUser) {
        myFeed = new NewsFeed(currentUser.getUserId());
        
        initComponents();
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                // todo your log out code here
                currentUser.changeStatus();
            }
        });
    }
    
    private void loadDataPanels(){
        storiesPanel = new contentPanel(new Dimension(100, 100), this.myFeed.getStoryList()).getContentScrollable();
        postsPanel = new contentPanel(new Dimension(100, 100), this.myFeed.getPostList()).getContentScrollable();
        friendsPanel = new friendsPanel(new Dimension(170, 200), this.myFeed.getFriendList()).getFriendsScrollable();
        suggestionsPanel = new SuggestionsPanel(new Dimension(170, 100), myFeed.getCurrentUser(), myFeed.getFriendSuggestions()).getSuggestionsPanel();
        groupsPanel = new groupsPanel(new Dimension(170, 100), this.myFeed.getGroupsList(), myFeed.getCurrentUser(), this).getGroupsScrollable();
    }
    
    private JPanel getProfilePicture(int width, int height) throws IOException{
        BufferedImage myImage = ImageIO.read(new File(myFeed.getCurrentUser().getProfilePic()));
        if(myImage.getWidth() > width || myImage.getHeight() > height){
             myImage = ImageFunctions.resizeImage(myImage, width, height);                    
        }else if(myImage.getWidth() > width){
            myImage = ImageFunctions.resizeImage(myImage, width, 0);
        }else if(myImage.getHeight()> height){
            myImage = ImageFunctions.resizeImage(myImage, 0, height);
        }
        JLabel imageLabel = new JLabel(new ImageIcon(myImage));
        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        imagePanel.add(imageLabel);
        imagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return imagePanel;
    }
    
    private void initComponents() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        profilePanel = new javax.swing.JPanel();
        refreshButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        newContentButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        Notifications = new javax.swing.JButton();
        loadDataPanels();
        
        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 700));

        javax.swing.GroupLayout profilePanelLayout = new javax.swing.GroupLayout(profilePanel);
        try {
            profilePanel = getProfilePicture(250, 150);
        } catch (IOException ex) {
            profilePanel.setBackground(new java.awt.Color(0, 0, 0));
            profilePanel.setLayout(profilePanelLayout);
        }

        profilePanelLayout.setHorizontalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );
        profilePanelLayout.setVerticalGroup(
            profilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 92, Short.MAX_VALUE)
        );
        
        profilePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                profileMouseClickEvent();
            }
        });

        refreshButton.setText("Refresh");
        refreshButton.setPreferredSize(new java.awt.Dimension(90, 23));
        
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshButtonPressed();
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Stories");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Suggestions");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Posts");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Friends");
        
        jLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                friendsMouseClickEvent();
            }
        });

        javax.swing.GroupLayout suggestionsPanelLayout = new javax.swing.GroupLayout(suggestionsPanel);
//        suggestionsPanel.setLayout(suggestionsPanelLayout);
        suggestionsPanelLayout.setHorizontalGroup(
            suggestionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
        );
        suggestionsPanelLayout.setVerticalGroup(
            suggestionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );

        newContentButton.setText("Craete New Content");
        newContentButton.setPreferredSize(new java.awt.Dimension(90, 23));
        newContentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newButtonPressed();
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Groups");

        searchButton.setText("Search");
        searchButton.setPreferredSize(new java.awt.Dimension(90, 23));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButtonPressed();
            }
        });

        Notifications.setText("Notifications");
        Notifications.setPreferredSize(new java.awt.Dimension(90, 23));    
        Notifications.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notificationsButtonPressed();
            }
        });
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(postsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
                        .addComponent(storiesPanel))
                    .addComponent(profilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                    .addComponent(newContentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Notifications, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel5))
                            .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel2))
                            .addGap(116, 116, 116)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(groupsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(friendsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(suggestionsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(profilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(newContentButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Notifications, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(storiesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(postsPanel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(friendsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(suggestionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 36, Short.MAX_VALUE)))
                .addGap(16, 16, 16))
        );

        pack();
    }
    
    private void profileMouseClickEvent(){
        Frontend.ProfileManagementWindow profile = new Frontend.ProfileManagementWindow("My Profile", this,this.myFeed.getCurrentUser());
        profile.setLocationRelativeTo(null);
        profile.setVisible(true);
        this.setVisible(false);
    }
    
    private void friendsMouseClickEvent(){
        try{
            System.out.println(this.myFeed.getCurrentUser());
            FriendManagementPage myPage = new FriendManagementPage(this, this.myFeed.getCurrentUser());
            myPage.setLocationRelativeTo(null);
            myPage.setVisible(true);
            this.setVisible(false);
        }catch(Exception e){
            this.setVisible(true);
        }
    }
    
    private void newButtonPressed(){
        JDialog newWindow = new CreateContentWindow(this, this.myFeed.getCurrentUser(), null);
        newWindow.setLocationRelativeTo(null);
        newWindow.setVisible(true);
        newWindow.dispose();
        refreshButtonPressed();
    }
    
    private void refreshButtonPressed(){
        NewsFeedWindow myWindow = new NewsFeedWindow(this.myFeed.getCurrentUser());
        myWindow.setLocationRelativeTo(null);
        myWindow.setVisible(true);
        this.dispose();
    }
    
    private void searchButtonPressed(){
        try {
            SearchBarWindow myWindow = new SearchBarWindow(this.myFeed.getCurrentUser());
            myWindow.setLocationRelativeTo(null);
            myWindow.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(NewsFeedWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(NewsFeedWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void notificationsButtonPressed(){
        try {
            NotificationFront myWindow = new NotificationFront();
            myWindow.setLocationRelativeTo(null);
            myWindow.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(NewsFeedWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(NewsFeedWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
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
                    GroupDatabase myGrD = GroupDatabase.getInstance();
                    
                    new NewsFeedWindow(myD.getUsers().get(0)).setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JScrollPane friendsPanel;
    private javax.swing.JScrollPane groupsPanel;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton newContentButton;
    private javax.swing.JScrollPane postsPanel;
    private javax.swing.JPanel profilePanel;
    private javax.swing.JButton refreshButton;
    private javax.swing.JScrollPane storiesPanel;
    private javax.swing.JPanel suggestionsPanel;
    private javax.swing.JButton Notifications;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton searchButton;

    // End of variables declaration                   
}
