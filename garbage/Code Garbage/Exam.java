package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Exam extends Enemy
{
      
      private int power=5;
      private int speed=2;
      
      public Exam (float x, float y, ObjectType type, float gravity, int speed, int power)
      {
            super(x, y, type, gravity);
            power=this.power;
            speed=this.speed;
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
      /*
       public  void walkAnim(Animation anim)
       {
        // TODO
       }
       
       public  void shootAnim(Animation anim)
       {
        // TODO
       }
       */
}
