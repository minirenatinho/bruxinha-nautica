package model;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

/** Shared state for a screen entity (image, bounds, visibility). */
public class Sprite {
   public static final int SCREEN_WIDTH = 640;

   private Image image;
   private int x;
   private int y;
   private int width;
   private int height;
   private boolean visible = true;

   protected void loadImage(String filePath) {
      ImageIcon icon = new ImageIcon(filePath);
      this.image = icon.getImage();
      this.height = this.image.getHeight((ImageObserver)null);
      this.width = this.image.getWidth((ImageObserver)null);
      this.visible = true;
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

   public boolean isVisible() {
      return this.visible;
   }

   public void setVisible(boolean visible) {
      this.visible = visible;
   }

   public int getHeight() {
      return this.height;
   }

   public void setHeight(int height) {
      this.height = height;
   }

   public int getWidth() {
      return this.width;
   }

   public void setWidth(int width) {
      this.width = width;
   }
}
