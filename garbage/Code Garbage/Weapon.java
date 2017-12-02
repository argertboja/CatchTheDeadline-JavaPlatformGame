package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import gameManager.Animation;

public class Weapon extends GameObject 
{
      private Animation anim;
      public Weapon(float x, float y, ObjectType type, int velX)
      {
         super(x, y, type);
         this.velocityX = velX;
      
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
}
