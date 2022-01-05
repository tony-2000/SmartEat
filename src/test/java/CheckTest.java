import model.Check;
import model.Esito;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class CheckTest
{
    @Test
    public void CFIsValidTooShortTest()
    {
        String CF="aaaaa";
        Esito esito=Check.CFIsValid(CF);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Lunghezza CF minore di 16 caratteri");
    }


    @Test
    public void CFIsValidTooLongTest()
    {
        String CF="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Esito esito=Check.CFIsValid(CF);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Lunghezza CF maggiore di 16 caratteri");
    }

    @Test
    public void CFIsValidNotLetterOrDigitTest()
    {
        String CF="aaaaaaaaaa.aaaaa";
        Esito esito=Check.CFIsValid(CF);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il CF può contenere solo lettere o numeri");
    }


    @Test
    public void CFIsValidStillUsedTest()
    {
        String CF="RSSMRA74D22A0010";
        Esito esito=Check.CFIsValid(CF);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il CF è già presente in database");
    }

    @Test
    public void CFIsValidTrueTest()
    {
        String CF="aaaaaaaaaaaaaaaa";
        Esito esito=Check.CFIsValid(CF);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }



    @Test
    public void nomeIsValidTooLongTest()
    {
        String nome="aaaaaaaaaaaaaaaaaaaaaaaaaa";
        Esito esito=Check.nomeIsValid(nome);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Lunghezza nome maggiore di 20 caratteri");
    }

    @Test
    public void nomeIsValidIsEmptyTest()
    {
        String nome="";
        Esito esito=Check.nomeIsValid(nome);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo nome non può essere vuoto");
    }

    @Test
    public void nomeIsValidTrueTest()
    {
        String nome="aaaaaaaa";
        Esito esito=Check.nomeIsValid(nome);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }






    @Test
    public void cognomeIsValidTooLongTest()
    {
        String cognome="aaaaaaaaaaaaaaaaaaaaaaaaaa";
        Esito esito=Check.cognomeIsValid(cognome);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Lunghezza cognome maggiore di 20 caratteri");
    }

    @Test
    public void cognomeIsValidIsEmptyTest()
    {
        String cognome="";
        Esito esito=Check.cognomeIsValid(cognome);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo cognome non può essere vuoto");
    }

    @Test
    public void cognomeIsValidTrueTest()
    {
        String cognome="aaaaaaaa";
        Esito esito=Check.cognomeIsValid(cognome);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }



    @Test
    public void sessoIsValidIsEmptyTest()
    {
        String sesso = "";
        Esito esito=Check.sessoIsValid(sesso);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Formato non valido, inserire uno tra i seguenti caratteri: M, F, N");
    }


    @Test
    public void sessoIsValidInvalidFormatTest()
    {
        String sesso = "2";
        Esito esito=Check.sessoIsValid(sesso);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Formato non valido, inserire uno tra i seguenti caratteri: M, F, N");
    }

    @Test
    public void sessoIsValidTrueTest()
    {
        String sesso = "M";
        Esito esito=Check.sessoIsValid(sesso);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }

    @Test
    public void nascitaIsValidTodayTest()
    {
        Date nascita =new Date(System.currentTimeMillis()+1000);
        Esito esito=Check.nascitaIsValid(nascita);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Data di nascita non valida");
    }


    @Test
    public void nascitaIsValidTest()
    {
        Date nascita = Date.valueOf("2002-05-20");
        Esito esito=Check.nascitaIsValid(nascita);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }




    @Test
    public void luogoDNIsValidTooLongTest()
    {
        String luogo = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Esito esito=Check.luogoDNIsValid(luogo);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Lunghezza luogo di nascita maggiore di 25 caratteri");
    }


    @Test
    public void luogoDNIsValidIsEmptyTest()
    {
        String luogo = "";
        Esito esito=Check.luogoDNIsValid(luogo);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo luogo di nascita non può essere vuoto");
    }


    @Test
    public void luogoDNIsValidTrueTest()
    {
        String luogo = "aaaaaaaaa";
        Esito esito=Check.luogoDNIsValid(luogo);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }


    @Test
    public void mailIsValidRegIsEmptyTest()
    {
        String mail = "";
        Esito esito=Check.mailIsValidReg(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail non può essere vuoto");
    }


    @Test
    public void mailIsValidRegNotATSIGNTest()
    {
        String mail = "aaaaaaa.iiiii.com";
        Esito esito=Check.mailIsValidReg(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail deve contenere @");
    }


    @Test
    public void mailIsValidRegNotDotTest()
    {
        String mail = "aaaaaaaiiiii@com";
        Esito esito=Check.mailIsValidReg(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail non rispetta il formato");
    }



    @Test
    public void mailIsValidRegTooShortTest()
    {
        String mail = "i@o.m";
        Esito esito=Check.mailIsValidReg(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail non contiene abbastanza caratteri");
    }


    @Test
    public void mailIsValidRegTooLongTest()
    {
        String mail = "iaaaaaaaaaaaaaaaaaaaa@osssssssssssssssss.mccccc";
        Esito esito=Check.mailIsValidReg(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail contiene più di 35 caratteri");
    }


    @Test
    public void mailIsValidRegStillUsedTest()
    {
        String mail = "mario.rossi@gmail.com";
        Esito esito=Check.mailIsValidReg(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail è già presente nel database");
    }


    @Test
    public void mailIsValidRegTrueTest()
    {
        String mail = "aaaaa@aaaa.com";
        Esito esito=Check.mailIsValidReg(mail);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }


    @Test
    public void mailIsValidLoginIsEmptyTest()
    {
        String mail = "";
        Esito esito=Check.mailIsValidLogin(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail non può essere vuoto");
    }


    @Test
    public void mailIsValidLoginNotATSIGNTest()
    {
        String mail = "aaaaaaa.iiiii.com";
        Esito esito=Check.mailIsValidLogin(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail deve contenere @");
    }


    @Test
    public void mailIsValidLoginNotDotTest()
    {
        String mail = "aaaaaaaiiiii@com";
        Esito esito=Check.mailIsValidLogin(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail non rispetta il formato");
    }



    @Test
    public void mailIsValidLoginTooShortTest()
    {
        String mail = "i@o.m";
        Esito esito=Check.mailIsValidLogin(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail non contiene abbastanza caratteri");
    }


    @Test
    public void mailIsValidLoginTooLongTest()
    {
        String mail = "iaaaaaaaaaaaaaaaaaaaa@osssssssssssssssss.mccccc";
        Esito esito=Check.mailIsValidLogin(mail);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail contiene più di 35 caratteri");
    }


    @Test
    public void mailIsValidLoginTrueTest()
    {
        String mail = "aaaaa@aaaa.com";
        Esito esito=Check.mailIsValidLogin(mail);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }


    @Test
    public void residenzaIsValidTooLongTest()
    {
        String res = "icssssssssssssssssssssssssssssssssssssssssssssssssssssssssss";
        Esito esito=Check.residenzaIsValid(res);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo residenza contiene più di 35 caratteri");
    }

    @Test
    public void residenzaIsValidIsEmptyTest()
    {
        String res = "";
        Esito esito=Check.residenzaIsValid(res);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo mail non può essere vuoto");
    }


    @Test
    public void residenzaIsValidTrueTest()
    {
        String res = "aaaaa";
        Esito esito=Check.residenzaIsValid(res);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }

    @Test
    public void passwordIsValidNotEqualsTest()
    {
        String password1 = "aaaaaa";
        String password2 = "bbbbbbb";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Le due password non coincidono");
    }


    @Test
    public void passwordIsValidIsEmptyTest()
    {
        String password1 = "";
        String password2 = "";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo password è vuoto");
    }


    @Test
    public void passwordIsValidTooShortTest()
    {
        String password1 = "aaaaa";
        String password2 = "aaaaa";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo password ha meno di 8 caratteri");
    }


    @Test
    public void passwordIsValidTooLongTest()
    {
        String password1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String password2 = "aaaaaaaaaaaaaaaaaaaaaaaaaaa";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo password ha più di 16 caratteri");
    }


    @Test
    public void passwordIsValidNotUppercaseTest()
    {
        String password1 = "aaaaaaaaaa";
        String password2 = "aaaaaaaaaa";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo password non ha caratteri maiuscoli");
    }


    @Test
    public void passwordIsValidNotLowercaseTest()
    {
        String password1 = "AAAAAAAAAA";
        String password2 = "AAAAAAAAAA";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo password non ha caratteri minuscoli");
    }


    @Test
    public void passwordIsValidNotNumberTest()
    {
        String password1 = "aaaaaAAAAA";
        String password2 = "aaaaaAAAAA";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo password non ha numeri");
    }


    @Test
    public void passwordIsValidNotSpecialCharacterTest()
    {
        String password1 = "aaaaaAAAAA55";
        String password2 = "aaaaaAAAAA55";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertFalse(esito.isValido());
        assertEquals(esito.getMessage(),"Il campo password non ha caratteri speciali");
    }


    @Test
    public void passwordIsValidTrueTest()
    {
        String password1 = "aaaaaAAAAA55..";
        String password2 = "aaaaaAAAAA55..";
        Esito esito=Check.passwordIsValid(password1,password2);
        assertTrue(esito.isValido());
        assertNull(esito.getMessage());
    }

}
