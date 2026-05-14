package dados;

import java.awt.event.KeyEvent;

public class PersonagemDificil extends PersonagemAbstrato {
   public void keyPressed(KeyEvent butao) {
      int code = butao.getKeyCode();
      if (code == 38) {
         this.atirar();
         Tiro t = (Tiro)super.getTiros().get(0);
         t.setSec(true);
         if (super.getTiros().size() > 1) {
            Tiro t2 = (Tiro)super.getTiros().get(1);
            t2.setSec(true);
         }

         if (super.getTiros().size() > 2) {
            Tiro t3 = (Tiro)super.getTiros().get(2);
            t3.setSec(true);
         }

         super.setyR(-3);
         super.setUpped(true);
      }

      if (code == 37) {
         this.cartaE();
      }

      if (code == 39) {
         this.cartaD();
      }

      Carta[] baralho = super.getBaralho();
      if (baralho[0].isSelecionada()) {
         super.setTipo("Gráficos\\Agua.png");
      }

      if (baralho[1].isSelecionada()) {
         super.setTipo("Gráficos\\Fogo.png");
      }

      if (baralho[2].isSelecionada()) {
         super.setTipo("Gráficos\\Ar.png");
      }

      if (baralho[3].isSelecionada()) {
         super.setTipo("Gráficos\\Elet.png");
      }

      if (baralho[4].isSelecionada()) {
         super.setTipo("Gráficos\\Terra.png");
      }

   }
}
