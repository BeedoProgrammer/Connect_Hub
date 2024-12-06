/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import java.util.HashMap;
import javax.swing.border.EmptyBorder;

public class SuggestionsPanel{
    private ArrayList<User> mySugesstions;
    private User currentUser;
    private Dimension myDimensions;
    private JPanel SuggestionsPanel;
    private int currentSuggestionIndex = 0;
    
    public SuggestionsPanel(Dimension myDimension, User currentUser, ArrayList<User> Suggested) {
        this.myDimensions = myDimension;
        this.currentUser = currentUser;
        mySugesstions = Suggested;
        SuggestionsPanel = new JPanel();
        SuggestionsPanel.setLayout(new BoxLayout(SuggestionsPanel, BoxLayout.Y_AXIS));
        populateSuggestionsPanel();
    }
    
    private JPanel createsuggestedPanel(User Suggested){
        JPanel suggestionPanel = new JPanel();
        suggestionPanel.setLayout(new BoxLayout(suggestionPanel, BoxLayout.Y_AXIS));

        BufferedImage myImage = null;
        try{
            myImage = ImageIO.read(new File(Suggested.getCoverPhoto()));
            if(myImage.getWidth() > this.myDimensions.getWidth()){
                 myImage = ImageFunctions.resizeImage(myImage, (int)this.myDimensions.getWidth(), 0);                    
            }
            JLabel imageLabel = new JLabel(new ImageIcon(myImage));
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            imagePanel.add(imageLabel);
            imagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            suggestionPanel.add(imagePanel);
        }catch(Exception e){}
        
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.X_AXIS));
        detailsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameLabel = new JLabel(Suggested.getUsername());
        usernameLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        detailsPanel.add(usernameLabel);
        detailsPanel.add(Box.createHorizontalStrut(10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        JButton addFriendButton = new JButton("Add Friend");
        JButton declineButton = new JButton("Decline");
        
        Dimension myDimension = new Dimension((int) (this.myDimensions.getWidth()* 2/3), 30);

        addFriendButton.setPreferredSize(myDimension);
        addFriendButton.setMaximumSize(myDimension);
        addFriendButton.setMinimumSize(myDimension);
        declineButton.setPreferredSize(myDimension);
        declineButton.setMaximumSize(myDimension);
        declineButton.setMinimumSize(myDimension);
        
        addFriendButton.addActionListener(e -> {
            FriendManagement.sendFriendRequest(currentUser, Suggested); //ALTERING TO PENDING RELATION
            System.out.println("Friend request sent to " + Suggested.getUsername());
            mySugesstions.remove(Suggested); // Remove from candidates
            populateSuggestionsPanel();
        });
        declineButton.addActionListener(e -> {
            System.out.println("Declined suggestion: " + Suggested.getUsername());
            mySugesstions.remove(Suggested); // Remove from candidates
            populateSuggestionsPanel(); 
        });
        
        buttonPanel.add(addFriendButton);
        buttonPanel.add(Box.createVerticalStrut(5));
        buttonPanel.add(declineButton);

        detailsPanel.add(buttonPanel);

        JPanel centeredDetailsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        centeredDetailsPanel.add(detailsPanel);

        suggestionPanel.add(centeredDetailsPanel);

        return suggestionPanel;
    }
    
    private void populateSuggestionsPanel() {
        SuggestionsPanel.removeAll();
        SuggestionsPanel.setLayout(new CardLayout());
        CardLayout cardLayout = (CardLayout) SuggestionsPanel.getLayout();

        for (int i = 0; i < mySugesstions.size(); i++) {
            User user = mySugesstions.get(i);
            JPanel suggestionPanel = createsuggestedPanel(user);
            SuggestionsPanel.add(suggestionPanel, "Suggestion " + i);
        }
        cardLayout.show(SuggestionsPanel, "Suggestion 0");
        SuggestionsPanel.revalidate();
        SuggestionsPanel.repaint();
    }
    
    private void showNextSuggestion() {
        CardLayout cardLayout = (CardLayout) SuggestionsPanel.getLayout();
        int nextSuggestionIndex = (currentSuggestionIndex + 1) % mySugesstions.size();
        cardLayout.show(SuggestionsPanel, "Suggestion " + nextSuggestionIndex);
        currentSuggestionIndex = nextSuggestionIndex;
        this.SuggestionsPanel.repaint();
    }

    public JPanel getSuggestionsPanel() {
        return SuggestionsPanel;
    }
    
    public JScrollPane getScrollPane(){
        return new JScrollPane(getSuggestionsPanel());
    }
    
    public static void main(String[] args){
        JFrame frame = new JFrame("Newsfeed");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 600);
        
        try {
            UserDatabase myD = UserDatabase.getInstance();
            myD.readFromFile();
            User currentUser = myD.getUsers().get(0);
            SuggestionsPanel mySP = new SuggestionsPanel(new Dimension(100, 100), myD.getUsers().get(0), myD.getUsers());
            JPanel myPanel = mySP.getSuggestionsPanel();
            frame.add(myPanel, BorderLayout.NORTH);
        } catch (Exception ex) {}
        
        frame.setVisible(true);
    }
}
