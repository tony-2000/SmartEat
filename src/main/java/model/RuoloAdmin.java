package model;


/**
 * Questa classe specifica che il ruolo dell'utente è di amministratore
 */
public class RuoloAdmin implements RuoloUtente
{
    /**
     * Specifica che l'utente è un amministratore
     * @return true
     */
    public boolean isAdmin() {return true;}
}
