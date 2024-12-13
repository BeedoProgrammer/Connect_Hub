/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Groups;

import Backend.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import Utilities.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Abdel
 */
public class membersPanel{
    ArrayList<User> myMembers;
    Dimension myDimensions;
    User currentUser;
    Group myGroup;
    final Color backgroundColor = Color.lightGray;
    
    public membersPanel(Dimension myDimension, ArrayList<User> myMembers, User currentUser, Group myGroup) {
        this.myDimensions = myDimension;
        this.myMembers = myMembers;
        this.currentUser = currentUser;
        this.myGroup = myGroup;
    }
    
    private JPanel createMemberPanel(User member){
        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.X_AXIS));
        memberPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        memberPanel.setBackground(backgroundColor);

        JLabel nameLabel = new JLabel(member.getUsername());
        FontMetrics metrics = nameLabel.getFontMetrics(nameLabel.getFont());
        nameLabel.setPreferredSize(new Dimension((int) this.myDimensions.getWidth() - 40, metrics.getHeight() + 2));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        memberPanel.add(nameLabel);

        memberPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        Color myColor = member.isStatus() ? Color.GREEN : Color.RED;
        JPanel circlePanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(myColor);
                g.fillOval(0, 0, 20, 20);
            }
        };
        circlePanel.setPreferredSize(new Dimension(20, 20));
        circlePanel.setMinimumSize(new Dimension(20, 20));
        circlePanel.setMaximumSize(new Dimension(20, 20));
        circlePanel.setOpaque(false);
        memberPanel.add(circlePanel);

        memberPanel.setPreferredSize(new Dimension((int) this.myDimensions.getWidth() - 10, 40));
        memberPanel.setMinimumSize(new Dimension((int) this.myDimensions.getWidth() - 10, 40));
        memberPanel.setMaximumSize(new Dimension((int) this.myDimensions.getWidth() - 10, 40));
      
  
        Button editButton = new Button();
        editButton.setLabel("Edit");
        editButton.setPreferredSize(new Dimension(20, 40));
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the editUser function
                editUser();
            }
        });
      
        memberPanel.add(editButton);
        
        memberPanel.revalidate();
        memberPanel.repaint();
        return memberPanel;
    }
    
    private void editUser(){
//        if(currentUser.getGroupRelationStatus() == GroupDetails.CREATOR){
            
//        }
    }
    
    public JScrollPane getFriendsScrollable(){
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        for (User friend : myMembers) {
            JPanel myFriendPanel = createMemberPanel(friend);
            myFriendPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            container.add(Box.createRigidArea(new Dimension(0, 10)));
            container.add(myFriendPanel);
        }
        
        container.revalidate();
        container.repaint();

        JScrollPane scrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scrollPane.setBackground(Color.CYAN);
        scrollPane.setPreferredSize(this.myDimensions);
        scrollPane.revalidate();
        scrollPane.repaint();

        return scrollPane;
    }    
}
