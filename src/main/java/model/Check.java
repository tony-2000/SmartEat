package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Questa classe di servizio realizza la convalida degli input
 */
public class Check
{

    public static void CFIsValid(String CF) throws Exception
    {
        if(CF.length()<16) throw new Exception("Lunghezza CF minore di 16 caratteri");
        if(CF.length()>16) throw new Exception("Lunghezza CF maggiore di 16 caratteri");
        for(int i=0;i<16;i++)
           if (!Character.isLetterOrDigit(CF.charAt(i)))
               throw new Exception("Il CF può contenere solo lettere o numeri");
        ArrayList<Utente> array;
        UtenteDAO users = new UtenteDAO();
        array= (ArrayList<Utente>) users.doRetrieveAllUtente();
        for(Utente x: array)
        {
            if(x.getCF().equals(CF))
                throw new Exception("Il CF è già presente in database");
        }
    }

    public static void nomeIsValid(String name) throws Exception {
        if(name.length()>20) throw new Exception("Lunghezza nome maggiore di 20 caratteri");
        if(name.length()==0) throw new Exception("Il campo nome non può essere vuoto");
    }

    public static void cognomeIsValid(String surname) throws Exception {
        if(surname.length()>20) throw new Exception("Lunghezza cognome maggiore di 20 caratteri");
        if(surname.length()==0) throw new Exception("Il campo cognome non può essere vuoto");
    }


    public static void sessoIsValid(char g) throws Exception {
       if(!(Character.toString(g).equals("M")) || (Character.toString(g).equals("F"))|| (Character.toString(g).equals("N")))
           throw new Exception("Formato non valido, inserire uno tra i segueti caratteri: M, F, N");
    }


    public static void nascitaIsValid(Date data) throws Exception {
         if(data.compareTo(new Date(System.currentTimeMillis()))>=0)
        throw new Exception("Data di nascita non valida");
    }


    public static void luogoDNIsValid(String ldn) throws Exception {
        if(ldn.length()>25) throw new Exception("Lunghezza luogo di nascita maggiore di 25 caratteri");
        if(ldn.length()==0) throw new Exception("Il campo luogo di nascita non può essere vuoto");
    }



   public static void mailIsValid(String mail) throws Exception {
        if(mail.equals("")) throw new Exception("Il campo mail non può essere vuoto");
        if(!(mail.contains("@"))) throw new Exception("Il campo mail non può essere vuoto");
        if(!(mail.contains("."))) throw new Exception("Il campo mail non rispetta il formato");
        if(mail.length()<6) throw new Exception("Il campo mail non contiene abbastanza caratteri");
        if(mail.length()>35) throw new Exception("Il campo mail contiene più di 35 caratteri");
        ArrayList<Utente> array;
        UtenteDAO users = new UtenteDAO();
        array= (ArrayList<Utente>) users.doRetrieveAllUtente();
        for(Utente x: array)
        {
            if(x.getEmail().equals(mail))
                throw new Exception("Il campo mail è già presente nel database");
        }
    }



    public static void residenzaIsValid(String res) throws Exception {
        if(res.length()>35) throw new Exception("Il campo residenza ncontiene più di 35 caratteri");
        if(res.length()==0) throw new Exception("Il campo mail non può essere vuoto");
    }



    public static void passwordIsValid(String password, String passwordCheck) throws Exception
    {
        if(!password.equals(passwordCheck)) throw new Exception("Le due password non coincidono");
        if(password.equals("")) throw new Exception("Il campo password è vuoto");
        if(password.length()<8) throw new Exception("Il campo password ha meno di 8 caratteri");
        if(password.length()>16) throw new Exception("Il campo password ha più di 16 caratteri");
        if(password.equals(password.toLowerCase())) throw new Exception("Il campo password non ha caratteri maiuscoli");
        if(password.equals(password.toUpperCase())) throw new Exception("Il campo password non ha caratteri minuscoli");
        if(!password.matches("(.*[0-9].*)")) throw new Exception("Il campo password non ha numeri");
        boolean checkSpecial=false;
        for(int i=0;i<password.length();i++)
            if (!Character.isLetterOrDigit(password.charAt(i)))
                checkSpecial=true;
        if(!checkSpecial) throw new Exception("Il campo password non ha caratteri speciali");
    }

}