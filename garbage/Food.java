package gameobjects;
public class Food extends PowerUps {
      
      private int value=3;
       
      public Food(float x, float y, ObjectType type,int value){
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
