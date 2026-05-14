package dados;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

/** Estado comum de entidade gráfica na tela (imagem, retângulo, visibilidade). */
public class Sprite {
   public static final int LARGURA_TELA = 640;

   private Image imagem;
   private int x;
   private int y;
   private int largura;
   private int altura;
   private boolean visivel = true;

   protected void carregarImagem(String caminhoArquivo) {
      ImageIcon icon = new ImageIcon(caminhoArquivo);
      this.imagem = icon.getImage();
      this.altura = this.imagem.getHeight((ImageObserver)null);
      this.largura = this.imagem.getWidth((ImageObserver)null);
      this.visivel = true;
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
      return this.visivel;
   }

   public void setVisivel(boolean visivel) {
      this.visivel = visivel;
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
}
