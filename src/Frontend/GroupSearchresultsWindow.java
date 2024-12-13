package Frontend;

import Groups.*;
import Backend.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class GroupSearchresultsWindow extends javax.swing.JFrame {
    
    private ArrayList<Group> suggestedGroups; // Users who are suggested based on the friendship status
    private ArrayList<Group> acceptedGroups;
    private User CurrentUser;
    private GroupSearch GroupOption;
    private JPanel SuggestionPanel;
    private JPanel AcceptedPanel;
    
    public GroupSearchresultsWindow(User CurrentUser, GroupSearch GroupOption) {
        initComponents();
        this.CurrentUser = CurrentUser;
        this.GroupOption = GroupOption;
        suggestedGroups = new ArrayList<>();
        acceptedGroups = new ArrayList<>();
        suggestedGroups = GroupOption.unjoinedGroups();
        acceptedGroups = GroupOption.joinedGroups();
        setTitle("Search Group Results");
        setLayout(new BorderLayout());
        SuggestionPanel = new JPanel();
        AcceptedPanel = new JPanel();
        SuggestionPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); 
        AcceptedPanel.setLayout(new BoxLayout(AcceptedPanel, BoxLayout.Y_AXIS));
        JScrollPane suggestionScrollPane = new JScrollPane(SuggestionPanel);
        suggestionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        suggestionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); 
        JScrollPane acceptedScrollPane = new JScrollPane(AcceptedPanel);
        JPanel suggestionSection = createLabeledSection("Suggested Groups", suggestionScrollPane);
        JPanel acceptedSection = createLabeledSection("Accepted Groups", acceptedScrollPane);
        JPanel mainPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        mainPanel.add(suggestionSection);
        mainPanel.add(acceptedSection);
        add(mainPanel, BorderLayout.CENTER);
        displaySuggestedGroups();
        displayAcceptedGroups();
        pack();
        setVisible(true);
    }

    private JPanel createLabeledSection(String title, JScrollPane content) {
        JPanel sectionPanel = new JPanel();
        sectionPanel.setLayout(new BorderLayout());
        JLabel sectionLabel = new JLabel(title, JLabel.CENTER);
        sectionPanel.add(sectionLabel, BorderLayout.NORTH);
        sectionPanel.add(content, BorderLayout.CENTER);
        return sectionPanel;
    }
    
    private void displayAcceptedGroups() {
        AcceptedPanel.removeAll();
        for (Group group : acceptedGroups) {
            JPanel groupPanel = new JPanel();
            groupPanel.setLayout(new FlowLayout());
            JLabel groupLabel = new JLabel(group.getName());
            JButton leaveButton = new JButton("Leave");
            JButton viewButton = new JButton("View");
            leaveButton.addActionListener(e -> {
                group.leaveGroup(CurrentUser);
                acceptedGroups.remove(group);
                leaveButton.setEnabled(false);
                viewButton.setEnabled(false);
                // Refresh the accepted groups display
                // displayAcceptedGroups();
            });
            viewButton.addActionListener(e -> {
                //Abdo implment
                JOptionPane.showMessageDialog(this, "Viewing group: " + group.getName());
            });
            groupPanel.add(groupLabel);
            groupPanel.add(leaveButton);
            groupPanel.add(viewButton);
            AcceptedPanel.add(groupPanel);
        }
        
        AcceptedPanel.revalidate();
        AcceptedPanel.repaint();
    }
    
    private void displaySuggestedGroups() {
        SuggestionPanel.removeAll();
        SuggestionPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        for (Group group : suggestedGroups) {
            JPanel groupPanel = new JPanel();
            groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));
            JLabel groupLabel = new JLabel(group.getName()); 
            groupPanel.add(groupLabel);
            JButton joinButton = new JButton("Join Group");
            joinButton.addActionListener(e -> {
                CurrentUser.deleteGroupRelation(group.getGroupID());
                CurrentUser.addGroupRelation(group.getGroupID(), GroupDetails.PENDING); //Must send him to Admins so they can approve
                suggestedGroups.remove(group);
                acceptedGroups.add(group);
                joinButton.setEnabled(false);
                // Refresh the suggested groups display
                //displaySuggestedGroups();
                //displayAcceptedGroups();
            });
            groupPanel.add(joinButton);
            SuggestionPanel.add(groupPanel);
        }
        SuggestionPanel.revalidate();
        SuggestionPanel.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
