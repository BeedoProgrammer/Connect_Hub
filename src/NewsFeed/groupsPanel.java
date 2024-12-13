/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NewsFeed;
import Backend.AppManager.*;
import Backend.User;
import Groups.Group;
import Utilities.ModernScrollBarUI;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 *
 * @author ADMIN
 */
public class groupsPanel {
    private ArrayList<Group> groups;
    Dimension dimension;
    final Color backgroundColor = Color.lightGray;

    public groupsPanel (Dimension dimension, ArrayList<Group> groups) {
        this.groups = groups;
        this.dimension = dimension;
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
}
