package model;

import java.sql.Date;

public class Acquisto
{
    /** La data di acquisto deve essere inferiore o uguale a quella attuale.
     * @invariant dataAcquisto.compareTo(new Date(System.currentTimeMillis()))<=0
     */
    private Date dataAcquisto;

    /**
     * Il codice fiscale deve essere composto da 16 caratteri.
     * @invariant CF.lenght==16
     */
    private String CF;

    /**
     * codiceMenu indica univocamente un singolo menù.
     */
    private int codiceMenu;

    /**
     * postoMensa indica se si vuole o meno prenotare un posto in mensa.
     */
    private boolean postoMensa;


    public Date getDataAcquisto() { return dataAcquisto;}

    public void setDataAcquisto(Date dataAcquisto) {this.dataAcquisto = dataAcquisto;}

    public String getCF() {return CF;}

    public void setCF(String CF) {this.CF = CF;}

    public int getCodiceMenu() {return codiceMenu;}

    public void setCodiceMenu(int codiceMenu) {
        this.codiceMenu = codiceMenu;}

    public boolean isPostoMensa() {return postoMensa;}

    public void setPostoMensa(boolean postoMensa) {this.postoMensa = postoMensa;}

}
