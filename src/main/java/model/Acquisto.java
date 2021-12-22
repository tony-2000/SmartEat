package model;

import java.sql.Date;

/**
 * La classe modella oggetti che rappresentano un acquisto di un menu.
 */
public class Acquisto
{
    /** La data di acquisto deve essere inferiore o uguale a quella attuale.
     *  @invariant  CurrentDate - dataAcquisto {@literal >}=0.
     */
    private Date dataAcquisto;

    /**
     * Il codice fiscale identifica un singolo cliente.
     */
    private String CF;

    /**
     * codiceMenu indica univocamente un singolo menu.
     */
    private int codiceMenu;

    /**
     * postoMensa indica se un acquisto comprende o meno la prenotazione di un posto in mensa.
     */
    private boolean postoMensa;

    /**
     * refund indica se un acquisto può essere cancellato per ottenere un riborso.
     */
    private boolean refund;

    /** Restituisce la data di acquisto.
     * @return
     * Restituisce la data di acquisto.
     */
    public Date getDataAcquisto() { return dataAcquisto;}

    /**
     * Imposta la data di acquisto.
     * @param dataAcquisto data di acquisto
     */
    public void setDataAcquisto(Date dataAcquisto) {this.dataAcquisto = dataAcquisto;}

    /**Restituisce il codice fiscale.
     * @return
     * Restituisce il codice fiscale.
     */
    public String getCF() {return CF;}

    /**
     * Imposta il codice fiscale.
     * @param CF codice fiscale
     */
    public void setCF(String CF) {this.CF = CF;}

    /**Restituisce il codice del menu.
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

    /**Verifica se si e' prenotato il posto in mensa.
     * @return
     * Verifica se si e' prenotato il posto in mensa.
     */
    public boolean isPostoMensa() {return postoMensa;}

    /**
     * Imposta la prenotazione di un posto in mensa.
     * @param postoMensa Posto prenotato in mensa o meno
     */
    public void setPostoMensa(boolean postoMensa) {this.postoMensa = postoMensa;}


    /**Verifica se il rimborso è disponibile per questo acquisto.
     * @return
     *Verifica se il rimborso è disponibile per questo acquisto.
     */
    public boolean isRefund() {return refund;}

    /**
     * Imposta la possibilità di rimborso.
     * @param refund Rimborsso possibile o meno
     */
    public void setRefund(boolean refund) {this.refund = refund;}

}
