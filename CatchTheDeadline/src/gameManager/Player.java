/**
 * 
 */

package gameManager;

import java.util.LinkedList;

/**
 * @author Hareem Larik
 *
 */
public class Player extends Animation {

	// properties
	private Camera cam;
	private int speed;
	private int gravity;
	private Animation playerWalk;
	private Animation playerRun;
	private Animation playerShoot;
	private Pen pen;
	private Eraser eraser;
	private PaintSpray paintSpray;
	
	// constructor
	public Player() {
		// TODO Auto-generated constructor stub
	}
	
	// methods
	public boolean collisions( LinkedList<PowerUps> objects, LinkedList<Enemy> enemies) {
	
		boolean hasCollided = false;
		
		
		
		return hasCollided;
	}

	public void walkAnimation( Animation anim ) {
		
	}
	
	public void runAnimation( Animation anim ) {
		
	}
	
	public void shootAnimation( Animation shoot ) {
		
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}
	
}