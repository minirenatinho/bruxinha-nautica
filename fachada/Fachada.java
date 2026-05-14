package fachada;

import negocios.Fase;
import negocios.Fase1Dificil;
import negocios.Fase1Facil;
import negocios.Fase2Dificil;
import negocios.Fase2Facil;

public class Fachada {
   private Fase1Facil fase1facil = new Fase1Facil();
   private Fase1Dificil fase1dificil = new Fase1Dificil();
   private Fase2Facil fase2facil = new Fase2Facil();
   private Fase2Dificil fase2dificil = new Fase2Dificil();

   public Fase iniciarFacil() {
      Fase ret = this.fase1facil;
      if (this.fase1facil.getBossHit() == 0) {
         ret = this.fase2facil;
      }

      return ret;
   }

   public Fase iniciarDificil() {
      Fase ret = this.fase1dificil;
      if (this.fase1dificil.getBossHit() == 0) {
         ret = this.fase2dificil;
      }

      return ret;
   }

   public Fase1Facil getFase1facil() {
      return this.fase1facil;
   }

   public void setFase1facil(Fase1Facil fase1facil) {
      this.fase1facil = fase1facil;
   }

   public Fase1Dificil getFase1dificil() {
      return this.fase1dificil;
   }

   public void setFase1dificil(Fase1Dificil fase1dificil) {
      this.fase1dificil = fase1dificil;
   }

   public Fase2Facil getFase2facil() {
      return this.fase2facil;
   }

   public void setFase2facil(Fase2Facil fase2facil) {
      this.fase2facil = fase2facil;
   }

   public Fase2Dificil getFase2dificil() {
      return this.fase2dificil;
   }

   public void setFase2dificil(Fase2Dificil fase2dificil) {
      this.fase2dificil = fase2dificil;
   }
}
