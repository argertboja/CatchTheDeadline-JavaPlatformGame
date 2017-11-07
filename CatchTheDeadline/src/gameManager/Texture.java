package gameManager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Texture {

	private BufferedImage playerRunSprite = null, playerRunSpriteM = null;
	public BufferedImage[] playerRun = new BufferedImage[42];
	public BufferedImage[] playerRunM = new BufferedImage[42];
	int x1 = 75, y1 = 127;
	SpriteSheet ps, psM;
	
	public Texture() {

		BufferedImageLoader bufferedImageLoader = new BufferedImageLoader();
		playerRunSprite = bufferedImageLoader.loadImg("/images/runSlowSprite.png");
		playerRunSpriteM = bufferedImageLoader.loadImg("/images/runSlowSpriteM.png");
		ps = new SpriteSheet(playerRunSprite);
		psM = new SpriteSheet(playerRunSpriteM);
		generateTextures();
	}

	public void generateTextures() {
		int count = 0;
		for( int i = 1; i <= 7; i++ ) {
			for( int j = 1; j <= 6; j++ ) {
				playerRun[count] = ps.divideImage( i, j, x1, y1 );// player's running animation
				count++;
				if( count >= 42 )
					break;
			}
		}
		count = 0;
		for( int i = 1; i <= 7; i++ ) {
			for( int j = 1; j <= 6; j++ ) {
				playerRunM[count] = psM.divideImage( i, j, x1, y1 );// player's running animation
				count++;
				if( count >= 42 )
					break;
			}
		}
	}
}
