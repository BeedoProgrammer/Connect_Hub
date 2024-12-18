package Frontend;

import Database.StoryDatabase;
import Database.PostDatabase;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import Backend.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.parser.ParseException;


public class CreateContentWindow extends javax.swing.JDialog {

    private String imagePath;
    private User currentUser;
    private Long groupID;
    
    public CreateContentWindow(JFrame parent, User currentUser, Long groupID) {
        super(parent, "Create New Content");
        this.setModal(true);
        initComponents();
        this.imagePath = "";
        this.currentUser = currentUser;
        this.groupID = groupID;
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        textContent = new javax.swing.JTextField();
        uploadImageButton = new javax.swing.JButton();
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcoin = new ImageIcon(imagePath);
                Image image = imageIcoin.getImage();
                int x = (this.getWidth() - image.getWidth(null)) / 2;
                int y = (this.getHeight()- image.getHeight(null)) / 2;
                if (image != null) {

                    g.drawImage(image, x, y, this);
                }
                else {
                    g.clearRect(x, y, image.getWidth(null), image.getHeight(null));
                }
            }
        };
        jLabel2 = new javax.swing.JLabel();
        createPostButton = new javax.swing.JButton();
        createStorybutton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        clearImageButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("What is on your mind");

        textContent.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        textContent.setOpaque(true);

        uploadImageButton.setText("Upload Image (optional)");
        uploadImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadImageButtonActionPerformed(evt);
            }
        });

        panel.setBackground(new java.awt.Color(204, 204, 204));
        panel.setToolTipText("Click to upload image");
        panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 547, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        jLabel2.setText("image preview");

        createPostButton.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        createPostButton.setText("Create Post");
        createPostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPostButtonActionPerformed(evt);
            }
        });

        createStorybutton.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        createStorybutton.setText("Create Story");
        createStorybutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createStorybuttonActionPerformed(evt);
            }
        });

        jLabel3.setText("Stories last 24 hours");

        clearImageButton.setText("Clear Image");
        clearImageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearImageButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(textContent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(createPostButton, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(createStorybutton, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(clearImageButton)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(uploadImageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 53, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(textContent, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uploadImageButton)
                .addGap(18, 18, 18)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(clearImageButton))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createPostButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createStorybutton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void uploadImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadImageButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        int r = chooser.showDialog(null,"Open");
        if (r == JFileChooser.APPROVE_OPTION) {
            imagePath = chooser.getSelectedFile().getAbsolutePath();
            panel.repaint();
        }
    }//GEN-LAST:event_uploadImageButtonActionPerformed

    private long generatePostId(PostDatabase postDb) {
        Random random = new Random();
        long generatedId;
        do {            
            generatedId = Math.abs(random.nextLong());
        } while (postDb.getPostFromId(generatedId) != null);
        return generatedId;
    }
     private long generateStoryId(StoryDatabase storyDb) {
        Random random = new Random();
        long generatedId;
        do {            
            generatedId = Math.abs(random.nextLong());
        } while (storyDb.getStoryFromId(generatedId) != null);
        return generatedId;
    }
    
    private void createPostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPostButtonActionPerformed
        try {                                                 
            // TODO add your handling code here:
            PostDatabase postDb = PostDatabase.getInstance();
            long postId = generatePostId(postDb);
            Content post = new Post(postId, currentUser.getUserId(), textContent.getText(), imagePath, groupID);
            try {
                postDb.addPost((Post) post);
                // return to main window
            } catch (IOException ex) {
                Logger.getLogger(CreateContentWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(CreateContentWindow.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                this.dispose();
            }
        } catch (IOException ex) {
            Logger.getLogger(CreateContentWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CreateContentWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_createPostButtonActionPerformed

    private void clearImageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearImageButtonActionPerformed
        // TODO add your handling code here:
        imagePath = "";
        panel.repaint();
    }//GEN-LAST:event_clearImageButtonActionPerformed

    private void createStorybuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createStorybuttonActionPerformed
        try {                                                  
            // TODO add your handling code here:
            StoryDatabase storyDb = StoryDatabase.getInstance();
            long generatedId = generateStoryId(storyDb);
            Content story = new Story(generatedId, currentUser.getUserId(), textContent.getText(), imagePath, groupID);
            try {
                storyDb.addStory((Story)story);
            } catch (IOException ex) {
                Logger.getLogger(CreateContentWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(CreateContentWindow.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                this.dispose();
            }
        } catch (IOException ex) {
            Logger.getLogger(CreateContentWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(CreateContentWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_createStorybuttonActionPerformed

    private void panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseClicked
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        int r = chooser.showDialog(null,"Open");
        if (r == JFileChooser.APPROVE_OPTION) {
            imagePath = chooser.getSelectedFile().getAbsolutePath();
            panel.repaint();
        }
    }//GEN-LAST:event_panelMouseClicked

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CreateContentWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CreateContentWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CreateContentWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CreateContentWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CreateContentWindow().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearImageButton;
    private javax.swing.JButton createPostButton;
    private javax.swing.JButton createStorybutton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField textContent;
    private javax.swing.JButton uploadImageButton;
    // End of variables declaration//GEN-END:variables
}
