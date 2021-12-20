package model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

/**
 * La classe modella oggetti che rappresentano un utente.
 */
public class Utente {

    /**
     * Il CF identifica un singolo utente.
     */
    private String CF;

    /**
     * nome dell'utente.
     */
    private String nome;

    /**
     * cognome dell'utente.
     */
    private String cognome;

    /**
     * sesso dell'utente.
     */
    private char sesso;

    /**
     * La data di nascita dell'utente
     * @invariant CurrentDate - dataDiNascita {@literal >}=0.
     */
    private Date dataDiNascita;

    /**
     * luogo di nascita dell'utente.
     */
    private String luogoDiNascita;

    /**
     * e-mail dell'utente.
     */
    private String email;

    /**
     * residenza dell'utente.
     */
    private String residenza;

    /**
     * password dell'utente.
     */
    private String password;

    /**
     * Se l'utente amministratore.
     */
    private RuoloUtente amministratore;

    /**
     * Se l'utente è stato accettato sulla piattaforma.
     */
    private boolean accepted;


    /** Restituisce il CF dell'utente.
     * @return
     * Restituisce il CF dell'utente.
     */
    public String getCF() {return CF;}


    /**
     * Imposta il CF dell'utente.
     * @param CF CF dell'utente.
     */
    public void setCF(String CF) {this.CF = CF;}


    /** Restituisce il nome dell'utente.
     * @return
     * Restituisce il nome dell'utente.
     */
    public String getNome() {return nome;}


    /**
     * Imposta il nome dell'utente.
     * @param nome nome dell'utente.
     */
    public void setNome(String nome) {this.nome = nome;}


    /** Restituisce il cognome dell'utente.
     * @return
     * Restituisce il cognome dell'utente.
     */
    public String getCognome() {return cognome;}


    /**
     * Imposta il cognome dell'utente.
     * @param cognome cognome dell'utente.
     */
    public void setCognome(String cognome) {this.cognome = cognome;}


    /** Restituisce il sesso dell'utente.
     * @return
     * Restituisce il sesso dell'utente.
     */
    public char getSesso() {return sesso;}


    /**
     * Imposta il sesso dell'utente.
     * @param sesso sesso dell'utente.
     */
    public void setSesso(char sesso) {this.sesso = sesso;}


    /** Restituisce la data di nascita dell'utente.
     * @return
     * Restituisce la data di nascita dell'utente.
     */
    public Date getDataDiNascita() {return dataDiNascita;}


    /**
     * Imposta la data di nascita dell'utente.
     * @param dataDiNascita data di nascita dell'utente.
     */
    public void setDataDiNascita(Date dataDiNascita) {this.dataDiNascita = dataDiNascita;}


    /** Restituisce il luogo di nascita dell'utente.
     * @return
     * Restituisce il luogo di nascita dell'utente.
     */
    public String getLuogoDiNascita() {return luogoDiNascita;}


    /**
     * Imposta il luogo di nascita dell'utente.
     * @param luogoDiNascita luogo di nascita dell'utente.
     */
    public void setLuogoDiNascita(String luogoDiNascita) {this.luogoDiNascita = luogoDiNascita;}


    /** Restituisce l'e-mail dell'utente.
     * @return
     * Restituisce l'e-mail dell'utente.
     */
    public String getEmail() {return email;}


    /**
     * Imposta la mail dell'utente.
     * @param email e-mail dell'utente.
     */
    public void setEmail(String email) {this.email = email;}


    /** Restituisce il luogo di residenza dell'utente.
     * @return
     * Restituisce il luogo di residenza dell'utente.
     */
    public String getResidenza() {return residenza;}


    /**
     * Imposta la residenza dell'utente.
     * @param residenza residenza dell'utente.
     */
    public void setResidenza(String residenza) {this.residenza = residenza;}


    /** Restituisce la password criptata dell'utente.
     * @return
     * Restituisce la password criptata dell'utente.
     */
    public String getPassword() {return password;}

    /**
     * Imposta la password dell'utente in maniera criptata.
     * @param password password criptata dell'utente.
     */
    public void setPasswordHash(String password)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            this.password = String.format("%040x", new BigInteger(1, digest.digest()));
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }


    /** Verifica se l'utente è amministratore.
     * @return
     * Restituisce il valore che verifica se l'utente è amministratore .
     */
    public RuoloUtente isAmministratore() {return amministratore;}


    /**
     * Imposta il ruolo di amministratore dell'utente.
     * @param amministratore ruolo di amministratore dell'utente.
     */
    public void setAmministratore(RuoloUtente amministratore) {this.amministratore = amministratore;}


    /** Verifica se l'utente è stato accettato sulla piattaforma.
     * @return
     * Restituisce il valore che verifica se l'utente è stato accettato sulla piattaforma. .
     */
    public boolean isAccepted() {return accepted;}


    /**
     * Imposta se l'utente è stato accettato.
     * @param accepted utente accettato.
     */
    public void setAccepted(boolean accepted) {this.accepted = accepted;}
}
