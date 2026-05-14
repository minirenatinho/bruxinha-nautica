package dados;

import java.awt.Image;
import javax.swing.ImageIcon;
import negocios.Dificuldade;

public class Usuario {
   private Personagem pFacil;
   private Personagem pDificil;
   private String nome;
   private Image imagem;

   public Usuario(String nome, String arquivo) {
      ImageIcon adds = new ImageIcon(arquivo);
      this.imagem = adds.getImage();
      this.pFacil = new Personagem(Dificuldade.FACIL);
      this.pFacil.setImagem(this.imagem);
      this.pDificil = new Personagem(Dificuldade.DIFICIL);
      this.pDificil.setImagem(this.imagem);
      this.nome = nome;
   }

   public Personagem getpFacil() {
      return this.pFacil;
   }

   public void setpFacil(Personagem pFacil) {
      this.pFacil = pFacil;
   }

   public Personagem getpDificil() {
      return this.pDificil;
   }

   public void setpDificil(Personagem pDificil) {
      this.pDificil = pDificil;
   }

   public String getNome() {
      return this.nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public Image getImagem() {
      return this.imagem;
   }

   public void setImagem(Image imagem) {
      this.imagem = imagem;
   }
}
