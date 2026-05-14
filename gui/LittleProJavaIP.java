package gui;

import fachada.Fachada;
import java.awt.Component;
import javax.swing.JFrame;

public class LittleProJavaIP extends JFrame {
   public LittleProJavaIP() {
      Fachada fachada = new Fachada();
      this.add((Component)fachada.iniciarFacil());
      this.setTitle("Little ProJava IP");
      this.setDefaultCloseOperation(3);
      this.setSize(640, 480);
      this.setLocationRelativeTo((Component)null);
      this.setVisible(true);
      this.setResizable(false);
   }

   public static void main(String[] args) {
      new LittleProJavaIP();
   }
}
