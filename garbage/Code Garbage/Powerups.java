package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Powerups extends GameObject 
{
      
      private int value=0 ;
      
      public Powerups(float x, float y, ObjectType type) 
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
            // TODO Auto-generated method stub
            
      }
      
      @Override
      public Rectangle objectBounds() 
      {
            // TODO Auto-generated method stub
            return null;
      }
      public int getValue() 
      {
            return value;
      }
      
      public void setValue(int value) 
      {
            this.value = value;
      }

}