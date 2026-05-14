package dados;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import negocios.Dificuldade;

public class Personagem {
   protected static final int TIROS_SECUNDARIOS_APOS_DISPARO = 3;

   private final Dificuldade dificuldade;

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

   public Personagem(Dificuldade dificuldade) {
      this.dificuldade = dificuldade;
      ImageIcon adds = new ImageIcon("Gráficos\\Personagem.png");
      this.imagem = adds.getImage();
      this.altura = this.imagem.getHeight((ImageObserver)null);
      this.largura = this.imagem.getWidth((ImageObserver)null);
      this.tiros = new ArrayList<>();
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

   public void keyPressed(KeyEvent butao) {
      int code = butao.getKeyCode();
      if (code == KeyEvent.VK_UP) {
         if (this.dificuldade == Dificuldade.DIFICIL) {
            this.disparoComTirosSecundariosPadrao();
         }
         this.setyR(-3);
         this.setUpped(true);
      }

      if (code == KeyEvent.VK_LEFT) {
         this.cartaE();
      }

      if (code == KeyEvent.VK_RIGHT) {
         this.cartaD();
      }

      if (code == KeyEvent.VK_SPACE && this.dificuldade == Dificuldade.FACIL) {
         this.disparoComTirosSecundariosPadrao();
      }

      this.atualizarTipoPelaCartaSelecionada();
   }

   private void atualizarTipoPelaCartaSelecionada() {
      Carta[] b = this.baralho;
      if (b[0].isSelecionada()) {
         this.setTipo("Gráficos\\Agua.png");
      } else if (b[1].isSelecionada()) {
         this.setTipo("Gráficos\\Fogo.png");
      } else if (b[2].isSelecionada()) {
         this.setTipo("Gráficos\\Ar.png");
      } else if (b[3].isSelecionada()) {
         this.setTipo("Gráficos\\Elet.png");
      } else if (b[4].isSelecionada()) {
         this.setTipo("Gráficos\\Terra.png");
      }
   }

   public void atirar() {
      this.tiros.add(new Tiro(this.x + this.largura - 20, this.y - 20, this.tipo, ""));
   }

   protected void marcarPrimeirosTirosComoSecundarios(int quantidadeMax) {
      int n = Math.min(quantidadeMax, this.tiros.size());
      for (int i = 0; i < n; ++i) {
         this.tiros.get(i).setSec(true);
      }
   }

   protected void disparoComTirosSecundariosPadrao() {
      this.atirar();
      this.marcarPrimeirosTirosComoSecundarios(TIROS_SECUNDARIOS_APOS_DISPARO);
   }

   public void cartaE() {
      this.moverSelecaoCarta(-1);
   }

   public void cartaD() {
      this.moverSelecaoCarta(1);
   }

   private void moverSelecaoCarta(int delta) {
      int n = this.baralho.length;
      for (int i = 0; i < n; ++i) {
         if (this.baralho[i].isSelecionada()) {
            this.baralho[i].setSelecionada(false);
            int j = (i + delta + n) % n;
            this.baralho[j].setSelecionada(true);
            return;
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
      if (code == KeyEvent.VK_UP) {
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

   public Dificuldade getDificuldade() {
      return this.dificuldade;
   }
}
