package model;


/**
 * Questa classe specifica che il ruolo dell'utente è standard
 */
public class RuoloStandard implements RuoloUtente
    {
        /**
         * Specifica che l'utente non è un amministratore
         * @return false
         */
        public boolean isAdmin() {return false;}
    }
