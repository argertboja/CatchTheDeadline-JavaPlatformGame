package gameobjects;

import gameManager.Animation;
import gameManager.Handler;
import gameManager.Texture;
import window.GameEngine;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class Homework extends GameObject 
{
  
  // properties
  private Animation homeworkAnim;
  
  private final float MAX_SPEED = 10;
  private Handler handler;
  private Texture texture = GameEngine.getInstance();
  
  private ImageIcon homework = new ImageIcon(getClass().getResource("/images/homework.png"));
  
  private float gravity = 0.15f;
  private float width = homework.getIconWidth(), height = homework.getIconHeight();
  
  private Player player;
  
  private int lives = 5;
  private int id;
  
  // constructor
  public Homework( float x, float y, Handler handler, ObjectType type, int id ) 
  {
    super(x, y, type);
    this.handler = handler;
    homeworkAnim = new Animation( 1, texture.homeworkflipping );
    setVelocityX(5);
    this.id = id;
    
    for (int i =0; i < handler.objectLinkedList.size(); i++) 
    {
      GameObject temp = null;
      temp = handler.objectLinkedList.get(i);
      if (temp.getType() ==ObjectType.Player)
      {
        player = (Player) temp;
      }
    }
  }
  
  // methods
  public void collisions( LinkedList<GameObject> objects ) 
  {
    
    for( int i = 0; i < handler.objectLinkedList.size(); i++ ) 
    {
      GameObject temp = null;
      try 
      {
        temp = handler.objectLinkedList.get(i);
      }
      catch (Exception e) {}
      
      if( temp.getType() == ObjectType.Player )
      {
        if( objectBoundsTop().intersects( temp.objectBounds() ) ) 
        {
          handler.objectLinkedList.remove(temp);
        }
        if( objectBounds().intersects( temp.objectBounds() ) ) 
        {
          handler.objectLinkedList.remove(temp);
        }
        if( objectBoundsRight().intersects( temp.objectBounds() ) ) 
        {
          handler.objectLinkedList.remove(temp);
        }
        if( objectBoundsLeft().intersects( temp.objectBounds() ) ) 
        {
          handler.objectLinkedList.remove(temp);
        }
        // end level here
      }
      if( temp.getType() == ObjectType.Pen )
      {
        if( objectBoundsTop().intersects( temp.objectBounds() ) ) 
        {
          handler.objectLinkedList.remove(temp);
        }
        if( objectBounds().intersects( temp.objectBounds() ) ) 
        {
          handler.objectLinkedList.remove(temp);
        }
        if( objectBoundsRight().intersects( temp.objectBounds() ) ) 
        {
          handler.objectLinkedList.remove(temp);
          lives -= 4;
          if (lives <= 0)
            player.setEnemyId(id);
        }
        if( objectBoundsLeft().intersects( temp.objectBounds() ) ) 
        {
          handler.objectLinkedList.remove(temp);
          lives -= 4;
          if (lives <= 0)
            player.setEnemyId(id);
        }
        // end level here
      }
      if( temp.getType() == ObjectType.Eraser )
      {
        if( objectBoundsTop().intersects( temp.objectBounds() ) ) 
        {
          handler.objectLinkedList.remove(temp);
        }
        if( objectBounds().intersects( temp.objectBounds() ) ) 
        {
          handler.objectLinkedList.remove(temp);
        }
        if( objectBoundsRight().intersects( temp.objectBounds() ) ) 
        {
          handler.objectLinkedList.remove(temp);
          lives -= 1;
          if (lives <= 0)
            player.setEnemyId(id);
        }
        if( objectBoundsLeft().intersects( temp.objectBounds() ) ) 
        {
          handler.objectLinkedList.remove(temp);
          lives -= 1;
          if (lives <= 0)
            player.setEnemyId(id);
        }
        // end level here
      }
      if( temp.getType() == ObjectType.PaintSpray )
      {
        if( objectBoundsTop().intersects( temp.objectBounds() ) ) 
        {
          handler.objectLinkedList.remove(temp);
        }
        if( objectBounds().intersects( temp.objectBounds() ) ) 
        {
          handler.objectLinkedList.remove(temp);
        }
        if( objectBoundsRight().intersects( temp.objectBounds() ) ) 
        {
          handler.objectLinkedList.remove(temp);
          lives -= 3;
          if (lives <= 0)
            player.setEnemyId(id);
        }
        if( objectBoundsLeft().intersects( temp.objectBounds() ) )
        {
          handler.objectLinkedList.remove(temp);
          lives -= 3;
          if (lives <= 0)
            player.setEnemyId(id);
        }
        // end level here
      }
    }
  }
  
  public float getGravity() 
  {
    return gravity;
  }
  
  public void setGravity(int gravity) 
  {
    this.gravity = gravity;
  }
  
  @Override
  public void collisionDetector(LinkedList<GameObject> objects ) 
  {
    ////////////
    collisions( objects );
    homeworkAnim.runAnimation(); 
  }
  
  @Override
  public void render(Graphics graphics) 
  {
    ///////////////////////////
    homeworkAnim.drawAnimation(graphics, (int) posX, (int) posY, (int) width, (int) height);
  }
  
  @Override
  public Rectangle objectBounds() 
  {
    return new Rectangle( (int) ((int)posX+(width/2)-((width/2)/2)), (int) ((int)posY+(height/2)), (int)width/2, (int)height/2 );
  }
  
  public Rectangle objectBoundsTop() 
  {
    return new Rectangle( (int) ((int)posX+(width/2)-((width/2)/2)), (int)posY, (int)width/2, (int)height/2 );
  }
  
  public Rectangle objectBoundsRight() 
  {
    return new Rectangle( (int) ((int)posX+width-5), (int)posY+5, (int)5, (int)height-10 );
  }
  
  public Rectangle objectBoundsLeft() 
  {
    return new Rectangle( (int)posX, (int)posY+5, (int)5, (int)height-10 );
  }
  
  public Handler getHandler() 
  {
    return handler;
  }
  
  public int getId () 
  {
    return id;
  }
  
  
}

 
 
