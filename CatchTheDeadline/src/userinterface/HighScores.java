package userinterface;

import database.DBInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HighScores extends JFrame {

    private MyMouseHandler mouseHandler;
    private JLabel bg, back, scores;
    private ImageIcon backImg, hoverBackImg;
    private DBInterface db;
    private String highScores = "";
    private Font font = new Font("Cooper Black", Font.BOLD, 28);

    public HighScores() {
        setTitle("Catch The Deadline - High Scores");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        db = new DBInterface();

        // Initialize mouse handler
        mouseHandler = new MyMouseHandler();

        // Set Frame Icon
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/icon.png"));
        setIconImage(imageIcon.getImage());

        // Set Background Image
        ImageIcon bgImage = new ImageIcon(getClass().getResource("/images/highscoresmenubg.png"));
        bg = new JLabel(bgImage);
        bg.setBounds(0, 0, 1000, 500);

        // Scores
        ResultSet rs = null;
        try {
            rs = db.getHighScores();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (rs.next())
                highScores = "<html>" + rs.getString(1) + " " + rs.getInt(2);
            while (rs.next()) {
                highScores = highScores + " <br><br>" + rs.getString(1) + " " + rs.getInt(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        highScores += "<html>";
        scores = new JLabel();
        scores.setFont(font);
        scores.setText(highScores);
        scores.setBounds(580, 50, 238, 350);
        bg.add(scores);

        // On Off Images
        backImg = new ImageIcon(getClass().getResource("/images/back.png"));
        hoverBackImg = new ImageIcon(getClass().getResource("/images/hoverback.png"));

        // Back Label
        back = new JLabel ();
        back.setIcon(backImg);
        back.setBounds(620, 350, 206, 70);
        back.addMouseListener(mouseHandler);
        bg.add(back);

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
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            if (source == back) {
                back.setIcon(hoverBackImg);
            }
        }

        @Override
        public void mouseExited (MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            if (source == back) {
                back.setIcon(backImg);
            }
        }
    }
}
