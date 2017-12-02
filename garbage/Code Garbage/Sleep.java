package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import javax.swing.ImageIcon;

public class Sleep extends Powerups 
{
      private int value=3;
      ImageIcon bed = new ImageIcon(getClass().getResource("/images/bed.png"));
      
      public Sleep(float x, float y, ObjectType type,int value)
      {
            super(x, y, type);
            value=this.value;
      }
      
      @Override
      public void collisionDetector(LinkedList<GameObject> objects) 
      {
            // TODO Auto-generated method stub
            
      }
      
     @Override
      public void render(Graphics graphics) 
      {
            graphics.drawImage( bed.getImage(), (int) posX, (int) posY, null );
            
      }
      
      @Override
      public Rectangle objectBounds() 
      {
            // TODO Auto-generated method stub
          return new Rectangle((int) posX, (int) posY, 32, 32);
      }
      
}
