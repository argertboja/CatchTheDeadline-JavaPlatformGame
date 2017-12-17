package userinterface;

import window.GameEngine;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PauseMenu extends  JFrame{

    private MyMouseHandler mouseHandler;
    private JLabel bg, resume;
    private ImageIcon resumeImg, hoverResumeImg;

    public PauseMenu() {
        getContentPane().setLayout(null);
        setSize(1100, 550);
        setLocationRelativeTo(null);
        setUndecorated(true);

        // Initialize mouse handler
        mouseHandler = new MyMouseHandler();

        // Set Background Image
        ImageIcon bgImage = new ImageIcon(getClass().getResource("/images/pauseMenu.png"));
        bg = new JLabel(bgImage);
        bg.setBounds(0, 0, 1100, 550);

        // Images
        resumeImg = new ImageIcon(getClass().getResource("/images/resume.png"));
        hoverResumeImg = new ImageIcon(getClass().getResource("/images/resumeHover.png"));

        // Back Label
        resume = new JLabel ();
        resume.setIcon(resumeImg);
        resume.setBounds(620, 150, 230, 40);
        resume.addMouseListener(mouseHandler);
        bg.add(resume);

        add(bg);
        setVisible(true);
    }

    // Private class for Mouse Handler
    private class MyMouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            if (source == resume) {
                dispose();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            if (source == resume) {
                resume.setIcon(hoverResumeImg);
            }
        }

        @Override
        public void mouseExited (MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            if (source == resume) {
                resume.setIcon(resumeImg);
            }
        }
    }
}