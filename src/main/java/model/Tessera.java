package model;

public class Tessera {

    /**
     * La classe modella le tessere prepagate degli utenti.
     */
    private String CF;
    private float saldo;

    public String getCF() {return CF;}

    public void setCF(String CF) {this.CF = CF;}

    public float getSaldo() {return saldo;}

    public void setSaldo(float saldo) {this.saldo = saldo;}
}
