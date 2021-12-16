package model;

import java.sql.Time;

public class Mensa
{
    private String nome;
    private int postiDisponibili;
    private Time orarioApertura;
    private Time orarioChiusura;


    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public int getPostiDisponibili() {return postiDisponibili;}

    public void setPostiDisponibili(int postiDisponibili) {this.postiDisponibili = postiDisponibili;}

    public Time getOrarioApertura() {return orarioApertura;}

    public void setOrarioApertura(Time orarioApertura) {this.orarioApertura = orarioApertura;}

    public Time getOrarioChiusura() {return orarioChiusura;}

    public void setOrarioChiusura(Time orarioChiusura) {this.orarioChiusura = orarioChiusura;}
}
