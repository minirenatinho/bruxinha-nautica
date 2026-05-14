package dados;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public abstract class PersonagemAbstrato {
   private Image imagem;
   private int x;
   private int y;
   private int xR;
   private int yR;
   private boolean upped;
   private boolean isVivo;
   private List<Tiro> tiros;
   private int largura;
   private int altura;
   private Carta[] baralho;
   private String tipo = "Gráficos\\Agua.png";

   public PersonagemAbstrato() {
      ImageIcon adds = new ImageIcon("Gráficos\\Personagem.png");
      this.imagem = adds.getImage();
      this.altura = this.imagem.getHeight((ImageObserver)null);
      this.largura = this.imagem.getWidth((ImageObserver)null);
      this.tiros = new ArrayList();
      this.x = 100;
      this.y = 1;
      this.baralho = new Carta[5];
      this.baralho[0] = new Carta("Gráficos\\Carta_Agua.png", "Gráficos\\Carta_AguaS.png", 50, 1);
      this.baralho[1] = new Carta("Gráficos\\Carta_Fogo.png", "Gráficos\\Carta_FogoS.png", 50, 1);
      this.baralho[2] = new Carta("Gráficos\\Carta_Ar.png", "Gráficos\\Carta_ArS.png", 50, 1);
      this.baralho[3] = new Carta("Gráficos\\Carta_Elet.png", "Gráficos\\Carta_EletS.png", 50, 1);
      this.baralho[4] = new Carta("Gráficos\\Carta_Terra.png", "Gráficos\\Carta_TerraS.png", 50, 1);
      this.baralho[0].setSelecionada(true);
      this.isVivo = true;
   }

   public abstract void keyPressed(KeyEvent var1);

   public void atirar() {
      this.tiros.add(new Tiro(this.x + this.largura - 20, this.y - 20, this.tipo, ""));
   }

   public void cartaE() {
      int ope = 0;

      for(int i = 0; i < this.baralho.length; ++i) {
         if (this.baralho[i].isSelecionada()) {
            ope = i + 1;
         }
      }

      for(int i = 0; i < ope; ++i) {
         if (this.baralho[i].isSelecionada()) {
            this.baralho[i].setSelecionada(false);
            if (i > 0) {
               this.baralho[i - 1].setSelecionada(true);
            } else {
               this.baralho[4].setSelecionada(true);
            }
         }
      }

   }

   public void cartaD() {
      int ope = 0;

      for(int i = 0; i < this.baralho.length; ++i) {
         if (this.baralho[i].isSelecionada()) {
            ope = i + 1;
         }
      }

      for(int i = 0; i < ope; ++i) {
         if (this.baralho[i].isSelecionada()) {
            this.baralho[i].setSelecionada(false);
            if (i < 4) {
               this.baralho[i + 1].setSelecionada(true);
            } else {
               this.baralho[0].setSelecionada(true);
            }
         }
      }

   }

   public void gravidade() {
      this.yR = 2;
   }

   public void movimentar() {
      this.x += this.xR;
      this.y += this.yR;
      if (this.y > 400) {
         this.y = 400;
         this.setVivo(false);
      }

      if (this.y < -this.imagem.getHeight((ImageObserver)null) + 30) {
         this.y = -this.imagem.getHeight((ImageObserver)null) + 30;
      }

   }

   public void keyReleased(KeyEvent butao) {
      int code = butao.getKeyCode();
      if (code == 38) {
         this.yR = 0;
         this.upped = false;
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

   public int getxR() {
      return this.xR;
   }

   public void setxR(int xR) {
      this.xR = xR;
   }

   public int getyR() {
      return this.yR;
   }

   public void setyR(int yR) {
      this.yR = yR;
   }

   public boolean isUpped() {
      return this.upped;
   }

   public void setUpped(boolean upped) {
      this.upped = upped;
   }

   public boolean isVivo() {
      return this.isVivo;
   }

   public void setVivo(boolean isVivo) {
      this.isVivo = isVivo;
   }

   public List<Tiro> getTiros() {
      return this.tiros;
   }

   public void setTiros(List<Tiro> tiros) {
      this.tiros = tiros;
   }

   public int getLargura() {
      return this.largura;
   }

   public void setLargura(int largura) {
      this.largura = largura;
   }

   public int getAltura() {
      return this.altura;
   }

   public void setAltura(int altura) {
      this.altura = altura;
   }

   public Carta[] getBaralho() {
      return this.baralho;
   }

   public void setBaralho(Carta[] baralho) {
      this.baralho = baralho;
   }

   public String getTipo() {
      return this.tipo;
   }

   public void setTipo(String tipo) {
      this.tipo = tipo;
   }
}
