package gameManager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Texture {

	private BufferedImage playerRunSprite = null;
	private BufferedImage[] player = new BufferedImage[5];

	SpriteSheet ss;
	
	public Texture() {

		try {
			playerRunSprite = ImageIO.read(new File("/images/runSlowSprite.png"));
		} catch (IOException e) {
		}
		ss = new SpriteSheet(playerRunSprite);
		generateTextures();
	}
	
	ImageIcon player1 = new ImageIcon(getClass().getResource("/images/player.png"));

	int x = player1.getIconWidth();
	int y = player1.getIconHeight();
	
	
	public void generateTextures() {
		//player[0] = ss.divideImage( 0, 0, x, y ); // player's running animation
	}

	/*public BufferedImage divideImage( int row, int col, int width, int height, BufferedImage image ) {
		BufferedImage img = image.getSubimage( (col * width)-width, (col * height)-height, width, height);
		return img;
	}*/
}
