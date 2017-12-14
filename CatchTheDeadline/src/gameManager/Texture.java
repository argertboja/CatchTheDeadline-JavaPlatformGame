package gameManager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Texture {

	private BufferedImage playerRunSprite = null, playerRunSpriteM = null, playerJumpSprite = null, playerJumpSpriteM = null, penSpin = null, eraserSpin = null;
	public BufferedImage[] playerRun = new BufferedImage[42];
	public BufferedImage[] playerRunM = new BufferedImage[42];
	public BufferedImage[] playerJump = new BufferedImage[24];
	public BufferedImage[] playerJumpM = new BufferedImage[24];
	public BufferedImage[] penSpinning = new BufferedImage[24];
	public BufferedImage[] eraserSpinning = new BufferedImage[24];
	private final int width = 75, height = 127; // dimensions of sub image of player
	private SpriteSheet ps, psM, pj, pjM, penSprite, eraserSprite;
	
	public Texture() {

		BufferedImageLoader bufferedImageLoader = new BufferedImageLoader();
		playerRunSprite = bufferedImageLoader.loadImg("/images/runSlowSprite.png"); // loads the 'forward running sprite sheet of player'
		playerRunSpriteM = bufferedImageLoader.loadImg("/images/runSlowSpriteM.png"); // loads the 'backward running sprite sheet of player'
		playerJumpSprite = bufferedImageLoader.loadImg("/images/jumpSprite.png"); // loads the 'forward jumping sprite sheet of player'
		playerJumpSpriteM = bufferedImageLoader.loadImg("/images/jumpSpriteM.png"); // loads the 'backward jumping sprite sheet of player'
		penSpin = bufferedImageLoader.loadImg("/images/penSprite.png");
		eraserSpin = bufferedImageLoader.loadImg("/images/eraserSprite.png");
		ps = new SpriteSheet(playerRunSprite);
		psM = new SpriteSheet(playerRunSpriteM);
		pj = new SpriteSheet(playerJumpSprite);
		pjM = new SpriteSheet(playerJumpSpriteM);
		penSprite = new SpriteSheet(penSpin);
		eraserSprite = new SpriteSheet(eraserSpin);
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
		for( int i = 1; i <= 6; i++ ) { // divides the 'forward jumping sprite sheet of player' into sub-images
			for( int j = 1; j <= 4; j++ ) { 
				playerJump[count] = pj.divideImage( i, j, width, 152 ); // saving each sub-image as a single image onto the array 
				count++;
				if( count >= 24 )
					break;
			}
		}
		count = 0;
		for( int i = 1; i <= 6; i++ ) { // divides the 'backward jumping sprite sheet of player' into sub-images
			for( int j = 1; j <= 4; j++ ) { 
				playerJumpM[count] = pjM.divideImage( i, j, width, 152 ); // saving each sub-image as a single image onto the array 
				count++;
				if( count >= 24 )
					break;
			}
		}
		count = 0;
		for( int i = 1; i <= 6; i++ ) { 
			for( int j = 1; j <= 4; j++ ) {
				penSpinning[count] = penSprite.divideImage( i, j, 1124, 1131 ); // saving each sub-image as a single image onto the array
				count++;
				if( count >= 24 )
					break;
			}
		}
		/*count = 0;
		for( int i = 1; i <= 24; i++ ) { 
			eraserSpinning[count] = eraserSprite.divideImage( i, 1, 20, 20 ); // saving each sub-image as a single image onto the array
			count++;
			if( count >= 24 )
				break;
		}*/
	}
}
