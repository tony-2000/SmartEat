package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Questa classe di servizio realizza la convalida degli input
 */
public class Check {

    public static Esito CFIsValid(String CF)
    {
        Esito result = new Esito();
        result.setValido(false);
        if (CF.length() < 16) {
            result.setMessage("Lunghezza CF minore di 16 caratteri");
            return result;
        }
        if (CF.length() > 16) {
            result.setMessage("Lunghezza CF maggiore di 16 caratteri");
            return result;
        }
        for (int i = 0; i < 16; i++)
            if (!Character.isLetterOrDigit(CF.charAt(i))) {
                result.setMessage("Il CF può contenere solo lettere o numeri");
                return result;
            }
        ArrayList<Utente> array;
        UtenteDAO users = new UtenteDAO();
        array = (ArrayList<Utente>) users.doRetrieveAllUtente();
        for (Utente x : array) {
            if (x.getCF().equals(CF)) {
                result.setMessage("Il CF è già presente in database");
                return result;
            }
        }
        result.setValido(true);
        return result;
    }

    public static Esito nomeIsValid(String name)
    {
        Esito result = new Esito();
        result.setValido(false);
        if (name.length() > 20) {
            result.setMessage("Lunghezza nome maggiore di 20 caratteri");
            return result;
        }
        if (name.length() == 0) {
            result.setMessage("Il campo nome non può essere vuoto");
            return result;
        }
        result.setValido(true);
        return result;
    }

    public static Esito cognomeIsValid(String surname)
    {
        Esito result = new Esito();
        result.setValido(false);
        if (surname.length() > 20) {
            result.setMessage("Lunghezza cognome maggiore di 20 caratteri");
            return result;
        }
        if (surname.length() == 0) {
            result.setMessage("Il campo cognome non può essere vuoto");
            return result;
        }
        result.setValido(true);
        return result;
    }


    public static Esito sessoIsValid(char g)
    {
        Esito result = new Esito();
        result.setValido(false);
        if (!(Character.toString(g).equals("M")) && !(Character.toString(g).equals("F")) && !(Character.toString(g).equals("N"))) {
            result.setMessage("Formato non valido, inserire uno tra i seguenti caratteri: M, F, N");
            return result;
        }
        result.setValido(true);
        return result;
    }


    public static Esito nascitaIsValid(Date data)
    {
        Esito result = new Esito();
        result.setValido(false);
        if (data.compareTo(new Date(System.currentTimeMillis())) >= 0) {
            result.setMessage("Data di nascita non valida");
            return result;
        }
        result.setValido(true);
        return result;
    }


    public static Esito luogoDNIsValid(String ldn)
    {
        Esito result = new Esito();
        result.setValido(false);
        if (ldn.length() > 25) {
            result.setMessage("Lunghezza luogo di nascita maggiore di 25 caratteri");
            return result;
        }
        if (ldn.length() == 0) {
            result.setMessage("Il campo luogo di nascita non può essere vuoto");
            return result;
        }
        result.setValido(true);
        return result;
    }


    public static Esito mailIsValidReg(String mail)
    {
        Esito result = new Esito();
        result.setValido(false);
        if (mail.equals("")) {
            result.setMessage("Il campo mail non può essere vuoto");
            return result;
        }
        if (!(mail.contains("@"))) {
            result.setMessage("Il campo mail deve contenere @");
            return result;
        }
        if (!(mail.contains("."))) {
            result.setMessage("Il campo mail non rispetta il formato");
            return result;
        }
        if (mail.length() < 6) {
            result.setMessage("Il campo mail non contiene abbastanza caratteri");
            return result;
        }
        if (mail.length() > 35) {
            result.setMessage("Il campo mail contiene più di 35 caratteri");
            return result;
        }
        ArrayList<Utente> array;
        UtenteDAO users = new UtenteDAO();
        array = (ArrayList<Utente>) users.doRetrieveAllUtente();
        for (Utente x : array) {
            if (x.getEmail().equals(mail)) {
                result.setMessage("Il campo mail è già presente nel database");
                return result;
            }
        }
        result.setValido(true);
        return result;
    }

    public static Esito mailIsValidLogin(String mail)
    {
        Esito result = new Esito();
        result.setValido(false);
        if (mail.equals("")) {
            result.setMessage("Il campo mail non può essere vuoto");
            return result;
        }
        if (!(mail.contains("@"))) {
            result.setMessage("Il campo mail deve contenere @");
            return result;
        }
        if (!(mail.contains("."))) {
            result.setMessage("Il campo mail non rispetta il formato");
            return result;
        }
        if (mail.length() < 6) {
            result.setMessage("Il campo mail non contiene abbastanza caratteri");
            return result;
        }
        if (mail.length() > 35) {
            result.setMessage("Il campo mail contiene più di 35 caratteri");
            return result;
        }
        result.setValido(true);
        return result;
    }


    public static Esito residenzaIsValid(String res)
    {
        Esito result = new Esito();
        result.setValido(false);
        if (res.length() > 35)
        {
            result.setMessage("Il campo residenza ncontiene più di 35 caratteri");
            return result;
        }
        if (res.length() == 0)
        {
            result.setMessage("Il campo mail non può essere vuoto");
            return result;
        }
        result.setValido(true);
        return result;
    }


    public static Esito passwordIsValid(String password, String passwordCheck) {
        Esito result = new Esito();
        result.setValido(false);
        if (!password.equals(passwordCheck)) {
            result.setMessage("Le due password non coincidono");
            return result;
        }
        if (password.equals("")) {
            result.setMessage("Il campo password è vuoto");
            return result;
        }
        if (password.length() < 8) {
            result.setMessage("Il campo password ha meno di 8 caratteri");
            return result;
        }
        if (password.length() > 16) {
            result.setMessage("Il campo password ha più di 16 caratteri");
            return result;
        }
        if (password.equals(password.toLowerCase())) {
            result.setMessage("Il campo password non ha caratteri maiuscoli");
            return result;
        }
        if (password.equals(password.toUpperCase())) {
            result.setMessage("Il campo password non ha caratteri minuscoli");
            return result;
        }
        if (!password.matches("(.*[0-9].*)")) {
            result.setMessage("Il campo password non ha numeri");
            return result;
        }
        boolean checkSpecial = false;
        for (int i = 0; i < password.length(); i++)
            if (!Character.isLetterOrDigit(password.charAt(i)))
                checkSpecial = true;
        if (!checkSpecial) {
            result.setMessage("Il campo password non ha caratteri speciali");
            return result;
        }
        result.setValido(true);
        return result;
    }

}