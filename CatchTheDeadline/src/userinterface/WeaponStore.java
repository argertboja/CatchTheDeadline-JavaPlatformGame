package userinterface;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WeaponStore extends JFrame {

    private MyMouseHandler mouseHandler;
    private JLabel bg, buyEraser, buyPaintSpray, back;
    private ImageIcon buy, buyHover, backImg, hoverBackImg;
    private String from;

    public WeaponStore( String from ) {
        setTitle("Catch The Deadline - Weapon Store");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        this.from = from;

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
        buyHover = new ImageIcon(getClass().getResource("/images/buyHover.png"));
        backImg = new ImageIcon(getClass().getResource("/images/back.png"));
        hoverBackImg = new ImageIcon(getClass().getResource("/images/hoverback.png"));

        // buyEraser Label
        buyEraser = new JLabel ();
        buyEraser.setIcon(buy);
        buyEraser.setBounds(750, 140, 160, 40);
        buyEraser.addMouseListener(mouseHandler);
        bg.add(buyEraser);

        // buyPaintSpray Label
        buyPaintSpray = new JLabel ();
        buyPaintSpray.setIcon(buy);
        buyPaintSpray.setBounds(750, 250, 160, 40);
        buyPaintSpray.addMouseListener(mouseHandler);
        bg.add(buyPaintSpray);

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
            if (source == buyPaintSpray) {
                // decrement coins
            	//get paintSpray
            } else if (source == buyEraser) {
            	// decrement coins
            	//get eraser
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
            	buyEraser.setIcon(buyHover);
            }
            if (source == buyPaintSpray) {
            	buyPaintSpray.setIcon(buyHover);
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
