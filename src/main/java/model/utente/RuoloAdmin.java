package model.utente;


/**
 * Questa classe specifica che il ruolo dell'utente e' di amministratore
 */
public class RuoloAdmin implements RuoloUtente
{
    /**
     * Specifica che l'utente e' un amministratore
     * @return true
     */
    public boolean isAdmin() {return true;}

    /**
     * ToString
     * @return String
     */
    public String toString() {return "Amministratore";}
}
