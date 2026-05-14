package model;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Card {
   private Image normalImage;
   private Image selectedImage;
   private String path;
   private boolean selected;
   private int x;
   private int y;

   public Card(String normalPath, String selectedPath, int x, int y) {
      this.x = x;
      this.y = y;
      this.path = normalPath;
      ImageIcon icon = new ImageIcon(normalPath);
      this.normalImage = icon.getImage();
      ImageIcon iconS = new ImageIcon(selectedPath);
      this.selectedImage = iconS.getImage();
      this.selected = false;
   }

   public Image getNormalImage() {
      return this.normalImage;
   }

   public void setNormalImage(Image normalImage) {
      this.normalImage = normalImage;
   }

   public boolean isSelected() {
      return this.selected;
   }

   public void setSelected(boolean selected) {
      this.selected = selected;
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

   public Image getSelectedImage() {
      return this.selectedImage;
   }

   public void setSelectedImage(Image selectedImage) {
      this.selectedImage = selectedImage;
   }

   public String getPath() {
      return this.path;
   }

   public void setPath(String path) {
      this.path = path;
   }
}
