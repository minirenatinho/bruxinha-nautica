package dados;

import javax.swing.ImageIcon;

public class Tiro extends Sprite {
   private static final int VELOCIDADE = 3;
   private boolean isSec;
   private String tipo;

   public Tiro(int x, int y, String arquivo, String tipo) {
      super.carregarImagem(arquivo);
      this.tipo = tipo;
      this.setX(x);
      this.setY(y);
      this.isSec = false;
   }

   public void movimentar() {
      int d = "Inimigo".equals(this.tipo) ? -VELOCIDADE : VELOCIDADE;
      this.setX(this.getX() + d);
      if (this.getX() > LARGURA_TELA) {
         this.setVisivel(false);
      }
   }

   public static int getLarguraTela() {
      return LARGURA_TELA;
   }

   public static int getVelocidade() {
      return VELOCIDADE;
   }

   public boolean isSec() {
      return this.isSec;
   }

   public void setSec(boolean isSec) {
      this.isSec = isSec;
   }
}
