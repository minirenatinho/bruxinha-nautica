package negocios;

import dados.Carta;
import dados.InimigoDificil;
import dados.PersonagemFacil;
import dados.Tiro;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase2Facil extends JPanel implements ActionListener, Fase {
   private Image fundo;
   private PersonagemFacil eu;
   private Timer tempo;
   private boolean emJogo;
   private List<InimigoDificil> inimigos;
   private static int ale;
   private boolean isboss = true;
   private int bossHit = 49;
   private int[][] coordenadas = new int[][]{{1380, 29}, {1200, 59}, {1380, 89}, {780, 109}, {580, 139}, {880, 239}, {790, 259}, {760, 50}, {790, 150}, {1180, 209}, {560, 45}, {510, 70}, {930, 159}, {590, 80}, {530, 60}, {940, 59}, {990, 30}, {920, 200}, {900, 259}, {660, 50}, {540, 90}, {810, 220}, {860, 20}, {740, 180}, {820, 128}, {490, 170}, {700, 30}, {920, 300}, {856, 328}, {456, 320}};

   public Fase2Facil() {
      this.setFocusable(true);
      this.setDoubleBuffered(true);
      ImageIcon adds = new ImageIcon("Gráficos\\Fase2.png");
      this.fundo = adds.getImage();
      this.eu = new PersonagemFacil();
      this.emJogo = true;
      this.inicializaInimigos();
      this.addKeyListener(new TecladoAdapter((TecladoAdapter)null));
      this.tempo = new Timer(5, this);
      this.tempo.start();
   }

   public void inicializaInimigos() {
      this.inimigos = new ArrayList();

      for(int i = 0; i < this.coordenadas.length; ++i) {
         this.inimigos.add(new InimigoDificil(this.coordenadas[i][0], this.coordenadas[i][1], "", "normal"));
      }

   }

   public void inicializaBoss() {
      this.inimigos = new ArrayList();
      this.inimigos.add(new InimigoDificil(640, 50, "Gráficos\\Boss.png", "Boss"));
   }

   public void checarColisoes() {
      Rectangle recPersonagem = this.eu.getBounds();

      for(int i = 0; i < this.inimigos.size(); ++i) {
         InimigoDificil aux = (InimigoDificil)this.inimigos.get(i);
         Rectangle recInimigo = aux.getBounds();
         if (recPersonagem.intersects(recInimigo)) {
            this.eu.setVivo(false);
            aux.setVisivel(false);
            this.emJogo = false;
         }
      }

      List<Tiro> tiros = this.eu.getTiros();

      for(int i = 0; i < tiros.size(); ++i) {
         Tiro aux = (Tiro)tiros.get(i);
         Rectangle recTiro = aux.getBounds();

         for(int j = 0; j < this.inimigos.size(); ++j) {
            InimigoDificil aux2 = (InimigoDificil)this.inimigos.get(j);
            Rectangle recInimigo = aux2.getBounds();
            if (recTiro.intersects(recInimigo)) {
               aux.setVisivel(false);
               if (aux2.getTipo().equals("Boss")) {
                  --this.bossHit;
                  if (this.bossHit == -1) {
                     aux2.setVisivel(false);
                  }
               } else {
                  aux2.setVisivel(false);
               }
            }
         }
      }

   }

   public void paint(Graphics e) {
      Graphics2D grafico = (Graphics2D)e;
      grafico.drawImage(this.fundo, 0, 0, (ImageObserver)null);
      if (this.emJogo) {
         grafico.drawImage(this.eu.getImagem(), this.eu.getX(), this.eu.getY(), this);
         List<Tiro> tiros = this.eu.getTiros();

         for(int i = 0; i < tiros.size(); ++i) {
            Tiro t = (Tiro)tiros.get(i);
            if (t.isVisivel()) {
               grafico.drawImage(t.getImagem(), t.getX(), t.getY(), this);
            }
         }

         for(int j = 0; j < this.inimigos.size(); ++j) {
            List<Tiro> tirosInimigos = ((InimigoDificil)this.inimigos.get(j)).getTiros();

            for(int i = 0; i < tirosInimigos.size(); ++i) {
               Tiro tI = (Tiro)tirosInimigos.get(i);
               if (tI.isVisivel()) {
                  grafico.drawImage(tI.getImagem(), tI.getX(), tI.getY(), this);
               }
            }
         }

         Carta[] baralho = this.eu.getBaralho();

         for(int i = 0; i < baralho.length; ++i) {
            if (!baralho[i].isSelecionada()) {
               grafico.drawImage(baralho[i].getArquivo(), baralho[i].getX(), baralho[i].getY(), this);
            } else {
               grafico.drawImage(baralho[i].getArquivoS(), baralho[i].getX(), baralho[i].getY(), this);
            }
         }

         for(int i = 0; i < this.inimigos.size(); ++i) {
            InimigoDificil in = (InimigoDificil)this.inimigos.get(i);
            grafico.drawImage(in.getImagem(), in.getX(), in.getY(), this);
         }

         grafico.setColor(Color.BLACK);
         grafico.drawString("MATE MAIS " + this.inimigos.size() + " DRAGÔES", 5, 15);
         e.dispose();
      } else {
         ImageIcon adds = new ImageIcon("Gráficos\\Morte.png");
         grafico.drawImage(adds.getImage(), 0, 0, this);
      }

   }

   public void actionPerformed(ActionEvent arg0) {
      if (this.inimigos.size() == 0 && this.isboss) {
         this.inicializaBoss();
         this.isboss = false;
      }

      for(int i = 0; i < this.inimigos.size(); ++i) {
         for(int j = 0; j < ((InimigoDificil)this.inimigos.get(i)).getTiros().size(); ++j) {
            if (!((Tiro)((InimigoDificil)this.inimigos.get(i)).getTiros().get(j)).isSec()) {
               ((Tiro)((InimigoDificil)this.inimigos.get(i)).getTiros().get(j)).setVisivel(false);
            }
         }
      }

      for(int i = 0; i < this.eu.getTiros().size(); ++i) {
         if (!((Tiro)this.eu.getTiros().get(i)).isSec()) {
            ((Tiro)this.eu.getTiros().get(i)).setVisivel(false);
         }
      }

      if (!this.eu.isVivo()) {
         this.emJogo = false;
      }

      if (!this.eu.isUpped()) {
         this.eu.gravidade();
      }

      List<Tiro> tiros = this.eu.getTiros();

      for(int i = 0; i < tiros.size(); ++i) {
         Tiro t = (Tiro)tiros.get(i);
         if (t.isVisivel()) {
            t.movimentar();
         } else {
            tiros.remove(i);
         }
      }

      for(int j = 0; j < this.inimigos.size(); ++j) {
         List<Tiro> tirosInimigos = ((InimigoDificil)this.inimigos.get(j)).getTiros();

         for(int i = 0; i < tirosInimigos.size(); ++i) {
            Tiro tI = (Tiro)tirosInimigos.get(i);
            if (tI.isVisivel()) {
               tI.movimentar();
            } else {
               tI.setVisivel(false);
               tirosInimigos.remove(i);
            }
         }
      }

      for(int i = 0; i < this.inimigos.size(); ++i) {
         InimigoDificil t = (InimigoDificil)this.inimigos.get(i);
         if (t.isVisivel()) {
            t.movimentar();
         } else {
            this.inimigos.remove(i);
         }
      }

      this.eu.movimentar();
      this.checarColisoes();
      this.repaint();
   }

   public boolean isEmJogo() {
      return this.emJogo;
   }

   public void setEmJogo(boolean emJogo) {
      this.emJogo = emJogo;
   }

   public int[][] getCoordenadas() {
      return this.coordenadas;
   }

   public void setCoordenadas(int[][] coordenadas) {
      this.coordenadas = coordenadas;
   }

   public int getAle() {
      return ale;
   }

   public int getBossHit() {
      return this.bossHit;
   }

   public void setBossHit(int bossHit) {
      this.bossHit = bossHit;
   }

   public void setFundo(ImageIcon imagem) {
      this.fundo = imagem.getImage();
   }

   private class TecladoAdapter extends KeyAdapter {
      private TecladoAdapter() {
      }

      public void keyPressed(KeyEvent arg0) {
         if (arg0.getKeyCode() == 10) {
            Fase2Facil.this.emJogo = true;
            Fase2Facil.this.eu = new PersonagemFacil();
            Fase2Facil.this.inicializaInimigos();
            Fase2Facil.this.isboss = true;
            Fase2Facil.this.bossHit = 49;
         }

         Fase2Facil.this.eu.keyPressed(arg0);
      }

      public void keyReleased(KeyEvent arg0) {
         Fase2Facil.this.eu.keyReleased(arg0);
      }

      // $FF: synthetic method
      TecladoAdapter(TecladoAdapter var2) {
         this();
      }
   }
}
