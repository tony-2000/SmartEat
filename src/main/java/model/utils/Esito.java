package model.utils;

/**
 * Questa classe di servizio realizza la convalida degli input tramite un valore di validita' ed
 * un eventuale messaggio di errore.
 */
public class Esito
{
    /**
     * Validita' dell'input
     */
    private boolean valido;

    /**
     * Stringa di errore, nel caso ci sia.
     */
    private String message;



    /** Verifica se l'input e' valido.
     * @return
     * Restituisce il valore che verifica se l'input e' valido.
     */
    public boolean isValido() {
        return valido;
    }

    /**
     * Imposta se l'input e' valido.
     * @param valido validita' dell'input.
     */
    public void setValido(boolean valido) {
        this.valido = valido;
    }



    /** Restituisce l'eventuale messaggio di errore.
     * @return
     * Restituisce l'eventuale messaggio di errore.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Imposta l'eventuale stringa di errore
     * @param message eventuale stringa di errore.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
