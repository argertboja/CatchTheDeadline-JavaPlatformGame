package window;

import javax.swing.*;
import java.awt.*;

public class Window {

    public Window (int width, int height, String title, Game game) {
        game.setPreferredSize(new Dimension(width, height));
        game.setMaximumSize(new Dimension(width, height));
        game.setMinimumSize(new Dimension(width, height));

        JFrame frame = new JFrame(title);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // Set Frame Icon
        String path = "/images/icon.png";
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(path));

        frame.setIconImage(imageIcon.getImage());


        game.startGame();


        frame.setVisible(true);
    }
}
