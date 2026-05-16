package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Enemy extends Sprite {
   private static final int SPEED = 1;
   private static final int BOSS_SHOOT_INTERVAL = 30;

   private String kind;
   private Element element;
   private final boolean shoots;
   private final List<Shot> shots;
   private int shootTimer = 0;

   public Enemy(int x, int y, String filePath, String kind, boolean shoots) {
      this.kind = kind;
      this.shoots = shoots;
      this.shots = shoots ? new ArrayList<>() : Collections.emptyList();
      Random random = new Random();
      if (!kind.equals("Boss")) {
         switch (random.nextInt(5)) {
            case 0:
               filePath = "graphics\\Enemy_Water.png";
               this.element = Element.WATER;
               break;
            case 1:
               filePath = "graphics\\Enemy_Fire.png";
               this.element = Element.FIRE;
               break;
            case 2:
               filePath = "graphics\\Enemy_Air.png";
               this.element = Element.AIR;
               break;
            case 3:
               filePath = "graphics\\Enemy_Electric.png";
               this.element = Element.ELECTRIC;
               break;
            default:
               filePath = "graphics\\Enemy_Earth.png";
               this.element = Element.EARTH;
         }
      } else {
         this.element = null;
      }

      this.setX(x - 25);
      this.setY(y);
      super.loadImage(filePath);
   }

   public void move() {
      if (this.getX() < 0) {
         this.setX(SCREEN_WIDTH);
      } else {
         this.setX(this.getX() - SPEED);
      }

      if (this.shoots) {
         if (this.kind.equals("Boss")) {
            this.shootTimer++;
            if (this.shootTimer >= BOSS_SHOOT_INTERVAL) {
               this.shootTimer = 0;
               this.shootBoss();
            }
         } else {
            this.shoot();
            if (!this.shots.isEmpty()) {
               this.shots.get(0).setSecondary(true);
            }
         }
      }
   }

   private void shoot() {
      Shot shot = new Shot(this.getX(), this.getY(), "graphics\\Dark.png", "Enemy", null);
      shot.setX(shot.getX() + 35);
      shot.setY(shot.getY() + 10);
      this.shots.add(shot);
   }

   private void shootBoss() {
      int[] yOffsets = {10, 80, 150};
      for (int offset : yOffsets) {
         Shot shot = new Shot(this.getX(), this.getY(), "graphics\\Dark.png", "Enemy", null);
         shot.setX(shot.getX() + 35);
         shot.setY(shot.getY() + offset);
         shot.setSecondary(true);
         this.shots.add(shot);
      }
   }

   public List<Shot> getShots() {
      return this.shots;
   }

   public String getKind() {
      return this.kind;
   }

   /** {@code null} for the boss; set for elemental dragons. */
   public Element getElement() {
      return this.element;
   }
}
