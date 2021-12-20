package model;

import java.sql.Time;

/**
 * La classe modella oggetti che la mensa
 * @invariant orarioApertura.getTime()-orarioChiusura.getTime()<0
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
     * Restituisce il nome della mensa.
     */
    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public int getPostiDisponibili() {return postiDisponibili;}

    public void setPostiDisponibili(int postiDisponibili) {this.postiDisponibili = postiDisponibili;}

    public Time getOrarioApertura() {return orarioApertura;}

    public void setOrarioApertura(Time orarioApertura) {this.orarioApertura = orarioApertura;}

    public Time getOrarioChiusura() {return orarioChiusura;}

    public void setOrarioChiusura(Time orarioChiusura) {this.orarioChiusura = orarioChiusura;}
}
