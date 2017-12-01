package gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Coins extends PowerUps {
      private int value=0;
      
      public Coins (float x, float y, ObjectType type,int value){
            super(x, y, type);
            value=this.value;
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
}
