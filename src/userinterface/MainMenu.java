package userinterface;

import window.GameEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainMenu extends  JFrame {

    private JLabel bg, play, settings, help, highScores, credits;
    private ImageIcon hoveredPlayImage, playImage, settingsImage, hoveredSettingsImage, helpImage, hoveredHelpImage;
    private ImageIcon highScoresImage, hoveredHighScoresImage, creditsImage, hoveredCreditsImage;
    private MyMouseHandler mouseHandler;

    public MainMenu() {
        setTitle ("Catch The Deadline - Main Menu");
        setDefaultCloseOperation (EXIT_ON_CLOSE);
        getContentPane().setLayout (null);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setResizable(false);

        // Initialize mouse handler
        mouseHandler = new MyMouseHandler();

        // Set Frame Icon
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/icon.png"));
        setIconImage(imageIcon.getImage());

        // Set Background Image
        ImageIcon bgImage = new ImageIcon(getClass().getResource("/images/menubg.png"));
        bg = new JLabel (bgImage);
        bg.setBounds(0, 0, 1000, 500);

        // Play
        playImage = new ImageIcon(getClass().getResource("/images/play.png"));
        hoveredPlayImage = new ImageIcon(getClass().getResource("/images/hoverplay.png"));
        play = new JLabel ();
        play.setIcon(playImage);
        play.setBounds(680, 150, 150, 40);
        play.addMouseListener(mouseHandler);
        bg.add(play);

        // Settings
        settingsImage = new ImageIcon(getClass().getResource("/images/settings.png"));
        hoveredSettingsImage = new ImageIcon(getClass().getResource("/images/hoversettings.png"));
        settings = new JLabel ();
        settings.setIcon(settingsImage);
        settings.setBounds(615, 200, 280, 40);
        settings.addMouseListener(mouseHandler);
        bg.add(settings);

        // Help
        helpImage = new ImageIcon(getClass().getResource("/images/help.png"));
        hoveredHelpImage = new ImageIcon(getClass().getResource("/images/hoverhelp.png"));
        help = new JLabel ();
        help.setIcon(helpImage);
        help.setBounds(680, 250, 150, 40);
        help.addMouseListener(mouseHandler);
        bg.add(help);

        // High Scores
        highScoresImage = new ImageIcon(getClass().getResource("/images/highscores.png"));
        hoveredHighScoresImage = new ImageIcon(getClass().getResource("/images/hoverhighscores.png"));
        highScores = new JLabel ();
        highScores.setIcon(highScoresImage);
        highScores.setBounds(565, 300, 380, 40);
        highScores.addMouseListener(mouseHandler);
        bg.add(highScores);

        // Credits
        creditsImage = new ImageIcon(getClass().getResource("/images/credits.png"));
        hoveredCreditsImage = new ImageIcon(getClass().getResource("/images/hovercredits.png"));
        credits = new JLabel ();
        credits.setIcon(creditsImage);
        credits.setBounds(635, 350, 240, 40);
        credits.addMouseListener(mouseHandler);
        bg.add(credits);

        // Add main JLabel
        add(bg);

        //Make Frame Visible
        setVisible(true);
    }

    // Private class for Mouse Handler
    private class MyMouseHandler extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            if (source == play) {
                play.setIcon(hoveredPlayImage);
            }
            else if (source == settings) {
                settings.setIcon(hoveredSettingsImage);
            }
            else if (source == help) {
                help.setIcon(hoveredHelpImage);
            }
            else if (source == highScores) {
                highScores.setIcon(hoveredHighScoresImage);
            }
            else if (source == credits) {
                credits.setIcon(hoveredCreditsImage);
            }
        }
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            if (source == play) {
            	/*gameEngine = new GameEngine();
                gameEngine.startTheGame();		                  
                */
                new ChooseLevel();
                dispose();
            }
            else if (source == settings) {
                new SettingsMenu("menu");
                dispose();
            }
            else if (source == help) {
                new Help("menu");
                dispose();
            }
            else if (source == credits) {
                new Credits();
                dispose();
            }
            else if (source == highScores) {
                new HighScores();
                dispose();
            }
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            if (source == play) {
                play.setIcon(playImage);
            }
            else if (source == settings) {
                settings.setIcon(settingsImage);
            }
            else if (source == help) {
                help.setIcon(helpImage);
            }
            else if (source == highScores) {
                highScores.setIcon(highScoresImage);
            }
            else if (source == credits) {
                credits.setIcon(creditsImage);
            }
        }
    }
}
