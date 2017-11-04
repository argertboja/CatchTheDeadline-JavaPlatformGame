/**
 * 
 */

package gameManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 * @author Hareem Larik
 *
 */
public class Player extends GameObject {

	// properties
	private Camera cam;
	private int speed;
	private Animation playerWalk;
	private Animation playerRun;
	private Animation playerShoot;
	private Pen pen;
	private Eraser eraser;
	private PaintSpray paintSpray;
	
	private float gravity = 0.5f;
	private float width = 32, height = 64;
	private final float MAX_SPEED = 10;
	
	// constructor
	public Player(float x, float y, ObjectType type) {
		super(x, y, type);
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

	public float getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	public void tick( LinkedList<GameObject> objects ) {
		posX += velocityX;
		posY -= velocityY;
		
		if ( falling || jumping ) {
			velocityY += gravity;
		}
		if ( velocityY > MAX_SPEED )
			velocityY = MAX_SPEED;
	}
	
	@Override
	public void collisionDetector(LinkedList<GameObject> objects) {
		 
		
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.blue); 
		graphics.fillRect((int)posX, (int)posY, (int)width, (int)height );
		
		Graphics2D g2d = (Graphics2D) graphics;
		
		g2d.draw(objectBounds());
		g2d.draw(objectBoundsRight());
		g2d.draw(objectBoundsLeft());
		g2d.draw(objectBoundsTop());
	}

	@Override
	public Rectangle objectBounds() {
		return new Rectangle( (int)posX, (int) ((int)posY+(height/2)), (int)width, (int)height/2 );
	}
	
	public Rectangle objectBoundsRight() {
		return new Rectangle( (int)posX, (int)posY, (int)width, (int)height/2 );
	}
	
	public Rectangle objectBoundsLeft() {
		return new Rectangle( (int)posX, (int)posY, (int)width, (int)height );
	}
	
	public Rectangle objectBoundsTop() {
		return new Rectangle( (int)posX, (int)posY, (int)width, (int)height );
	}
	
}