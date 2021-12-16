package model;

import java.sql.Date;

public class Acquisto
{

    private Date dataAcquisto;
    private String CF;
    private int CodiceMenu;
    private boolean postoMensa;


    public Date getDataAcquisto() {return dataAcquisto;}

    public void setDataAcquisto(Date dataAcquisto) {this.dataAcquisto = dataAcquisto;}

    public String getCF() {return CF;}

    public void setCF(String CF) {this.CF = CF;}

    public int getCodiceMenu() {return CodiceMenu;}

    public void setCodiceMenu(int codiceMenu) {CodiceMenu = codiceMenu;}

    public boolean isPostoMensa() {return postoMensa;}

    public void setPostoMensa(boolean postoMensa) {this.postoMensa = postoMensa;}

}
