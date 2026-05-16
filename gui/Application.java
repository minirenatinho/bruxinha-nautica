package gui;

import javax.swing.JFrame;

public class Application extends JFrame {
   public Application() {
      this.setTitle("Bruxinha Náutica");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(640, 480);
      this.setLocationRelativeTo(null);
      this.setResizable(false);
      this.setContentPane(new DifficultyScreen(this));
      this.setVisible(true);
   }

   public static void main(String[] args) {
      new Application();
   }
}
