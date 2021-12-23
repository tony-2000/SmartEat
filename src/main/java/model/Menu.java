package model;


import java.util.ArrayList;

/**
 * La classe modella i menu.
 */
public class Menu
{
    /**
     * Il codice menu identifica un singolo menu.
     */
    private int codiceMenu;

    /**
     * Nome del menu.
     */
    private String nome;


    /**
     *Prima portata del menu.
     */
    private String primo;

    /**
     *Seconda portata del menu.
     */
    private String secondo;

    /**
     *Dessert del menu.
     */
    private String dessert;

    /**
     *Descrizione del menu.
     */
    private String descrizione;

    /**
     *Immagine del menu.
     */
    private String immagine;

    /**
     *Prezzo del menu.
     */
    private float prezzo;

    /**
     *Indica se il menu e' disponibile.
     */
    private boolean available;

    /**
     * ArrayList di servizio per aggiungere menu.
     */
    private static ArrayList<Menu> listAddMenu=new ArrayList<>();

    /**
     * ArrayList di servizio per modificare la disponibilita' di un menu.
     */
    private static ArrayList<Integer> listToggleMenu=new ArrayList<>();


    /**
     * ArrayList di servizio per eliminare un menu.
     */
    private static ArrayList<Integer> listDeleteMenu=new ArrayList<>();


    /** Restituisce il codice del menu.
     * @return
     * Restituisce il codice del menu.
     */
    public int getCodiceMenu() {return codiceMenu;}


    /**
     * Imposta il codice del menu.
     * @param codiceMenu Codice del menu
     */
    public void setCodiceMenu(int codiceMenu) {this.codiceMenu = codiceMenu;}



    /** Restituisce il nome del menu.
     * @return
     * Restituisce il nome del menu.
     */
    public String getNome() {return nome;}


    /**
     * Imposta il nome del menu.
     * @param nome nome del menu.
     */
    public void setNome(String nome) {this.nome = nome;}



    /** Restituisce il nome del primo piatto del menu.
     * @return
     * Restituisce il nome del primo piatto del menu.
     */
    public String getPrimo() {return primo;}


    /**
     * Imposta il nome del primo piatto del menu.
     * @param primo nome primo piatto del menu.
     */
    public void setPrimo(String primo) {this.primo = primo;}


    /** Restituisce il nome del secondo piatto del menu.
     * @return
     * Restituisce il nome del secondo piatto del menu.
     */
    public String getSecondo() {return secondo;}


    /**
     * Imposta il nome del secondo piatto del menu.
     * @param secondo nome secondo piatto del menu.
     */
    public void setSecondo(String secondo) {this.secondo = secondo;}


    /** Restituisce il nome del dessert del menu.
     * @return
     * Restituisce il nome del dessert del menu.
     */
    public String getDessert() {return dessert;}


    /**
     * Imposta il nome del dessert del menu.
     * @param dessert nome dessert del menu.
     */
    public void setDessert(String dessert) {this.dessert = dessert;}


    /** Restituisce la descrizione del menu.
     * @return
     * Restituisce la descrizione del menu.
     */
    public String getDescrizione() {return descrizione;}


    /**
     * Imposta la descrizione del menu.
     * @param descrizione descrizione del menu.
     */
    public void setDescrizione(String descrizione) {this.descrizione = descrizione;}


    /** Restituisce l'immagine del menu.
     * @return
     * Restituisce l'immagine del menu.
     */
    public String getImmagine() {return immagine;}


    /**
     * Imposta l'immagine del menu.
     * @param immagine immagine del menu.
     */
    public void setImmagine(String immagine) {this.immagine = immagine;}


    /** Restituisce il prezzo del menu.
     * @return
     * Restituisce il prezzo del menu.
     */
    public float getPrezzo() {return prezzo;}


    /**
     * Imposta il prezzo del menu.
     * @param prezzo prezzo del menu.
     */
    public void setPrezzo(float prezzo) {this.prezzo = prezzo;}


    /** Verifica se il menu e' disponibile.
     * @return
     * Restituisce il valore che verifica se il menu è disponibile.
     */
    public boolean isAvailable() {return available;}


    /**
     * Imposta se il menu e' disponibile.
     * @param available disponibilita del menu.
     */
    public void setAvailable(boolean available) {this.available = available;}

    /**
     * Restituisce i menu da aggiungere
     * @return Lista di menu da aggiungere
     */
    public static ArrayList<Menu> getListAddMenu() {return listAddMenu;}


    /**
     * Restituisce i codici dei menu a cui cambiare la disponibilita'
     * @return Lista di codici di menu
     */
    public static ArrayList<Integer> getListToggleMenu() {return listToggleMenu;}



    /**
     *  Restituisce i codici dei menu da eliminare
     * @return Lista di codici di menu
     */
    public static ArrayList<Integer> getListDeleteMenu() {return listDeleteMenu;}

}
