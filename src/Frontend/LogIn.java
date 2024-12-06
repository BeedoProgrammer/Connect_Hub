package frontend;

import Backend.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import java.util.*;
import org.json.simple.parser.ParseException;

public class LogIn extends javax.swing.JFrame {
    private UserDatabase userDatabase;
    
    public LogIn(String title) throws IOException, FileNotFoundException, ParseException {
        super(title);
        userDatabase = UserDatabase.getInstance();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        email = new javax.swing.JTextField();
        Jpanel1 = new javax.swing.JLabel();
        Jpanel2 = new javax.swing.JLabel();
        login = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        Jpanel1.setBackground(new java.awt.Color(0, 0, 0));
        Jpanel1.setForeground(new java.awt.Color(255, 255, 255));
        Jpanel1.setText("             Email");
        Jpanel1.setOpaque(true);

        Jpanel2.setBackground(new java.awt.Color(0, 0, 0));
        Jpanel2.setForeground(new java.awt.Color(255, 255, 255));
        Jpanel2.setText("           Password");
        Jpanel2.setOpaque(true);

        login.setBackground(new java.awt.Color(0, 0, 0));
        login.setForeground(new java.awt.Color(255, 255, 255));
        login.setText("Login");
        login.setFocusPainted(false);
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Jpanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(Jpanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(password))
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Jpanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        char[] Pass =  password.getPassword();
        
        if(Validation.isEmpty(email.getText()) || Validation.isEmpty(Pass.toString()))
            JOptionPane.showMessageDialog(rootPane, "Enter all fileds!");
        else{
            ArrayList<User> userData = userDatabase.getUsers();
            User user = null;
            for(int i = 0; i < userData.size(); i++){
                if(email.getText().equals(userData.get(i).getEmail())){
                    user = userData.get(i);
                    break;
                }
            }
            if(user == null)
                JOptionPane.showMessageDialog(rootPane, "There is no account with this email");
            else if(!email.getText().equals(user.getEmail()) || !Arrays.equals(Pass, user.getPassword()))
                JOptionPane.showMessageDialog(rootPane, "Wrong email or password!");
            else{
                //this.dispose();
            }  
        }
    }//GEN-LAST:event_loginActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jpanel1;
    private javax.swing.JLabel Jpanel2;
    private javax.swing.JTextField email;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField password;
    // End of variables declaration//GEN-END:variables
}
