/**
 * 
 */

package gameobjects;

import gameManager.*;

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
	
	private float gravity = 0.1f;
	private float width = 64, height = 96;
	private final float MAX_SPEED = 10;
	private Handler handler;
	
	// constructor
	public Player(float x, float y, Handler handler, ObjectType type) {
		super(x, y, type);
		this.handler = handler;
	}
	
	// methods
	public void collisions( LinkedList<GameObject> objects ) {
	
		for( int i = 0; i < handler.objectLinkedList.size(); i++ ) {
			GameObject temp = handler.objectLinkedList.get(i);

			if( temp.getType() == ObjectType.Block )
			{
				if( objectBoundsTop().intersects( temp.objectBounds() ) ) {
					posY = temp.getPosY() + (height/2);
					velocityY = 0;
				}
				if( objectBounds().intersects( temp.objectBounds() ) ) {
					posY = temp.getPosY() - height;
					velocityY = 0;
					falling = false;
					jumping = false;
				}
				else
					falling = true;
				if( objectBoundsRight().intersects( temp.objectBounds() ) ) {
					posX = temp.getPosX() - width;
				}
				if( objectBoundsLeft().intersects( temp.objectBounds() ) ) {
					posX = temp.getPosX() + width;
				}
				
			}
		}
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

	@Override
	public void collisionDetector(LinkedList<GameObject> objects ) {
		posX += velocityX;
		posY += velocityY;
		
		if ( falling || jumping ) {
			velocityY += gravity;
			
			if ( velocityY > MAX_SPEED )
				velocityY = MAX_SPEED;
		}
		collisions( objects );
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.blue); 
		graphics.fillRect((int)posX, (int)posY, (int)width, (int)height );
		
		Graphics2D g2d = (Graphics2D) graphics;
		graphics.setColor(Color.red);
		g2d.draw(objectBounds());
		g2d.draw(objectBoundsRight());
		g2d.draw(objectBoundsLeft());
		g2d.draw(objectBoundsTop());
	}

	@Override
	public Rectangle objectBounds() {
		return new Rectangle( (int) ((int)posX+(width/2)-((width/2)/2)), (int) ((int)posY+(height/2)), (int)width/2, (int)height/2 );
	}
	
	public Rectangle objectBoundsTop() {
		return new Rectangle( (int) ((int)posX+(width/2)-((width/2)/2)), (int)posY, (int)width/2, (int)height/2 );
	}
	
	public Rectangle objectBoundsRight() {
		return new Rectangle( (int) ((int)posX+width-5), (int)posY+5, (int)5, (int)height-10 );
	}
	
	public Rectangle objectBoundsLeft() {
		return new Rectangle( (int)posX, (int)posY+5, (int)5, (int)height-10 );
	}
	
	
	
}