package dados;

import java.util.ArrayList;
import java.util.List;

public class InimigoDificil extends Inimigo {
   private List<Tiro> tiros = new ArrayList();

   public InimigoDificil(int x, int y, String arquivo, String tipo) {
      super(x, y, arquivo, tipo);
   }

   public void movimentar() {
      if (super.getX() < 0) {
         super.setX(Inimigo.getLarguraTela());
      } else {
         super.setX(super.getX() - Inimigo.getVelocidade());
      }

      this.atirar();
      Tiro t = (Tiro)this.tiros.get(0);
      t.setSec(true);
   }

   public void atirar() {
      Tiro tI = new Tiro(this.getX(), this.getY(), "Gráficos\\Dark.png", "Inimigo");
      tI.setX(tI.getX() + 35);
      tI.setY(tI.getY() + 10);
      this.tiros.add(tI);
   }

   public List<Tiro> getTiros() {
      return this.tiros;
   }

   public void setTiros(List<Tiro> tiros) {
      this.tiros = tiros;
   }
}
