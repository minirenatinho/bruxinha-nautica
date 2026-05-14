package dados;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

public class Tiro {
   private Image imagem;
   private int x;
   private int y;
   private int altura;
   private int largura;
   private boolean isVisivel;
   private static final int LARGURA_TELA = 640;
   private static final int VELOCIDADE = 3;
   private boolean isSec;
   private String tipo;

   public Tiro(int x, int y, String arquivo, String tipo) {
      ImageIcon adds = new ImageIcon(arquivo);
      this.imagem = adds.getImage();
      this.tipo = tipo;
      this.x = x;
      this.y = y;
      this.altura = this.imagem.getHeight((ImageObserver)null);
      this.largura = this.imagem.getWidth((ImageObserver)null);
      this.isVisivel = true;
      this.isSec = false;
   }

   public void movimentar() {
      if (this.tipo.equals("Inimigo")) {
         this.x -= 3;
         if (this.x > 640) {
            this.isVisivel = false;
         }
      } else {
         this.x += 3;
         if (this.x > 640) {
            this.isVisivel = false;
         }
      }

   }

   public Rectangle getBounds() {
      return new Rectangle(this.x, this.y, this.largura, this.altura);
   }

   public Image getImagem() {
      return this.imagem;
   }

   public void setImagem(Image imagem) {
      this.imagem = imagem;
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

   public boolean isVisivel() {
      return this.isVisivel;
   }

   public void setVisivel(boolean isVisivel) {
      this.isVisivel = isVisivel;
   }

   public static int getLarguraTela() {
      return 640;
   }

   public static int getVelocidade() {
      return 3;
   }

   public int getAltura() {
      return this.altura;
   }

   public void setAltura(int altura) {
      this.altura = altura;
   }

   public int getLargura() {
      return this.largura;
   }

   public void setLargura(int largura) {
      this.largura = largura;
   }

   public boolean isSec() {
      return this.isSec;
   }

   public void setSec(boolean isSec) {
      this.isSec = isSec;
   }
}
