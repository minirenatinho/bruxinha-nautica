package model;

import game.Difficulty;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Player {
   protected static final int SECONDARY_SHOTS_AFTER_FIRE = 3;

   private final Difficulty difficulty;

   private Image image;
   private int x;
   private int y;
   private int velocityX;
   private int velocityY;
   private boolean thrusting;
   private boolean alive;
   private List<Shot> shots;
   private int width;
   private int height;
   private Card[] deck;
   private String shotTexturePath = "graphics\\Water.png";

   public Player(Difficulty difficulty) {
      this.difficulty = difficulty;
      ImageIcon icon = new ImageIcon("graphics\\Player.png");
      this.image = icon.getImage();
      this.height = this.image.getHeight((ImageObserver)null);
      this.width = this.image.getWidth((ImageObserver)null);
      this.shots = new ArrayList<>();
      this.x = 100;
      this.y = 1;
      this.deck = new Card[5];
      this.deck[0] = new Card("graphics\\Card_Water.png", "graphics\\Card_WaterS.png", 50, 1);
      this.deck[1] = new Card("graphics\\Card_Fire.png", "graphics\\Card_FireS.png", 50, 1);
      this.deck[2] = new Card("graphics\\Card_Air.png", "graphics\\Card_AirS.png", 50, 1);
      this.deck[3] = new Card("graphics\\Card_Electric.png", "graphics\\Card_ElectricS.png", 50, 1);
      this.deck[4] = new Card("graphics\\Card_Earth.png", "graphics\\Card_EarthS.png", 50, 1);
      this.deck[0].setSelected(true);
      this.alive = true;
   }

   public void keyPressed(KeyEvent keyEvent) {
      int code = keyEvent.getKeyCode();
      if (code == KeyEvent.VK_UP) {
         if (this.difficulty == Difficulty.HARD) {
            this.fireWithDefaultSecondaryShots();
         }
         this.setVelocityY(-3);
         this.setThrusting(true);
      }

      if (code == KeyEvent.VK_LEFT) {
         this.selectPreviousCard();
      }

      if (code == KeyEvent.VK_RIGHT) {
         this.selectNextCard();
      }

      if (code == KeyEvent.VK_SPACE && this.difficulty == Difficulty.EASY) {
         this.fireWithDefaultSecondaryShots();
      }

      this.updateShotTextureFromSelectedCard();
   }

   private void updateShotTextureFromSelectedCard() {
      Card[] d = this.deck;
      if (d[0].isSelected()) {
         this.setShotTexturePath("graphics\\Water.png");
      } else if (d[1].isSelected()) {
         this.setShotTexturePath("graphics\\Fire.png");
      } else if (d[2].isSelected()) {
         this.setShotTexturePath("graphics\\Air.png");
      } else if (d[3].isSelected()) {
         this.setShotTexturePath("graphics\\Electric.png");
      } else if (d[4].isSelected()) {
         this.setShotTexturePath("graphics\\Earth.png");
      }
   }

   public void shoot() {
      this.shots.add(new Shot(this.x + this.width - 20, this.y - 20, this.shotTexturePath, ""));
   }

   protected void markFirstShotsAsSecondary(int maxCount) {
      int n = Math.min(maxCount, this.shots.size());
      for (int i = 0; i < n; ++i) {
         this.shots.get(i).setSecondary(true);
      }
   }

   protected void fireWithDefaultSecondaryShots() {
      this.shoot();
      this.markFirstShotsAsSecondary(SECONDARY_SHOTS_AFTER_FIRE);
   }

   public void selectPreviousCard() {
      this.moveCardSelection(-1);
   }

   public void selectNextCard() {
      this.moveCardSelection(1);
   }

   private void moveCardSelection(int delta) {
      int n = this.deck.length;
      for (int i = 0; i < n; ++i) {
         if (this.deck[i].isSelected()) {
            this.deck[i].setSelected(false);
            int j = (i + delta + n) % n;
            this.deck[j].setSelected(true);
            return;
         }
      }
   }

   public void applyGravity() {
      this.velocityY = 2;
   }

   public void move() {
      this.x += this.velocityX;
      this.y += this.velocityY;
      if (this.y > 400) {
         this.y = 400;
         this.setAlive(false);
      }

      if (this.y < -this.image.getHeight((ImageObserver)null) + 30) {
         this.y = -this.image.getHeight((ImageObserver)null) + 30;
      }

   }

   public void keyReleased(KeyEvent keyEvent) {
      int code = keyEvent.getKeyCode();
      if (code == KeyEvent.VK_UP) {
         this.velocityY = 0;
         this.thrusting = false;
      }

   }

   public Rectangle getBounds() {
      return new Rectangle(this.x, this.y, this.width, this.height);
   }

   public Image getImage() {
      return this.image;
   }

   public void setImage(Image image) {
      this.image = image;
   }

   public int getX() {
      return this.x;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getY() {
      return this.y;
   }

   public void setY(int y) {
      this.y = y;
   }

   public int getVelocityX() {
      return this.velocityX;
   }

   public void setVelocityX(int velocityX) {
      this.velocityX = velocityX;
   }

   public int getVelocityY() {
      return this.velocityY;
   }

   public void setVelocityY(int velocityY) {
      this.velocityY = velocityY;
   }

   public boolean isThrusting() {
      return this.thrusting;
   }

   public void setThrusting(boolean thrusting) {
      this.thrusting = thrusting;
   }

   public boolean isAlive() {
      return this.alive;
   }

   public void setAlive(boolean alive) {
      this.alive = alive;
   }

   public List<Shot> getShots() {
      return this.shots;
   }

   public void setShots(List<Shot> shots) {
      this.shots = shots;
   }

   public int getWidth() {
      return this.width;
   }

   public void setWidth(int width) {
      this.width = width;
   }

   public int getHeight() {
      return this.height;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public Card[] getDeck() {
      return this.deck;
   }

   public void setDeck(Card[] deck) {
      this.deck = deck;
   }

   public String getShotTexturePath() {
      return this.shotTexturePath;
   }

   public void setShotTexturePath(String shotTexturePath) {
      this.shotTexturePath = shotTexturePath;
   }

   public Difficulty getDifficulty() {
      return this.difficulty;
   }
}
