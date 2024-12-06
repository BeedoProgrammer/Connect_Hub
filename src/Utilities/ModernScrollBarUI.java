/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

/**
 *
 * @author Abdel
 */
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class ModernScrollBarUI extends BasicScrollBarUI{

    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = new Color(150, 150, 150, 150); // Initial thumb color (semi-transparent gray)
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createInvisibleButton(); // Remove arrow buttons
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createInvisibleButton(); // Remove arrow buttons
    }

    private JButton createInvisibleButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (thumbBounds.isEmpty() || !scrollbar.isEnabled()) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(thumbColor);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
        g2.dispose();
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(new Color(220, 220, 220)); // Light gray
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected void installListeners() {
        super.installListeners();
        scrollbar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                thumbColor = new Color(100, 100, 100); // Darker color on hover
                scrollbar.repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                thumbColor = new Color(150, 150, 150, 150); // Return to original color
                scrollbar.repaint();
            }
        });
    }
}

