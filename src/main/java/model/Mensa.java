package model;

import java.sql.Time;
import java.util.ArrayList;

/**
 * La classe modella gli oggetti mensa ed utilizza il Singleton Pattern
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
     * Posti attualmente vuoti in mensa.
     */
    private static int postiVuoti;

    /**
     * Istanza privata del Singleton Mensa
     */
    public static  Mensa mensa=new Mensa();

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


    /**
     * ArrayList di servizio per aggiornare la mensa
     */
    private static ArrayList<String> listUpdateMensa=new ArrayList<>();



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

    /** Restituisce i posti attualmente vuoti in mensa
     *
     * @return posti ancora vuoti in mensa
     */
    public static int getPostiVuoti() {
        return postiVuoti;
    }

    /** Imposta i posti attualmente vuoti in mensa
     *
     * @param postiVuoti posti ancora vuoti in mensa
     */
    public static void setPostiVuoti(int postiVuoti) {
        Mensa.postiVuoti = postiVuoti;
    }


    /**
     * restituisce i dati da aggiornare della mensa
     * @return lista di informazioni della mensa
     */
    public static ArrayList<String> getListUpdateMensa() {return listUpdateMensa;}



    /** Verifica se la mensa e' aperta.
     * @return true se la mensa e' aperta, altrimenti false
     */
    public static boolean isMensaAperta()
    {
        MensaDAOInterface mensadao=new MensaDAO();
        ArrayList<String> mensa=mensadao.doRetrieveMensaByKey("mensa1");
        Time apertura= Time.valueOf(mensa.get(2));
        Time chiusura= Time.valueOf(mensa.get(3));
        Time attuale= new Time(System.currentTimeMillis());

        String chiusuraString=chiusura.toString();
        String aperturaString=apertura.toString();
        String attualeString=attuale.toString();

        return attualeString.compareTo(aperturaString)>0 && attualeString.compareTo(chiusuraString)<0;
    }

    /** Verifica se e' possibile effettuare modifiche al sistema.
     * @return true se e' possibile modificare il sistema, altrimenti false
     */
    public static boolean isMensaConfig()
    {
        MensaDAOInterface mensadao=new MensaDAO();
        ArrayList<String> mensa=mensadao.doRetrieveMensaByKey("mensa1");
        Time chiusura= Time.valueOf(mensa.get(3));
        Time config=Time.valueOf("23:59:59");
        Time attuale= new Time(System.currentTimeMillis());

        String chiusuraString=chiusura.toString();
        String confiString=config.toString();
        String attualeString=attuale.toString();

        return attualeString.compareTo(chiusuraString)>0 && attualeString.compareTo(confiString)<=0;
    }

    /** Verifica se e' possibile effettuare acquisti o rimborsi.
     * @return true se e' possibile effettuare acquisti o rimborsi, altrimenti false
     */
    public static boolean isMensaPurchase()
    {
        MensaDAOInterface mensadao=new MensaDAO();
        ArrayList<String> mensa=mensadao.doRetrieveMensaByKey("mensa1");
        Time apertura= Time.valueOf(mensa.get(2));
        Time attuale= new Time(System.currentTimeMillis());

        String aperturaString=apertura.toString();
        String attualeString=attuale.toString();

        return attualeString.compareTo(aperturaString)<0;
    }

}
