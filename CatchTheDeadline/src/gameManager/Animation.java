
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
	
	// methods
	public BufferedImage loadImage( String address ) {
		try {
			image = ImageIO.read( getClass().getResource(address) );
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	
	public void runAnimation() {
		
	}
	
	public void drawAnimation() {
		
	}
}
