package model;

import javax.swing.ImageIcon;

public class Shot extends Sprite {
   private static final int SPEED = 3;
   private boolean secondary;
   private String kind;

   public Shot(int x, int y, String filePath, String kind) {
      super.loadImage(filePath);
      this.kind = kind;
      this.setX(x);
      this.setY(y);
      this.secondary = false;
   }

   public void move() {
      int d = "Enemy".equals(this.kind) ? -SPEED : SPEED;
      this.setX(this.getX() + d);
      if (this.getX() > SCREEN_WIDTH) {
         this.setVisible(false);
      }
   }

   public static int getScreenWidth() {
      return SCREEN_WIDTH;
   }

   public static int getSpeed() {
      return SPEED;
   }

   public boolean isSecondary() {
      return this.secondary;
   }

   public void setSecondary(boolean secondary) {
      this.secondary = secondary;
   }
}
