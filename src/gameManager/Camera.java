/**
 * 
 */
package gameManager;

import gameobjects.GameObject;
import window.GameEngine;

/**
 * @author Hareem Larik
 *
 */
public class Camera {

	// properties
	private float xPos;
	private float yPos;
	
	// constructor
	public Camera( float xPos, float yPos ) {
		this.xPos = xPos;
		this.xPos = xPos;
	}

	public void updateFrame( GameObject player ) {
		xPos = -player.getPosX() + GameEngine.WIDTH / 2;
	}
	
	public float getXPos() {
		return xPos;
	}

	public void setXPos(float xPos) {
		this.xPos = xPos;
	}

	public float getYPos() {
		return yPos;
	}

	public void setYPos(float yPos) {
		this.yPos = yPos;
	}
}
