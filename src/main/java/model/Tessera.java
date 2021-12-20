package model;

/**
 * La classe modella le tessere prepagate degli utenti.
 */
public class Tessera {

    /**
     * Il CF identifica una singola tessera associata ad un utente.
     */
    private String CF;

    /**
     * saldo disponibile sulla tessera.
     */
    private float saldo;

    /** Restituisce il CF del proprietario della tessera.
     * @return
     * Restituisce il CF del proprietario della tessera.
     */
    public String getCF() {return CF;}


    /**
     * Imposta il CF del proprietario della tessera
     * @param CF CF del proprietario della tessera.
     */
    public void setCF(String CF) {this.CF = CF;}


    /** Restituisce il saldo disponibile sulla tessera.
     * @return
     * Restituisce il saldo disponibile sulla tessera.
     */
    public float getSaldo() {return saldo;}


    /**
     * Imposta il saldo disponibile sulla tessera.
     * @param saldo saldo disponibile sulla tessera.
     */
    public void setSaldo(float saldo) {this.saldo = saldo;}
}
