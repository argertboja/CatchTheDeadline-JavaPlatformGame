package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Food extends Powerups 
{
      
      ImageIcon food = new ImageIcon(getClass().getResource("/images/food.png"));
      
      private int value=3;
      
      public Food( float x, float y, ObjectType type, int value )
      {
            super( x, y, type);
            this.value = value;
      }
      
      @Override
      public void collisionDetector(LinkedList<GameObject> objects) 
      {
      }
      
      @Override
      public void render(Graphics graphics) 
      {
            graphics.drawImage( food.getImage(), (int) posX, (int) posY, null );
      }
      
      @Override
      public Rectangle objectBounds() 
      {
            return new Rectangle((int) posX, (int) posY, 32, 32);
      }
}