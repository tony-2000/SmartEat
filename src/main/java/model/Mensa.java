package model;

import java.sql.Time;

/**
 * La classe modella gli oggeti mensa ed utilizza il Singleton Pattern
 * @invariant orarioApertura.getTime()-orarioChiusura.getTime(){@literal <}0
 */
public class Mensa
{
    /**
     * Il nome della mensa.
     */
    private String nome;
    /**
     * Il numero di posti prenotabili in un giorno.
     */
    private int postiDisponibili;
    /**
     * L'orario di apertura della mensa.
     */
    private Time orarioApertura;
    /**
     * L'orario di chiusura della mensa.
     */
    private Time orarioChiusura;

    /**
     * Instanza privata del Singleton Mensa
     */
    private static  Mensa mensa=new Mensa();

    /**
     *Costruttore privato per realizzare il Singleton Pattern
     */
    private Mensa(){}


    /** Restituisce il nome della mensa.
     * @return
     * Restituisce il nome della mensa.
     */
    public String getNome() {return nome;}


    /**
     * Imposta il nome della mensa.
     * @param nome nome della mensa
     */
    public void setNome(String nome) {this.nome = nome;}


    /** Restituisce il numero di posti disponibili.
     * @return
     *Restituisce il numero di posti disponibili.
     */
    public int getPostiDisponibili() {return postiDisponibili;}


    /**
     * Imposta il numero di posti disponibili.
     * @param postiDisponibili numero di posti disponibili.
     */
    public void setPostiDisponibili(int postiDisponibili) {this.postiDisponibili = postiDisponibili;}


    /** Restituisce l'orario di apertura.
     * @return
     * Restituisce l'orario di apertura.
     */
    public Time getOrarioApertura() {return orarioApertura;}


    /**
     * Imposta l'orario di apertura.
     * @param orarioApertura orario di apertura.
     */
    public void setOrarioApertura(Time orarioApertura) {this.orarioApertura = orarioApertura;}


    /** Restituisce l'orario di chiusura.
     * @return
     * Restituisce l'orario di chiusura.
     */
    public Time getOrarioChiusura() {return orarioChiusura;}


    /**
     * Imposta l'orario di chiusura.
     * @param orarioChiusura orario di chiusura.
     */
    public void setOrarioChiusura(Time orarioChiusura) {this.orarioChiusura = orarioChiusura;}
}
