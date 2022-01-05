import model.Check;
import model.Esito;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class CheckTest
{
    @Test
    public void CFIsValidTooShort()
    {
        String CF="aaaaa";
        Esito esito=Check.CFIsValid(CF);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Lunghezza CF minore di 16 caratteri");
    }


    @Test
    public void CFIsValidTooLong()
    {
        String CF="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Esito esito=Check.CFIsValid(CF);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Lunghezza CF maggiore di 16 caratteri");
    }

    @Test
    public void CFIsValidNotLetterOrDigit()
    {
        String CF="aaaaaaaaaa.aaaaa";
        Esito esito=Check.CFIsValid(CF);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il CF può contenere solo lettere o numeri");
    }


    @Test
    public void CFIsValidStillUsed()
    {
        String CF="RSSMRA74D22A0010";
        Esito esito=Check.CFIsValid(CF);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il CF è già presente in database");
    }

    @Test
    public void CFIsValidTrue()
    {
        String CF="aaaaaaaaaaaaaaaa";
        Esito esito=Check.CFIsValid(CF);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }



    @Test
    public void nomeIsValidTooLong()
    {
        String nome="aaaaaaaaaaaaaaaaaaaaaaaaaa";
        Esito esito=Check.nomeIsValid(nome);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Lunghezza nome maggiore di 20 caratteri");
    }

    @Test
    public void nomeIsValidIsEmpty()
    {
        String nome="";
        Esito esito=Check.nomeIsValid(nome);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo nome non può essere vuoto");
    }

    @Test
    public void nomeIsValidTrue()
    {
        String nome="aaaaaaaa";
        Esito esito=Check.nomeIsValid(nome);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }






    @Test
    public void cognomeIsValidTooLong()
    {
        String cognome="aaaaaaaaaaaaaaaaaaaaaaaaaa";
        Esito esito=Check.cognomeIsValid(cognome);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Lunghezza cognome maggiore di 20 caratteri");
    }

    @Test
    public void cognomeIsValidIsEmpty()
    {
        String cognome="";
        Esito esito=Check.cognomeIsValid(cognome);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo cognome non può essere vuoto");
    }

    @Test
    public void cognomeIsValidTrue()
    {
        String cognome="aaaaaaaa";
        Esito esito=Check.cognomeIsValid(cognome);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }



    @Test
    public void sessoIsValidIsEmpty()
    {
        String sesso = "";
        Esito esito=Check.sessoIsValid(sesso);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Formato non valido, inserire uno tra i seguenti caratteri: M, F, N");
    }


    @Test
    public void sessoIsValidInvalidFormat()
    {
        String sesso = "P";
        Esito esito=Check.sessoIsValid(sesso);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Formato non valido, inserire uno tra i seguenti caratteri: M, F, N");
    }

    @Test
    public void sessoIsValidTrue()
    {
        String sesso = "M";
        Esito esito=Check.sessoIsValid(sesso);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }



    @Test
    public void nascitaIsValidToday()
    {
        Date nascita =new Date(System.currentTimeMillis()+1000);
        Esito esito=Check.nascitaIsValid(nascita);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Data di nascita non valida");
    }


    /*Non testabile
    @Test
    public void nascitaIsValidFormatInvalid()
    {
        Date nascita = Date.valueOf("2002-02-30");
        Esito esito=Check.nascitaIsValid(nascita);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Data di nascita non valida");
    }
    */

    @Test
    public void nascitaIsValid()
    {
        Date nascita = Date.valueOf("2002-05-20");
        Esito esito=Check.nascitaIsValid(nascita);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }




    @Test
    public void luogoDNIsValidTooLong()
    {
        String luogo = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Esito esito=Check.luogoDNIsValid(luogo);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Lunghezza luogo di nascita maggiore di 25 caratteri");
    }


    @Test
    public void luogoDNIsValidIsEmpty()
    {
        String luogo = "";
        Esito esito=Check.luogoDNIsValid(luogo);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo luogo di nascita non può essere vuoto");
    }


    @Test
    public void luogoDNIsValidTrue()
    {
        String luogo = "aaaaaaaaa";
        Esito esito=Check.luogoDNIsValid(luogo);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }


    @Test
    public void mailIsValidRegIsEmpty()
    {
        String mail = "";
        Esito esito=Check.mailIsValidReg(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail non può essere vuoto");
    }


    @Test
    public void mailIsValidRegNotATSIGN()
    {
        String mail = "aaaaaaa.iiiii.com";
        Esito esito=Check.mailIsValidReg(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail deve contenere @");
    }


    @Test
    public void mailIsValidRegNotDot()
    {
        String mail = "aaaaaaaiiiii@com";
        Esito esito=Check.mailIsValidReg(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail non rispetta il formato");
    }



    @Test
    public void mailIsValidRegTooShort()
    {
        String mail = "i@o.m";
        Esito esito=Check.mailIsValidReg(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail non contiene abbastanza caratteri");
    }


    @Test
    public void mailIsValidRegTooLong()
    {
        String mail = "iaaaaaaaaaaaaaaaaaaaa@osssssssssssssssss.mccccc";
        Esito esito=Check.mailIsValidReg(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail contiene più di 35 caratteri");
    }


    @Test
    public void mailIsValidRegStillUsed()
    {
        String mail = "mario.rossi@gmail.com";
        Esito esito=Check.mailIsValidReg(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail è già presente nel database");
    }


    @Test
    public void mailIsValidRegTrue()
    {
        String mail = "aaaaa@aaaa.com";
        Esito esito=Check.mailIsValidReg(mail);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }


    @Test
    public void mailIsValidLoginIsEmpty()
    {
        String mail = "";
        Esito esito=Check.mailIsValidLogin(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail non può essere vuoto");
    }


    @Test
    public void mailIsValidLoginNotATSIGN()
    {
        String mail = "aaaaaaa.iiiii.com";
        Esito esito=Check.mailIsValidLogin(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail deve contenere @");
    }


    @Test
    public void mailIsValidLoginNotDot()
    {
        String mail = "aaaaaaaiiiii@com";
        Esito esito=Check.mailIsValidLogin(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail non rispetta il formato");
    }



    @Test
    public void mailIsValidLoginTooShort()
    {
        String mail = "i@o.m";
        Esito esito=Check.mailIsValidLogin(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail non contiene abbastanza caratteri");
    }


    @Test
    public void mailIsValidLoginTooLong()
    {
        String mail = "iaaaaaaaaaaaaaaaaaaaa@osssssssssssssssss.mccccc";
        Esito esito=Check.mailIsValidLogin(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail contiene più di 35 caratteri");
    }


    @Test
    public void mailIsValidLoginTrue()
    {
        String mail = "aaaaa@aaaa.com";
        Esito esito=Check.mailIsValidLogin(mail);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }


    @Test
    public void residenzaIsValidTooLong()
    {
        String res = "icssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";
        Esito esito=Check.residenzaIsValid(res);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo residenza contiene più di 35 caratteri");
    }

    @Test
    public void residenzaIsValidIsEmpty()
    {
        String res = "";
        Esito esito=Check.residenzaIsValid(res);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail non può essere vuoto");
    }


    @Test
    public void residenzaIsValidTrue()
    {
        String res = "aaaaa";
        Esito esito=Check.residenzaIsValid(res);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }

    @Test
    public void passwordIsValidNotEquals()
    {
        String password1 = "aaaaaa";
        String password2 = "bbbbbbb";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Le due password non coincidono");
    }


    @Test
    public void passwordIsValidIsEmpty()
    {
        String password1 = "";
        String password2 = "";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo password è vuoto");
    }


    @Test
    public void passwordIsValidTooShort()
    {
        String password1 = "aaaaa";
        String password2 = "aaaaa";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo password ha meno di 8 caratteri");
    }


    @Test
    public void passwordIsValidTooLong()
    {
        String password1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String password2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo password ha più di 16 caratteri");
    }


    @Test
    public void passwordIsValidNotUppercase()
    {
        String password1 = "aaaaaaaaaa";
        String password2 = "aaaaaaaaaa";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo password non ha caratteri maiuscoli");
    }


    @Test
    public void passwordIsValidNotLowercase()
    {
        String password1 = "AAAAAAAAAA";
        String password2 = "AAAAAAAAAA";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo password non ha caratteri minuscoli");
    }


    @Test
    public void passwordIsValidNotNumber()
    {
        String password1 = "aaaaaAAAAA";
        String password2 = "aaaaaAAAAA";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo password non ha numeri");
    }


    @Test
    public void passwordIsValidNotSpecialCharacter()
    {
        String password1 = "aaaaaAAAAA55";
        String password2 = "aaaaaAAAAA55";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo password non ha caratteri speciali");
    }


    @Test
    public void passwordIsValidTrue()
    {
        String password1 = "aaaaaAAAAA55..";
        String password2 = "aaaaaAAAAA55..";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }

}
