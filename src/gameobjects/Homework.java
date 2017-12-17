package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import accessManager.LogIn;
import database.DBInterface;
import gameManager.Animation;
import gameManager.Handler;
import gameManager.Texture;
import window.GameEngine;

public class Homework extends GameObject {
      
    private final float MAX_SPEED = 10;
	private Handler handler;
	private int id;
	private int lives = 8;

	private ImageIcon homework = new ImageIcon(getClass().getResource("/images/homework.gif"));

	private float gravity = 0.15f;
	private float width = homework.getIconWidth(), height = homework.getIconHeight();
	private Player player;
	private GameEngine gm;
	private DBInterface db;
	private String username;
	private int scores, prevCoins;

	// constructor
	public Homework( float x, float y, Handler handler, ObjectType type, int id, GameEngine gm ) {
		super(x, y, type);
		this.handler = handler;
		setVelocityX(5);
		this.id = id;
		this.gm = gm;
		for (int i = 0; i < handler.objectLinkedList.size(); i++) {
			GameObject temp = null;
			temp = handler.objectLinkedList.get(i);
			if (temp.getType() == ObjectType.Player) {
				player = (Player) temp;
			}
		}
		db = new DBInterface();
		username = LogIn.usernameValue;
		scores = gm.getScores();
		prevCoins = gm.getTotalCoins();
	}

	// methods
	public void collisions( LinkedList<GameObject> objects ) {

		for( int i = 0; i < handler.objectLinkedList.size(); i++ ) {
			GameObject temp = null;
			try {
			 temp = handler.objectLinkedList.get(i);
			}
			catch (Exception e) {}
			if( temp.getType() == ObjectType.Player )
			{
			    if( objectBoundsTop().intersects( temp.objectBounds() ) ) {
			        handler.objectLinkedList.remove(temp);
			        gm.setLives( gm.getLives() - 1 );
					try {
						db.saveHighScoresAndCoins(username, scores, prevCoins);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			    }
			    if( objectBoundsRight().intersects( temp.objectBounds() ) ) {
			        handler.objectLinkedList.remove(temp);
			        gm.setLives( gm.getLives() - 1 );
					try {
						db.saveHighScoresAndCoins(username, scores, prevCoins);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			    }
			    if( objectBoundsLeft().intersects( temp.objectBounds() ) ) {
			        handler.objectLinkedList.remove(temp);
			        gm.setLives( gm.getLives() - 1 );
					try {
						db.saveHighScoresAndCoins(username, scores, prevCoins);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			    }
			}
			if( temp.getType() == ObjectType.Pen )
			{
				if( objectBoundsRight().intersects( temp.objectBounds() ) ) {
					handler.objectLinkedList.remove(temp);
					lives -= 4;
					if (lives <= 0)
						player.setEnemyId(id);
				}
				if( objectBoundsLeft().intersects( temp.objectBounds() ) ) {
					handler.objectLinkedList.remove(temp);
					lives -= 4;
					if (lives <= 0)
						player.setEnemyId(id);
				}
			}
			if( temp.getType() == ObjectType.Eraser )
			{
				if( objectBoundsRight().intersects( temp.objectBounds() ) ) {
					handler.objectLinkedList.remove(temp);
					lives -= 1;
					if (lives <= 0)
						player.setEnemyId(id);
				}
				if( objectBoundsLeft().intersects( temp.objectBounds() ) ) {
					handler.objectLinkedList.remove(temp);
					lives -= 1;
					if (lives <= 0)
						player.setEnemyId(id);
				}
			}
			if( temp.getType() == ObjectType.PaintSpray )
			{
				if( objectBoundsRight().intersects( temp.objectBounds() ) ) {
					handler.objectLinkedList.remove(temp);
					lives -= 3;
					if (lives <= 0)
						player.setEnemyId(id);
				}
				if( objectBoundsLeft().intersects( temp.objectBounds() ) ) {
					handler.objectLinkedList.remove(temp);
					lives -= 3;
					if (lives <= 0)
						player.setEnemyId(id);
				}
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
		collisions( objects );
	}

	@Override
	public void render(Graphics graphics) {
        graphics.drawImage(homework.getImage(), (int) posX, (int) posY, null);
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

	public int getId () {
		return id;
	}
}