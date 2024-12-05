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
public class previewerPanel extends javax.swing.JPanel {
    ArrayList<Content> myContent;
    Dimension myDimensions;
    
    public previewerPanel(int width, int height) {
        initComponents();
        myDimensions = new Dimension(width, height);
        // myContent = Database.getContent();
        // TEMP
        myContent = new ArrayList<>();
        myContent.add(new Post("123", "8451", "COntentttt", null));
        myContent.add(new Post("124", "8910", "COntent 2", null));
        
        
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
    
    private JPanel createPostPanel(Content myPost){
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        postPanel.setBackground(Color.lightGray);
        
        JLabel authorLabel = new JLabel(myPost.getAuthorId());
        authorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        postPanel.add(authorLabel);
        
        BufferedImage myImage = myPost.getContentImage();
        if (myImage != null){
            if(myImage.getWidth() > this.myDimensions.getWidth()){
                 myImage = ImageFunctions.resizeImage(myImage, (int)this.myDimensions.getWidth(), 0);                    
            }
            JLabel imageLabel = new JLabel(new ImageIcon(myImage));
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            imagePanel.add(imageLabel);
            imagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            postPanel.add(imagePanel);

        }
        
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
    
    public JScrollPane getContentScrollable(ArrayList<Content> myContent){
        JPanel previewPanel = new JPanel();
        previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.Y_AXIS));
//        previewPanel.setPreferredSize(this.myDimensions);
        
        for(Content i : myContent){
            JPanel contentPanel = createPostPanel(i);
            contentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            previewPanel.add(contentPanel);
            contentPanel.setVisible(true);
        }
        
        previewPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        previewPanel.revalidate();
        previewPanel.repaint();

        JScrollPane scrollPane = new JScrollPane(previewPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        previewPanel.revalidate();
        previewPanel.repaint();

        return scrollPane;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args) throws IOException {
        // Example usage
        JFrame frame = new JFrame("Newsfeed");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);


        BufferedImage sampleImage = ImageIO.read(new File("picture.jpg"));
        BufferedImage sampleImage2 = ImageIO.read(new File("picture2.jpg"));
        
        // Create a sample post panel
        Post myPost = new Post("First Post","John Doe" ,"This is a sample post text.", sampleImage);
        Post myPost2 = new Post("First Post","Joe" ,"This is a sample post text.", null);
        
//        System.out.println("LAST: " + myP.getPreferredSize());
        ArrayList<Content> myContent = new ArrayList<>();
        myContent.add(myPost);
        myContent.add(myPost2);
        myContent.add(new Story("3rd Post","Abdo" ,"This is a sample Story text.", sampleImage2));
        myContent.add(myPost2);
        myContent.add(myPost2);
        myContent.add(myPost2);
        myContent.add(myPost2);
        myContent.add(myPost2);
        myContent.add(myPost2);
        myContent.add(myPost2);
        myContent.add(myPost2);
        myContent.add(myPost2);
        
        previewerPanel myPanel = new previewerPanel(400, 600);
//        JPanel myP = myPanel.createPostPanel(myPost2);
//        JPanel post = myPanel.showContent(myPost);
//        JPanel post2 = myPanel.showContent(myPost2);
        JScrollPane preview = myPanel.getContentScrollable(myContent);
        
        
//        frame.getContentPane().add(post);
        frame.add(preview, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
