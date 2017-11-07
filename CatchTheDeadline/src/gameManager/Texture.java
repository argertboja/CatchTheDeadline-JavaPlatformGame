package gameManager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Texture {

	private BufferedImage playerRunSprite = null, playerRunSpriteM = null,penSpin = null;
	public BufferedImage[] playerRun = new BufferedImage[42];
	public BufferedImage[] playerRunM = new BufferedImage[42];
	public BufferedImage[] penSpinning = new BufferedImage[24];
	private final int width = 75, height = 127; // dimensions of sub image of player
	private SpriteSheet ps, psM, penSprite;
	
	public Texture() {

		BufferedImageLoader bufferedImageLoader = new BufferedImageLoader();
		playerRunSprite = bufferedImageLoader.loadImg("/images/runSlowSprite.png"); // loads the 'forward running sprite sheet of player'
		playerRunSpriteM = bufferedImageLoader.loadImg("/images/runSlowSpriteM.png"); // loads the 'backward running sprite sheet of player'
		penSpin = bufferedImageLoader.loadImg("/images/penSprite.png"); // loads the 'backward running sprite sheet of player'
		ps = new SpriteSheet(playerRunSprite);
		psM = new SpriteSheet(playerRunSpriteM);
		penSprite = new SpriteSheet(penSpin);
		generateTextures();
	}

	public void generateTextures() {
		int count = 0;
		for( int i = 1; i <= 7; i++ ) { // divides the 'forward running sprite sheet of player' into sub-images
			for( int j = 1; j <= 6; j++ ) {
				playerRun[count] = ps.divideImage( i, j, width, height ); // saving each sub-image as a single image onto the array 
				count++;
				if( count >= 42 )
					break;
			}
		}
		count = 0;
		for( int i = 1; i <= 7; i++ ) { // divides the 'backward running sprite sheet of player' into sub-images
			for( int j = 1; j <= 6; j++ ) { 
				playerRunM[count] = psM.divideImage( i, j, width, height ); // saving each sub-image as a single image onto the array 
				count++;
				if( count >= 42 )
					break;
			}
		}
		count = 0;
		for( int i = 1; i <= 6; i++ ) { // divides the 'backward running sprite sheet of player' into sub-images
			for( int j = 1; j <= 4; j++ ) {
				penSpinning[count] = penSprite.divideImage( i, j, 1124, 1131 ); // saving each sub-image as a single image onto the array
				count++;
				if( count >= 24 )
					break;
			}
		}
	}
}
