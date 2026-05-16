package model;

public class Shot extends Sprite {
   private static final int SPEED = 3;
   private boolean secondary;
   private String kind;
   /** Player shot element; null for enemy projectiles. */
   private final Element element;

   public Shot(int x, int y, String filePath, String kind, Element element) {
      super.loadImage(filePath);
      this.kind = kind;
      this.element = element;
      this.setX(x);
      this.setY(y);
      this.secondary = false;
   }

   public void move() {
      int d = "Enemy".equals(this.kind) ? -SPEED : SPEED;
      this.setX(this.getX() + d);
      if (this.getX() > SCREEN_WIDTH || this.getX() < 0) {
         this.setVisible(false);
      }
   }

   public boolean isSecondary() {
      return this.secondary;
   }

   public void setSecondary(boolean secondary) {
      this.secondary = secondary;
   }

   public Element getElement() {
      return this.element;
   }
}
