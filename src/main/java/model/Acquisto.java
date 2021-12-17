package model;

import java.sql.Date;

/**
 * La classe modella oggetti che rappresentano un acquisto di un menu...
 */
public class Acquisto
{
    /** La data di acquisto deve essere inferiore o uguale a quella attuale.
     *  @invariant  CurrentDate - dataAcquisto {@literal >}=0.
     */
    private Date dataAcquisto;

    /**
     * Il codice fiscale deve essere composto da 16 caratteri.
     * @invariant CF.lenght==16
     */
    private String CF;

    /**
     * codiceMenu indica univocamente un singolo menu.
     */
    private int codiceMenu;

    /**
     * postoMensa indica se si vuole o meno prenotare un posto in mensa.
     */
    private boolean postoMensa;


    /**
     * @return
     * Restituisce la data di acquisto.
     */
    public Date getDataAcquisto() { return dataAcquisto;}

    /**
     * Imposta la data di acquisto.
     * @param dataAcquisto data di acquisto
     */
    public void setDataAcquisto(Date dataAcquisto) {this.dataAcquisto = dataAcquisto;}

    /**
     * @return
     * Restituisce il codice fiscale.
     */
    public String getCF() {return CF;}

    /**
     * Imposta il codice fiscale.
     * @param CF codice fiscale
     */
    public void setCF(String CF) {this.CF = CF;}

    /**
     *@return
     * Restituisce il codice del menu.
     */
    public int getCodiceMenu() {return codiceMenu;}

    /**
     * Imposta il codice del menu.
     * @param codiceMenu codice univoco del menu
     */
    public void setCodiceMenu(int codiceMenu) {
        this.codiceMenu = codiceMenu;}

    /**
     * @return
     * Verifica se si e' prenotato il posto in mensa.
     */
    public boolean isPostoMensa() {return postoMensa;}

    /**
     * Imposta il posto in mensa.
     * @param postoMensa Posto prenotato in mensa o meno
     */
    public void setPostoMensa(boolean postoMensa) {this.postoMensa = postoMensa;}

}
