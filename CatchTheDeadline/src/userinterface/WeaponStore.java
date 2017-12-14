package userinterface;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WeaponStore extends JFrame {

    private MyMouseHandler mouseHandler;
    private JLabel bg, buyEraser, buyPaintSpray, back, coinCount;
    private ImageIcon buy, buy20, buy30, backImg, hoverBackImg;
    private String from;
    private int coins;

    public WeaponStore( String from, int coins ) {
        setTitle("Catch The Deadline - Weapon Store");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        this.from = from;
        this.coins = coins;
        
        if (from.equalsIgnoreCase("game")) {
            setUndecorated(true);
        }
        
        // Initialize mouse handler
        mouseHandler = new MyMouseHandler();

        // Set Frame Icon
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/icon.png"));
        setIconImage(imageIcon.getImage());

        // Set Background Image
        ImageIcon bgImage = new ImageIcon(getClass().getResource("/images/weaponstorebg.png"));
        bg = new JLabel(bgImage);
        bg.setBounds(0, 0, 1000, 500);

        // buy and back Images
        buy = new ImageIcon(getClass().getResource("/images/buy.png"));
        buy20 = new ImageIcon(getClass().getResource("/images/buy20.png"));
        buy30 = new ImageIcon(getClass().getResource("/images/buy30.png"));
        backImg = new ImageIcon(getClass().getResource("/images/back.png"));
        hoverBackImg = new ImageIcon(getClass().getResource("/images/hoverback.png"));

        // buyEraser Label
        buyEraser = new JLabel ();
        buyEraser.setIcon(buy);
        buyEraser.setBounds(730, 250, 160, 40);
        buyEraser.addMouseListener(mouseHandler);
        bg.add(buyEraser);

        // buyPaintSpray Label
        buyPaintSpray = new JLabel ();
        buyPaintSpray.setIcon(buy);
        buyPaintSpray.setBounds(730, 300, 160, 40);
        buyPaintSpray.addMouseListener(mouseHandler);
        bg.add(buyPaintSpray);

        // coins label
        Font font = new Font("Brush Script MT", Font.BOLD, 40);
        coinCount = new JLabel( String.valueOf(coins) );
        coinCount.setFont(font);
        coinCount.setBounds(720, 140, 160, 40);
        bg.add(coinCount);
        
        // Back Label
        back = new JLabel ();
        back.setIcon(backImg);
        back.setBounds(600, 360, 206, 70);
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
            if (source == buyPaintSpray && coins >= 30 ) {
            	coins = coins - 30;   // decrement coins
            	coinCount.setText( String.valueOf(coins) );
            	//activate paintSpray
            } else if (source == buyEraser && coins >= 20 ) {
            	coins = coins - 20;   // decrement coins
            	coinCount.setText( String.valueOf(coins) );
            	//activate eraser
            } else if (source == back) {
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
            if (source == buyEraser) {
            	buyEraser.setIcon(buy20);
            }
            if (source == buyPaintSpray) {
            	buyPaintSpray.setIcon(buy30);
            }
        }

        @Override
        public void mouseExited (MouseEvent e) {
            JLabel source = (JLabel) e.getSource();
            if (source == back) {
                back.setIcon(backImg);
            }
            if (source == buyEraser) {
            	buyEraser.setIcon(buy);
            }
            if (source == buyPaintSpray) {
            	buyPaintSpray.setIcon(buy);
            }
        }
    }
}
