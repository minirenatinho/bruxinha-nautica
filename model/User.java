package model;

import game.Difficulty;
import java.awt.Image;
import javax.swing.ImageIcon;

public class User {
   private Player easyModePlayer;
   private Player hardModePlayer;
   private String name;
   private Image image;

   public User(String name, String filePath) {
      ImageIcon icon = new ImageIcon(filePath);
      this.image = icon.getImage();
      this.easyModePlayer = new Player(Difficulty.EASY);
      this.easyModePlayer.setImage(this.image);
      this.hardModePlayer = new Player(Difficulty.HARD);
      this.hardModePlayer.setImage(this.image);
      this.name = name;
   }

   public Player getEasyModePlayer() {
      return this.easyModePlayer;
   }

   public void setEasyModePlayer(Player easyModePlayer) {
      this.easyModePlayer = easyModePlayer;
   }

   public Player getHardModePlayer() {
      return this.hardModePlayer;
   }

   public void setHardModePlayer(Player hardModePlayer) {
      this.hardModePlayer = hardModePlayer;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Image getImage() {
      return this.image;
   }

   public void setImage(Image image) {
      this.image = image;
   }
}
