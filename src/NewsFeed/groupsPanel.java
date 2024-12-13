/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NewsFeed;
import Backend.User;
import Groups.Group;
import Groups.GroupWindow;
import Utilities.ModernScrollBarUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 *
 * @author ADMIN
 */
public class groupsPanel {
    private ArrayList<Group> groups;
    private User currentUser;
    private Dimension dimension;
    private JFrame parent;
    final Color backgroundColor = Color.lightGray;

    public groupsPanel (Dimension dimension, ArrayList<Group> groups, User currentUser, JFrame parent) {
        this.groups = groups;
        this.dimension = dimension;
        this.currentUser = currentUser;
        this.parent = parent;
    }
    
    private JPanel createGroupPanel(Group group) {
        JPanel groupPanel = new JPanel();
        groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.X_AXIS));
        groupPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        groupPanel.setBackground(backgroundColor);

        JLabel nameLabel = new JLabel(group.getName());
        FontMetrics metrics = nameLabel.getFontMetrics(nameLabel.getFont());
        nameLabel.setPreferredSize(new Dimension((int) this.dimension.getWidth() - 40, metrics.getHeight() + 2));
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        groupPanel.add(nameLabel);

        groupPanel.add(Box.createRigidArea(new Dimension(10, 0)));

        groupPanel.setPreferredSize(new Dimension((int) this.dimension.getWidth() - 10, 40));
        groupPanel.setMinimumSize(new Dimension((int) this.dimension.getWidth() - 10, 40));
        groupPanel.setMaximumSize(new Dimension((int) this.dimension.getWidth() - 10, 40));

        groupPanel.revalidate();
        groupPanel.repaint();
        
        groupPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) { // Left mouse button
                    System.out.println("Panel clicked!");
                    createGroupWindow(group);
                }
            }
        });
        
        return groupPanel;
        
    }
    public JScrollPane getGroupsScrollable() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        for (Group group : groups) {
            JPanel myFriendPanel = createGroupPanel(group);
            myFriendPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
            container.add(Box.createRigidArea(new Dimension(0, 10)));
            container.add(myFriendPanel);
        }
        
        container.revalidate();
        container.repaint();

        JScrollPane scrollPane = new JScrollPane(container, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scrollPane.setBackground(Color.CYAN);
        scrollPane.setPreferredSize(this.dimension);
        scrollPane.revalidate();
        scrollPane.repaint();

        return scrollPane;
    }
    
    private void createGroupWindow(Group myGroup){
        JFrame newWindow = new GroupWindow(this.currentUser, myGroup, this.parent);
        newWindow.setVisible(true);
        parent.setVisible(false);
    }
}
