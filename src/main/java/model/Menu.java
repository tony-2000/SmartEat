package model;

public class Menu
{
    private int codiceMenu;
    private String nome;
    private String primo;
    private String secondo;
    private String dessert;
    private String descrizione;
    private String immagine;
    private float prezzo;
    private boolean available;



    public int getCodiceMenu() {return codiceMenu;}

    public void setCodiceMenu(int codiceMenu) {this.codiceMenu = codiceMenu;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getPrimo() {return primo;}

    public void setPrimo(String primo) {this.primo = primo;}

    public String getSecondo() {return secondo;}

    public void setSecondo(String secondo) {this.secondo = secondo;}

    public String getDessert() {return dessert;}

    public void setDessert(String dessert) {this.dessert = dessert;}

    public String getDescrizione() {return descrizione;}

    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}

    public String getImmagine() {return immagine;}

    public void setImmagine(String immagine) {this.immagine = immagine;}

    public float getPrezzo() {return prezzo;}

    public void setPrezzo(float prezzo) {this.prezzo = prezzo;}

    public boolean isAvailable() {return available;}

    public void setAvailable(boolean available) {this.available = available;}
}
