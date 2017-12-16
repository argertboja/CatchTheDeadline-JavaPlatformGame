package gameobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import gameManager.*;
import window.GameEngine;


public class Player extends GameObject {

	// properties
	private Animation playerWalk, playerWalkM, playerJump, playerJumpM;
	private int foodCount = 3, sleepCount = 3, coinCount = 50;

	private final float MAX_SPEED = 10;
	private Handler handler;
	Texture texture = GameEngine.getInstance();
	
	ImageIcon player = new ImageIcon(getClass().getResource("/images/player.png"));
	ImageIcon playerLeftLooking = new ImageIcon(getClass().getResource("/images/playerLeftLooking.png"));
	private int enemyId = -1;
	
	private float gravity = 0.15f;
	private float width = player.getIconWidth(), height = player.getIconHeight();
	
	// constructor
	public Player( float x, float y, Handler handler, ObjectType type ) {
		super(x, y, type);
		this.handler = handler;
		playerWalk = new Animation( 1, texture.playerRun ); 
		playerWalkM = new Animation( 1, texture.playerRunM );
		playerJump = new Animation( 1, texture.playerJump );
		playerJumpM = new Animation( 1, texture.playerJumpM );
	}
	
	// methods
	public void collisions( LinkedList<GameObject> objects ) {
	
		for( int i = 0; i < handler.objectLinkedList.size(); i++ ) {
			GameObject temp = null;
			 try {
			 	temp = handler.objectLinkedList.get(i);
			 }
			 catch (Exception e) {
			 }
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
					if( ((Block) temp).getBlockType() == 3 ) {
						// exit from level here	
						JOptionPane.showMessageDialog(null, "You have finished this level! \nKeep up the good work", "Congratulations", JOptionPane.PLAIN_MESSAGE);
						// return back to level menu ????
					}
				}
				if( objectBoundsLeft().intersects( temp.objectBounds() ) ) {
					posX = temp.getPosX() + 40;
					// DEBUG
					//System.out.println(width);
				}
			}
			if( temp.getType() == ObjectType.Food )
			{
				if( objectBounds().intersects( temp.objectBounds() ) ) {
					foodCount++;
					handler.objectLinkedList.remove( temp );
				}

				if( objectBoundsRight().intersects( temp.objectBounds() ) ) {
					foodCount++;
					handler.objectLinkedList.remove( temp );
				}
				
				if( objectBoundsLeft().intersects( temp.objectBounds() ) ) {
					foodCount++;
					handler.objectLinkedList.remove( temp );
				}
			}
			if( temp.getType() == ObjectType.Sleep )
			{
				if( objectBounds().intersects( temp.objectBounds() ) ) {
					sleepCount++;
					handler.objectLinkedList.remove( temp );
				}

				if( objectBoundsRight().intersects( temp.objectBounds() ) ) {
					sleepCount++;
					handler.objectLinkedList.remove( temp );
				}
				
				if( objectBoundsLeft().intersects( temp.objectBounds() ) ) {
					sleepCount++;
					handler.objectLinkedList.remove( temp );
				}
			}
			if( temp.getType() == ObjectType.Coin )
			{
				if( objectBounds().intersects( temp.objectBounds() ) ) {
					coinCount++;
					handler.objectLinkedList.remove( temp );
				}

				if( objectBoundsRight().intersects( temp.objectBounds() ) ) {
					coinCount++;
					handler.objectLinkedList.remove( temp );
				}
				
				if( objectBoundsLeft().intersects( temp.objectBounds() ) ) {
					coinCount++;
					handler.objectLinkedList.remove( temp );
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
		
		if (velocityX > 0)
			facing = 1;
		else if (velocityX < 0)
			facing = -1;
		
		if ( falling || jumping ) {
			velocityY += gravity;
			
			if ( velocityY > MAX_SPEED )
				velocityY = MAX_SPEED;
		}
		collisions( objects );
		playerWalk.runAnimation(); // generate animation for player running towards right
		playerWalkM.runAnimation(); // generate animation for player running towards left
		playerJump.runAnimation(); // generate animation for player jumping towards right
		playerJumpM.runAnimation(); // generate animation for player jumping towards left
	}

	@Override
	public void render(Graphics graphics) {	
		if( ( velocityY < 0 || jumping == true ) && facing == 1 )
			playerJump.drawAnimation(graphics, (int) posX, (int) posY, (int) width, (int) 115 );
		else if( ( velocityY < 0 || jumping == true ) && facing == -1 )
			playerJumpM.drawAnimation(graphics, (int) posX, (int) posY, (int) width, (int) 100 );
		else if( velocityX > 0 )
			playerWalk.drawAnimation(graphics, (int) posX, (int) posY, (int) width, (int) height);
		else if( velocityX < 0 )
			playerWalkM.drawAnimation(graphics, (int) posX, (int) posY, (int) width, (int) height);
		else if ( facing == 1 )
			graphics.drawImage( player.getImage(), (int) posX, (int) posY, (int) width, (int) height, null );
		else
			graphics.drawImage( playerLeftLooking.getImage(), (int) posX, (int) posY, (int) width, (int) height, null );
		
		//graphics.fillRect((int)posX, (int)posY, (int)width, (int)height );
		//graphics2d.drawImage( RunSlowGIF.getImage(), (int) posX, (int) posY, null );// gif animation
		//graphics2d.drawImage( player.getImage(), (int) posX+10, (int) posY+10, null );
		
		///////////~FOR CHECKING BOUNDS ON DEBUG MODE~///////////////////////////
		//Graphics2D graphics2d = (Graphics2D) graphics; 
		//graphics2d.setColor(Color.red);
		//Color myColour = new Color(255, 0, 0, 0); // for making the lines transparent
        //graphics.setColor(myColour);
		//graphics2d.draw(objectBounds());
		//graphics2d.draw(objectBoundsRight());
		//graphics2d.draw(objectBoundsLeft());
		//graphics2d.draw(objectBoundsTop());
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
	
	public Handler getHandler() {
		 return handler;
	}

	public int getFoodCount() {
		return foodCount;
	}

	public int getCoinCount() {
		return coinCount;
	}

	public int getSleepCount() {
		return sleepCount;
	}

	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}

	public void setSleepCount(int sleepCount) {
		this.sleepCount = sleepCount;
	}

	public void setCoinCount(int coinCount) {
		this.coinCount = coinCount;
	}

	public void setEnemyId(int id) {
		enemyId = id;
	}

	public int getEnemyId() {
		return enemyId;
	}
}