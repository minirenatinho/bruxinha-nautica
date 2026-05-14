package dados;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Random;
import javax.swing.ImageIcon;

public class Inimigo {
   private Image imagem;
   private int x;
   private int y;
   private int altura;
   private int largura;
   private boolean isVisivel;
   private String tipo;
   private String arquivo;
   private static int ale;
   private static final int LARGURA_TELA = 640;
   private static final int VELOCIDADE = 1;

   public Inimigo(int x, int y, String arquivo, String tipo) {
      Random gera = new Random();
      this.tipo = tipo;
      if (!tipo.equals("Boss")) {
         ale = gera.nextInt(5) + 1;
         if (ale == 1) {
            arquivo = "Gráficos\\Inimigo_Agua.png";
         }

         if (ale == 2) {
            arquivo = "Gráficos\\Inimigo_Fogo.png";
         }

         if (ale == 3) {
            arquivo = "Gráficos\\Inimigo_Ar.png";
         }

         if (ale == 4) {
            arquivo = "Gráficos\\Inimigo_Elet.png";
         }

         if (ale == 5) {
            arquivo = "Gráficos\\Inimigo_Terra.png";
         }
      }

      this.setArquivo(arquivo);
      this.x = x - 25;
      this.y = y;
      ImageIcon adds = new ImageIcon(arquivo);
      this.imagem = adds.getImage();
      this.altura = this.imagem.getHeight((ImageObserver)null);
      this.largura = this.imagem.getWidth((ImageObserver)null);
      this.isVisivel = true;
   }

   public void movimentar() {
      if (this.x < 0) {
         this.x = getLarguraTela();
      } else {
         this.x -= getVelocidade();
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

   public String getTipo() {
      return this.tipo;
   }

   public void setTipo(String tipo) {
      this.tipo = tipo;
   }

   public static int getLarguraTela() {
      return 640;
   }

   public static int getVelocidade() {
      return 1;
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

   public String getArquivo() {
      return this.arquivo;
   }

   public void setArquivo(String arquivo) {
      this.arquivo = arquivo;
   }
}
