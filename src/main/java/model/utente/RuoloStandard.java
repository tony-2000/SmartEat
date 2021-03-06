package model.utente;


/**
 * Questa classe specifica che il ruolo dell'utente e' standard
 */
public class RuoloStandard implements RuoloUtente
    {
        /**
         * Specifica che l'utente non e' un amministratore
         * @return false
         */
        public boolean isAdmin() {return false;}

        /**
         * ToString
         * @return String
         */
        public String toString() {return "Standard";}
    }
