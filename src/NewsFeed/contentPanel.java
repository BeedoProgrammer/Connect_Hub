/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package NewsFeed;

import Database.UserDatabase;
import Backend.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import Utilities.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Abdel
 */
public class contentPanel{
    ArrayList<Content> myContent;
    Dimension myDimensions;
    
    public contentPanel(Dimension myDimension, ArrayList<Content> myContent) {
        this.myDimensions = myDimension;
        this.myContent = myContent;
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
    
    private JPanel createPostPanel(Content myPost) throws Exception{
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));        
        UserDatabase myD = UserDatabase.getInstance();
        
        String name = myD.getUserFromId(myPost.getAuthorId()).getUsername();
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel authorLabel = new JLabel(name);
        authorLabel.setHorizontalAlignment(SwingConstants.LEFT);
        
        LocalDateTime contentTime = myPost.getTimestamp();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        JLabel dateTimeLabel = new JLabel(contentTime.format(formatter));     
        dateTimeLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        headerPanel.add(authorLabel, BorderLayout.WEST);
        headerPanel.add(dateTimeLabel, BorderLayout.EAST);

        postPanel.add(headerPanel);
        BufferedImage myImage = null;
        try{
            myImage = ImageIO.read(new File(myPost.getContentImagePath()));
            if(myImage.getWidth() > this.myDimensions.getWidth()){
                 myImage = ImageFunctions.resizeImage(myImage, (int)this.myDimensions.getWidth(), 0);                    
            }
            JLabel imageLabel = new JLabel(new ImageIcon(myImage));
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            imagePanel.add(imageLabel);
            imagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            postPanel.add(imagePanel);
        }catch(Exception e){}
        
        JTextArea postText = new JTextArea(myPost.getContentString());
        postText.setLineWrap(true);
        postText.setWrapStyleWord(true);
        postText.setEditable(false);
        postText.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        postPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        postPanel.add(postText);
        
        int height = this.getTotalHeight(authorLabel, myImage, postText);
        postPanel.setPreferredSize(new Dimension(this.myDimensions.width, height));
        postPanel.revalidate();
        postPanel.repaint();
        return postPanel;
    }
    
    public JScrollPane getContentScrollable(){
        JPanel previewPanel = new JPanel();
        previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.Y_AXIS));        
        for(Content i : myContent){
            try{
                JPanel contentPanel = createPostPanel(i);
                contentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
                previewPanel.add(contentPanel);
                contentPanel.setVisible(true);
            }catch(Exception e){}
        }
        
        previewPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        previewPanel.revalidate();
        previewPanel.repaint();

        JScrollPane scrollPane = new JScrollPane(previewPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scrollPane.revalidate();
        scrollPane.repaint();

        return scrollPane;
    }
    
    public static void main(String[] args) throws IOException {
        // Example usage
        JFrame frame = new JFrame("Newsfeed");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        
        Post myPost = new Post(1250,1L ,"This is a sample post text.", "picture.jpg");
        Post myPost2 = new Post(5120,1L ,"This is a sample post text.", null);
        
        ArrayList<Content> myContent = new ArrayList<>();
        myContent.add(myPost);
        myContent.add(myPost2);
        myContent.add(new Story(1231,1L ,"This is a sample Story text.", "picture2.jpg"));
        myContent.add(myPost2);
        myContent.add(myPost2);
        myContent.add(myPost2);
        myContent.add(myPost2);
        myContent.add(myPost2);
        myContent.add(myPost2);
        myContent.add(myPost2);
        myContent.add(myPost2);
        myContent.add(myPost2);
        
        JScrollPane preview =  new contentPanel(new Dimension(400, 600), myContent).getContentScrollable();
        
        frame.add(preview, BorderLayout.CENTER);
        frame.setVisible(true);
    }
              
}
