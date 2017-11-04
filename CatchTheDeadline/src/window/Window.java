package window;

import javax.swing.*;
import java.awt.*;

public class Window {

    public Window (int width, int height, String title, GameEngine gameEngine) {
        gameEngine.setPreferredSize(new Dimension(width, height));
        gameEngine.setMaximumSize(new Dimension(width, height));
        gameEngine.setMinimumSize(new Dimension(width, height));

        JFrame frame = new JFrame(title);
        frame.add(gameEngine);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Set Frame Icon
        String path = "/images/icon.png";
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(path));

        frame.setIconImage(imageIcon.getImage());

        gameEngine.start();
        frame.setVisible(true);
    }
}