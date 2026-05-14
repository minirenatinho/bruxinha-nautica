package dados;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Carta {
   private Image arquivo;
   private Image arquivoS;
   private String caminho;
   private boolean isSelecionada;
   private int x;
   private int y;

   public Carta(String arquivo, String arquivoS, int x, int y) {
      this.x = x;
      this.y = y;
      this.caminho = arquivo;
      ImageIcon adds = new ImageIcon(arquivo);
      this.arquivo = adds.getImage();
      ImageIcon addsS = new ImageIcon(arquivoS);
      this.arquivoS = addsS.getImage();
      this.isSelecionada = false;
   }

   public Image getArquivo() {
      return this.arquivo;
   }

   public void setArquivo(Image arquivo) {
      this.arquivo = arquivo;
   }

   public boolean isSelecionada() {
      return this.isSelecionada;
   }

   public void setSelecionada(boolean isSelecionada) {
      this.isSelecionada = isSelecionada;
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

   public Image getArquivoS() {
      return this.arquivoS;
   }

   public void setArquivoS(Image arquivoS) {
      this.arquivoS = arquivoS;
   }

   public String getCaminho() {
      return this.caminho;
   }

   public void setCaminho(String caminho) {
      this.caminho = caminho;
   }
}
