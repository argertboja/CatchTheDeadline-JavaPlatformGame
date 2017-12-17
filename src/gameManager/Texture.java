package gameManager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Texture {

	private BufferedImage playerRunSprite = null, playerRunSpriteM = null, playerJumpSprite = null, playerJumpSpriteM = null, 
			penSpin = null, eraserSpin = null, paintSpraySpin = null, examAnim = null, examAnimM = null, blankSheetFlip = null;
	public BufferedImage[] playerRun = new BufferedImage[42];
	public BufferedImage[] playerRunM = new BufferedImage[42];
	public BufferedImage[] playerJump = new BufferedImage[24];
	public BufferedImage[] playerJumpM = new BufferedImage[24];
	public BufferedImage[] penSpinning = new BufferedImage[24];
	public BufferedImage[] eraserSpinning = new BufferedImage[24];
	public BufferedImage[] paintSpraySpinning = new BufferedImage[24];
	public BufferedImage[] examAnimation = new BufferedImage[18];
	public BufferedImage[] examAnimationM = new BufferedImage[18];
	public BufferedImage[] blankSheetFlipping = new BufferedImage[17];
	private final int width = 75, height = 127; // dimensions of sub image of player
	private SpriteSheet ps, psM, pj, pjM, penSprite, eraserSprite, paintSpraySprite, examSprite, examSpriteM, blankSheetSprite;
	
	public Texture() {

		BufferedImageLoader bufferedImageLoader = new BufferedImageLoader();
		playerRunSprite = bufferedImageLoader.loadImg("/images/runSlowSprite.png"); // loads the 'forward running sprite sheet of player'
		playerRunSpriteM = bufferedImageLoader.loadImg("/images/runSlowSpriteM.png"); // loads the 'backward running sprite sheet of player'
		playerJumpSprite = bufferedImageLoader.loadImg("/images/jumpSprite.png"); // loads the 'forward jumping sprite sheet of player'
		playerJumpSpriteM = bufferedImageLoader.loadImg("/images/jumpSpriteM.png"); // loads the 'backward jumping sprite sheet of player'
		
		penSpin = bufferedImageLoader.loadImg("/images/penSprite.png");
		eraserSpin = bufferedImageLoader.loadImg("/images/eraserSprite.png");
		paintSpraySpin = bufferedImageLoader.loadImg("/images/PaintSpraySprite.png");
		
		examAnim = bufferedImageLoader.loadImg("/images/examSpriteM.png"); 
		examAnimM = bufferedImageLoader.loadImg("/images/examSprite.png"); 
		blankSheetFlip = bufferedImageLoader.loadImg("/images/blankSheetSprite.png"); 
		
		ps = new SpriteSheet(playerRunSprite);
		psM = new SpriteSheet(playerRunSpriteM);
		pj = new SpriteSheet(playerJumpSprite);
		pjM = new SpriteSheet(playerJumpSpriteM);
		
		penSprite = new SpriteSheet(penSpin);
		eraserSprite = new SpriteSheet(eraserSpin);
		paintSpraySprite = new SpriteSheet(paintSpraySpin);
		
		examSprite = new SpriteSheet(examAnim);
		examSpriteM = new SpriteSheet(examAnimM);
		blankSheetSprite = new SpriteSheet(blankSheetFlip);
		
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
		count = 0;
		for( int i = 1; i <= 24; i++ ) { 
			eraserSpinning[count] = eraserSprite.divideImage( 1, i, 20, 20 ); // saving each sub-image as a single image onto the array
			count++;
			if( count >= 24 )
				break;
		}
		count = 0;
		for( int i = 1; i <= 24; i++ ) { 
			paintSpraySpinning[count] = paintSpraySprite.divideImage( 1, i, 24, 24 ); // saving each sub-image as a single image onto the array
			count++;
			if( count >= 24 )
				break;
		}
		count = 0;
		for( int i = 1; i <= 18; i++ ) { 
			examAnimation[count] = examSprite.divideImage( 1, i, 95, 113 ); // saving each sub-image as a single image onto the array
			count++;
			if( count >= 18 )
				break;
		}
		count = 0;
		for( int i = 1; i <= 18; i++ ) { 
			examAnimationM[count] = examSpriteM.divideImage( 1, i, 95, 113 ); // saving each sub-image as a single image onto the array
			count++;
			if( count >= 18 )
				break;
		
		}
		count = 0;
		for( int i = 1; i <= 17; i++ ) { 
			blankSheetFlipping[count] = blankSheetSprite.divideImage( 1, i, 126, 192 ); // saving each sub-image as a single image onto the array
			count++;
			if( count >= 17 )
				break;
		}
	}
}
