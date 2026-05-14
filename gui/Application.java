package gui;

import facade.GameFacade;
import javax.swing.JFrame;

public class Application extends JFrame {
   public Application() {
      GameFacade gameFacade = new GameFacade();
      this.add(gameFacade.startHard());
      this.setTitle("Bruxinha Náutica");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(640, 480);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
      this.setResizable(false);
   }

   public static void main(String[] args) {
      new Application();
   }
}
