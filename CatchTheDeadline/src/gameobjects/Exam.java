package gameobjects;

import gameManager.Animation;
import gameManager.Handler;
import gameManager.Texture;
import window.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Exam extends GameObject {
      
   // properties
	private Animation examWalk, examWalkM;

	private final float MAX_SPEED = 10;
	private Handler handler;
	Texture texture = GameEngine.getInstance();

	ImageIcon exam = new ImageIcon(getClass().getResource("/images/exam.png"));
	ImageIcon examLeftLooking = new ImageIcon(getClass().getResource("/images/examLeftLooking.png"));

	private float gravity = 0.15f;
	private float width = exam.getIconWidth(), height = exam.getIconHeight();

	// constructor
	public Exam( float x, float y, Handler handler, ObjectType type ) {
		super(x, y, type);
		this.handler = handler;
		examWalk = new Animation( 1, texture.examAnimation );
		examWalkM = new Animation( 1, texture.examAnimationM );
		setVelocityX(5);
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
				/*if( objectBoundsTop().intersects( temp.objectBounds() ) ) {
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
					falling = true;*/
				if( objectBoundsRight().intersects( temp.objectBounds() ) ) {
					posX = temp.getPosX() - width;
					setVelocityX(-5);
				}
				if( objectBoundsLeft().intersects( temp.objectBounds() ) ) {
					posX = temp.getPosX() + 40;
					setVelocityX(5);
				}
			}
            if( temp.getType() == ObjectType.Player )
            {
                if( objectBoundsTop().intersects( temp.objectBounds() ) ) {
                    handler.objectLinkedList.remove(temp);
                }
                if( objectBounds().intersects( temp.objectBounds() ) ) {
                    handler.objectLinkedList.remove(temp);
                }
                if( objectBoundsRight().intersects( temp.objectBounds() ) ) {
                    handler.objectLinkedList.remove(temp);
                }
                if( objectBoundsLeft().intersects( temp.objectBounds() ) ) {
                    handler.objectLinkedList.remove(temp);
                }
                // end level here
            }
		}
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
		examWalk.runAnimation(); // generate animation for player running towards right
		examWalkM.runAnimation(); // generate animation for player running towards left
	}

	@Override
	public void render(Graphics graphics) {


		if( velocityX > 0 )
			examWalk.drawAnimation(graphics, (int) posX, (int) posY, (int) width, (int) height);
		else if( velocityX < 0 )
			examWalkM.drawAnimation(graphics, (int) posX, (int) posY, (int) width, (int) height);
		else if ( facing == 1 )
			graphics.drawImage( exam.getImage(), (int) posX, (int) posY, (int) width, (int) height, null );
		else
			graphics.drawImage( examLeftLooking.getImage(), (int) posX, (int) posY, (int) width, (int) height, null );
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
}