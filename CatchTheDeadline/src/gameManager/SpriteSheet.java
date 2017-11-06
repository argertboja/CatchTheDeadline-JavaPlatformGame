package gameManager;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage image;
	
	public SpriteSheet( BufferedImage image ) {
		this.image = image;
	}
	
	public BufferedImage divideImage( int row, int col, int width, int height ) {
		BufferedImage img = image.getSubimage( (col * width)-width, (col * height)-height, width, height);
		return img;
	}
}
