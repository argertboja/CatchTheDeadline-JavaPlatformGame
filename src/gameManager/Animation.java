
package gameManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * @author Hareem Larik
 *
 */
public class Animation {

	// properties 
	private int speed;
	private int frames;
	
	
	private BufferedImage[] images;
	private BufferedImage image;
	
	private int currentIndex = 0;
	private int count = 0;
	
	public Animation( int speed, BufferedImage... args ) {
		this.speed = speed;
		images = new BufferedImage[args.length];
		for( int i = 0; i < args.length; i ++ ) {
			images[i] = args[i];
		}
		frames = args.length;
	}
	
	// methods
	public void runAnimation() {
		currentIndex++;
		if( currentIndex > speed ) {
			currentIndex = 0;
			nextFrame();
		}
	}
	
	private void nextFrame() {
		for( int i = 0; i < frames; i++ ) {
			if( count == i )
				image = images[i];
		}
		count++;
		if( count > frames ) // repeat the animation once its gone through
			count = 0;
	}
	/*private void nextFrame(){
	     image = images[ count % frames ];
	     count++;     
	}*/
	
	public void drawAnimation( Graphics graphics, int x, int y ){
		graphics.drawImage( image, x, y, null );
	}
	
	public void drawAnimation( Graphics graphics, int x, int y, int scaleX, int scaleY ){
		graphics.drawImage( image, x, y, scaleX, scaleY, null );
	}
}
