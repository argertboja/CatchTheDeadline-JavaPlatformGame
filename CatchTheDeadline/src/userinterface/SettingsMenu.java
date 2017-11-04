package userinterface;

import window.GameEngine;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SettingsMenu extends JFrame {

    private MyMouseHandler mouseHandler;
    private JLabel bg, music, sound;
    private ImageIcon on, off;
    private boolean musicOn = true, soundOn = true;

    public SettingsMenu() {
        setTitle("Catch The Deadline - Settings Menu");
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
        ImageIcon bgImage = new ImageIcon(getClass().getResource("/images/settingsmenubg.png"));
        bg = new JLabel(bgImage);
        bg.setBounds(0, 0, 1000, 500);

        // On Off Images
        on = new ImageIcon(getClass().getResource("/images/on.png"));
        off = new ImageIcon(getClass().getResource("/images/off.png"));

        // Music Label
        music = new JLabel ();
        music.setIcon(on);
        music.setBounds(750, 140, 160, 40);
        music.addMouseListener(mouseHandler);
        bg.add(music);

        // Sound Label
        sound = new JLabel ();
        sound.setIcon(on);
        sound.setBounds(750, 250, 160, 40);
        sound.addMouseListener(mouseHandler);
        bg.add(sound);

        add(bg);
        setVisible(true);
    }

    // Private class for Mouse Handler
    private class MyMouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            if (source == music && musicOn) {
                music.setIcon(off);
                musicOn = false;
            }
            else if (source == music && !musicOn) {
                music.setIcon(on);
                musicOn = true;
            }
            else if (source == sound && soundOn) {
                sound.setIcon(off);
                soundOn = false;
            }
            else if (source == sound && !soundOn) {
                sound.setIcon(on);
                soundOn = true;
            }
        }
    }
}
