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
    ArrayList<Friend> myFriends;
    Dimension myDimensions;
    
    public friendsPanel(Dimension myDimension, ArrayList<Friend> myFriends) {
        this.myDimensions = myDimension;
        this.myFriends = myFriends;
    }
    
    private int getTotalHeight(JLabel authorLabel, BufferedImage image, JTextArea postText){
        int lineCount = postText.getLineCount();
        FontMetrics metrics = postText.getFontMetrics(postText.getFont());
        int lineHeight = metrics.getHeight();
        int textHeight = lineCount * lineHeight;
        
        int imageHeight = 0;
        if (image != null) {
            imageHeight = image.getHeight();
        }
        metrics = authorLabel.getFontMetrics(authorLabel.getFont());
        int labelHeight = metrics.getHeight();
        int padding = 30;
                
        return textHeight + imageHeight + labelHeight + padding;
    }
    
    private JPanel createFriendPanel(Content myPost){
        
    }
    
    public JScrollPane getContentScrollable(){
        JPanel previewPanel = new JPanel();
        previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.Y_AXIS));        
       
        
        
        previewPanel.revalidate();
        previewPanel.repaint();

        JScrollPane scrollPane = new JScrollPane(previewPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        previewPanel.revalidate();
        previewPanel.repaint();

        return scrollPane;
    }
    
    public static void main(String[] args) throws IOException {
        // Example usage
        JFrame frame = new JFrame("Newsfeed");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        
        frame.add(preview, BorderLayout.CENTER);
        frame.setVisible(true);
    }
              
}
