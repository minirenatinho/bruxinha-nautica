package gui;

import fachada.Fachada;
import javax.swing.JFrame;

public class LittleProJavaIP extends JFrame {
   public LittleProJavaIP() {
      Fachada fachada = new Fachada();
      this.add(fachada.iniciarFacil());
      this.setTitle("Little ProJava IP");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setSize(640, 480);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
      this.setResizable(false);
   }

   public static void main(String[] args) {
      new LittleProJavaIP();
   }
}
