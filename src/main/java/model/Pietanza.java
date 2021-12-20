package model;


/**
 * La classe modella le pietanze.
 */
public class Pietanza {

    /**
     * Il nome identifica una singola pietanza.
     */
    private String nome;

    /**
     * descrizione della pietanza.
     */
    private String descrizione;

    /**
     * tipo della pietanza.
     */
    private char tipo;

    /**
     * ingredienti della pietanza.
     */
    private String ingredienti;

    /**
     * immagine della pietanza.
     */
    private String immagine;

    /**
     * numero di acquisti della pietanza.
     */
    private int numeroAcquisti;


    /** Restituisce il nome della pietanza.
     * @return
     * Restituisce il nome della pietanza.
     */
    public String getNome() {return nome;}


    /**
     * Imposta il nome della pietanza.
     * @param nome nome della pietanza.
     */
    public void setNome(String nome) {this.nome = nome;}


    /** Restituisce la descrizione della pietanza.
     * @return
     * Restituisce la descrizione della pietanza.
     */
    public String getDescrizione() {return descrizione;}


    /**
     * Imposta la descrizione della pietanza.
     * @param descrizione descrizione della pietanza.
     */
    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}


    /** Restituisceil tipo della pietanza.
     * @return
     * Restituisce il tipo della pietanza.
     */
    public char getTipo() {return tipo;}


    /**
     * Imposta il tipo di piatto della pietanza.
     * @param tipo tipo di piatto della pietanza.
     */
    public void setTipo(char tipo) {this.tipo = tipo;}


    /** Restituisce gli ingredienti della pietanza.
     * @return
     * Restituisce gli ingredienti della pietanza.
     */
    public String getIngredienti() {return ingredienti;}


    /**
     * Imposta gli ingredienti della pietanza.
     * @param ingredienti ingredienti della pietanza.
     */
    public void setIngredienti(String ingredienti) {this.ingredienti = ingredienti;}


    /** Restituisce l'immagine della pietanza.
     * @return
     * Restituisce l'immagine della pietanza.
     */
    public String getImmagine() {return immagine;}


    /**
     * Imposta l'immagine della pietanza.
     * @param immagine immagine della pietanza.
     */
    public void setImmagine(String immagine) {this.immagine = immagine;}


    /** Restituisce il numero di acquisti della pietanza.
     * @return
     * Restituisce il numero di acquisti della pietanza.
     */
    public int getNumeroAcquisti() {return numeroAcquisti;}


    /**
     * Imposta il numero di acquisti della pietanza.
     * @param numeroAcquisti numero di acquisti della pietanza.
     */
    public void setNumeroAcquisti(int numeroAcquisti) {this.numeroAcquisti = numeroAcquisti;}
}