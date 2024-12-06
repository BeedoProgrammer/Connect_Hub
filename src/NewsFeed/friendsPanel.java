/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package NewsFeed;

import Backend.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import Utilities.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Abdel
 */
public class friendsPanel{
    ArrayList<User> myFriends;
    Dimension myDimensions;
    final Color backgroundColor = Color.lightGray;
    
    public friendsPanel(Dimension myDimension, ArrayList<User> myFriends) {
        this.myDimensions = myDimension;
        this.myFriends = myFriends;
    }
    
    private JPanel createFriendPanel(User myFriend){
      JPanel friendPanel = new JPanel();
      friendPanel.setLayout(new BoxLayout(friendPanel, BoxLayout.X_AXIS));
      friendPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      friendPanel.setBackground(backgroundColor);

      JLabel nameLabel = new JLabel(myFriend.getUsername());
      FontMetrics metrics = nameLabel.getFontMetrics(nameLabel.getFont());
      nameLabel.setPreferredSize(new Dimension((int) this.myDimensions.getWidth() - 40, metrics.getHeight() + 2));
      nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
      friendPanel.add(nameLabel);

      friendPanel.add(Box.createRigidArea(new Dimension(10, 0)));

      Color myColor = myFriend.isStatus() ? Color.GREEN : Color.RED;
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
      friendPanel.add(circlePanel);

      friendPanel.setPreferredSize(new Dimension((int) this.myDimensions.getWidth() - 10, 40));
      friendPanel.setMinimumSize(new Dimension((int) this.myDimensions.getWidth() - 10, 40));
      friendPanel.setMaximumSize(new Dimension((int) this.myDimensions.getWidth() - 10, 40));
      
      friendPanel.revalidate();
      friendPanel.repaint();
      return friendPanel;
    }
    
    public JScrollPane getFriendsScrollable(){
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        for (User friend : myFriends) {
            JPanel myFriendPanel = createFriendPanel(friend);
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
    
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Newsfeed");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 500);
        frame.setLayout(new BorderLayout());

        UserDatabase myD = UserDatabase.getInstance();
        try {
            myD.readFromFile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ArrayList<User> myFriends = myD.getUsers();
        myFriends.add(myFriends.get(0));
        myFriends.add(myFriends.get(0));
        myFriends.add(myFriends.get(0));
        myFriends.add(myFriends.get(0));
        myFriends.add(myFriends.get(0));
        myFriends.add(myFriends.get(0));
        myFriends.add(myFriends.get(0));
        myFriends.add(myFriends.get(0));
        myFriends.add(myFriends.get(1));
        myFriends.add(myFriends.get(0));
        myFriends.add(myFriends.get(0));

        friendsPanel FriendsPanel = new friendsPanel(new Dimension(120, 200), myFriends);
        JScrollPane scrollPane = FriendsPanel.getFriendsScrollable();
        scrollPane.setPreferredSize(new Dimension(120, 200));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane, BorderLayout.NORTH);
        frame.setVisible(true);
    }        
}