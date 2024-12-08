package frontend;

import Backend.*;
import NewsFeed.NewsFeedWindow;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import org.json.simple.parser.ParseException;

public class SignUp extends javax.swing.JDialog {
    private UserDatabase userDatabase;
    private User currentUser;
    private JFrame parent;
    
    public SignUp(JFrame parent, String title) throws IOException, FileNotFoundException, ParseException {
        super(parent, title);
        this.setModal(true);
        userDatabase = UserDatabase.getInstance();
        this.parent = parent;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        username = new javax.swing.JTextField();
        Jpanel1 = new javax.swing.JLabel();
        Jpanel2 = new javax.swing.JLabel();
        signup = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();
        Jpanel3 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        Jpanel4 = new javax.swing.JLabel();
        password1 = new javax.swing.JPasswordField();
        Jpanel5 = new javax.swing.JLabel();
        date = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Jpanel1.setBackground(new java.awt.Color(0, 0, 0));
        Jpanel1.setForeground(new java.awt.Color(255, 255, 255));
        Jpanel1.setText("          Username");
        Jpanel1.setOpaque(true);

        Jpanel2.setBackground(new java.awt.Color(0, 0, 0));
        Jpanel2.setForeground(new java.awt.Color(255, 255, 255));
        Jpanel2.setText("           Password");
        Jpanel2.setOpaque(true);

        signup.setBackground(new java.awt.Color(0, 0, 0));
        signup.setForeground(new java.awt.Color(255, 255, 255));
        signup.setText("Sign Up");
        signup.setFocusPainted(false);
        signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupActionPerformed(evt);
            }
        });

        Jpanel3.setBackground(new java.awt.Color(0, 0, 0));
        Jpanel3.setForeground(new java.awt.Color(255, 255, 255));
        Jpanel3.setText("             Email");
        Jpanel3.setOpaque(true);

        Jpanel4.setBackground(new java.awt.Color(0, 0, 0));
        Jpanel4.setForeground(new java.awt.Color(255, 255, 255));
        Jpanel4.setText("   Confirm Password");
        Jpanel4.setOpaque(true);

        Jpanel5.setBackground(new java.awt.Color(0, 0, 0));
        Jpanel5.setForeground(new java.awt.Color(255, 255, 255));
        Jpanel5.setText("         Birth Date");
        Jpanel5.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Jpanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(Jpanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Jpanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                    .addComponent(Jpanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Jpanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(username, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(password)
                    .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(password1)
                    .addComponent(date, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(signup, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Jpanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Jpanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Jpanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Jpanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Jpanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(signup, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupActionPerformed
        // TODO add your handling code here:
        char[] Pass = password.getPassword();
        char[] Pass1 = password1.getPassword();
        // overide method valideFields
        if(Validation.isEmpty(username.getText()) || Validation.isEmpty(Pass.toString()) || Validation.isEmpty(email.getText()) || Validation.isEmpty(Pass1.toString()) || date.getDate() == null)
            JOptionPane.showMessageDialog(rootPane, "Enter all fileds!");
        else if(!Validation.isValidEmail(email.getText()))
            JOptionPane.showMessageDialog(rootPane, "Enter correct Email!");
        else if(!Validation.isValidUsername(username.getText()))
            JOptionPane.showMessageDialog(rootPane, "Username must be atleast 5 characters and must contain atleast one letter and one number(no special characters allowed except underscore)!");
        else if(!Arrays.equals(Pass, Pass1))
            JOptionPane.showMessageDialog(rootPane, "Please enter the same password");
        else{
            boolean exists = false;
            ArrayList<User> userData = userDatabase.getUsers();                     /// can check using getUser
            for(int i = 0; i < userData.size(); i++){
                if(username.getText().equals(userData.get(i).getUsername())){
                    exists = true;
                    break;
                }
            }
            
            if(exists){
                JOptionPane.showMessageDialog(rootPane, "Username already taken");
            }
            else{
                try {
                    Random random = new Random();
                    long uniqueId;
                    
                    do {
                        uniqueId = Math.abs(random.nextLong());
                    }while(userDatabase.getUserFromId(uniqueId) != null);
                    
                    LocalDate dateOfBirth = date.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    
                    User user = new User.UserBuilder(uniqueId, email.getText(), username.getText(), Pass1, dateOfBirth, true).build();
                    userDatabase.addUser(user);
                    this.dispose();
                    parent.dispose();
                    NewsFeedWindow mainWindow = new NewsFeedWindow(user);
                    mainWindow.setVisible(true);
                    mainWindow.setLocationRelativeTo(null);
                } catch (IOException ex) {
                    Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_signupActionPerformed

    public User getCurrentUser(){
        return this.currentUser;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Jpanel1;
    private javax.swing.JLabel Jpanel2;
    private javax.swing.JLabel Jpanel3;
    private javax.swing.JLabel Jpanel4;
    private javax.swing.JLabel Jpanel5;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JTextField email;
    private javax.swing.JPasswordField password;
    private javax.swing.JPasswordField password1;
    private javax.swing.JButton signup;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
