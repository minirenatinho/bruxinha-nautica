package fachada;

import javax.swing.JPanel;
import negocios.Dificuldade;
import negocios.Fase;

public class Fachada {
   private Fase fase1facil = new Fase(Dificuldade.FACIL, 1);
   private Fase fase1dificil = new Fase(Dificuldade.DIFICIL, 1);
   private Fase fase2facil = new Fase(Dificuldade.FACIL, 2);
   private Fase fase2dificil = new Fase(Dificuldade.DIFICIL, 2);

   public JPanel iniciarFacil() {
      JPanel ret = this.fase1facil;
      if (this.fase1facil.deveExibirFaseSeguinte()) {
         ret = this.fase2facil;
      }

      return ret;
   }

   public JPanel iniciarDificil() {
      JPanel ret = this.fase1dificil;
      if (this.fase1dificil.deveExibirFaseSeguinte()) {
         ret = this.fase2dificil;
      }

      return ret;
   }
}
