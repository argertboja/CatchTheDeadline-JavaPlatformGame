package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class BlankSheet extends Enemy {
      
      private int power= 1 ;
      private int speed=1;
      
      public BlankSheet (float x, float y, ObjectType type, float gravity, int speed, int power){
            super(x, y, type, gravity);
            speed=this.speed;
            power=this.power;
            
      }
      @Override
      public void collisionDetector(LinkedList<GameObject> objects) {
            // TODO Auto-generated method stub
            
      }
      
      @Override
      public void render(Graphics graphics) {
            // TODO Auto-generated method stub
            
      }
      
      @Override
      public Rectangle objectBounds() {
            // TODO Auto-generated method stub
            return null;
      }
      /*
       public  void walkAnim(Animation anim){
       
       }
       
       public  void shootAnim(Animation anim){
       }
       */
}
