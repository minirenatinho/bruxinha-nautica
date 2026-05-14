package dados;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Inimigo extends Sprite {
   private static final int VELOCIDADE = 1;

   private String tipo;
   private String arquivo;
   private final boolean atira;
   private final List<Tiro> tiros;

   public Inimigo(int x, int y, String arquivo, String tipo, boolean atira) {
      this.tipo = tipo;
      this.atira = atira;
      this.tiros = atira ? new ArrayList<>() : Collections.emptyList();
      Random gera = new Random();
      if (!tipo.equals("Boss")) {
         switch (gera.nextInt(5)) {
            case 0:
               arquivo = "Gráficos\\Inimigo_Agua.png";
               break;
            case 1:
               arquivo = "Gráficos\\Inimigo_Fogo.png";
               break;
            case 2:
               arquivo = "Gráficos\\Inimigo_Ar.png";
               break;
            case 3:
               arquivo = "Gráficos\\Inimigo_Elet.png";
               break;
            default:
               arquivo = "Gráficos\\Inimigo_Terra.png";
         }
      }

      this.setArquivo(arquivo);
      this.setX(x - 25);
      this.setY(y);
      super.carregarImagem(arquivo);
   }

   public void movimentar() {
      if (this.getX() < 0) {
         this.setX(LARGURA_TELA);
      } else {
         this.setX(this.getX() - VELOCIDADE);
      }

      if (this.atira) {
         this.atirar();
         if (!this.tiros.isEmpty()) {
            this.tiros.get(0).setSec(true);
         }
      }
   }

   private void atirar() {
      Tiro tI = new Tiro(this.getX(), this.getY(), "Gráficos\\Dark.png", "Inimigo");
      tI.setX(tI.getX() + 35);
      tI.setY(tI.getY() + 10);
      this.tiros.add(tI);
   }

   public List<Tiro> getTiros() {
      return this.tiros;
   }

   public String getTipo() {
      return this.tipo;
   }

   public void setTipo(String tipo) {
      this.tipo = tipo;
   }

   public static int getLarguraTela() {
      return LARGURA_TELA;
   }

   public static int getVelocidade() {
      return VELOCIDADE;
   }

   public String getArquivo() {
      return this.arquivo;
   }

   public void setArquivo(String arquivo) {
      this.arquivo = arquivo;
   }
}
