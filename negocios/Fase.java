package negocios;

import dados.Carta;
import dados.Inimigo;
import dados.Personagem;
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
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Fase extends JPanel implements ActionListener {
   private static final int[][] COORDENADAS_PADRAO = new int[][]{{1380, 29}, {1200, 59}, {1380, 89}, {780, 109}, {580, 139}, {880, 239}, {790, 259}, {760, 50}, {790, 150}, {1180, 209}, {560, 45}, {510, 70}, {930, 159}, {590, 80}, {530, 60}, {940, 59}, {990, 30}, {920, 200}, {900, 259}, {660, 50}, {540, 90}, {810, 220}, {860, 20}, {740, 180}, {820, 128}, {490, 170}, {700, 30}, {920, 300}, {856, 328}, {456, 320}};

   private static final int NUMERO_FASE_MIN = 1;
   private static final int NUMERO_FASE_MAX = 2;
   private static final int NUMERO_FASE_COM_TIROS_INIMIGO = 2;
   private static final int BOSS_HIT_INICIAL = 49;
   private static final int BOSS_HIT_PARA_EXIBIR_FASE_2 = 0;
   private static final int BOSS_HIT_BOSS_ELIMINADO = -1;

   private final Dificuldade dificuldade;
   private final int numeroFase;
   private final boolean faseComTirosInimigo;

   private Image fundo;
   private Personagem eu;
   private Timer tempo;
   private boolean emJogo;
   private List<Inimigo> inimigos;
   private boolean isboss = true;
   private int bossHit = BOSS_HIT_INICIAL;
   private int[][] coordenadas;

   public Fase(Dificuldade dificuldade, int numeroFase) {
      if (numeroFase < NUMERO_FASE_MIN || numeroFase > NUMERO_FASE_MAX) {
         throw new IllegalArgumentException("numeroFase deve ser " + NUMERO_FASE_MIN + " ou " + NUMERO_FASE_MAX);
      }
      this.dificuldade = dificuldade;
      this.numeroFase = numeroFase;
      this.faseComTirosInimigo = numeroFase == NUMERO_FASE_COM_TIROS_INIMIGO;
      this.coordenadas = new int[COORDENADAS_PADRAO.length][];
      for (int i = 0; i < COORDENADAS_PADRAO.length; ++i) {
         this.coordenadas[i] = COORDENADAS_PADRAO[i].clone();
      }

      this.setFocusable(true);
      this.setDoubleBuffered(true);
      ImageIcon adds = new ImageIcon("Gráficos\\Fase" + numeroFase + ".png");
      this.fundo = adds.getImage();
      this.eu = this.criarPersonagem();
      this.emJogo = true;
      this.inicializaInimigos();
      this.addKeyListener(new KeyAdapter() {
         public void keyPressed(KeyEvent arg0) {
            if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
               Fase.this.emJogo = true;
               Fase.this.eu = Fase.this.criarPersonagem();
               Fase.this.inicializaInimigos();
               Fase.this.isboss = true;
               Fase.this.bossHit = BOSS_HIT_INICIAL;
            }

            Fase.this.eu.keyPressed(arg0);
         }

         public void keyReleased(KeyEvent arg0) {
            Fase.this.eu.keyReleased(arg0);
         }
      });
      this.tempo = new Timer(5, this);
      this.tempo.start();
   }

   private Personagem criarPersonagem() {
      return new Personagem(this.dificuldade);
   }

   private Inimigo novoInimigo(int x, int y, String arquivo, String tipo) {
      return new Inimigo(x, y, arquivo, tipo, this.faseComTirosInimigo);
   }

   public void inicializaInimigos() {
      this.inimigos = new ArrayList<>();

      for (int i = 0; i < this.coordenadas.length; ++i) {
         this.inimigos.add(this.novoInimigo(this.coordenadas[i][0], this.coordenadas[i][1], "", "normal"));
      }

   }

   public void inicializaBoss() {
      this.inimigos = new ArrayList<>();
      this.inimigos.add(this.novoInimigo(640, 50, "Gráficos\\Boss.png", "Boss"));
   }

   public void checarColisoes() {
      Rectangle recPersonagem = this.eu.getBounds();

      for (int i = 0; i < this.inimigos.size(); ++i) {
         Inimigo aux = this.inimigos.get(i);
         Rectangle recInimigo = aux.getBounds();
         if (recPersonagem.intersects(recInimigo)) {
            this.eu.setVivo(false);
            aux.setVisivel(false);
            this.emJogo = false;
         }
      }

      List<Tiro> tiros = this.eu.getTiros();

      for (int i = 0; i < tiros.size(); ++i) {
         Tiro aux = tiros.get(i);
         Rectangle recTiro = aux.getBounds();

         for (int j = 0; j < this.inimigos.size(); ++j) {
            Inimigo aux2 = this.inimigos.get(j);
            Rectangle recInimigo = aux2.getBounds();
            if (recTiro.intersects(recInimigo)) {
               aux.setVisivel(false);
               if (aux2.getTipo().equals("Boss")) {
                  --this.bossHit;
                  if (this.bossHit == BOSS_HIT_BOSS_ELIMINADO) {
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
      grafico.drawImage(this.fundo, 0, 0, null);
      if (this.emJogo) {
         grafico.drawImage(this.eu.getImagem(), this.eu.getX(), this.eu.getY(), this);
         List<Tiro> tiros = this.eu.getTiros();

         for (int i = 0; i < tiros.size(); ++i) {
            Tiro t = tiros.get(i);
            if (t.isVisivel()) {
               grafico.drawImage(t.getImagem(), t.getX(), t.getY(), this);
            }
         }

         for (int j = 0; j < this.inimigos.size(); ++j) {
            List<Tiro> tirosInimigos = this.inimigos.get(j).getTiros();

            for (int i = 0; i < tirosInimigos.size(); ++i) {
               Tiro tI = tirosInimigos.get(i);
               if (tI.isVisivel()) {
                  grafico.drawImage(tI.getImagem(), tI.getX(), tI.getY(), this);
               }
            }
         }

         Carta[] baralho = this.eu.getBaralho();

         for (int i = 0; i < baralho.length; ++i) {
            if (!baralho[i].isSelecionada()) {
               grafico.drawImage(baralho[i].getArquivo(), baralho[i].getX(), baralho[i].getY(), this);
            } else {
               grafico.drawImage(baralho[i].getArquivoS(), baralho[i].getX(), baralho[i].getY(), this);
            }
         }

         for (int i = 0; i < this.inimigos.size(); ++i) {
            Inimigo in = this.inimigos.get(i);
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

      for (int i = 0; i < this.inimigos.size(); ++i) {
         Inimigo in = this.inimigos.get(i);
         List<Tiro> lista = in.getTiros();
         for (int j = 0; j < lista.size(); ++j) {
            if (!lista.get(j).isSec()) {
               lista.get(j).setVisivel(false);
            }
         }
      }

      for (int i = 0; i < this.eu.getTiros().size(); ++i) {
         if (!this.eu.getTiros().get(i).isSec()) {
            this.eu.getTiros().get(i).setVisivel(false);
         }
      }

      if (!this.eu.isVivo()) {
         this.emJogo = false;
      }

      if (!this.eu.isUpped()) {
         this.eu.gravidade();
      }

      List<Tiro> tiros = this.eu.getTiros();

      for (int i = 0; i < tiros.size(); ++i) {
         Tiro t = tiros.get(i);
         if (t.isVisivel()) {
            t.movimentar();
         } else {
            tiros.remove(i);
         }
      }

      for (int j = 0; j < this.inimigos.size(); ++j) {
         List<Tiro> tirosInimigos = this.inimigos.get(j).getTiros();

         for (int i = 0; i < tirosInimigos.size(); ++i) {
            Tiro tI = tirosInimigos.get(i);
            if (tI.isVisivel()) {
               tI.movimentar();
            } else {
               tI.setVisivel(false);
               tirosInimigos.remove(i);
            }
         }
      }

      for (int i = 0; i < this.inimigos.size(); ++i) {
         Inimigo t = this.inimigos.get(i);
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

   public int getNumeroFase() {
      return this.numeroFase;
   }

   public int[][] getCoordenadas() {
      return this.coordenadas;
   }

   public void setCoordenadas(int[][] coordenadas) {
      this.coordenadas = coordenadas;
   }

   public int getBossHit() {
      return this.bossHit;
   }

   public void setBossHit(int bossHit) {
      this.bossHit = bossHit;
   }

   public boolean deveExibirFaseSeguinte() {
      return this.bossHit == BOSS_HIT_PARA_EXIBIR_FASE_2;
   }

   public void setFundo(ImageIcon imagem) {
      this.fundo = imagem.getImage();
   }
}
