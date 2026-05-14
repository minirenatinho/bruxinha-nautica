package dados;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Usuario {
   private PersonagemFacil pFacil;
   private PersonagemDificil pDificil;
   private String nome;
   private Image imagem;

   public Usuario(String nome, String arquivo) {
      ImageIcon adds = new ImageIcon(arquivo);
      this.imagem = adds.getImage();
      this.pFacil = new PersonagemFacil();
      this.pFacil.setImagem(this.imagem);
      this.pDificil = new PersonagemDificil();
      this.pDificil.setImagem(this.imagem);
      this.nome = nome;
   }

   public PersonagemFacil getpFacil() {
      return this.pFacil;
   }

   public void setpFacil(PersonagemFacil pFacil) {
      this.pFacil = pFacil;
   }

   public PersonagemDificil getpDificil() {
      return this.pDificil;
   }

   public void setpDificil(PersonagemDificil pDificil) {
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
