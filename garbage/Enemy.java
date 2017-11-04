package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Enemy extends GameObject {
      
      // private Animation walkAnim;
      private int speed=0;
      private int power=0;
      private float gravity= 0.05f;
      
      public Enemy(float x, float y, ObjectType type, float gravity, int speed, int power ){
            super(x, y, type);
            gravity= this.gravity;
            speed= this.speed;
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

