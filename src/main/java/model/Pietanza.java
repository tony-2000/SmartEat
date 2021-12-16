package model;

public class Pietanza {

    private String nome;
    private String descrizione;
    private int tipo;
    private String ingredienti;
    private String immagine;
    private String numeroAcquisti;


    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getDescrizione() {return descrizione;}

    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}

    public int getTipo() {return tipo;}

    public void setTipo(int tipo) {this.tipo = tipo;}

    public String getIngredienti() {return ingredienti;}

    public void setIngredienti(String ingredienti) {this.ingredienti = ingredienti;}

    public String getImmagine() {return immagine;}

    public void setImmagine(String immagine) {this.immagine = immagine;}

    public String getNumeroAcquisti() {return numeroAcquisti;}

    public void setNumeroAcquisti(String numeroAcquisti) {this.numeroAcquisti = numeroAcquisti;}
}