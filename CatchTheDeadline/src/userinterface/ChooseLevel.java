package userinterface;

import window.GameEngine;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChooseLevel extends JFrame {

    private MyMouseHandler mouseHandler;
    private JLabel bg, back, primary, high, univer;
    private ImageIcon backImg, hoverBackImg, primarySchool, highSchool, university,primaryHover, highHover, uniHover;
    private GameEngine gameEngine;

    public ChooseLevel() {
        setTitle("Catch The Deadline - Choose Level");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Initialize mouse handler
        mouseHandler = new MyMouseHandler();

        // Set Frame Icon
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/icon.png"));
        setIconImage(imageIcon.getImage());

        // Set Background Image
        ImageIcon bgImage = new ImageIcon(getClass().getResource("/images/chooseLevel.png"));
        bg = new JLabel(bgImage);
        bg.setBounds(0, 0, 1000, 500);

        // Images
        backImg = new ImageIcon(getClass().getResource("/images/back.png"));
        hoverBackImg = new ImageIcon(getClass().getResource("/images/hoverback.png"));
        primarySchool = new ImageIcon(getClass().getResource("/images/primarySchool.png"));
        primaryHover = new ImageIcon(getClass().getResource("/images/primarySchoolHover.png"));
        highSchool = new ImageIcon(getClass().getResource("/images/highSchool.png"));
        highHover = new ImageIcon(getClass().getResource("/images/highSchoolHover.png"));
        university = new ImageIcon(getClass().getResource("/images/university.png"));
        uniHover = new ImageIcon(getClass().getResource("/images/universityHover.png"));

        // Back Label
        back = new JLabel ();
        back.setIcon(backImg);
        back.setBounds(620, 350, 206, 70);
        back.addMouseListener(mouseHandler);
        bg.add(back);

        // Primary School Label
        primary = new JLabel ();
        primary.setIcon(primarySchool);
        primary.setBounds(540, 105, 282, 82);
        primary.addMouseListener(mouseHandler);
        bg.add(primary);

        // High School Label
        high = new JLabel ();
        high.setIcon(highSchool);
        high.setBounds(540, 190, 287, 82);
        high.addMouseListener(mouseHandler);
        bg.add(high);

        // University Label
        univer = new JLabel ();
        univer.setIcon(university);
        univer.setBounds(540, 280, 290, 82);
        univer.addMouseListener(mouseHandler);
        bg.add(univer);

        add(bg);
        setVisible(true);
    }

    // Private class for Mouse Handler
    private class MyMouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            if (source == back) {
                new MainMenu();
                dispose();
            }
            else if (source == primary) {
                gameEngine = new GameEngine(1);
                gameEngine.startTheGame();
                dispose();
            }
            else if (source == high) {
                gameEngine = new GameEngine(2);
                gameEngine.startTheGame();
                dispose();
            }
            else if (source == univer) {
                gameEngine = new GameEngine(3);
                gameEngine.startTheGame();
                dispose();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            if (source == back) {
                back.setIcon(hoverBackImg);
            }
            else if (source == primary) {
                primary.setIcon(primaryHover);
            }
            else if (source == high) {
                high.setIcon(highHover);
            }
            else if (source == univer) {
                univer.setIcon(uniHover);
            }
        }

        @Override
        public void mouseExited (MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            if (source == back) {
                back.setIcon(backImg);
            }
            else if (source == primary) {
                primary.setIcon(primarySchool);
            }
            else if (source == high) {
                high.setIcon(highSchool);
            }
            else if (source == univer) {
                univer.setIcon(university);
            }
        }
    }
}
