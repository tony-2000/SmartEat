package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Questa classe di servizio realizza la convalida degli input
 */
public class Check {

    public static Esito CFIsValid(String CF) throws Exception {
        Esito result = new Esito();
        result.setValido(false);
        if (CF.length() < 16) result.setMessage("Lunghezza CF minore di 16 caratteri");
        if (CF.length() > 16) throw new Exception("Lunghezza CF maggiore di 16 caratteri");
        for (int i = 0; i < 16; i++)
            if (!Character.isLetterOrDigit(CF.charAt(i)))
                throw new Exception("Il CF può contenere solo lettere o numeri");
        ArrayList<Utente> array;
        UtenteDAO users = new UtenteDAO();
        array = (ArrayList<Utente>) users.doRetrieveAllUtente();
        for (Utente x : array) {
            if (x.getCF().equals(CF))
                throw new Exception("Il CF è già presente in database");
        }
        result.setValido(true);
        return result;
    }

    public static void nomeIsValid(String name) throws Exception {
        if (name.length() > 20) throw new Exception("Lunghezza nome maggiore di 20 caratteri");
        if (name.length() == 0) throw new Exception("Il campo nome non può essere vuoto");
    }

    public static void cognomeIsValid(String surname) throws Exception {
        if (surname.length() > 20) throw new Exception("Lunghezza cognome maggiore di 20 caratteri");
        if (surname.length() == 0) throw new Exception("Il campo cognome non può essere vuoto");
    }


    public static void sessoIsValid(char g) throws Exception {
        if (!(Character.toString(g).equals("M")) && !(Character.toString(g).equals("F")) && !(Character.toString(g).equals("N")))
            throw new Exception("Formato non valido, inserire uno tra i seguenti caratteri: M, F, N");
    }


    public static void nascitaIsValid(Date data) throws Exception {
        if (data.compareTo(new Date(System.currentTimeMillis())) >= 0)
            throw new Exception("Data di nascita non valida");
    }


    public static void luogoDNIsValid(String ldn) throws Exception {
        if (ldn.length() > 25) throw new Exception("Lunghezza luogo di nascita maggiore di 25 caratteri");
        if (ldn.length() == 0) throw new Exception("Il campo luogo di nascita non può essere vuoto");
    }


    public static Esito mailIsValidReg(String mail) {
        Esito result = new Esito();
        result.setValido(false);
        if (mail.equals("")) {
            result.setMessage("Il campo mail non può essere vuoto");
            return result;
        }
        if (!(mail.contains("@"))) {
            result.setMessage("Il campo mail non può essere vuoto");
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

    public static Esito mailIsValidLogin(String mail) {
        Esito result = new Esito();
        result.setValido(false);
        if (mail.equals("")) {
            result.setMessage("Il campo mail non può essere vuoto");
            return result;
        }
        if (!(mail.contains("@"))) {
            result.setMessage("Il campo mail non può essere vuoto");
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


    public static void residenzaIsValid(String res) throws Exception {
        if (res.length() > 35) throw new Exception("Il campo residenza ncontiene più di 35 caratteri");
        if (res.length() == 0) throw new Exception("Il campo mail non può essere vuoto");
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