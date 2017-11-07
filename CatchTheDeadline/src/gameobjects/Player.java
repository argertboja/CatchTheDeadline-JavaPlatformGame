/**
 * 
 */

package gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import gameManager.*;
import window.GameEngine;

/**
 * @author Hareem Larik
 *
 */
public class Player extends GameObject {

	// properties
	private int speed;
	private Animation playerWalk, playerWalkM;
	private Animation playerRun;
	private Animation playerShoot;
	private Pen pen;
	private Eraser eraser;
	private PaintSpray paintSpray;

	private final float MAX_SPEED = 10;
	private Handler handler;
	private Camera cam;
	Texture texture = GameEngine.getInstance();
	
	ImageIcon player = new ImageIcon(getClass().getResource("/images/player.png"));
	/*ImageIcon RunSlowGIF = new ImageIcon(getClass().getResource("/images/RunSlowGIF.gif"));
	ImageIcon RunFastGIF = new ImageIcon(getClass().getResource("/images/RunFastGIF.gif"));
	ImageIcon RollJumpGIF = new ImageIcon(getClass().getResource("/images/RollJumpGIF.gif"));
	ImageIcon JumpGIF = new ImageIcon(getClass().getResource("/images/JumpGIF.gif"));
	ImageIcon KO_GIF = new ImageIcon(getClass().getResource("/images/KO_GIF.gif"));
	*/
	private float gravity = 0.15f;
	private float width = player.getIconWidth(), height = player.getIconHeight();
	
	// constructor
	public Player(float x, float y, Handler handler, ObjectType type) {
		super(x, y, type);
		this.handler = handler;
		playerWalk = new Animation( 5, texture.playerRun ); 
		playerWalkM = new Animation( 5, texture.playerRunM ); 
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
					posX = temp.getPosX() + 40;
					// DEBUG
					//System.out.println(width);
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
		playerWalk.runAnimation();
		playerWalkM.runAnimation();
	}

	@Override
	public void render(Graphics graphics) {
		
		//graphics.fillRect((int)posX, (int)posY, (int)width, (int)height );

		//Graphics2D graphics2d = (Graphics2D) graphics; 
		if( velocityX > 0 )
			playerWalk.drawAnimation(graphics, (int) posX, (int) posY, (int) width, (int) height);
		else if( velocityX < 0 )
			playerWalkM.drawAnimation(graphics, (int) posX, (int) posY, (int) width, (int) height);
		else
			graphics.drawImage( player.getImage(), (int) posX, (int) posY, (int) width, (int) height, null );
		//graphics2d.drawImage( RunSlowGIF.getImage(), (int) posX, (int) posY, null );// gif animation
		
		//graphics2d.drawImage( player.getImage(), (int) posX+10, (int) posY+10, null );
		
		///////////~FOR CHECKING BOUNDS ON DEBUG MODE~///////////////////////////
		//graphics.setColor(Color.red);
		//Color myColour = new Color(255, 0, 0, 0); // for making the lines transparent
        //graphics.setColor(myColour);
		//g2d.draw(objectBounds());
		//g2d.draw(objectBoundsRight());
		//g2d.draw(objectBoundsLeft());
		//g2d.draw(objectBoundsTop());
		////////////////////////////////////////
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